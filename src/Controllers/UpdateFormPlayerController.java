/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class UpdateFormPlayerController implements Initializable {

	@FXML
	private JFXComboBox<Integer> id;
	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField video;
	@FXML
	private JFXTextArea description;
	@FXML
	private JFXTextField lastName;
	@FXML
	private JFXTextField age;
	@FXML
	private JFXComboBox<String> nation;
	@FXML
	private JFXTextField club;
	@FXML
	private JFXTextField height;
	@FXML
	private JFXTextField weight;
	@FXML
	private JFXComboBox<String> position;
	@FXML
	private JFXTextField goals;
	@FXML
	private JFXTextField shirtNb;
	@FXML
	private JFXTextField profilePhoto;
	@FXML
	private JFXTextField blanketPhoto;
	@FXML
	private JFXTextField descriptionPhoto;
	@FXML
	private JFXTextField fbLink;
	@FXML
	private JFXTextField twitterLink;

	Cloudinary cloudinary;
	private File image; //profilephoto
	private File image2; //blanketphoto
	private File image3; //Descriptionphoto

	private boolean profileP = false;
	private boolean blanketP = false;
	private boolean DescriptionP = false;

	private List<String> list;
	private List<String> Listposition = new ArrayList<String>();
	private List<Integer> listId;
	@FXML
	private StackPane playerSP;
	@FXML
	private ImageView PlayerImageView;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		list = TeamCrud.GetNamelist();
		nation.setItems(FXCollections.observableArrayList(list));

		Listposition.add("STR");
		Listposition.add("DEF");
		Listposition.add("MDF");
		Listposition.add("GK");
		position.setItems(FXCollections.observableArrayList(Listposition));

		//listId = PlayerCrud.GeIdlistPlayer();
		// id.setItems(FXCollections.observableArrayList(listId));
		System.out.println(TeamsCrudController.x);
		id.getItems().add(PlayersCrudController.x);
		id.getSelectionModel().selectFirst();

		name.setText(PlayerCrud.findById(id.getValue()).getName());
		lastName.setText(PlayerCrud.findById(id.getValue()).getLastName());
		age.setText(Integer.toString(PlayerCrud.findById(id.getValue()).getAge()));
		club.setText(PlayerCrud.findById(id.getValue()).getClub());
		nation.setValue(PlayerCrud.findById(id.getValue()).getNation());
		height.setText(Double.toString(PlayerCrud.findById(id.getValue()).getHeight()));
		weight.setText(Double.toString(PlayerCrud.findById(id.getValue()).getWeight()));
		position.setValue(PlayerCrud.findById(id.getValue()).getPosition());
		goals.setText(Integer.toString(PlayerCrud.findById(id.getValue()).getGoals()));
		description.setText(PlayerCrud.findById(id.getValue()).getDescription());
		profilePhoto.setText(PlayerCrud.findById(id.getValue()).getProfilePhoto());
		blanketPhoto.setText(PlayerCrud.findById(id.getValue()).getBlanketPhoto());
		descriptionPhoto.setText(PlayerCrud.findById(id.getValue()).getDescriptionPhoto());
		fbLink.setText(PlayerCrud.findById(id.getValue()).getFbLink());
		twitterLink.setText(PlayerCrud.findById(id.getValue()).getTwitterLink());
		shirtNb.setText(Integer.toString(PlayerCrud.findById(id.getValue()).getShirtNb()));
		video.setText(PlayerCrud.findById(id.getValue()).getVideo());

	}

	@FXML
	private void submit(MouseEvent event) throws IOException {

		if (id.getValue() == null) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Error !"));
			content.setBody(new Text("Please select an id"));
			JFXDialog check_player = new JFXDialog(playerSP, content, JFXDialog.DialogTransition.CENTER);
			check_player.show();
		} else {
			if ((age.getText().matches("[0-9]*") && goals.getText().matches("[0-9]*") && shirtNb.getText().matches("[0-9]*")) == false) {
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Error !"));
				content.setBody(new Text("Age, shirt number and goals have to be numbers "));
				JFXDialog check_player = new JFXDialog(playerSP, content, JFXDialog.DialogTransition.CENTER);
				check_player.show();
			} else {
				if ((height.getText().matches("(([1-9][0-9]*)|0)?(\\.[0-9]*)?") && weight.getText().matches("(([1-9][0-9]*)|0)?(\\.[0-9]*)?")) == false) {
					JFXDialogLayout content = new JFXDialogLayout();
					content.setHeading(new Text("Error !"));
					content.setBody(new Text("Height and weight have to be double !"));
					JFXDialog check_player = new JFXDialog(playerSP, content, JFXDialog.DialogTransition.CENTER);
					check_player.show();
				} else {
					if (profileP && blanketP && DescriptionP) {
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Update Player confirmation");
						alert.setHeaderText("Are you sure about updating this player ?");
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //profilephoto
							Map uploadResult1 = cloudinary.uploader().upload(image2, ObjectUtils.emptyMap()); //blanketphoto
							Map uploadResult2 = cloudinary.uploader().upload(image3, ObjectUtils.emptyMap());//Descriptionphoto
							// *PlayerCrud.addPlayer(new Player(name.getText(), lastName.getText(), Integer.parseInt(age.getText()), club.getText(), nation.getValue(), Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()), position.getValue(), Integer.parseInt(goals.getText()), description.getText(), (String) uploadResult.get("url"), (String) uploadResult1.get("url"), (String) uploadResult2.get("url"), fbLink.getText(), twitterLink.getText(), Integer.parseInt(shirtNb.getText()), video.getText()));
							//* TeamCrud.updateTeam(new Team(id.getValue(),name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(gamesPlayed.getText()),Integer.parseInt(goalScored.getText()),Integer.parseInt(goalAgainst.getText()),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(win.getText()),Integer.parseInt(loose.getText()),Integer.parseInt(draw.getText()),Integer.parseInt(points.getText()),Integer.parseInt(fifarank.getText()),(String) uploadResult.get("url"),(String) uploadResult2.get("url"),(String) uploadResult1.get("url"),(String) uploadResult3.get("url"),description.getText(),website.getText(),video.getText()));
							System.out.println(id.getValue() + " " + " yyyyyy ");
							PlayerCrud.updateTeam(new Player(id.getValue(), name.getText(), lastName.getText(), Integer.parseInt(age.getText()), club.getText(), nation.getValue(), Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()), position.getValue(), Integer.parseInt(goals.getText()), description.getText(), (String) uploadResult.get("url"), (String) uploadResult1.get("url"), (String) uploadResult2.get("url"), fbLink.getText(), twitterLink.getText(), Integer.parseInt(shirtNb.getText()), video.getText()));
							Notifications notificationBuilder
									= Notifications.create().title("Avertissment")
											.text("the player has been updated ! ")
											.hideAfter(Duration.seconds(3))
											.position(Pos.TOP_RIGHT)
											.onAction((ActionEvent event1) -> {
												// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
												System.out.println("Clicked on notification !");
											});

							notificationBuilder.showInformation();
							((Node)(event.getSource())).getScene().getWindow().hide();  

						}

					} else {
						JFXDialogLayout content = new JFXDialogLayout();
						content.setHeading(new Text("Error !"));
						content.setBody(new Text("Please reinsert all photos !"));
						JFXDialog check_player = new JFXDialog(playerSP, content, JFXDialog.DialogTransition.CENTER);
						check_player.show();
					}

				}
			}

		}

	}

	@FXML
	private void choixId(ActionEvent event) {

	}

	@FXML
	private void choixNation(ActionEvent event) {
	}

	@FXML
	private void choixPosition(ActionEvent event) {
	}

	@FXML
	private void profilePhoto(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);

		profilePhoto.setText(image.getPath());
		Image image = new Image(new File(profilePhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
		profileP = true;

	}

	@FXML
	private void blanketPhoto(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image2 = fileChooser.showOpenDialog(null);

		blanketPhoto.setText(image2.getPath());
		Image image = new Image(new File(blanketPhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
		blanketP = true;

	}

	@FXML
	private void DescriptionPhoto(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image3 = fileChooser.showOpenDialog(null);

		descriptionPhoto.setText(image3.getPath());
		Image image = new Image(new File(descriptionPhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
		DescriptionP = true;
	}

}
