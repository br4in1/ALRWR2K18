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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;
//import org.apache.poi.ss.usermodel.Row;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamsCrudController implements Initializable {

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
	static int x = 0;

	@FXML
	private TableView<Team> tableT;

	private List<String> list;
	@FXML
	private JFXComboBox<String> findName;

	/**
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		listDisplay();
		list = TeamCrud.GetNamelist();// prepare the elements 
		findName.setItems(FXCollections.observableArrayList(list));// put the elements in combox

	}

	@FXML
	private void newTeam(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddFormTeam.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void deleteTeam(MouseEvent event) {
		if (tableT.getSelectionModel().getSelectedItem() == null) { 
			Notifications notificationBuilder
					= Notifications.create().title("Avertissment")
							.text("Please select a team")
							.hideAfter(Duration.seconds(3))
							.position(Pos.TOP_RIGHT)
							.onAction((ActionEvent event1) -> {
								// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
								System.out.println("Clicked on notification !");
							});

			notificationBuilder.showInformation();
		} else {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Delete Team confirmation");
			alert.setHeaderText("Are you sure about deleting this team ?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				TeamCrud.RemoveTeam(tableT.getSelectionModel().getSelectedItem().getId());// delete from database
				tableT.getItems().removeAll(tableT.getSelectionModel().getSelectedItem());// delete from table
				Notifications notificationBuilder
						= Notifications.create().title("Avertissment")
								.text("the team has been deleted ! ")
								.hideAfter(Duration.seconds(3))
								.position(Pos.TOP_RIGHT)
								.onAction((ActionEvent event1) -> {
									// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
									System.out.println("Clicked on notification !");
								});

				notificationBuilder.showInformation();
			}

		}

	}

	@FXML
	private void updateTeam(MouseEvent event) {

		if (tableT.getSelectionModel().getSelectedItem() == null) {
			Notifications notificationBuilder
					= Notifications.create().title("Avertissment")
							.text("Please select a team")
							.hideAfter(Duration.seconds(3))
							.position(Pos.TOP_RIGHT)
							.onAction((ActionEvent event1) -> {
								// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
								System.out.println("Clicked on notification !");
							});

			notificationBuilder.showInformation();
		} else {
			try {
				x = tableT.getSelectionModel().getSelectedItem().getId();

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/UpdateFormTeam.fxml"));
				Parent root = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private void listDisplay() {

		id.setCellValueFactory(
				new PropertyValueFactory<>("id"));// expects correctlly named property getters
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
		tableT.setItems(OL);
	}

	private void listDisplaySortName() {

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

	private void listDisplayGroupByArea() {

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

	private void listDisplayOnce() {

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
	private void refreshList(MouseEvent event) throws FileNotFoundException, IOException {
		listDisplay();
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet spreadsheet = workbook.createSheet("sample");
		HSSFRow row = spreadsheet.createRow(0);

		for (int j = 0; j < tableT.getColumns().size(); j++) {
			row.createCell(j).setCellValue(tableT.getColumns().get(j).getText());
		}
		for (int i = 0; i < tableT.getItems().size(); i++) {
			row = spreadsheet.createRow(i + 1);
			for (int j = 0; j < tableT.getColumns().size(); j++) {
				if (tableT.getColumns().get(j).getCellData(i) != null) {
					row.createCell(j).setCellValue(tableT.getColumns().get(j).getCellData(i).toString());
				} else {
					row.createCell(j).setCellValue("");
				}
			}
		}

		FileOutputStream fileOut = new FileOutputStream("workbook.xls");
		workbook.write(fileOut);
		fileOut.close();
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
