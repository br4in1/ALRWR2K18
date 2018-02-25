/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class PlayerFrontEndController implements Initializable {

	@FXML
	private TableView<Player> tableT;
	@FXML
	private TableColumn<?, ?> Name;
	@FXML
	private TableColumn<?, ?> lastName;
	@FXML
	private TableColumn<?, ?> profilePhoto;
	@FXML
	private Label Age;
	@FXML
	private Label Position;
	@FXML
	private Label Height;
	@FXML
	private Label Weight;
	@FXML
	private Label shirtNumber;
	@FXML
	private ImageView PlayerImageView;
 
	private int ID ;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private ImageView PlayerImageView2;
	
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		listDisplay();
		//listener ID selected 
		final ObservableList<TablePosition> selectedCells = tableT.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				for (TablePosition pos : selectedCells) {
					if (tableT.getSelectionModel().getSelectedItem() != null) {
						ID = tableT.getSelectionModel().getSelectedItem().getId();
						Age.setText(Integer.toString(PlayerCrud.findById(ID).getAge()));
						Position.setText(PlayerCrud.findById(ID).getPosition());
						Height.setText(Double.toString(PlayerCrud.findById(ID).getHeight()));
						Weight.setText(Double.toString(PlayerCrud.findById(ID).getWeight()));
						shirtNumber.setText(Integer.toString(PlayerCrud.findById(ID).getShirtNb()));
						

						//image view
						//	Image image = new Image(new File(PlayerCrud.findById(ID).getDescriptionPhoto()).toString());
						PlayerImageView.setImage(new Image(PlayerCrud.findById(ID).getDescriptionPhoto()));
						PlayerImageView2.setImage(new Image(PlayerCrud.findById(ID).getBlanketPhoto()));
						//	System.out.println("flag url " + image);

						

					}

				}
			}
		;
		});
	}	

	private void listDisplay() {

		//	TableColumn<File, Image> imageColumn = TableColumnBuilder.<File, Image>create().text("Image").build();
		profilePhoto.setCellValueFactory(
				new PropertyValueFactory<>("image"));

		id.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		Name.setCellValueFactory(
				new PropertyValueFactory<>("name"));
		lastName.setCellValueFactory(
				new PropertyValueFactory<>("lastName"));
		ObservableList<Player> OL = FXCollections.observableList(PlayerCrud.findPlayersByNationFront(TeamFrontEndController.selecteNation));
		tableT.setItems(OL);
		System.out.println("nation =  " + TeamFrontEndController.selecteNation);

	
	}
	
}
