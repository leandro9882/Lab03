package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	Dictionary dict;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> boxLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnCheck;

    @FXML
    private TextArea txtRisposta;

    @FXML
    private Button btnClear;

    @FXML
    private Label lblTime;
   
    @FXML
    private Label lblError;
    

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long start= System.nanoTime();
    	String language=this.boxLanguage.getValue();
    	if(language==null) {
    		this.txtRisposta.setText("Seleziona una lingua!");
    		return;
    	}
    	dict.loadDictionary(language);
    	List<String> frase=new LinkedList<>();
    	String input=this.txtInput.getText().toLowerCase();
    	StringTokenizer s=new StringTokenizer(input.replaceAll("[.,?\\/#!$%\\^&\\*;:{}=\\-_'~()\\[\\]\"]", "")," ");
    	while(s.hasMoreElements()) {
    		frase.add(s.nextToken());
    	}
    	List<RichWord> checkWord=dict.spellCheckText(frase);
    	this.txtRisposta.setText(dict.getParoleErrate(checkWord));
    	this.lblError.setText("The Text contains "+dict.getNumeroErrori(checkWord)+" errors");
    	this.lblTime.setText("Spell check completed in 0.00"+((System.nanoTime()-start))+" seconds");
    }

    @FXML
    void doClearText(ActionEvent event) {
    	this.txtRisposta.clear();
    	this.txtInput.clear();
    }

    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"boxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisposta != null : "fx:id=\"txtRisposta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblError != null : "fx:id=\"lblError\" was not injected: check your FXML file 'Scene.fxml'.";
        this.boxLanguage.setValue("English");
        this.boxLanguage.setItems(FXCollections.observableArrayList("English","Italian"));
    }
    public void setModel(Dictionary dict) {
		
		this.dict=dict;
	}
}

