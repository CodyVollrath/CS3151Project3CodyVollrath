package edu.westga.cs3151.animalgame.model;

import edu.westga.cs3151.animalgame.resources.Resources;

/**
 * This class keeps track of a value, and nodes that correspond to it such as a parent, a left node, and a right node
 * @author CodyVollrath
 */
public class GameNode {
	private String value;
	private GameNode parent;
	private GameNode rightNode;
	private GameNode leftNode;
	
	/**
	 * Creates a GameNode object
	 * @param value the value to be entered into the node
	 * @pre value != null
	 * @post getValue() == value && getParent() == null && getLeft() == null && getRight() == null
	 * @throws IllegalArgumentException
	 */
	public GameNode(String value) {
		if (value == null) {
			throw new IllegalArgumentException(Resources.VALUE_NOT_NULL);
		}
		this.value = value;
		this.parent = null;
		this.rightNode = null;
		this.leftNode = null;
	}
	
	/**
	 * Gets the value of the node
	 * @return the value of the node
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the value of the node
	 * @param value the value associated with the node
	 * @pre none
	 * @post getValue() == value
	 * @throws IllegalArgumentException
	 */
	public void setValue(String value) {
		if (value == null) {
			throw new IllegalArgumentException(Resources.VALUE_NOT_NULL);
		}
		this.value = value;
	}
	
	/**
	 * Determines if it is a leaf
	 * @return true if the node has no right node or left node, false otherwise;
	 */
	public boolean isLeaf() {
		return this.rightNode == null && this.leftNode == null;
	}
	/**
	 * Gets the parent of the current node
	 * @return the parent of the current node
	 */
	public GameNode getParent() {
		return this.parent;
	}
	
	/**
	 * Gets the right node of the current node
	 * @return the right node for the current node
	 */
	public GameNode getRight() {
		return this.rightNode;
	}
	
	/**
	 * Gets the left node of the current node
	 * @return the left node of the current node
	 */
	public GameNode getLeft() {
		return this.leftNode;
	}
	
	/**
	 * Sets the parent of the current node
	 * @param node the soon to be parent of the current node
	 */
	public void setParent(GameNode node) {
		this.parent = node;
	}
	
	/**
	 * Sets the right node of the current node
	 * @param node the soon to be right node associated with the current node
	 */
	public void setRight(GameNode node) {
		this.rightNode = node;
	}
	
	/**
	 * Sets the left node of the current node
	 * @param node the soon to be left node associated with the current node
	 */
	public void setLeft(GameNode node) {
		this.leftNode = node;
	}
	
	/**
	 * Checks to see if the node has two children
	 * @return true if the node has two children, false otherwise
	 */
	public boolean hasTwoChildren() {
		return this.leftNode != null && this.rightNode != null;
	}
}
