package dataStructures;

public class LinkedStack<E> /*implements Stack<E>*/ {
	private Node<E> top;
	private int size;

	public LinkedStack(Node<E> top, int size) {
		this.top = top;
		this.size = size;
	}

	public LinkedStack() {
		// TODO Auto-generated constructor stub
	}
	public void setTop(Node<E> top) {
		this.top=top;
	}
	public Node<E> getTop(){
		return this.top;
	}
	public void setSize(int size) {
		this.size=size;
	}
	public int getSize() {
		return this.size;
	}

	//@Override
	public boolean isEmpty() {
		if (top != null) {
			return false;
		} else {
			return true;
		}
	}

	//@Override
	public int size() {
		return this.size;
	}

	//@Override
	public void push(E info) {
		Node<E> current = top;
		Node<E> last = new Node<E>(info, null);
		if (!isEmpty()) {
			while (current.getNext() != null) {
				current = current.getNext();
			}
			current.setNext(last);
			this.size++;
		}else {
			this.top=last;
		}
	}

	//@Override
	public E pop() {
		// TODO Auto-generated method stub
		E info;
		Node<E> current = top;
		if(isEmpty()) {
			return null;
		}else {
			while(current.getNext()!=null) {
				current=current.getNext();
			}
			info = current.getInfo();
			current=null;
			return info;	
		}
		
	}

	//@Override
	public E top() {
		if(!isEmpty()) {
			return top.getInfo();
		}else {
			return null;
		}
	}

}
