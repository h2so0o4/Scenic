package matrixGraph;

public class Triple {                                //三元组类
    int row,column,value;                            //行号、列号、元素值
    
    public Triple(int row,int column,int value) {
    	if(row>=0 && column>=0) {
    		this.row = row;
    		this.column = column;
    		this.value = value;
    	}
    	else throw new IllegalArgumentException("行、列号不能为负数");
    }
}
