package Project;

public class Node <E>{
	private E info;
	private Node<E> next;
	public Node(E info, Node<E> next) {
		this.info=info;
		this.next=next;
	}
	public Node() {
	}
	public void setInfo(E info) {
		this.info=info;
	}
	public E getInfo() {
		return this.info;
	}
	public void setNext(Node<E> next) {
		this.next=next;
	}
	public Node<E> getNext(){
		return this.next;
	}
	public String toString() {
		if(info!=null) {
			return info.toString();
		}else {
			return null;
		}
	}
	public void print() {
		System.out.println(this.toString());
	}
	
	public static void main (String args[]) {
		Node<String> n1= new Node();
		Node<String> n2 = new Node("string", null);
		Node<Integer> n3 = new Node(null, null);
		n1.setInfo("AASDF");
		n1.setNext(n2);
		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(n3.toString());
		
		
		
		
	}

}
