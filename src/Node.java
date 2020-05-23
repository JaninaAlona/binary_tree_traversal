public class Node {
	private int key;
	private Node leftChild;
	private Node rightChild;
	private Node parent;
	private boolean visited;
	private boolean deadEnd;
	Node() {
		visited = false;
		deadEnd = false;
	}
	Node(int key) { 
		this.key = key; 
		visited = false;
		deadEnd = false;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public int getKey() {
		return key;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public boolean getVisited() {
		return visited;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public Node getParent() {
		return parent;
	}
	public void setDeadEnd(boolean deadEnd) {
		this.deadEnd = deadEnd;
	}
	public boolean getDeadEnd() {
		return deadEnd;
	}
 }