package matrixGraph;

public class Triple {                                //��Ԫ����
    int row,column,value;                            //�кš��кš�Ԫ��ֵ
    
    public Triple(int row,int column,int value) {
    	if(row>=0 && column>=0) {
    		this.row = row;
    		this.column = column;
    		this.value = value;
    	}
    	else throw new IllegalArgumentException("�С��кŲ���Ϊ����");
    }
}
