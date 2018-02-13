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
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
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
	private HashMap<String, Integer> map1;
	private HashMap<String, Integer> map2;

	
	/**
	 * Initializes the controller class.
	 */
	
	public void display()
	{
		id.setCellValueFactory(
            new  PropertyValueFactory<>("id"));
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
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		display();
		//update();
		
		map1 = TeamCrud.GetNameIdMap();
		map2 = StadiumCrud.GetNameIdMap();
		ObservableList<String> teams = FXCollections.observableArrayList(map1.keySet());
		ObservableList<String> stadiums  = FXCollections.observableArrayList(map2.keySet());
		
		home.setCellFactory(ComboBoxTableCell.forTableColumn(teams));
		away.setCellFactory(ComboBoxTableCell.forTableColumn(teams));
		result.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		stadium.setCellFactory(ComboBoxTableCell.forTableColumn(stadiums));
		summary.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		summaryPhoto.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		highlights.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		referee.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		// ------ //
		summary.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSummary(t.getNewValue());
				GameCrud.update("Summary", t.getNewValue(),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
        });
		summaryPhoto.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSummary(t.getNewValue());
				GameCrud.update("summaryPhoto", t.getNewValue(),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
        });
		away.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAwayTeam(t.getNewValue());
				GameCrud.update("AwayTeam", map1.get(t.getNewValue()),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
        });
		
		// ------ //
		
	}	

	@FXML
	private void newGame(MouseEvent event) {
         try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
	}
    }

	@FXML
	private void refresh(MouseEvent event) {
		display();
	}

	@FXML
	private void date(TableColumn.CellEditEvent<Game, Date> event) {
			  try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/Datepicker.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
	}
	}

}

