package matrixGraph;

import java.util.Scanner;

public class Matrix<T>  {
	private int vexNum;  //顶点数量
	private int arcNum;     //边数
    private T[] vexs;    //顶点数组
    private int[][] arcs;  //二维数组表示方阵
    private static final int MAX=0x0000ffff;  //最大权重
    public Matrix(int vexNum,int arcNum,T[] vexs,int[][] arcs) {
    	this.vexNum = vexNum;
    	this.arcNum = arcNum;
    	this.vexs = vexs;
    	this.arcs = arcs;
    }
    
    public Matrix(int vexNum) {
    	this.vexNum = vexNum;
    	this.arcNum = 0;
    	this.vexs = (T[]) new Object[vexNum];
    	this.arcs = new int[vexNum][vexNum];
    }
    
    public Matrix() {
    	this(0,0,null,null);
    }
    
    public void createM(int vexNum,int arcNum,T[] vexs,int[][] arcs) {
    	this.vexNum = vexNum;   //顶点数n
    	this.arcNum = arcNum;   //边数e
    	//输入图中各顶点
    	this.vexs = (T[]) new Object[vexNum];
    	for (int i = 0; i < vexNum; i++)   //构造顶点向量
    		this.vexs[i] = (T)vexs[i];
    	//初始化邻接矩阵
    	this.arcs = new int[vexNum][vexNum];
    	for (int i = 0; i < vexNum; i++)
    		for (int j = 0; j < vexNum; j++) {
    			if(i==j) this.arcs[i][j]=0;
    			else this.arcs[i][j]=MAX;
    		}
    	for(int i=0;i<vexNum;i++) {
    		for(int j=0;j<vexNum;j++) {
    			this.arcs[i][j]=arcs[i][j];
    		}
    	}
        
    }
    // 无向网邻接矩阵的创建
    public void createUW() {
        Scanner in = new Scanner(System.in);
        try {
        	System.out.println("请分别输入景区的的景点数、景区的道路数:");
        	vexNum = in.nextInt();   //顶点数n
        	arcNum = in.nextInt();   //边数e
        }
        catch(Exception e) {
        	System.out.println("您输入出现错误！输入样例[5 20]");
        }
    	
    	//输入图中各顶点
    	vexs = (T[]) new Object[vexNum];
    	System.out.println("请分别输入景区的各个顶点:");
    	for (int i = 0; i < vexNum; i++)   //构造顶点向量
    		vexs[i] = (T) in.next(); 
    	//初始化邻接矩阵
    	arcs = new int[vexNum][vexNum];
    	for (int i = 0; i < vexNum; i++)
    		for (int j = 0; j < vexNum; j++) {
    			if(i==j) this.arcs[i][j]=0;
    			else this.arcs[i][j]=MAX;
    		}
    	//输入边信息
    	try {
    		System.out.println("请输入各个边的两个顶点及其权值:");
            for (int k = 0; k < arcNum; k++) {
            	int i =  locateVex((T) in.next()); //顶点
                int j =  locateVex((T) in.next()); //顶点
                arcs[i][j] = in.nextInt();  //权值
                arcs[j][i] = arcs[i][j];
            } //结束
        }
        catch(Exception e) {
        	System.out.println("您输入出现错误！输入样例[A B 20]");
        }
        
    }  

    public int getVexNum() {
    	return vexNum;
    }
    
    public int[][] getArcs() {
    	return arcs;
    }
    
    public T getVertex(int i) {
    	return vexs[i];
    }
    
    public void set(int i,int j,int w) {  //i行j列复制为值为w
    	this.arcs[i][j]=w;
    }
    private int locateVex(T vex) {
    	//遍历顶点数组
    	for (int i = 0; i < vexNum; i++)
    		if (vexs[i].equals(vex))
    			return i;  
    	return -1;  
    }

    
    public void find() {                          //查找景点
    	Scanner in = new Scanner(System.in);
    	try {
    		System.out.println("请输入需要查找的景点名称:");
    		T vex = (T)in.nextLine();
        	int k = locateVex(vex);
        	for(int i=0;i<getVexNum();i++) {
        		if(getArcs()[k][i]>0 && getArcs()[k][i]<MAX)
        			System.out.println(vex+"与"+vexs[i]+"相连，距离为"+getArcs()[k][i]);
    	    }
    	}
    	catch(Exception e){
    		System.out.println("输入景点名称有误！");
    	}
    	
    }
    
    private void insertVex(T x) {                    //插入顶点
    	T[] temp = this.vexs;
    	this.vexs = (T[])new Object[vexNum+1];
    	for(int i=0;i<this.vexNum+1;i++) {
    		if(i==this.vexNum)
    			this.vexs[i] = x;
    		else 
    			this.vexs[i] = temp[i];
    	}
    	setRowsColumns(this.vexNum+1,this.vexNum+1);
    	vexNum++;
    		
    }
    private void setRowsColumns(int m,int n) {    //扩充二维数组
    	if(m>0 && n>0) {
    		if(m>this.arcs.length || n>this.arcs[0].length) {
    			int[][] source = this.arcs;
    			this.arcs = new int[m][n];
    			for(int i=0;i<this.vexNum;i++) 
    				for(int j=0;j<this.vexNum;j++)
    					this.arcs[i][j] = source[i][j];
    		}
    	}
    	else throw new IllegalArgumentException("扩充的行列不能≤0！");
    }

