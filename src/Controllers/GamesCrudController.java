/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Game;
import Services.GameCrud;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class GamesCrudController implements Initializable {

	@FXML
	private Pane pane;
	@FXML
	private TableView tablev;
	@FXML
	private TableColumn<Game, Date> date;
	@FXML
	private TableColumn<Game, String> home;
	@FXML
	private TableColumn<Game, String> away;
	@FXML
	private TableColumn<Game, String> result;
	@FXML
	private TableColumn<Game, String> stadium;
	@FXML
	private TableColumn<Game, String> summary;
	@FXML
	private TableColumn<Game, String> summaryPhoto;
	@FXML
	private TableColumn<Game, String> highlights;
	@FXML
	private TableColumn<Game, String> referee;
	@FXML
	private TableColumn<Game, Integer> id;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		id.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		date.setCellValueFactory(
				new PropertyValueFactory<>("date"));
		home.setCellValueFactory(
				new PropertyValueFactory<>("HomeTeam"));
		away.setCellValueFactory(
				new PropertyValueFactory<>("AwayTeam"));
		result.setCellValueFactory(
				new PropertyValueFactory<>("result"));
		stadium.setCellValueFactory(
				new PropertyValueFactory<>("stadium"));
		summary.setCellValueFactory(
				new PropertyValueFactory<>("summary"));
		summaryPhoto.setCellValueFactory(
				new PropertyValueFactory<>("summaryPhoto"));
		highlights.setCellValueFactory(
				new PropertyValueFactory<>("highlights"));
		referee.setCellValueFactory(
				new PropertyValueFactory<>("referee"));
		ObservableList<Game> OL = FXCollections.observableList(GameCrud.findAllGames());
		System.out.println(OL);
		tablev.setItems(OL);
	}

	@FXML
	private void newGame(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddForm.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
