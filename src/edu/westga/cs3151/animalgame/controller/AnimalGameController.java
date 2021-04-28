package edu.westga.cs3151.animalgame.controller;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs3151.animalgame.model.GameTree;

/**
 * This class is responsible for keeping the model and the view seperated
 * @author CodyVollrath
 *
 */
public class AnimalGameController {
	private GameTree tree;
	
	/**
	 * Creates an instance of the AnimalGameController
	 */
	public AnimalGameController() {
		this.tree = new GameTree();
	}
	
	/**
	 * Gets the value that is currently being visited in the tree
	 * @return the current value of the activated node in the tree
	 */
	public String getCurrentValue() {
		return this.tree.getCurrentValue();
	}
	
	/**
	 * Goes to the next node in the tree and returns the value of it.
	 * @param yesOrNo determines which way to traverse the node based on the question answered
	 * @return the value of the node that gets visted
	 */
	public String next(boolean yesOrNo) {
		return this.tree.moveToNextNode(yesOrNo);
	}
	
	/**
	 * Adds a question to the tree
	 * @param question the question to be added
	 * @param answer the answer to be added to the question
	 * @param whichSide this side the answer will be placed in respect to the question
	 */
	public void addQuestionToTree(String question, String answer, boolean whichSide) {
		this.tree.addQuestion(question, answer, whichSide);
	}
	
	/**
	 * Resets the tree
	 */
	public void reset() {
		this.tree.reset();
	}
	
	/**
	 * Writes the data from the tree to a file
	 * @param file the file to be written to
	 * @return true if the file was successfully written, false otherwise
	 */
	public boolean writeToFile(File file) {
		if (file == null) {
			return true;
		}
		try {
			FileWriter writer = new FileWriter(file.getAbsoluteFile());
			writer.write(this.tree.toString());
			writer.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * Loads the data from a file
	 * @param file the file to load the data from
	 * @return true if the file was able to be loaded from, false otherwise
	 */
	public boolean loadFromFile(File file) {
		if (file == null) {
			return true;
		}
		try (FileReader reader = new FileReader(file.getAbsoluteFile());) {
			char[] treeDataChars = new char[(int) file.length()];
			reader.read(treeDataChars);
			String treeDataStr = new String(treeDataChars);
			this.tree.createTreeFromData(treeDataStr);
			reader.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
