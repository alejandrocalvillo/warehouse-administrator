package Project;

public class LBSNode<E> {
	private E info;
	private Comparable key;
	private BSTree<E> right;
	private BSTree<E> left;

	public LBSNode(Comparable key, E info, BSTree<E> left, BSTree<E> right) {
		this.info = info;
		this.key = key;
		this.right = right;
		this.left = left;
	}

	public E getInfo() {
		return this.info;
	}

	public void setInfo(E info) {
		this.info = info;
	}

	public Comparable getKey() {
		return this.key;
	}

	public void setKey(Comparable key) {
		this.key = key;
	}

	public BSTree<E> getLeft() {
		return this.left;
	}

	public void setLeft(BSTree<E> left) {
		this.left = left;
	}

	public BSTree<E> getRight() {

		return this.right;
	}

	public void setRight(BSTree<E> right) {
		this.right = right;
	}

	public String toString() {
		if (info != null) {
			return info.toString();
		} else {
			return "";
		}
	}
	public void print() {
		System.out.println(this.toString());
	}
}
