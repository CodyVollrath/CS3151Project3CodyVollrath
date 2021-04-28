package edu.westga.cs3151.animalgame.model;


import edu.westga.cs3151.animalgame.resources.Resources;

/**
 * GameTree
 *
 * @author 
 */
public class GameTree {

	private GameNode root;
	private GameNode currentNode;

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
	
	public String getCurrentValue() {
		String value = this.currentNode.getValue();
		if (this.currentNode.isLeaf()) {
			value = String.format(Resources.ASK_IF_ANIMAL_TEMPLATE, value);
		}
		return value;
	}
	
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
	
	public void reset() {
		this.currentNode = this.root;
	}
	
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
					System.out.println("It is a root node " + currentValue);
					this.root = new GameNode(currentValue);
					this.currentNode = this.root;
				} else if (isRightNode) {
					System.out.println("It is a right node " + currentValue);
					this.currentNode.setRight(new GameNode(currentValue));
					this.currentNode.getRight().setParent(this.currentNode);
					this.currentNode = this.currentNode.getRight();
					isRightNode = false;
				} else if (!isRightNode) {
					System.out.println("It is a left node " + currentValue);
					this.currentNode.setLeft(new GameNode(currentValue));
					GameNode currentNode = this.currentNode;
					this.currentNode.getLeft().setParent(currentNode);
					this.currentNode = this.currentNode.getLeft();
				}
				
				if (letter.equals(Resources.ANIMAL_NODE_VALUE)) {
					System.out.println("It is a leaf node " + currentValue);
					this.currentNode = this.currentNode.getParent();
					isRightNode = true;
					System.out.println("\n\nGoing up: " + this.currentNode.getValue());
				}
			}
		}
	}
}
