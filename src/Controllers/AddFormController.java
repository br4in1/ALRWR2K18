/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Game;
import Services.GameCrud;
import Services.StadiumCrud;
import Services.TeamCrud;
import Views.main;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author simo
 */
public class AddFormController implements Initializable {

	@FXML
	private DatePicker GameDate;
	@FXML
	private JFXComboBox<String> HomeTeam;
	@FXML
	private JFXComboBox<String> AwayTeam;
	@FXML
	private JFXComboBox<String> Stadium;
	@FXML
	private JFXTextField Result;
	@FXML
	private JFXTextArea Summary;
	@FXML
	private JFXTextField SummaryPhoto;
	@FXML
	private JFXTextField Highlights;
	@FXML
	private JFXTextField Referee;
	private HashMap<String, Integer> map1;
	private List<String > listTeam ;
	private HashMap<String, Integer> map2;
	Cloudinary cloudinary;
	private File image;
	@FXML
	private BorderPane main;
	@FXML
	private Label error;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		map1 = TeamCrud.GetNameIdMap();
		listTeam = TeamCrud.GetNamelist() ;
		
		map2 = StadiumCrud.GetNameIdMap();
		HomeTeam.setItems(FXCollections.observableArrayList(listTeam));
		AwayTeam.setItems(FXCollections.observableArrayList(listTeam));
		Stadium.setItems(FXCollections.observableArrayList(map2.keySet()));
	}

	@FXML
	private void submit(MouseEvent event) throws IOException {
		if (HomeTeam.getSelectionModel().getSelectedItem() == AwayTeam.getSelectionModel().getSelectedItem()) {
			error.setText("Cannot Have a game with both teams the same");
		} else if (!Result.getText().matches("\\d+[-]\\d+")) {
			error.setText("Result format not valid");
		} else {

			Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
			// old add -- GameCrud.InsertGame(new Game(Date.valueOf(GameDate.getValue()), String.valueOf(map1.get(HomeTeam.getSelectionModel().getSelectedItem())), String.valueOf(map1.get(AwayTeam.getSelectionModel().getSelectedItem())), Result.getText(), String.valueOf(map2.get(Stadium.getSelectionModel().getSelectedItem())), Summary.getText(), (String) uploadResult.get("url"), Highlights.getText(), Referee.getText()));
			GameCrud.InsertGame(new Game(Date.valueOf(GameDate.getValue()), HomeTeam.getValue(), AwayTeam.getValue(), Result.getText(), String.valueOf(map2.get(Stadium.getSelectionModel().getSelectedItem())), Summary.getText(), (String) uploadResult.get("url"), Highlights.getText(), Referee.getText()));

			Node source = (Node) event.getSource();
			Stage stage = (Stage) source.getScene().getWindow();
			stage.close();

			Notifications notificationBuilder
					= Notifications.create().title("Information")
							.text("Your Game Has Been Succesfully Added ")
							.hideAfter(Duration.seconds(5))
							.position(Pos.TOP_RIGHT)
							.darkStyle();

			notificationBuilder.showInformation();
		}

	}

	@FXML
	private void photo(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);
		SummaryPhoto.setText(image.getPath());
	}

}
