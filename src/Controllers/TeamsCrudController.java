/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.List;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamsCrudController  implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> coach;
    @FXML
    private TableColumn<?, ?> president;
    @FXML
    private TableColumn<?, ?> area;
    @FXML
    private TableColumn<?, ?> gamesPlayed;
    @FXML
    private TableColumn<?, ?> goalScored;
    @FXML
    private TableColumn<?, ?> goalAgainst;
    @FXML
    private TableColumn<?, ?> participations;
    @FXML
    private TableColumn<?, ?> fifaDate;
    @FXML
    private TableColumn<?, ?> wcGroup;
    @FXML
    private TableColumn<?, ?> win;
    @FXML
    private TableColumn<?, ?> loose;
    @FXML
    private TableColumn<?, ?> draw;
    @FXML
    private TableColumn<?, ?> points;
    @FXML
    private TableColumn<?, ?> fifaRank;
    @FXML
    private TableColumn<?, ?> flagPhoto;
    @FXML
    private TableColumn<?, ?> logoPhoto;
    @FXML
    private TableColumn<?, ?> squadPhoto;
    @FXML
    private TableColumn<?, ?> descriptionPhoto;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> website;
    @FXML
    private TableColumn<?, ?> video;

    @FXML
    private TableView<Team> tableT;
	
	private List<String> list;
	@FXML
	private JFXComboBox<String> findName;
     
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       listDisplay();
	   list = TeamCrud.GetNamelist();
	   findName.setItems(FXCollections.observableArrayList(list));

    }     

    @FXML
    private void newTeam(MouseEvent event) {
            try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddFormTeam.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void deleteTeam(MouseEvent event) {
       
        TeamCrud.RemoveTeam(tableT.getSelectionModel().getSelectedItem().getId());
        tableT.getItems().removeAll(tableT.getSelectionModel().getSelectedItem());
        
        
    }

    @FXML
    private void updateTeam(MouseEvent event) {
               try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/UpdateFormTeam.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    private void listDisplay()
    {
             
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
       name.setCellValueFactory(
            new PropertyValueFactory<>("name"));
       coach.setCellValueFactory(
            new PropertyValueFactory<>("coach"));
       president.setCellValueFactory(
            new PropertyValueFactory<>("president"));
       area.setCellValueFactory(
            new PropertyValueFactory<>("area"));
       gamesPlayed.setCellValueFactory(
            new PropertyValueFactory<>("gamesPlayed"));
       goalScored.setCellValueFactory(
            new PropertyValueFactory<>("goalScored"));
       goalAgainst.setCellValueFactory(
            new PropertyValueFactory<>("goalAgainst"));
       participations.setCellValueFactory(
            new PropertyValueFactory<>("participations"));
       fifaDate.setCellValueFactory(
            new PropertyValueFactory<>("fifaDate"));
       wcGroup.setCellValueFactory(
            new PropertyValueFactory<>("wcGroup"));
       win.setCellValueFactory(
            new PropertyValueFactory<>("win"));
       loose.setCellValueFactory(
            new PropertyValueFactory<>("loose"));
       draw.setCellValueFactory(
            new PropertyValueFactory<>("draw"));
        points.setCellValueFactory(
            new PropertyValueFactory<>("points"));
       fifaRank.setCellValueFactory(
            new PropertyValueFactory<>("fifaRank"));
       flagPhoto.setCellValueFactory(
            new PropertyValueFactory<>("flagPhoto"));
       logoPhoto.setCellValueFactory(
            new PropertyValueFactory<>("logoPhoto"));
       squadPhoto.setCellValueFactory(
            new PropertyValueFactory<>("squadPhoto"));
       descriptionPhoto.setCellValueFactory(
            new PropertyValueFactory<>("descriptionPhoto"));
       description.setCellValueFactory(
            new PropertyValueFactory<>("description"));
       website.setCellValueFactory(
            new PropertyValueFactory<>("website"));
       video.setCellValueFactory(
            new PropertyValueFactory<>("video"));
         ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeam());
		System.out.println(OL);
		tableT.setItems(OL);
    }
	
	 private void listDisplaySortName()
    {
             
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
       name.setCellValueFactory(
            new PropertyValueFactory<>("name"));
       coach.setCellValueFactory(
            new PropertyValueFactory<>("coach"));
       president.setCellValueFactory(
            new PropertyValueFactory<>("president"));
       area.setCellValueFactory(
            new PropertyValueFactory<>("area"));
       gamesPlayed.setCellValueFactory(
            new PropertyValueFactory<>("gamesPlayed"));
       goalScored.setCellValueFactory(
            new PropertyValueFactory<>("goalScored"));
       goalAgainst.setCellValueFactory(
            new PropertyValueFactory<>("goalAgainst"));
       participations.setCellValueFactory(
            new PropertyValueFactory<>("participations"));
       fifaDate.setCellValueFactory(
            new PropertyValueFactory<>("fifaDate"));
       wcGroup.setCellValueFactory(
            new PropertyValueFactory<>("wcGroup"));
       win.setCellValueFactory(
            new PropertyValueFactory<>("win"));
       loose.setCellValueFactory(
            new PropertyValueFactory<>("loose"));
       draw.setCellValueFactory(
            new PropertyValueFactory<>("draw"));
        points.setCellValueFactory(
            new PropertyValueFactory<>("points"));
       fifaRank.setCellValueFactory(
            new PropertyValueFactory<>("fifaRank"));
       flagPhoto.setCellValueFactory(
            new PropertyValueFactory<>("flagPhoto"));
       logoPhoto.setCellValueFactory(
            new PropertyValueFactory<>("logoPhoto"));
       squadPhoto.setCellValueFactory(
            new PropertyValueFactory<>("squadPhoto"));
       descriptionPhoto.setCellValueFactory(
            new PropertyValueFactory<>("descriptionPhoto"));
       description.setCellValueFactory(
            new PropertyValueFactory<>("description"));
       website.setCellValueFactory(
            new PropertyValueFactory<>("website"));
       video.setCellValueFactory(
            new PropertyValueFactory<>("video"));
         ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeamSortedByName());
		System.out.println(OL);
		tableT.setItems(OL);
    }
	 
	  private void listDisplayGroupByArea()
    {
             
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
       name.setCellValueFactory(
            new PropertyValueFactory<>("name"));
       coach.setCellValueFactory(
            new PropertyValueFactory<>("coach"));
       president.setCellValueFactory(
            new PropertyValueFactory<>("president"));
       area.setCellValueFactory(
            new PropertyValueFactory<>("area"));
       gamesPlayed.setCellValueFactory(
            new PropertyValueFactory<>("gamesPlayed"));
       goalScored.setCellValueFactory(
            new PropertyValueFactory<>("goalScored"));
       goalAgainst.setCellValueFactory(
            new PropertyValueFactory<>("goalAgainst"));
       participations.setCellValueFactory(
            new PropertyValueFactory<>("participations"));
       fifaDate.setCellValueFactory(
            new PropertyValueFactory<>("fifaDate"));
       wcGroup.setCellValueFactory(
            new PropertyValueFactory<>("wcGroup"));
       win.setCellValueFactory(
            new PropertyValueFactory<>("win"));
       loose.setCellValueFactory(
            new PropertyValueFactory<>("loose"));
       draw.setCellValueFactory(
            new PropertyValueFactory<>("draw"));
        points.setCellValueFactory(
            new PropertyValueFactory<>("points"));
       fifaRank.setCellValueFactory(
            new PropertyValueFactory<>("fifaRank"));
       flagPhoto.setCellValueFactory(
            new PropertyValueFactory<>("flagPhoto"));
       logoPhoto.setCellValueFactory(
            new PropertyValueFactory<>("logoPhoto"));
       squadPhoto.setCellValueFactory(
            new PropertyValueFactory<>("squadPhoto"));
       descriptionPhoto.setCellValueFactory(
            new PropertyValueFactory<>("descriptionPhoto"));
       description.setCellValueFactory(
            new PropertyValueFactory<>("description"));
       website.setCellValueFactory(
            new PropertyValueFactory<>("website"));
       video.setCellValueFactory(
            new PropertyValueFactory<>("video"));
         ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeamGroupByArea());
		System.out.println(OL);
		tableT.setItems(OL);
    }
	
	 private void listDisplayOnce()
    {
             
        id.setCellValueFactory(
            new PropertyValueFactory<>("id"));
       name.setCellValueFactory(
            new PropertyValueFactory<>("name"));
       coach.setCellValueFactory(
            new PropertyValueFactory<>("coach"));
       president.setCellValueFactory(
            new PropertyValueFactory<>("president"));
       area.setCellValueFactory(
            new PropertyValueFactory<>("area"));
       gamesPlayed.setCellValueFactory(
            new PropertyValueFactory<>("gamesPlayed"));
       goalScored.setCellValueFactory(
            new PropertyValueFactory<>("goalScored"));
       goalAgainst.setCellValueFactory(
            new PropertyValueFactory<>("goalAgainst"));
       participations.setCellValueFactory(
            new PropertyValueFactory<>("participations"));
       fifaDate.setCellValueFactory(
            new PropertyValueFactory<>("fifaDate"));
       wcGroup.setCellValueFactory(
            new PropertyValueFactory<>("wcGroup"));
       win.setCellValueFactory(
            new PropertyValueFactory<>("win"));
       loose.setCellValueFactory(
            new PropertyValueFactory<>("loose"));
       draw.setCellValueFactory(
            new PropertyValueFactory<>("draw"));
        points.setCellValueFactory(
            new PropertyValueFactory<>("points"));
       fifaRank.setCellValueFactory(
            new PropertyValueFactory<>("fifaRank"));
       flagPhoto.setCellValueFactory(
            new PropertyValueFactory<>("flagPhoto"));
       logoPhoto.setCellValueFactory(
            new PropertyValueFactory<>("logoPhoto"));
       squadPhoto.setCellValueFactory(
            new PropertyValueFactory<>("squadPhoto"));
       descriptionPhoto.setCellValueFactory(
            new PropertyValueFactory<>("descriptionPhoto"));
       description.setCellValueFactory(
            new PropertyValueFactory<>("description"));
       website.setCellValueFactory(
            new PropertyValueFactory<>("website"));
       video.setCellValueFactory(
            new PropertyValueFactory<>("video"));
	   ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findTeamByNameList(findName.getValue().toString()));
	   
		System.out.println(OL);
		tableT.setItems(OL);
    }
	
    @FXML
    private void refreshList(MouseEvent event) {
            listDisplay();
    }
	
	@FXML
		private void GroupeByArea(MouseEvent event) {
		listDisplayGroupByArea();
		}	

	
	@FXML
	private void sortTable(MouseEvent event) {
		listDisplaySortName();
	}


	@FXML
	private void choixId(MouseEvent event) {
		System.out.println(findName.getValue().toString());
		listDisplayOnce();
	}
	}
	
	

