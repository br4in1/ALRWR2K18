/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Admin;
import Entities.Gallery;
import Entities.Opinions;
import Entities.SimpleUser;
import Services.GalleryCrud;
import Services.OpinionsCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import static jdk.nashorn.internal.objects.NativeJava.type;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AddImageController implements Initializable {

	ObservableList<String> TypeDate = FXCollections.observableArrayList("Saint-Pétersbourg", "Novossibirsk", "Kazan", "Tcheliabins", "Omsk", "Oufa");

	List<String> type;
	private StackPane Validation;
	@FXML
	private JFXTextField Li;
	@FXML
	private JFXTextArea Dc;
	@FXML
	private ImageView imgvw;
	@FXML
	private ComboBox<String> villeCombo;

	Cloudinary cloudinary;
	private File image1;
	@FXML
	private AnchorPane browse1;
	@FXML
	private JFXTextField insertionIm;
	@FXML
	private StackPane verfication;
	@FXML
	private Text emp;
	@FXML
	private Text Des;
	@FXML
	private JFXButton SaveImage;
	@FXML
	private Text emp0;
	@FXML
	private Text inserer;
	@FXML
	private Text avis;
	@FXML
	private JFXTextArea Av;
	@FXML
	private Text inserer1;
	@FXML
	private Label rat;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		type = new ArrayList<>();
		type.add("*.png");
		type.add("*.jpg");
		villeCombo.setItems(TypeDate);
		villeCombo.setValue("Moscou");
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		final Rating rating = new Rating();
		rating.setOrientation(Orientation.HORIZONTAL);
		rating.setLayoutX(630);
		rating.setLayoutY(445);
		rating.ratingProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				rat.setText(newValue.toString());
			}
		});
		browse1.getChildren().add(rating);

		Li.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(Li.getText())) {
						Li.setUnFocusColor(Color.rgb(0, 0, 255, 1));
						try {
							Integer.parseInt(Li.getText());
							Li.setUnFocusColor(Color.rgb(255, 0, 0, 1));
							Alert alert = new Alert(Alert.AlertType.WARNING, "Erreur de saisie :Impossible d'enter des chiffres ");
							alert.show();
							Li.setText("");
						} catch (NumberFormatException e) {

							Li.setUnFocusColor(Color.rgb(0, 0, 255, 1));

						}

					} else {
						Li.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});
		Dc.focusedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(Dc.getText())) {
						Dc.setUnFocusColor(Color.rgb(0, 0, 255, 1));
						try {
							Integer.parseInt(Dc.getText());
							Dc.setUnFocusColor(Color.rgb(255, 0, 0, 1));
							Alert alert = new Alert(Alert.AlertType.WARNING, "Erreur de saisie :Impossible d'enter des chiffres ");
							alert.show();
							Dc.setText("");
						} catch (NumberFormatException e) {

							Dc.setUnFocusColor(Color.rgb(0, 0, 255, 1));

						}

					} else {
						Dc.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});

	}

	@FXML
	private void InsertImagee(MouseEvent event) throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image1 = fileChooser.showOpenDialog(null);

		insertionIm.setText(image1.getPath());
		Image im = new Image(image1.toURI().toURL().toExternalForm());
		imgvw.setImage(im);

		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(""));
		content.setBody(new Text("Vérifier votre photo"));
		JFXDialog verif = new JFXDialog(verfication, content, JFXDialog.DialogTransition.CENTER);
		verif.show();

	}

	@FXML
	private void savee(MouseEvent event) throws IOException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		Map uploadResult = cloudinary.uploader().upload(image1, ObjectUtils.emptyMap());
		alert.setTitle("Confirmation d'ajout de photo");
		alert.setHeaderText("voulez vous vraiment Ajouter cette photo?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Gallery g1 = new Gallery(SimpleUser.current_user.getId(), villeCombo.getValue(), Li.getText(), Dc.getText(), (String) uploadResult.get("url"), "0");
			GalleryCrud.AddImage(g1);
			Opinions o1 = new Opinions(SimpleUser.current_user.getId(), Av.getText(), rat.getText());
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Ajout fait avec succés"));
			JFXDialog check_username = new JFXDialog(Validation, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();
		}
		villeCombo.setValue(null);
		Li.setText("");
		Dc.setText("");
		insertionIm.setText("");
		imgvw.setVisible(false);

	}

}
