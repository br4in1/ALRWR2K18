/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Entities.Game;
import java.util.Date;
import javafx.scene.layout.VBox;
import Services.GameCrud;
import Services.StadiumCrud;
import Services.TeamCrud;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class GamesCrudController implements Initializable {

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
	public void display() {
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
		tablev.setItems(OL);
	}

	void update() {
		map1 = TeamCrud.GetNameIdMap();
		map2 = StadiumCrud.GetNameIdMap();
		ObservableList<String> teams = FXCollections.observableArrayList(map1.keySet());
		ObservableList<String> stadiums = FXCollections.observableArrayList(map2.keySet());

		home.setCellFactory(ComboBoxTableCell.forTableColumn(teams));
		away.setCellFactory(ComboBoxTableCell.forTableColumn(teams));
		result.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		stadium.setCellFactory(ComboBoxTableCell.forTableColumn(stadiums));
		summary.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		summaryPhoto.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		highlights.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		referee.setCellFactory(TextFieldTableCell.<Game>forTableColumn());
		// ------ //
		result.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setResult(t.getNewValue());
					GameCrud.update("Result", t.getNewValue(), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}

			}
		});
		summary.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSummary(t.getNewValue());
					GameCrud.update("Summary", t.getNewValue(), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}
		});
		summaryPhoto.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setSummaryPhoto(t.getNewValue());
					GameCrud.update("summaryPhoto", t.getNewValue(), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}
		});
		away.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {

				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {
					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAwayTeam(t.getNewValue());
					GameCrud.update("AwayTeam", map1.get(t.getNewValue()), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}
		});
		home.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setHomeTeam(t.getNewValue());
					GameCrud.update("HomeTeam", map1.get(t.getNewValue()), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}

		});
		stadium.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStadium(t.getNewValue());
					GameCrud.update("Stadium", map2.get(t.getNewValue()), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}
		});
		referee.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setReferee(t.getNewValue());
					GameCrud.update("referee", map2.get(t.getNewValue()), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
			}
		});
		highlights.setOnEditCommit(new EventHandler<CellEditEvent<Game, String>>() {
			@Override
			public void handle(CellEditEvent<Game, String> t) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("Confirmation Dialog");
				alert.setContentText("Are you sure of your edition?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == ButtonType.OK) {

					((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).setStadium(t.getNewValue());
					GameCrud.update("Highlights", map2.get(t.getNewValue()), ((Game) t.getTableView().getItems().get(t.getTablePosition().getRow())).getId());
				} else {
					alert.close();
				}
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
						GameCrud.update("date", java.sql.Date.valueOf(bis.getValue()), tablev.getSelectionModel().getSelectedItem().getId());

						dialog.close();
					});
					dialogVbox.getChildren().add(bis);
					dialogVbox.getChildren().add(button);
					Scene dialogScene = new Scene(dialogVbox, 150, 100);
					dialog.setScene(dialogScene);
					dialog.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		display();
		update();

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

	@FXML
	private void refresh(MouseEvent event) {
		OL = FXCollections.observableList(GameCrud.findAllGames());
		display();
	}

	@FXML
	private void delete(MouseEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setContentText("Are you sure of your edition?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			GameCrud.removeGame(tablev.getSelectionModel().getSelectedItem().getId());
			tablev.getItems().removeAll(tablev.getSelectionModel().getSelectedItem());
		} else {
			alert.close();
		}
	}

	@FXML
	private void export(MouseEvent event) throws FileNotFoundException, IOException {

   
        HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet spreadsheet = workbook.createSheet("sample");
				HSSFRow row = spreadsheet.createRow(0);
				
				for (int j = 0; j < tablev.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tablev.getColumns().get(j).getText());
        }
				for (int i = 0; i < tablev.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tablev.getColumns().size(); j++) {
                if(tablev.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tablev.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }
				
				FileOutputStream fileOut = new FileOutputStream("/Users/simo/Desktop/MyBackup.xls");
			 workbook.write(fileOut);
			 fileOut.close();

	}

}
