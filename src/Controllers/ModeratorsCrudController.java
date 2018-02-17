/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import Entities.Moderator;
import Entities.SimpleUser;
import Services.UserCrud;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class ModeratorsCrudController implements Initializable {

	@FXML
	private JFXButton refreshBtn;
	@FXML
	private TableView<Moderator> tvUsers;
	@FXML
	private TableColumn<Moderator, String> usernameCol;
	@FXML
	private TableColumn<Moderator, String> EmailCol;
	@FXML
	private TableColumn<Moderator, String> firstnameCol;
	@FXML
	private TableColumn<Moderator, String> lastnameCol;
	@FXML
	private TableColumn<Moderator, String> phoneCol;
	@FXML
	private TableColumn<Moderator, ImageView> enabledCol;
	@FXML
	private TableColumn<Moderator, Button> banCol;
	@FXML
	private JFXButton AddBtn;
	private Pane addformContainer;
	private StackPane addformContainer1;
	@FXML
	private AnchorPane main;
	@FXML
	private AnchorPane main1;
	@FXML
	private AnchorPane main2;
	@FXML
	private StackPane stack1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		enabledCol.setStyle("-fx-alignment: CENTER;");
		banCol.setStyle("-fx-alignment: CENTER;");
		RefreshData(new ActionEvent());
	}

	@FXML
	private void RefreshData(ActionEvent event) {
		tvUsers.getItems().clear();
		usernameCol.setCellValueFactory(
				new PropertyValueFactory<>("username"));
		EmailCol.setCellValueFactory(
				new PropertyValueFactory<>("email"));
		firstnameCol.setCellValueFactory(
				new PropertyValueFactory<>("firstname"));
		lastnameCol.setCellValueFactory(
				new PropertyValueFactory<>("lastname"));
		enabledCol.setCellValueFactory(
				new PropertyValueFactory<>("enabledStatus"));
		phoneCol.setCellValueFactory(
				new PropertyValueFactory<>("phonenumber"));
		banCol.setCellValueFactory(
				new PropertyValueFactory<>("banButton"));
		ObservableList<Moderator> OL = FXCollections.observableList(UserCrud.getAllModerators());
		tvUsers.setItems(OL);
	}

	@FXML
	private void ShowProfile(MouseEvent event) {
	}

	@FXML
	private void AddModerator(ActionEvent event) throws IOException {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddModerator.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
