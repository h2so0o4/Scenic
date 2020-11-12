package matrixGraph;

public class SinglyList<T> {
	public Node<T> head;
	public SinglyList() {
		this.head = new Node<T>();
	}
	public Node<T> insert(int i,T x){    //在单链表i位置插入元素T
		if(x==null)
			throw new NullPointerException("x==null");
		Node<T> front = this.head;
		for(int j=0;front.next!=null && j<i;j++)
			front = front.next;
		front.next = new Node<T>(x,front.next);
		return front.next;
	}
	public String toString() {
		String str = "";
		for(Node<T> p=head.next; p!=null; p=p.next) {
			str+= p.toString();
			if(p.next!=null)
				str+="->";
		}
		return str;
	}
}
