import java.util.*;
public class BinaryTree {
	Node root;
	ArrayList<ArrayList<Node>> layers = new ArrayList<ArrayList<Node>>();
	ArrayList<Integer> focusIDs = new ArrayList<Integer>();
	BinaryTree() {
	}
	public void addNodes(Integer... vals) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Integer> countingVals = new ArrayList<Integer>();
		int result = 0;
		int sum = 0;
		Integer inputValue;
		Node newNode = new Node();
		for(int i = 0; i< vals.length; i++) {
			try {
				inputValue = vals[i];
				newNode = new Node(inputValue);
			} catch(NullPointerException ne) {
				inputValue = 0;
				newNode = new Node(inputValue);
				newNode.setDeadEnd(true);
			}
			nodes.add(newNode);
		}
		result = nodes.size() + 1;
		while((result / 2) > 1) {
			result = result / 2;
			countingVals.add(result);
		}
		countingVals.add(1);
		Collections.reverse(countingVals);
		for(int i = 0; i < countingVals.size(); i++) {
			int index = countingVals.get(i);
			ArrayList<Node> nodeLayer = new ArrayList<Node>();
			for(int j = sum; j < (index+sum); j++) {
				nodeLayer.add(nodes.get(j));
			}
			sum = sum + index;
			layers.add(nodeLayer);
		}
		if(root == null) {
			root = layers.get(0).get(0);
		}
		traverseTree(root);
	}
	public void traverseTree(Node rootNode) {
		Node focusNode = new Node();
		focusNode = rootNode;
		Node leftChild = new Node();
		Node rightChild = new Node();
		Node parent = new Node();
		focusNode.setVisited(true);
		int previousLayer = 0;
		int currentLayer = 0;
		int nextLayer = 1;
		int previousFocus = 0;
		int currentFocus = 0;
		int nextFocus = 0;
		int direction = 0;
		for(int i = 0; i < layers.size(); i++) {
			focusIDs.add(0);
		}
		System.out.println(focusNode.getKey());
		do {
			//left
			if(direction == 0) {
				nextLayer = currentLayer + 1;
				if((nextLayer < layers.size()) && (nextLayer >= 1)) {
					nextFocus = focusIDs.get(nextLayer);
					if((nextFocus < layers.get(nextLayer).size()) && (nextFocus >= 0)) {
						if((nextFocus % 2) == 0) {
							leftChild = layers.get(nextLayer).get(nextFocus);
							if(!leftChild.getDeadEnd()) {
								if(!leftChild.getVisited()) {
									focusNode.setLeftChild(leftChild);
									focusNode = focusNode.getLeftChild();
									focusNode.setVisited(true);
									currentLayer = nextLayer;
									direction = 0;
									System.out.println(focusNode.getKey());
								} else {
									direction = 1;
									nextFocus++;
									focusIDs.set(nextLayer, nextFocus);
								}
							} else {
								direction = 1;
								nextFocus++;
								focusIDs.set(nextLayer, nextFocus);
							}	
						} else {
							direction = 1;
							nextFocus++;
							focusIDs.set(nextLayer, nextFocus);
						}
					} else {
						direction = 2;
						nextLayer = currentLayer;
					}
				} else {
					direction = 2;
					nextLayer = currentLayer;
				}
			}
			//right
			if(direction == 1) {
				nextFocus = focusIDs.get(nextLayer);
				if((nextFocus < layers.get(nextLayer).size()) && (nextFocus >= 0)) {
					if((nextFocus % 2) == 1) { 
						rightChild = layers.get(nextLayer).get(nextFocus);
						if(!rightChild.getDeadEnd()) {
							if(!rightChild.getVisited()) {
								focusNode.setRightChild(rightChild);
								focusNode = focusNode.getRightChild();
								focusNode.setVisited(true);
								currentLayer = nextLayer;
								direction = 0;
								System.out.println(focusNode.getKey());
							} else {
								direction = 2;
								nextFocus++;
								focusIDs.set(nextLayer, nextFocus);
								nextLayer = currentLayer;
							}
						} else {
							direction = 2;
							nextFocus++;
							focusIDs.set(nextLayer, nextFocus);
							nextLayer = currentLayer;
						}
					} else {
						direction = 2;
						nextLayer = currentLayer;
					}
				} else {
					direction = 2;
					nextLayer = currentLayer;
				}
			}
			//back
			if(direction == 2) {
				previousLayer = currentLayer - 1;
				if((previousLayer >= 0) && (previousLayer < layers.size() - 1)) {
					previousFocus = focusIDs.get(previousLayer);
					if((previousFocus >= 0) && (previousFocus < layers.get(previousLayer).size())) {
						parent = layers.get(previousLayer).get(previousFocus);
						if(parent.getVisited()) {
							focusNode.setParent(parent);
							currentFocus = focusIDs.get(currentLayer);
							nextFocus = currentFocus + 1;
							focusIDs.set(currentLayer, nextFocus);	
							focusNode = focusNode.getParent();
							currentLayer = previousLayer;
							direction = 1;
							System.out.println(focusNode.getKey());
						} else {
							System.out.println("Error in binary tree.");
							direction = -1;
						}
					} else {
						System.out.println("Error in binary tree.");
						direction = -1;
					}
				} else {
					direction = -1;
				}
			}
		} while((previousLayer >= 0) && ((direction == 0) || (direction == 1) || (direction == 2)));
		System.out.println("Binary Tree completed.");
	}	
}