    public void add() {
    	Scanner in = new Scanner(System.in);
    	System.out.println("请输入你要增加的新景点名称：");
    	T name = (T)in.nextLine();
    	if(locateVex(name)==-1) {
    		insertVex(name);
    		for(int i=0;i<vexNum-1;i++) {   //初始化新增的行列
    			arcs[vexNum-1][i]=MAX;
    			arcs[i][vexNum-1]=MAX;
    		}
        	System.out.println("该景点到已有景点的距离以及已有景点的名称（输入-1结束）：");
        	try {
        		while(true) {
            		int weight = in.nextInt();      //输入距离
            		if(weight==-1) break;
            		T name1 = (T)in.next();         //输入景点名称
            		this.arcs[vexNum-1][locateVex(name1)] = weight;
            		this.arcs[locateVex(name1)][vexNum-1] = weight;
        	    }
        	}
        	catch(Exception e) {
        		System.out.println("您输入的距离格式错误或景点名称不存在！输入样例[20 游乐园]");
        	}
    	}
    	else
    		System.out.println("该景点已存在！");
    }
    
    public void removeVex() {  //删除顶点
    	try {
    		Scanner in = new Scanner(System.in);
        	System.out.println("请输入你要删除的景点：");
        	T name = (T)in.nextLine();
        	int i = locateVex(name);  //i存放要删除顶点的坐标
            for(int k=i;k<vexNum-1;k++) {  //改变顶点数组vexs[]
        		vexs[k]=vexs[k+1];
        	}    	
        	for(int n=0;n<vexNum;n++)  //循环统计边减少的数量
        		if(getArcs()[i][n]>0 && getArcs()[i][n]<MAX)
        			arcNum--;
        	for(int k=i;k<vexNum-1;k++)   //将每行上移
        	    for(int j=0;j<vexNum;j++)
        	    	arcs[k][j]=arcs[k+1][j];
        	for(int k=i;k<vexNum-1;k++)   //将每列左移
        	    for(int j=0;j<vexNum;j++)
        	    	arcs[j][k]=arcs[j][k+1];
        	vexNum--;  //顶点数减一
    	}
    	catch(Exception e) {
    		System.out.println("您输入的景点名称有误！");
    	}
    }
    
    //返回vexs[v]在vexs[w]后的后继邻接顶点序号
    protected int next(int v,int w) throws Exception {
    	if(v<0&&v>=vexNum)
    		throw new Exception("第"+v+"个顶点不存在！");
    	//从w+1开始遍历第v行
    	for(int j=w+1;j<vexNum;j++)
    		if(arcs[v][j]!=0 && arcs[v][j]<MAX)
    			return j;
    	return -1;
    }
    
    //从顶点vexs[i]出发的一次深度优先搜索，遍历一个连通分量，visited指定访问标记数组。递归算法
    private void depthfs(int i,boolean[] visited) throws Exception {
    	System.out.print(vexs[i]+"->");     //访问顶点vexs[i]
    	visited[i] = true;                   //设置访问标记
    	int j = this.next(i, -1);            //返回vexs[i]的第一个邻接顶点序号
    	while(j!=-1) {                       //若vexs[i]存在一个邻接顶点vexs[j]
    		if(!visited[j])                  //且顶点vex[j]未被访问
    			depthfs(j,visited);          //递归调用
    		j=this.next(i, j);               //返回vexs[i]在vexs[j]后的后继邻接顶点序号
    	}
    }
    
    public void DFS() throws Exception {
    	Scanner in = new Scanner(System.in);
    	System.out.println("请输入你的出发起点：");
    	try {
    		T name = (T)in.nextLine();
    		int i = locateVex(name);
    		boolean[] visited = new boolean[vexNum];  //标记访问数组
        	int j=i;
        	System.out.println("您的推荐游览顺序为：");
        	do {
        		if(!visited[j]) {                    //若顶点未被访问
        			this.depthfs(j, visited);        //从顶点进行一次深度优先搜索  
        		}
        		j=(j+1)%vexNum;                      //在其他连通分量中寻找未被访问节点
        	}while(j!=i);
        	System.out.println("到达终点");
    	}
    	catch(Exception e){
    		System.out.println("您输入的出发起点有误！");
    	}
    }
   
    //返回path路径矩阵中从顶点i到顶点j的一条字符串路径
    private String toPath(Matrix path,int i,int j) {
    	SinglyList<T> pathlink = new SinglyList<T>();  //单链表，记录最短路径经过顶点，用于反序
    	pathlink.insert(0,this.getVertex(j));          //单链表插入最短路径终点j
    	for(int k=path.getArcs()[i][j];k!=i&&k!=j&&k!=-1;k=path.getArcs()[i][k])
    		pathlink.insert(0, this.getVertex(k));     //单链表头插入经过的顶点，反序
    	pathlink.insert(0, this.getVertex(i));         //最短路径的起点i
    	return pathlink.toString();
    }
    
