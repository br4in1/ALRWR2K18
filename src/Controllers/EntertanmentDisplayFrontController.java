/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Divertissement;
import Services.DivertissmentCRUD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
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
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class EntertanmentDisplayFrontController implements Initializable , MapComponentInitializedListener{

	@FXML
	private FlowPane mainMap;
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
	private JFXTimePicker open;
	@FXML
	private JFXTimePicker close;
	private JFXTextField latitude;
	private JFXTextField longtitude;
	@FXML
	private AnchorPane anchor1;
	private DivertissmentCRUD C = new DivertissmentCRUD();
	private List<Divertissement> ListD = new ArrayList<>();
	private Divertissement D = new Divertissement();
	@FXML
	private Pane mapPlace;
	@FXML
	private JFXButton buttonshow;
	private GoogleMap map;
	@FXML
	private GoogleMapView mapView;

	private GeocodingService geocodingService;
	/**
	 * Initializes the controller class.
	 */
	@Override
public void initialize(URL url, ResourceBundle rb) {
		// TODO
		init();
	}

	public void init() {
		mapPlace.setVisible(false);
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
						name.setText(ListD.get(0).getNom());
						link.setText(ListD.get(0).getLink());
						city.setText(ListD.get(0).getCity());
						buttonshow.setId(ListD.get(0).getId()+"");
						MapOptions mapOptions = new MapOptions();
			mapOptions.center(new LatLong(ListD.get(0).getGeolat(), ListD.get(0).getGeolong()))
					.mapType(MapTypeIdEnum.ROADMAP)
					.overviewMapControl(false)
					.panControl(false)
					.rotateControl(false)
					.scaleControl(false)
					.streetViewControl(true)
					.zoomControl(false)
					.zoom(8);

			map = mapView.createMap(mapOptions);
			LatLong Location = new LatLong(ListD.get(0).getGeolat(), ListD.get(0).getGeolong());
			MarkerOptions markerOptions1 = new MarkerOptions();
			markerOptions1.position(Location)
					.visible(true)
					.title(ListD.get(0).getNom());

			Marker joeSmithMarker = new Marker(markerOptions1);
			map.addMarker(joeSmithMarker);
						File file = new File(ListD.get(0).getImage());

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
	private void showLocation(ActionEvent event) {
		
		mapPlace.setVisible(true);
	}

	@FXML
	private void Close(MouseEvent event) {
		
		mapPlace.setVisible(false);
	}


	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		
			mapOptions.center(new LatLong(50.000,23.000))
					.mapType(MapTypeIdEnum.ROADMAP)
					.overviewMapControl(false)
					.panControl(false)
					.rotateControl(false)
					.scaleControl(false)
					.streetViewControl(true)
					.zoomControl(false)
					.zoom(14);

			map = mapView.createMap(mapOptions);
	
	
	}

	
}
