package matrixGraph;
import java.util.Scanner;
public class Test {
	private static final int MAX=0x0000ffff;  //最大权重  测试用

	static public void frame() {
		System.out.println("---------------");
		System.out.println("景区旅游信息管理系统");
		System.out.println("---------------");
		System.out.println("1.创建景区景点分布图");
		System.out.println("2.输出景区景点分布图");
		System.out.println("3.查询景点");
		System.out.println("4.增加景点");
		System.out.println("5.删除景点");
		System.out.println("6.输出导游线路图(无回路遍历景点)");
		System.out.println("7.景点之间最短路径");
		System.out.println("8.输出道路修建规划图");
		System.out.println("9.生成标准邻接矩阵");
		System.out.println("0.直接输入测试用图");
		System.out.println("请选择相应功能！（1~9）");
	}
	static int menu() throws Exception {
		//↓↓测试用↓↓
		int vexNum = 9;
		int arcNum = 13;
		String[] vexs = {"体育馆","教学楼","食堂","图书馆","学生公寓","计电楼","数理学院","经外楼","学校大门"};
		int[][] arcs = {{0,60,20,MAX,MAX,MAX,MAX,MAX,MAX},{60,0,MAX,15,MAX,44,MAX,MAX,MAX},
				{20,MAX,0,31,55,MAX,MAX,MAX,MAX},{MAX,15,31,0,MAX,20,MAX,40,50},
				{MAX,MAX,55,MAX,0,10,15,MAX,MAX},{MAX,44,MAX,20,10,0,5,MAX,MAX}
				,{MAX,MAX,MAX,MAX,15,5,0,MAX,6},{MAX,MAX,MAX,40,MAX,MAX,MAX,0,MAX}
				,{MAX,MAX,MAX,50,MAX,MAX,6,MAX,0}};
		//↑↑测试用↑↑
		Scanner in = new Scanner(System.in);
		Matrix<String> a = new Matrix<String>();
		frame();
		while(true) {
			
			int k = in.nextInt();
			switch(k) {
			case 0:
				a.createM(vexNum, arcNum, vexs, arcs);
				break;
			case 1:
				a.createUW();
			    break;
			case 2:
			    a.toString();
			    break;
			case 3:
				a.find();
				break;
			case 4:
				a.add();
				break;
			case 5:
				a.removeVex();
				break;
			case 6:
				a.DFS();
				break;
			case 7:
				a.shortestPath();
				break;
			case 8:
				a.minSpanTree();
				break;
			case 9:
				a.toGraph();
				break;
			default:
				System.out.println("你输入的数字范围出错！");
				break;
			}
			System.out.println("\n该功能完成，请继续输入下一个功能序号：（1~9）\n");
		}
	}
	public static void main(String[] args) throws Exception {
		menu();
	}

}
