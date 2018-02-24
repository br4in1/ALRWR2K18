/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Entities.Stade;
import Services.HotelCRUD;
import Services.StadeCRUD;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class AddStadeController implements Initializable {
	
	@FXML
	private Button enregistrer;
	@FXML
	private Button anuler;
	@FXML
	private JFXTextField nom;
	@FXML
	private JFXTextField capacite;
	@FXML
	private JFXTextField city;
	@FXML
	private JFXTextField latitude;
	@FXML
	private JFXTextField longtitude;
	@FXML
	private JFXTextField image;
	@FXML
	private StackPane confirmation;
	List<String>type ;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		type=new ArrayList<>();
		type.add("*.png");
		type.add("*.jpg");
		StadeCRUD C = new StadeCRUD();
		List<Stade> p2 = new ArrayList<Stade>();

		try {
			p2 = C.AfficherTousLesStade("test");
		} catch (SQLException ex) {
			Logger.getLogger(AddStadeController.class.getName()).log(Level.SEVERE, null, ex);
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


		capacite.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue) {
					try {
						Integer.parseInt(capacite.getText());
						capacite.setUnFocusColor(Color.rgb(0, 0, 255, 1));
					} catch (NumberFormatException e) {
						Alert alert = new Alert(Alert.AlertType.WARNING, "Entrer un Entier.");
						alert.show();
						capacite.setUnFocusColor(Color.rgb(255, 0, 0, 1));
						capacite.setText("");
					}
				}
			}
		});
	}

	@FXML
	private void Enregistrement(ActionEvent event)throws SQLException {
		if ((!"".equals(city.getText())) && (!"".equals(capacite.getText())) && (!"".equals(nom.getText()))) {

			//enregistrer
			Stade p = new Stade(0, nom.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(longtitude.getText()), Integer.parseInt(capacite.getText()), image.getText(), city.getText());
			StadeCRUD C = new StadeCRUD();
			C.ajouterStade(p);
			nom.setText("");
			
			capacite.setText("");
			city.setText("");
			longtitude.setText("");
			latitude.setText("");
			image.setText("");
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("Stade Ajouté avec succés"));
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
	private void browse(MouseEvent event) {
		FileChooser F = new FileChooser();
		F.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", type));
		File Fc = F.showOpenDialog(null);
		if(Fc != null)
		{
		image.setText(Fc.getAbsolutePath());
		}
	}

	}	

	
	
