package edu.westga.cs3151.animalgame.model;


import edu.westga.cs3151.animalgame.resources.Resources;

/**
 * GameTree keeps track of the nodes in a Binary Search Tree like fashion
 *
 * @author Cody Vollrath
 */
public class GameTree {

	private GameNode root;
	private GameNode currentNode;

	/**
	 * Creates an instance of the GameTree object
	 */
	public GameTree() {
		
		this.root = new GameNode(Resources.ROOT_QUESTION);
		GameNode leftNode = new GameNode(Resources.FIRST_LEFT_ANIMAL);
		GameNode rightNode = new GameNode(Resources.FIRST_RIGHT_ANIMAL);
		
		leftNode.setParent(this.root);
		rightNode.setParent(this.root);
		
		this.root.setLeft(leftNode);
		this.root.setRight(rightNode);
		this.currentNode = this.root;
	}
	
	/**
	 * Adds a question to the tree
	 * @param question the question to be added
	 * @param answer the answer associated with the question
	 * @param whichSide the left or right side flag to indicate which side of the question the answer belongs to
	 * 
	 * @pre question != null && answer != null
	 * @post question gets added to the tree
	 * 
	 */
	public void addQuestion(String question, String answer, boolean whichSide) {
		if (question == null) {
			throw new IllegalArgumentException();
		}
		if (answer == null) {
			throw new IllegalArgumentException();
		}
		if (!this.currentNode.isLeaf()) {
			return;
		}
		GameNode answerNode = new GameNode(answer);
		
		String currentValue = this.currentNode.getValue();
		
		if (whichSide) {
			this.currentNode.setValue(question);
			this.currentNode.setLeft(answerNode);
			this.currentNode.getLeft().setParent(this.currentNode);
			this.currentNode.setRight(new GameNode(currentValue));
			this.currentNode.getRight().setParent(this.currentNode);
		} else {
			this.currentNode.setValue(question);
			this.currentNode.setRight(answerNode);
			this.currentNode.getRight().setParent(this.currentNode);
			this.currentNode.setLeft(new GameNode(currentValue));
			this.currentNode.getLeft().setParent(this.currentNode);
		}
	}
	
	
	/**
	 * Gets the value of the currently activated node
	 * @return the value associated with the current node that is activated in the tree
	 */
	public String getCurrentValue() {
		String value = this.currentNode.getValue();
		if (this.currentNode.isLeaf()) {
			value = String.format(Resources.ASK_IF_ANIMAL_TEMPLATE, value);
		}
		return value;
	}
	
	/**
	 * Move to next node based on passed in flag indicated which node to traverse to
	 * @param isLeft indicates which side to move to
	 * @return the value of the node traveled to
	 */
	public String moveToNextNode(boolean isLeft) {
		if (this.currentNode.isLeaf()) {
			return null;
		}
		
		if (isLeft) {
			this.currentNode = this.currentNode.getLeft();
			
		} else {
			this.currentNode = this.currentNode.getRight();
		}
		return this.getCurrentValue();
	}
	
	/**
	 * Resets the current node back to the root node
	 */
	public void reset() {
		this.currentNode = this.root;
	}
	
	/**
	 * Creates a tree from a string with a particular organized structure
	 * @param treeData the data of the tree in the form of a string
	 * @return the boolean value indicating if the load was successful
	 */
	public boolean createTreeFromData(String treeData) {
		if (treeData == null) {
			throw new IllegalArgumentException();
		}
		try {
			this.createTreeFromString(treeData);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * Gets a string representation of the tree in the order of a preordered traversed tree
	 * @returns a string with the tree values in a preordered fashion
	 */
	@Override
	public String toString() {
		return this.getTreeInStringForm(this.root);
	}
	
	private String getTreeInStringForm(GameNode node) {
		String item = "";
		if (node != null) {
			String letter = node.isLeaf() ? Resources.ANIMAL_NODE_VALUE : Resources.QUESTION_NODE_VALUE;
			item = node.getValue() + "\n" + letter + "\n";
			item += getTreeInStringForm(node.getLeft());
			item += getTreeInStringForm(node.getRight());
		}
		return item;
	}
	
	private void createTreeFromString(String treeData) {
		String[] asvData = treeData.split("\n");
		String letter = "";
		boolean isRightNode = false;
		for (int i = 0; i < asvData.length; i++) {
			String currentValue = asvData[i];
			
			boolean isLetterA = currentValue.equals(Resources.ANIMAL_NODE_VALUE);
			boolean isLetterQ = currentValue.equals(Resources.QUESTION_NODE_VALUE);
			boolean isLetter = isLetterA || isLetterQ;
			if (!isLetter) {
				letter = asvData[i + 1];
			}
			if (!isLetter) {

				if (i == 0) {
					this.root = new GameNode(currentValue);
					this.currentNode = this.root;
				} else if (isRightNode) {
					if (this.currentNode.hasTwoChildren()) {
						this.currentNode = this.currentNode.getParent();
						isRightNode = false;
					}
					this.currentNode.setRight(new GameNode(currentValue));
					this.currentNode.getRight().setParent(this.currentNode);
					this.currentNode = this.currentNode.getRight();
					isRightNode = false;
				} else if (!isRightNode) {
					if (this.currentNode.hasTwoChildren()) {
						this.currentNode = this.currentNode.getParent();
						isRightNode = false;
					}
					this.currentNode.setLeft(new GameNode(currentValue));
					GameNode currentNode = this.currentNode;
					this.currentNode.getLeft().setParent(currentNode);
					this.currentNode = this.currentNode.getLeft();
				}
				
				if (letter.equals(Resources.ANIMAL_NODE_VALUE)) {
					this.currentNode = this.currentNode.getParent();
					isRightNode = true;
				}
			}
		}
	}
}
