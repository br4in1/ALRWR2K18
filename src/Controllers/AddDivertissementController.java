/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Divertissement;
import Entities.Stade;
import Services.DivertissmentCRUD;
import Services.StadeCRUD;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
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
public class AddDivertissementController implements Initializable {

	@FXML
	private Button enregistrer;
	@FXML
	private Button anuler;
	@FXML
	private JFXTextField nom;
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
	@FXML
	private JFXTimePicker opening;
	@FXML
	private JFXTimePicker closing;
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
		DivertissmentCRUD C = new DivertissmentCRUD();
		List<Divertissement> p2 = new ArrayList<Divertissement>();

		try {
			p2 = C.afficherseuelementdivertissement("test");
		} catch (SQLException ex) {
			Logger.getLogger(AddDivertissementController.class.getName()).log(Level.SEVERE, null, ex);
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


	
	}	

	@FXML
	private void Enregistrement(ActionEvent event) throws SQLException {
		
		if ((!"".equals(city.getText())) && (!"".equals(opening.getValue())) &&(!"".equals(closing.getValue())) && (!"".equals(nom.getText()))) {
			
			//enregistrer
			Divertissement p = new Divertissement(0, nom.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(longtitude.getText()), Timestamp.valueOf("2016-11-16 "+opening.getValue().toString()+":10"),Timestamp.valueOf("2016-11-16 "+closing.getValue().toString()+":10"),link.getText(), image.getText(), city.getText());
			DivertissmentCRUD C = new DivertissmentCRUD();
			C.ajouterdivertissement(p);
			nom.setText("");
			
			
			city.setText("");
			longtitude.setText("");
			latitude.setText("");
			image.setText("");
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text(""));
			content.setBody(new Text("REstaurant Ajouté avec succés"));
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

