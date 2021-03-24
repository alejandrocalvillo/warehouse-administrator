package Project;

public class LinkedQueue<E> implements Queue<E> {
	Node<E> top;
	Node<E> tail;
	int size;

	public LinkedQueue(Node<E> top, Node<E> tail, int size) {
		this.top = top;
		this.tail = tail;
		this.size = size;
	}
	public LinkedQueue() {
		//Void constructor.
	}
	public Node<E> getTop(){
		return this.top;
	}
	public Node<E> getTail(){
		return this.tail;
	}

	@Override
	public boolean isEmpty() {
		if (this.top == null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 1;
		while (top.getNext() != null) {
			size++;
			top = top.getNext();
		}
		return size;

	}

	@Override
	public void enqueue(E info) {
		// TODO Auto-generated method stub
		Node<E> node = new Node(info, null);
		if (this.isEmpty()) {
			this.top = node;
		} else {
			tail.setNext(node);
		}
		size++;
	}

	@Override
	public E dequeue() {
		E info;
		info = top.getInfo();
		top = top.getNext();
		size--;
		if (isEmpty()) {
			tail = null;
		} else {
			info = null;
		}
		return info;
	}

	@Override
	public E front() {
		if (!isEmpty()) {
			return top.getInfo();
		} else {
			return null;
		}
	}
}
