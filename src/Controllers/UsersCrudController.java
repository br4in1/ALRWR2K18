/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.SimpleUser;
import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class UsersCrudController implements Initializable {

	@FXML
	private TableView<SimpleUser> tvUsers;
	@FXML
	private TableColumn<SimpleUser, String> usernameCol;
	@FXML
	private TableColumn<SimpleUser, String> EmailCol;
	@FXML
	private TableColumn<SimpleUser, String> firstnameCol;
	@FXML
	private TableColumn<SimpleUser, String> lastnameCol;
	@FXML
	private TableColumn<SimpleUser, Date> birthdateCol;
	@FXML
	private TableColumn<SimpleUser, Date> registerdateCol;
	@FXML
	private TableColumn<SimpleUser, String> nationalityCol;
	@FXML
	private TableColumn<?, ?> statusCol;
	@FXML
	private TableColumn<SimpleUser, ImageView> enabledCol;
	@FXML
	private TableColumn<?, ?> banCol;
	@FXML
	private JFXButton refreshBtn;
	@FXML
	private JFXTextField searchfield;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		enabledCol.setStyle("-fx-alignment: CENTER;");
		statusCol.setStyle("-fx-alignment: CENTER;");
		banCol.setStyle("-fx-alignment: CENTER;");
		RefreshData(new ActionEvent());
	}

	@FXML
	private void ShowProfile(MouseEvent event) {
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
		registerdateCol.setCellValueFactory(
				new PropertyValueFactory<>("registrationdate"));
		birthdateCol.setCellValueFactory(
				new PropertyValueFactory<>("birthdate"));
		//fidelityptsCol.setCellValueFactory(
				//new PropertyValueFactory<>("fidaelitypoints"));
		nationalityCol.setCellValueFactory(
				new PropertyValueFactory<>("nationality"));
		enabledCol.setCellValueFactory(
				new PropertyValueFactory<>("enabledStatus"));
		statusCol.setCellValueFactory(
				new PropertyValueFactory<>("loggedinStatus"));
		banCol.setCellValueFactory(
				new PropertyValueFactory<>("banButton"));
		ObservableList<SimpleUser> OL = FXCollections.observableList(UserCrud.getAllSimpleUsers());
		tvUsers.setItems(OL);
	}

	@FXML
	private void searchItems(KeyEvent event) {
		tvUsers.getItems().clear();
		usernameCol.setCellValueFactory(
				new PropertyValueFactory<>("username"));
		EmailCol.setCellValueFactory(
				new PropertyValueFactory<>("email"));
		firstnameCol.setCellValueFactory(
				new PropertyValueFactory<>("firstname"));
		lastnameCol.setCellValueFactory(
				new PropertyValueFactory<>("lastname"));
		registerdateCol.setCellValueFactory(
				new PropertyValueFactory<>("registrationdate"));
		birthdateCol.setCellValueFactory(
				new PropertyValueFactory<>("birthdate"));
		//fidelityptsCol.setCellValueFactory(
				//new PropertyValueFactory<>("fidaelitypoints"));
		nationalityCol.setCellValueFactory(
				new PropertyValueFactory<>("nationality"));
		enabledCol.setCellValueFactory(
				new PropertyValueFactory<>("enabledStatus"));
		statusCol.setCellValueFactory(
				new PropertyValueFactory<>("loggedinStatus"));
		banCol.setCellValueFactory(
				new PropertyValueFactory<>("banButton"));
		ObservableList<SimpleUser> OL = FXCollections.observableList(UserCrud.searchSimpleUsers(searchfield.getText()));
		tvUsers.setItems(OL);
	}
}
