package matrixGraph;

import java.util.Scanner;

public class Matrix<T>  {
	private int vexNum;  //��������
	private int arcNum;     //����
    private T[] vexs;    //��������
    private int[][] arcs;  //��ά�����ʾ����
    private static final int MAX=0x0000ffff;  //���Ȩ��
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
    	this.vexNum = vexNum;   //������n
    	this.arcNum = arcNum;   //����e
    	//����ͼ�и�����
    	this.vexs = (T[]) new Object[vexNum];
    	for (int i = 0; i < vexNum; i++)   //���춥������
    		this.vexs[i] = (T)vexs[i];
    	//��ʼ���ڽӾ���
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
    // �������ڽӾ���Ĵ���
    public void createUW() {
        Scanner in = new Scanner(System.in);
        try {
        	System.out.println("��ֱ����뾰���ĵľ������������ĵ�·��:");
        	vexNum = in.nextInt();   //������n
        	arcNum = in.nextInt();   //����e
        }
        catch(Exception e) {
        	System.out.println("��������ִ�����������[5 20]");
        }
    	
    	//����ͼ�и�����
    	vexs = (T[]) new Object[vexNum];
    	System.out.println("��ֱ����뾰���ĸ�������:");
    	for (int i = 0; i < vexNum; i++)   //���춥������
    		vexs[i] = (T) in.next(); 
    	//��ʼ���ڽӾ���
    	arcs = new int[vexNum][vexNum];
    	for (int i = 0; i < vexNum; i++)
    		for (int j = 0; j < vexNum; j++) {
    			if(i==j) this.arcs[i][j]=0;
    			else this.arcs[i][j]=MAX;
    		}
    	//�������Ϣ
    	try {
    		System.out.println("����������ߵ��������㼰��Ȩֵ:");
            for (int k = 0; k < arcNum; k++) {
            	int i =  locateVex((T) in.next()); //����
                int j =  locateVex((T) in.next()); //����
                arcs[i][j] = in.nextInt();  //Ȩֵ
                arcs[j][i] = arcs[i][j];
            } //����
        }
        catch(Exception e) {
        	System.out.println("��������ִ�����������[A B 20]");
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
    
    public void set(int i,int j,int w) {  //i��j�и���ΪֵΪw
    	this.arcs[i][j]=w;
    }
    private int locateVex(T vex) {
    	//������������
    	for (int i = 0; i < vexNum; i++)
    		if (vexs[i].equals(vex))
    			return i;  
    	return -1;  
    }

    
    public void find() {                          //���Ҿ���
    	Scanner in = new Scanner(System.in);
    	try {
    		System.out.println("��������Ҫ���ҵľ�������:");
    		T vex = (T)in.nextLine();
        	int k = locateVex(vex);
        	for(int i=0;i<getVexNum();i++) {
        		if(getArcs()[k][i]>0 && getArcs()[k][i]<MAX)
        			System.out.println(vex+"��"+vexs[i]+"����������Ϊ"+getArcs()[k][i]);
    	    }
    	}
    	catch(Exception e){
    		System.out.println("���뾰����������");
    	}
    	
    }
    
    private void insertVex(T x) {                    //���붥��
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
    private void setRowsColumns(int m,int n) {    //�����ά����
    	if(m>0 && n>0) {
    		if(m>this.arcs.length || n>this.arcs[0].length) {
    			int[][] source = this.arcs;
    			this.arcs = new int[m][n];
    			for(int i=0;i<this.vexNum;i++) 
    				for(int j=0;j<this.vexNum;j++)
    					this.arcs[i][j] = source[i][j];
    		}
    	}
    	else throw new IllegalArgumentException("��������в��ܡ�0��");
    }

    public void add() {
    	Scanner in = new Scanner(System.in);
    	System.out.println("��������Ҫ���ӵ��¾������ƣ�");
    	T name = (T)in.nextLine();
    	if(locateVex(name)==-1) {
    		insertVex(name);
    		for(int i=0;i<vexNum-1;i++) {   //��ʼ������������
    			arcs[vexNum-1][i]=MAX;
    			arcs[i][vexNum-1]=MAX;
    		}
        	System.out.println("�þ��㵽���о���ľ����Լ����о�������ƣ�����-1��������");
        	try {
        		while(true) {
            		int weight = in.nextInt();      //�������
            		if(weight==-1) break;
            		T name1 = (T)in.next();         //���뾰������
            		this.arcs[vexNum-1][locateVex(name1)] = weight;
            		this.arcs[locateVex(name1)][vexNum-1] = weight;
        	    }
        	}
        	catch(Exception e) {
        		System.out.println("������ľ����ʽ����򾰵����Ʋ����ڣ���������[20 ����԰]");
        	}
    	}
    	else
    		System.out.println("�þ����Ѵ��ڣ�");
    }
    
    public void removeVex() {  //ɾ������
    	try {
    		Scanner in = new Scanner(System.in);
        	System.out.println("��������Ҫɾ���ľ��㣺");
        	T name = (T)in.nextLine();
        	int i = locateVex(name);  //i���Ҫɾ�����������
            for(int k=i;k<vexNum-1;k++) {  //�ı䶥������vexs[]
        		vexs[k]=vexs[k+1];
        	}    	
        	for(int n=0;n<vexNum;n++)  //ѭ��ͳ�Ʊ߼��ٵ�����
        		if(getArcs()[i][n]>0 && getArcs()[i][n]<MAX)
        			arcNum--;
        	for(int k=i;k<vexNum-1;k++)   //��ÿ������
        	    for(int j=0;j<vexNum;j++)
        	    	arcs[k][j]=arcs[k+1][j];
        	for(int k=i;k<vexNum-1;k++)   //��ÿ������
        	    for(int j=0;j<vexNum;j++)
        	    	arcs[j][k]=arcs[j][k+1];
        	vexNum--;  //��������һ
    	}
    	catch(Exception e) {
    		System.out.println("������ľ�����������");
    	}
    }
    
    //����vexs[v]��vexs[w]��ĺ���ڽӶ������
    protected int next(int v,int w) throws Exception {
    	if(v<0&&v>=vexNum)
    		throw new Exception("��"+v+"�����㲻���ڣ�");
    	//��w+1��ʼ������v��
    	for(int j=w+1;j<vexNum;j++)
    		if(arcs[v][j]!=0 && arcs[v][j]<MAX)
    			return j;
    	return -1;
    }
    
    //�Ӷ���vexs[i]������һ�������������������һ����ͨ������visitedָ�����ʱ�����顣�ݹ��㷨
    private void depthfs(int i,boolean[] visited) throws Exception {
    	System.out.print(vexs[i]+"->");     //���ʶ���vexs[i]
    	visited[i] = true;                   //���÷��ʱ��
    	int j = this.next(i, -1);            //����vexs[i]�ĵ�һ���ڽӶ������
    	while(j!=-1) {                       //��vexs[i]����һ���ڽӶ���vexs[j]
    		if(!visited[j])                  //�Ҷ���vex[j]δ������
    			depthfs(j,visited);          //�ݹ����
    		j=this.next(i, j);               //����vexs[i]��vexs[j]��ĺ���ڽӶ������
    	}
    }
    
    public void DFS() throws Exception {
    	Scanner in = new Scanner(System.in);
    	System.out.println("��������ĳ�����㣺");
    	try {
    		T name = (T)in.nextLine();
    		int i = locateVex(name);
    		boolean[] visited = new boolean[vexNum];  //��Ƿ�������
        	int j=i;
        	System.out.println("�����Ƽ�����˳��Ϊ��");
        	do {
        		if(!visited[j]) {                    //������δ������
        			this.depthfs(j, visited);        //�Ӷ������һ�������������  
        		}
        		j=(j+1)%vexNum;                      //��������ͨ������Ѱ��δ�����ʽڵ�
        	}while(j!=i);
        	System.out.println("�����յ�");
    	}
    	catch(Exception e){
    		System.out.println("������ĳ����������");
    	}
    }
   
    //����path·�������дӶ���i������j��һ���ַ���·��
    private String toPath(Matrix path,int i,int j) {
    	SinglyList<T> pathlink = new SinglyList<T>();  //��������¼���·���������㣬���ڷ���
    	pathlink.insert(0,this.getVertex(j));          //������������·���յ�j
    	for(int k=path.getArcs()[i][j];k!=i&&k!=j&&k!=-1;k=path.getArcs()[i][k])
    		pathlink.insert(0, this.getVertex(k));     //������ͷ���뾭���Ķ��㣬����
    	pathlink.insert(0, this.getVertex(i));         //���·�������i
    	return pathlink.toString();
    }
    
    public void shortestPath() {
    	Scanner in = new Scanner(System.in);
    	
    	int n = vexNum;
    	Matrix path = new Matrix(n),dist = new Matrix(n);         //���·�����Ⱦ��󣬳�ֵΪ0
    	for(int i=0;i<n;i++)                                      //��ʼ��dist��path����
    		for(int j=0;j<n;j++) {
    			int w = this.getArcs()[i][j];
    			dist.set(i,j,w);                                  //dist�ĳ�ֵ��ͼ���ڽӾ���
    			path.set(i, j, (i!=j&&w<MAX?i:-1));
    		}
    	for(int k=0;k<n;k++)                                      //��k��Ϊ����·�����м䶥��
    		for(int i=0;i<n;i++)                                  //����ÿ�Դ�i��j·�������Ƿ����
    			if(i!=k)
    				for(int j=0;j<n;j++)
    					if(j!=k&&j!=i&&dist.getArcs()[i][j]>dist.getArcs()[i][k]+dist.getArcs()[k][j]) {  //�����̣����滻
    						dist.set(i, j, (dist.getArcs()[i][k]+dist.getArcs()[k][j]));
    						path.set(i, j, path.getArcs()[k][j]);
    					}
    	System.out.println("��������㣺");
    	T name1 = (T)in.nextLine();
    	System.out.println("�������յ㣺");
    	T name2 = (T)in.nextLine();
    	int i = locateVex(name1);
    	int j = locateVex(name2);
    	System.out.println("�Ƽ���·��Ϊ��");
    	System.out.println(toPath(path,i,j)+"  ����Ϊ"+(dist.getArcs()[i][j]==MAX?"��":dist.getArcs()[i][j]));
    	//System.out.println("��׼�ڽӾ���Ϊ��");
    	//dist.toGraph();
    }
    
    //prim�㷨�������Ȩ����ͼ����С�������������С�����������ߴ���
    public void minSpanTree() {
    	Triple[] mst = new Triple[vexNum-1];                         //��С�������ıߺϼ�
    	for(int i=0;i<mst.length;i++)                                //�ߺϼ���ʼ�����Ӷ���0��������
    		mst[i] = new Triple(0,i+1,this.getArcs()[0][i+1]);       //�����0��������������ı�
    	for(int i=0;i<mst.length;i++) {                              //ѡ��n-1���ߣ�ÿ��ȷ��һ��Ȩֵ��С�ı�
    		int minweight = mst[i].value, min = i;                   //��СȨֵ���ߵ��±�
    		for(int j=i+1;j<mst.length;j++)                          //��i~n-1��Χ��Ѱ����С�ı�
    			if(mst[j].value<minweight) {                         //�����ڸ�СȨֵ���������Сֵ����
    				minweight = mst[j].value;                        //��СȨֵ
    				min = j;                                         //���浱ǰȨֵ��С�����
    			}
    		//��Ȩֵ��С�ı�min��������i��Ԫ�أ���ʾ�ñ߼���TE�ϼ�
    		Triple edge = mst[min];
    		mst[min] = mst[i];
    		mst[i] = edge;
    		//��i+1~n-1����������Ȩֵ��С�ı��滻
    		int tv = edge.column;                                    //�ղ���TV�Ķ���
    		for(int j=i+1;j<mst.length;j++) {
    			int v = mst[j].column;                               //ԭ����V-TV�е��յ�
    			int weight = this.getArcs()[tv][v];
    			if(weight<mst[j].value)                              //����tv��v���߱ȵ�j���ߵ�Ȩֵ��С�����滻
    				mst[j] = new Triple(tv,v,weight);
    		}
    	}
    	System.out.println("\n������·���轨�飺");
    	int mincost = 0;
    	for(int i=0;i<mst.length;i++) {
    		System.out.println("["+vexs[mst[i].row]+"]��["+vexs[mst[i].column]+"]����");
    		mincost += mst[i].value;
    	}
    	System.out.println("��̵�·�ܾ���Ϊ"+mincost+"\n");
    }
    //���ͼ���ڽӾ�����Ϣ
    public String toString() {
    	for(int i=0;i<getVexNum();i++)
    		System.out.print("\t"+vexs[i]);
    	System.out.println();
    	for(int i=0;i<getVexNum();i++) {
    		System.out.print(vexs[i]+"\t");
    		for(int j=0;j<getVexNum();j++) 
    			if(getArcs()[i][j]==MAX)
    				System.out.print("��\t");                      //���ֵ���ַ������ޡ�����
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
        				System.out.print("0");                      //���ֵ���ַ������ޡ�����
        			else 
        				System.out.print(getArcs()[i][j]);
    			}
    			else {
    				if(getArcs()[i][j]==MAX || i==j)
        				System.out.print("0,\t");                      //���ֵ���ַ������ޡ�����
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