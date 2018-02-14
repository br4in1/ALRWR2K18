/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.DatePickerCell;
import Entities.Game;
import java.util.Date;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import Services.GameCrud;
import Services.StadiumCrud;
import Services.TeamCrud;
import Views.main;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author simo
 */

public class GamesCrudController implements Initializable {
	
	@FXML
	private DatePicker datebis;
	@FXML
	private JFXButton sub;
	@FXML
	private Pane pane;
	@FXML
	private TableView<Game> tablev;
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
ObservableList<Game> OL = FXCollections.observableList(GameCrud.findAllGames());
	
	/**
	 * Initializes the controller class.
	 */
	
	public void display()
	{ 
		id.setCellValueFactory(
<<<<<<< HEAD
				new PropertyValueFactory<>("id"));
=======
            new  PropertyValueFactory<>("id"));
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda
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
<<<<<<< HEAD
				new PropertyValueFactory<>("referee"));
		ObservableList<Game> OL = FXCollections.observableList(GameCrud.findAllGames());
		System.out.println(OL);
		tablev.setItems(OL);
	}
=======
            new PropertyValueFactory<>("referee"));
		tablev.setItems(OL);
	}
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		display();
		 tablev.setRowFactory(tv -> {
            TableRow<Game> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Game clickedRow = row.getItem();
                    
                }
            });
            return row;
        });
		
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
		result.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setResult(t.getNewValue());
				GameCrud.update("Result", t.getNewValue(),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
        });
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
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSummaryPhoto(t.getNewValue());
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
		home.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setHomeTeam(t.getNewValue());
				GameCrud.update("HomeTeam", map1.get(t.getNewValue()),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
			
        });
		stadium.setOnEditCommit(new EventHandler<CellEditEvent<Game,String>>() {
            @Override
            public void handle(CellEditEvent<Game, String> t) {
                ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStadium(t.getNewValue());
				GameCrud.update("Stadium", map2.get(t.getNewValue()),((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
            }
        });
		date.setOnEditStart(new EventHandler<CellEditEvent<Game, Date>>() {
			@Override
			public void handle(CellEditEvent<Game, Date> event) {
				  try {
					  
  
          
            final Stage dialog = new Stage();
                dialog.initModality(Modality.APPLICATION_MODAL);
                
                VBox dialogVbox = new VBox(20);
				DatePicker bis = new DatePicker();
				JFXButton button = new JFXButton("Submit");
				button.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
					GameCrud.update("date", java.sql.Date.valueOf(bis.getValue()),tablev.getSelectionModel().getSelectedItem().getId());
		             
					dialog.close();
                    });
				dialogVbox.getChildren().add(bis);
				dialogVbox.getChildren().add(button);
                Scene dialogScene = new Scene(dialogVbox, 150, 100);
                dialog.setScene(dialogScene);
                dialog.show();
    } catch(Exception e) {
       e.printStackTrace();
	}
			}
		});
		// ------ //
		
	}	
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda

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
<<<<<<< HEAD
=======
    }

	@FXML
	private void refresh(MouseEvent event) {
}
		display();
	}

}
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda

}
