/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FrontUserBoxController.selected;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class MapBoxController implements Initializable {

	@FXML
	private VBox nav;
	@FXML
	private Label VisualizeMap;
	@FXML
	private Label Hotels;
	@FXML
	private Label Stadiums;
	@FXML
	private Label Entertanment;
	@FXML
	private Label Translate;
	public static String selected;
	public static MapBoxController thisController;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		selected = "visualize";
		VisualizeMap.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		thisController = this;
	}	

	@FXML
	public void unhighlightMyProfile(MouseEvent event) {
		if(!selected.equals("visualize")){
			VisualizeMap.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightMyProfile(MouseEvent event) {
		if(!selected.equals("visualize")){
			VisualizeMap.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	public void unhighlightEdit(MouseEvent event) {
		if(!selected.equals("hotels")){
			Hotels.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightEdit(MouseEvent event) {
		if(!selected.equals("hotels")){
			Hotels.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	public void getContent(MouseEvent event) {
		
	}

	@FXML
	public void unhighlightFavoris(MouseEvent event) {
		if(!selected.equals("stadiums")){
			Stadiums.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightFavoris(MouseEvent event) {
		if(!selected.equals("stadiums")){
			Stadiums.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	public void unhighlightPhotos(MouseEvent event) {
		if(!selected.equals("entertainement")){
			Entertanment.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightPhotos(MouseEvent event) {
		if(!selected.equals("entertainement")){
			Entertanment.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}

	@FXML
	private void unhighlightTranslate(MouseEvent event) {
		if(!selected.equals("translate")){
			Translate.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		}
	}

	@FXML
	public void highlightTranslate(MouseEvent event) {
		if(!selected.equals("translate")){
			Translate.setStyle("-fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
		}
	}
	
	public void unhighlightAll(){
		Translate.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		VisualizeMap.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		Hotels.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		Stadiums.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
		Entertanment.setStyle("-fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
	}
	
}
