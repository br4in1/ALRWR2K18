/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.PlayerCrud;
import Services.TeamCrud;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamFrontEndController implements Initializable {

	@FXML
	private TableView<Team> tableT;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<Team, ImageView> flag;
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> area;
	@FXML
	private Label President;
	@FXML
	private Label coach;
	@FXML
	private Label fifaDate;
	@FXML
	private Label participation;
	@FXML
	private Label wcGroupe;

	private List<Integer> listId;
	Integer ID ;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		listDisplay();
	//	tableT.getSelectionModel().setCellSelectionEnabled(true);
		
		if (tableT.getSelectionModel().getSelectedItem() != null)
		{
			ID=tableT.getSelectionModel().getSelectedItem().getId() ;
			coach.setText(TeamCrud.findById(ID).getCoach());	
		}
		System.out.println("coach " +  coach.getText());
		System.out.println("ID "+ ID );
	}	
	private void listDisplay(){
		
	//	TableColumn<File, Image> imageColumn = TableColumnBuilder.<File, Image>create().text("Image").build();
        
		
		flag.setCellValueFactory(
				new PropertyValueFactory<>("image"));
		
			id.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		name.setCellValueFactory(
				new PropertyValueFactory<>("name"));
			
		area.setCellValueFactory(
				new PropertyValueFactory<>("area"));
		ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeam());
		tableT.setItems(OL);
	}
	
}


