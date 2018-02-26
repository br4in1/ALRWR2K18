/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.HotelCRUD;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class AddHotelController implements Initializable {

	Cloudinary cloudinary;
	@FXML
	private Button enregistrer;
	@FXML
	private Button anuler;
	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField nb_etoiles;
	@FXML
	private JFXTextField city;
	@FXML
	private JFXTextField latitude;
	@FXML
	private JFXTextField longtitude;
	@FXML
	private JFXTextField image;
	@FXML
	private JFXTextField link;
	@FXML
	private StackPane confirmation;
	private File image1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");

		List<Hotel> p2 = null;
		HotelCRUD C = new HotelCRUD();

		try {
			p2 = C.AfficherTousLesHotels("test");
		} catch (SQLException ex) {
			Logger.getLogger(AddHotelController.class.getName()).log(Level.SEVERE, null, ex);
		}

		latitude.setText("" + p2.get(0).getGeolat());
		longtitude.setText("" + p2.get(0).getGeolong());

		nom.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(nom.getText())) {
						nom.setUnFocusColor(Color.rgb(0, 0, 255, 1));
						try {
							Integer.parseInt(nom.getText());
							nom.setUnFocusColor(Color.rgb(255, 0, 0, 1));
							Alert alert = new Alert(Alert.AlertType.WARNING, "Ne Pas entrer un Entier.");
							alert.show();
							nom.setText("");
						} catch (NumberFormatException e) {

							nom.setUnFocusColor(Color.rgb(0, 0, 255, 1));

						}

					} else {
						nom.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});

		city.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(city.getText())) {
						city.setUnFocusColor(Color.rgb(0, 0, 255, 1));
						try {
							Integer.parseInt(city.getText());
							city.setUnFocusColor(Color.rgb(255, 0, 0, 1));
							Alert alert = new Alert(Alert.AlertType.WARNING, "Ne Pas entrer un Entier.");
							alert.show();
							city.setText("");
						} catch (NumberFormatException e) {

							city.setUnFocusColor(Color.rgb(0, 0, 255, 1));

						}

					} else {
						city.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});

		image.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(image.getText())) {

						image.setUnFocusColor(Color.rgb(0, 0, 255, 1));

					} else {
						image.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});

		link.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {

					if (!"".equals(link.getText())) {
						link.setUnFocusColor(Color.rgb(0, 0, 255, 1));
						try {
							Integer.parseInt(link.getText());
							link.setUnFocusColor(Color.rgb(255, 0, 0, 1));
							Alert alert = new Alert(Alert.AlertType.WARNING, "Ne Pas entrer un Entier.");
							alert.show();
							link.setText("");
						} catch (NumberFormatException e) {

							link.setUnFocusColor(Color.rgb(0, 0, 255, 1));

						}

					} else {
						link.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					}
				}
			}
		});

		nb_etoiles.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						Integer.parseInt(nb_etoiles.getText());
						nb_etoiles.setUnFocusColor(Color.rgb(0, 0, 255, 1));
					} catch (NumberFormatException e) {
						Alert alert = new Alert(Alert.AlertType.WARNING, "Entrer un Entier.");
						alert.show();
						nb_etoiles.setUnFocusColor(Color.rgb(255, 0, 0, 1));
						nb_etoiles.setText("");
					}
				}
			}
		});
	}

	@FXML
	private void Enregistrement(ActionEvent event) throws SQLException, IOException {
		if ((!"".equals(city.getText())) && (!"".equals(nb_etoiles.getText())) && (!"".equals(nom.getText()))) {

			//enregistrer
			File toUpload = new File(image.getText());
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			Hotel p = new Hotel(0, nom.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(longtitude.getText()), Integer.parseInt(nb_etoiles.getText()), link.getText(),(String) uploadResult.get("url"), city.getText());
			HotelCRUD C = new HotelCRUD();
			C.ajouterHotel(p);
			nom.setText("");
			link.setText("");
			nb_etoiles.setText("");
			city.setText("");
			longtitude.setText("");
			latitude.setText("");
			image.setText("");
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Hotel Ajouté avec succés"));
			JFXDialog Validation_dajout = new JFXDialog(confirmation, content, JFXDialog.DialogTransition.CENTER);
			Validation_dajout.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Verifier vos donnees.");
			alert.show();
		}
	}

	@FXML
	private void RetourALAMap(ActionEvent event) {
		Stage stage = (Stage) anuler.getScene().getWindow();
		// do what you have to do
		stage.close();
	}

	@FXML
	private void browse(MouseEvent event) throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image1 = fileChooser.showOpenDialog(null);
		image.setText(image1.getPath());
	}
}
