package Project;

public interface Queue<E> {
	boolean isEmpty();
	int size();
	void enqueue (E info);
	E dequeue();
	E front();
}
