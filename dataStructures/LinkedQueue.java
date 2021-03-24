package dataStructures;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

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

	
	public int size() {
		int size = 1;
		while (top.getNext() != null) {
			size++;
			top = top.getNext();
		}
		return size;

	}

	
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

	
	public E front() {
		if (!isEmpty()) {
			return top.getInfo();
		} else {
			return null;
		}
	}
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E element() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean offer(E arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}
}
