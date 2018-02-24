/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Divertissement;
import Services.DivertissmentCRUD;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.LatLong;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import static java.time.temporal.ChronoField.MINUTE_OF_DAY;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class DivertissementDisplay2Controller implements Initializable {

	@FXML
	private AnchorPane back;
	@FXML
	private AnchorPane anchor2;
	@FXML
	private ImageView image;
	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField city;
	@FXML
	private JFXTextField link;
	@FXML
	private JFXTextField path;
	@FXML
	private JFXTimePicker open;
	@FXML
	private JFXTimePicker close;
	@FXML
	private AnchorPane anchor1;

	private DivertissmentCRUD C = new DivertissmentCRUD();
	private List<Divertissement> ListD = new ArrayList<>();
	private Divertissement D = new Divertissement();
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField latitude;
	@FXML
	private JFXTextField longtitude;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		init();
	}

	public void init() {
		anchor2.setVisible(false);
		try {
			ListD = C.AfficherTousdivertissement();
		} catch (SQLException ex) {
			Logger.getLogger(DivertissementDisplay2Controller.class.getName()).log(Level.SEVERE, null, ex);
		}

		for (int i = 0; i < ListD.size(); i++) {
			JFXTextField T = new JFXTextField();
			T.setText(ListD.get(i).getNom());
			T.setId("" + ListD.get(i).getId());
			T.setLayoutX(135);
			T.setLayoutY(80 + 20 * i);
			T.centerShapeProperty();
			Label L = new Label();
			int y = i + 1;
			L.setText("Restaurant :" + y + " ");
			L.setLayoutX(50);
			L.setLayoutY(85 + 20 * i);
			anchor1.getChildren().add(T);
			anchor1.getChildren().add(L);
			T.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {

					try {
						DivertissmentCRUD C = new DivertissmentCRUD();
						List<Divertissement> ListD = new ArrayList<>();
						ListD = C.afficherseuelementdivertissement(Integer.parseInt(T.getId()));
						anchor2.setVisible(true);
						id.setText(ListD.get(0).getId() + "");
						name.setText(ListD.get(0).getNom());
						path.setText(ListD.get(0).getImage());
						link.setText(ListD.get(0).getLink());
						city.setText(ListD.get(0).getCity());
						latitude.setText(ListD.get(0).getGeolat() + "");
						longtitude.setText(ListD.get(0).getGeolong() + "");

						File file = new File(path.getText());

						try {
							Image im = new Image(file.toURI().toURL().toExternalForm());
							image.setImage(im);
							image.setVisible(true);
						} catch (MalformedURLException ex) {
							
						}
						open.setValue(LocalTime.parse(ListD.get(0).getHeureOuverture().getHours() + ":" + ListD.get(0).getHeureOuverture().getMinutes() + ""));
						close.setValue(LocalTime.parse(ListD.get(0).getHeureFermeture().getHours() + ":" + ListD.get(0).getHeureFermeture().getMinutes() + ""));
					} catch (SQLException ex) {
						Logger.getLogger(DivertissementDisplay2Controller.class.getName()).log(Level.SEVERE, null, ex);
					}

				}

			});
		}
	}

	@FXML
	private void delete(ActionEvent event) throws SQLException {
		C.delete(Integer.parseInt(id.getText()));
		anchor1.getChildren().clear();
		init();

	}

	@FXML
	private void update(ActionEvent event) throws SQLException {
		Divertissement p = new Divertissement(Integer.parseInt(id.getText()), name.getText(), Double.parseDouble(latitude.getText()), Double.parseDouble(longtitude.getText()), Timestamp.valueOf("2016-11-16 " + open.getValue().toString() + ":10"), Timestamp.valueOf("2016-11-16 " + close.getValue().toString() + ":10"), link.getText(), path.getText(), city.getText());
		DivertissmentCRUD x = new DivertissmentCRUD();
		x.updateA(p);
		init();
	}

	@FXML
	private void location(ActionEvent event) {
	}

}
