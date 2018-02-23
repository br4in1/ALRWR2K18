/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Entities.Team;
import Services.PlayerCrud;
import Services.TeamCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class PlayersCrudController implements Initializable {

	@FXML
	private TableView<Player> tableT;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> descriptionPhoto;
	@FXML
	private TableColumn<?, ?> description;
	@FXML
	private TableColumn<?, ?> video;
	@FXML
	private TableColumn<?, ?> lastName;
	@FXML
	private TableColumn<?, ?> age;
	@FXML
	private TableColumn<?, ?> club;
	@FXML
	private TableColumn<?, ?> nation;
	@FXML
	private TableColumn<?, ?> height;
	@FXML
	private TableColumn<?, ?> weight;
	@FXML
	private TableColumn<?, ?> position;
	@FXML
	private TableColumn<?, ?> goals;
	@FXML
	private TableColumn<?, ?> profilePhoto;
	@FXML
	private TableColumn<?, ?> blanketPhoto;
	@FXML
	private TableColumn<?, ?> fbLink;
	@FXML
	private TableColumn<?, ?> twitterLink;
	@FXML
	private TableColumn<?, ?> shirtNb;
	static int x = 0;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		listDisplay();
	}

	@FXML
	private void newPlayer(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddFormPlayer.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void deletePlayer(MouseEvent event) {
		if (tableT.getSelectionModel().getSelectedItem() == null) {
			Notifications notificationBuilder
					= Notifications.create().title("Avertissment")
							.text("Please select a Player")
							.hideAfter(Duration.seconds(3))
							.position(Pos.TOP_RIGHT)
							.onAction((ActionEvent event1) -> {
								// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
								System.out.println("Clicked on notification !");
							});

			notificationBuilder.showInformation();
		} else {
			PlayerCrud.removePlayer(tableT.getSelectionModel().getSelectedItem().getId());
			tableT.getItems().removeAll(tableT.getSelectionModel().getSelectedItem());
		}

	}

	@FXML
	private void updatePlayer(MouseEvent event) {
		if (tableT.getSelectionModel().getSelectedItem() == null) {
			Notifications notificationBuilder
					= Notifications.create().title("Avertissment")
							.text("Please select a Player ")
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
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/UpdateFormPlayer.fxml"));
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
				new PropertyValueFactory<>("id"));
		name.setCellValueFactory(
				new PropertyValueFactory<>("name"));
		lastName.setCellValueFactory(
				new PropertyValueFactory<>("lastName"));
		age.setCellValueFactory(
				new PropertyValueFactory<>("age"));
		club.setCellValueFactory(
				new PropertyValueFactory<>("club"));
		nation.setCellValueFactory(
				new PropertyValueFactory<>("nation"));
		height.setCellValueFactory(
				new PropertyValueFactory<>("height"));
		weight.setCellValueFactory(
				new PropertyValueFactory<>("weight"));
		position.setCellValueFactory(
				new PropertyValueFactory<>("position"));
		goals.setCellValueFactory(
				new PropertyValueFactory<>("goals"));
		description.setCellValueFactory(
				new PropertyValueFactory<>("description"));
		profilePhoto.setCellValueFactory(
				new PropertyValueFactory<>("profilePhoto"));
		blanketPhoto.setCellValueFactory(
				new PropertyValueFactory<>("blanketPhoto"));
		descriptionPhoto.setCellValueFactory(
				new PropertyValueFactory<>("descriptionPhoto"));
		fbLink.setCellValueFactory(
				new PropertyValueFactory<>("fbLink"));
		twitterLink.setCellValueFactory(
				new PropertyValueFactory<>("twitterLink"));
		shirtNb.setCellValueFactory(
				new PropertyValueFactory<>("shirtNb"));
		video.setCellValueFactory(
				new PropertyValueFactory<>("video"));
		ObservableList<Player> OL = FXCollections.observableList(PlayerCrud.findAllPlayers());
		System.out.println(OL);
		tableT.setItems(OL);

		/*
            private int id ;//
    private String name ; //
    private String lastName ; //
    private int age ; //
    private String club ; //
    private String nation ; //
    private double height ;//
    private double weight ;//
    private String position ; //
    private int goals ; //
    private String description ; //
    private String profilePhoto ;//
    private String blanketPhoto ;//
    private String descriptionPhoto ;
    private String fbLink ;
    private String twitterLink ;
    private int shirtNb ; 
    private String video ; 
        
		 */
	}

	@FXML
	private void refreshList(MouseEvent event) {
		listDisplay();
	}

}
