package edu.westga.cs3151.animalgame.model;

public class GameNode {
	private String value;
	private GameNode parent;
	private GameNode rightNode;
	private GameNode leftNode;
	
	public GameNode(String value) {
		this.value = value;
		this.parent = null;
		this.rightNode = null;
		this.leftNode = null;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public void setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException("Value must not be null");
		}
		this.value = value;
	}
	
	public boolean isLeaf() {
		return this.rightNode == null && this.leftNode == null;
	}
	
	public GameNode getParent() {
		return this.parent;
	}
	
	public GameNode getRight() {
		return this.rightNode;
	}
	
	public GameNode getLeft() {
		return this.leftNode;
	}
	
	public void setParent(GameNode node) {
		this.parent = node;
	}
	
	public void setRight(GameNode node) {
		this.rightNode = node;
	}
	
	public void setLeft(GameNode node) {
		this.leftNode = node;
	}
	
	public boolean hasTwoChildren() {
		return this.leftNode != null && this.rightNode != null;
	}
}
