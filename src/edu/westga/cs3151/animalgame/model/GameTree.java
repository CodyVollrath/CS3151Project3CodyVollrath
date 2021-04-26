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
			System.out.println("It is a leaf");
			return;
		}
		GameNode answerNode = new GameNode(answer);
		
		String currentValue = this.currentNode.getValue();
		
		if (whichSide) {
			this.currentNode.setValue(question);
			this.currentNode.setLeft(answerNode);
			this.currentNode.setRight(new GameNode(currentValue));
			System.out.println("Set node to left");
		} else {
			this.currentNode.setValue(question);
			this.currentNode.setRight(answerNode);
			this.currentNode.setLeft(new GameNode(currentValue));
			System.out.println("Set node to right");
		}
		answerNode.setParent(this.currentNode);
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
	
	public void print() {
		this.print(this.root);
	}
	private void print(GameNode node) {
		if (node != null) {
			print(node.getLeft());
			System.out.println(node.getValue());
			print(node.getRight());
		}
	}
}
