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
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

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
	private HashMap<String, Integer> map2;
	Cloudinary cloudinary;
	private File image;
	private File videoo;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		map1 = TeamCrud.GetNameIdMap();
		map2 = StadiumCrud.GetNameIdMap();
		HomeTeam.setItems(FXCollections.observableArrayList(map1.keySet()));
		AwayTeam.setItems(FXCollections.observableArrayList(map1.keySet()));
		Stadium.setItems(FXCollections.observableArrayList(map2.keySet()));
	}

	@FXML
	private void submit(MouseEvent event) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
		Map uploadResult2 = cloudinary.uploader().upload(videoo, ObjectUtils.emptyMap());
		GameCrud.InsertGame(new Game(Date.valueOf(GameDate.getValue()), String.valueOf(map1.get(HomeTeam.getSelectionModel().getSelectedItem())), String.valueOf(map1.get(AwayTeam.getSelectionModel().getSelectedItem())), Result.getText(), String.valueOf(map2.get(Stadium.getSelectionModel().getSelectedItem())), Summary.getText(), (String) uploadResult.get("url"),(String) uploadResult2.get("url"), Referee.getText()));
	}

	@FXML
	private void photo(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);
		SummaryPhoto.setText(image.getPath());
	}

	@FXML
	private void video(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une video");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mpg", "*.avi"));
		videoo = fileChooser.showOpenDialog(null);
		Highlights.setText(videoo.getPath());
	}

}
