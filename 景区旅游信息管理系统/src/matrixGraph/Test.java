package matrixGraph;
import java.util.Scanner;
public class Test {
	private static final int MAX=0x0000ffff;  //���Ȩ��  ������

	static public void frame() {
		System.out.println("---------------");
		System.out.println("����������Ϣ����ϵͳ");
		System.out.println("---------------");
		System.out.println("1.������������ֲ�ͼ");
		System.out.println("2.�����������ֲ�ͼ");
		System.out.println("3.��ѯ����");
		System.out.println("4.���Ӿ���");
		System.out.println("5.ɾ������");
		System.out.println("6.���������·ͼ(�޻�·��������)");
		System.out.println("7.����֮�����·��");
		System.out.println("8.�����·�޽��滮ͼ");
		System.out.println("9.���ɱ�׼�ڽӾ���");
		System.out.println("0.ֱ�����������ͼ");
		System.out.println("��ѡ����Ӧ���ܣ���1~9��");
	}
	static int menu() throws Exception {
		//���������á���
		int vexNum = 9;
		int arcNum = 13;
		String[] vexs = {"������","��ѧ¥","ʳ��","ͼ���","ѧ����Ԣ","�Ƶ�¥","����ѧԺ","����¥","ѧУ����"};
		int[][] arcs = {{0,60,20,MAX,MAX,MAX,MAX,MAX,MAX},{60,0,MAX,15,MAX,44,MAX,MAX,MAX},
				{20,MAX,0,31,55,MAX,MAX,MAX,MAX},{MAX,15,31,0,MAX,20,MAX,40,50},
				{MAX,MAX,55,MAX,0,10,15,MAX,MAX},{MAX,44,MAX,20,10,0,5,MAX,MAX}
				,{MAX,MAX,MAX,MAX,15,5,0,MAX,6},{MAX,MAX,MAX,40,MAX,MAX,MAX,0,MAX}
				,{MAX,MAX,MAX,50,MAX,MAX,6,MAX,0}};
		//���������á���
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
				System.out.println("����������ַ�Χ����");
				break;
			}
			System.out.println("\n�ù�����ɣ������������һ��������ţ���1~9��\n");
		}
	}
	public static void main(String[] args) throws Exception {
		menu();
	}

}
