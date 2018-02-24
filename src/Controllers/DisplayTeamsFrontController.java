/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class DisplayTeamsFrontController implements Initializable {

	@FXML
	private AnchorPane sidebar;
	@FXML
	private AnchorPane mainContent;
	@FXML
	private TableView<Team> tableT;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> flag;
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> area;
	@FXML
	private Label homebutton;
	@FXML
	private Label fixturesbtn;
	@FXML
	private Label teamsbtn;
	@FXML
	private Label hotelsbtn;
	@FXML
	private Label stadesbtn;
	@FXML
	private Label gallerybtn;
	@FXML
	private Label contactbtn;
	@FXML
	private Circle profilepic;
	@FXML
	private Label firstlastname;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		
	}	

	private void listDisplay(){
			id.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		name.setCellValueFactory(
				new PropertyValueFactory<>("name"));
			flag.setCellValueFactory(
				new PropertyValueFactory<>("flag"));
		area.setCellValueFactory(
				new PropertyValueFactory<>("area"));
		ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeam());
		tableT.setItems(OL);
	}

	@FXML
	private void unhighlighthomebtn(MouseEvent event) {
	}

	@FXML
	private void highlighthomebtn(MouseEvent event) {
	}

	@FXML
	private void unhighlightFixturesbtn(MouseEvent event) {
	}

	@FXML
	private void highlightFixturesbtn(MouseEvent event) {
	}

	@FXML
	private void unhighlightteamsbtn(MouseEvent event) {
	}

	@FXML
	private void highlightteamsbtn(MouseEvent event) {
	}

	@FXML
	private void teamsClicked(MouseEvent event) {
	}

	@FXML
	private void unhighlighthotelsbtn(MouseEvent event) {
	}

	@FXML
	private void highlighthotelsbtn(MouseEvent event) {
	}

	@FXML
	private void unhighlightstadesbtn(MouseEvent event) {
	}

	@FXML
	private void highlightstadesbtn(MouseEvent event) {
	}

	@FXML
	private void unhighlightgallerybtn(MouseEvent event) {
	}

	@FXML
	private void highlightgallerybtn(MouseEvent event) {
	}

	@FXML
	private void GalerieCliked(MouseEvent event) {
	}

	@FXML
	private void unhighlightcontactbtn(MouseEvent event) {
	}

	@FXML
	private void highlightcontactbtn(MouseEvent event) {
	}

	@FXML
	private void myProfileClicked(MouseEvent event) {
	}
}
