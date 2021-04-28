package edu.westga.cs3151.animalgame.view;

import java.io.File;
import java.security.InvalidParameterException;

import edu.westga.cs3151.animalgame.controller.AnimalGameController;
import edu.westga.cs3151.animalgame.resources.Resources;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AnimalGameCodeBehind {

    @FXML
    private AnchorPane pane;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem loadItem;

    @FXML
    private MenuItem saveItem;

    @FXML
    private Menu menuHelp;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private Pane welcomePane;
    
    @FXML
    private Pane questionPane;
    
    @FXML
    private Pane resultPane;

    @FXML
    private Button startButton;

    @FXML
    private Text questionText;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private Text winLoseText;

    @FXML
    private TextField animalField;

    @FXML
    private TextField questionField;

    @FXML
    private HBox radioGroup;

    @FXML
    private RadioButton yesRadioButton;

    @FXML
    private ToggleGroup answer;

    @FXML
    private RadioButton noRadioButton;

    @FXML
    private Button submitButton;
    
    @FXML
    private Pane losingPane;
    
    @FXML
    private Button playAgainButton;
    
    @FXML
    private Button closeButton;
    
    private AnimalGameController controller;
    
    
    @FXML
    private void initialize() {
    	this.questionPane.setVisible(false);
    	this.resultPane.setVisible(false);
    	this.losingPane.setVisible(false);
    }
    public AnimalGameCodeBehind() {
    	this.controller = new AnimalGameController();
    }

    @FXML
    void aboutItem(ActionEvent event) {

    }

    @FXML
    void loadItem(ActionEvent event) {
    	FileChooser fileChooser = this.getFileChooser();
    	File selectedFile = fileChooser.showOpenDialog(this.pane.getScene().getWindow());
    	this.controller.loadFromFile(selectedFile);
    	this.restart();
    }

    @FXML
    void saveItem(ActionEvent event) {
    	FileChooser fileChooser = this.getFileChooser();
    	File createdFile = fileChooser.showSaveDialog(this.pane.getScene().getWindow());
    	this.controller.writeToFile(createdFile);
    }
    
    @FXML
    void start(ActionEvent event) {
    	this.questionText.textProperty().set(this.controller.getCurrentValue());
    	this.welcomePane.setVisible(false);
    	this.questionPane.setVisible(true);
    }
    
    @FXML
    void yesAction(ActionEvent event) {
    	String value = this.controller.next(true);
    	if (value == null) {
    		this.losingPane.setVisible(true);
    		this.questionPane.setVisible(false);
    		return;
    	}
    	this.questionText.textProperty().set(value);
    }
    
    @FXML
    void noAction(ActionEvent event) {
    	String value = this.controller.next(false);
    	if (value == null) {
    		this.resultPane.setVisible(true);
    		this.questionPane.setVisible(false);
    		return;
    	}
    	this.questionText.textProperty().set(value);
    }
    
    @FXML
    void submitAction(ActionEvent event) {
    	if (!this.areAllFieldsFilled()) {
    		return;
    	}
    	String animal = this.animalField.textProperty().get();
    	String question = this.questionField.textProperty().get();
    	boolean answerToQuestion = this.isYesSelected();
    	this.controller.addQuestionToTree(question, animal, answerToQuestion);
    	this.restart();
    }
    
    @FXML
    void restartApp(ActionEvent event) {
    	this.restart();
    }
    
    @FXML
    void closeApp(ActionEvent event) {
    	Platform.exit();
    }
    private void restart() {
    	this.controller.reset();
    	this.welcomePane.setVisible(true);
    	this.questionPane.setVisible(false);
    	this.resultPane.setVisible(false);
    	this.losingPane.setVisible(false);
    }
    
    private boolean areAllFieldsFilled() {
    	return (!this.animalField.textProperty().isEmpty().get() && 
    			!this.questionField.textProperty().isEmpty().get() && 
    			(this.yesRadioButton.isSelected() || this.noRadioButton.isSelected()));
    }
    
    private boolean isYesSelected() {
    	if (this.yesRadioButton.isSelected()) {
    		return true;
    	} else if (this.noRadioButton.isSelected()) {
    		return false;
    	} else {
    		throw new InvalidParameterException(Resources.RADIO_BUTTON_NOT_SELECTED);
    	}
    }
    
    private FileChooser getFileChooser() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle(Resources.SAVE_FILE_PRMOPT);
    	fileChooser.getExtensionFilters().addAll(
    			new ExtensionFilter("Animal Seperated Value", "*.asv"),
    			new ExtensionFilter("Other Text Files", "*.txt", "*.csv"),
    			new ExtensionFilter("All Files", "*.*"));
    	return fileChooser;
    }

}
