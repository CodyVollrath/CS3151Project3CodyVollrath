package edu.westga.cs3151.animalgame.controller;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
	
	
	public void reset() {
		this.tree.reset();
	}
	
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
	
	public boolean loadFromFile(File file) {
		if (file == null) {
			return true;
		}
		try (FileReader reader = new FileReader(file.getAbsoluteFile());){
			char[] treeDataChars = new char[(int)file.length()];
			reader.read(treeDataChars);
			String treeDataStr = new String(treeDataChars);
			this.tree.createTreeFromData(treeDataStr);
			reader.close();
			System.out.println(this.tree);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
}
