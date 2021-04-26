package edu.westga.cs3151.animalgame.controller;


import edu.westga.cs3151.animalgame.model.GameTree;

public class AnimalGameController {
	private GameTree tree;
	
	public AnimalGameController() {
		this.tree = new GameTree();
	}
	
	public String getCurrentValue() {
		return this.tree.getCurrentValue();
	}
	
	public String next(boolean yesOrNo) {
		return this.tree.moveToNextNode(yesOrNo);
	}
	
	public void addQuestionToTree(String question, String answer, boolean whichSide) {
		this.tree.addQuestion(question, answer, whichSide);
	}
	
	public void print() {
		this.tree.print();
	}
	
	public void reset() {
		this.tree.reset();
	}
	
}