    public void shortestPath() {
    	Scanner in = new Scanner(System.in);
    	
    	int n = vexNum;
    	Matrix path = new Matrix(n),dist = new Matrix(n);         //最短路径长度矩阵，初值为0
    	for(int i=0;i<n;i++)                                      //初始化dist、path矩阵
    		for(int j=0;j<n;j++) {
    			int w = this.getArcs()[i][j];
    			dist.set(i,j,w);                                  //dist的初值是图的邻接矩阵
    			path.set(i, j, (i!=j&&w<MAX?i:-1));
    		}
    	for(int k=0;k<n;k++)                                      //以k作为其他路径的中间顶点
    		for(int i=0;i<n;i++)                                  //测试每对从i到j路径长度是否更短
    			if(i!=k)
    				for(int j=0;j<n;j++)
    					if(j!=k&&j!=i&&dist.getArcs()[i][j]>dist.getArcs()[i][k]+dist.getArcs()[k][j]) {  //若更短，则替换
    						dist.set(i, j, (dist.getArcs()[i][k]+dist.getArcs()[k][j]));
    						path.set(i, j, path.getArcs()[k][j]);
    					}
    	System.out.println("请输入起点：");
    	T name1 = (T)in.nextLine();
    	System.out.println("请输入终点：");
    	T name2 = (T)in.nextLine();
    	int i = locateVex(name1);
    	int j = locateVex(name2);
    	System.out.println("推荐的路径为：");
    	System.out.println(toPath(path,i,j)+"  距离为"+(dist.getArcs()[i][j]==MAX?"∞":dist.getArcs()[i][j]));
    	//System.out.println("标准邻接矩阵为：");
    	//dist.toGraph();
    }
    
    //prim算法，构造带权无向图的最小生成树，输出最小生成树及各边代价
    public void minSpanTree() {
    	Triple[] mst = new Triple[vexNum-1];                         //最小生成树的边合集
    	for(int i=0;i<mst.length;i++)                                //边合集初始化，从顶点0出发构造
    		mst[i] = new Triple(0,i+1,this.getArcs()[0][i+1]);       //保存从0到其他各个顶点的边
    	for(int i=0;i<mst.length;i++) {                              //选择n-1条边，每趟确定一条权值最小的边
    		int minweight = mst[i].value, min = i;                   //最小权值及边的下标
    		for(int j=i+1;j<mst.length;j++)                          //在i~n-1范围内寻找最小的边
    			if(mst[j].value<minweight) {                         //若存在更小权值，则更新最小值变量
    				minweight = mst[j].value;                        //最小权值
    				min = j;                                         //保存当前权值最小的序号
    			}
    		//将权值最小的边min交换到第i个元素，表示该边加入TE合集
    		Triple edge = mst[min];
    		mst[min] = mst[i];
    		mst[i] = edge;
    		//将i+1~n-1的其他边用权值更小的边替换
    		int tv = edge.column;                                    //刚并入TV的顶点
    		for(int j=i+1;j<mst.length;j++) {
    			int v = mst[j].column;                               //原边在V-TV中的终点
    			int weight = this.getArcs()[tv][v];
    			if(weight<mst[j].value)                              //若（tv，v）边比第j条边的权值更小，则替换
    				mst[j] = new Triple(tv,v,weight);
    		}
    	}
    	System.out.println("\n景区道路铺设建议：");
    	int mincost = 0;
    	for(int i=0;i<mst.length;i++) {
    		System.out.println("["+vexs[mst[i].row]+"]与["+vexs[mst[i].column]+"]相连");
    		mincost += mst[i].value;
    	}
    	System.out.println("最短道路总距离为"+mincost+"\n");
    }
    //输出图的邻接矩阵信息
    public String toString() {
    	for(int i=0;i<getVexNum();i++)
    		System.out.print("\t"+vexs[i]);
    	System.out.println();
    	for(int i=0;i<getVexNum();i++) {
    		System.out.print(vexs[i]+"\t");
    		for(int j=0;j<getVexNum();j++) 
    			if(getArcs()[i][j]==MAX)
    				System.out.print("∞\t");                      //最大值用字符串“∞”代替
    			else 
    				System.out.print(getArcs()[i][j]+"\t");
    		System.out.println();
    	}
    	return null;
    }
    public String toGraph() {
    	
    	for(int i=0;i<getVexNum();i++) {
    		System.out.print("[");
    		for(int j=0;j<getVexNum();j++) 
    			if(j==getVexNum()-1) {
    				if(getArcs()[i][j]==MAX || i==j)
        				System.out.print("0");                      //最大值用字符串“∞”代替
        			else 
        				System.out.print(getArcs()[i][j]);
    			}
    			else {
    				if(getArcs()[i][j]==MAX || i==j)
        				System.out.print("0,\t");                      //最大值用字符串“∞”代替
        			else 
        				System.out.print(getArcs()[i][j]+",\t");
    			}
    		if(i==getVexNum()-1) 
    			System.out.println("]");
    		else 
    			System.out.println("],");
    	}
    	return null;
    }
}