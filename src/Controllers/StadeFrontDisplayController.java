/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Stade;
import Services.StadeCRUD;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class StadeFrontDisplayController implements Initializable, MapComponentInitializedListener {

	@FXML
	private FlowPane mainMap;
	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField capcity;
	@FXML
	private JFXTextField city;
	@FXML
	private Button next;
	@FXML
	private Button previw;
	@FXML
	private ImageView image;
	@FXML
	private JFXTextField idT;
	@FXML
	private Pane panMap;
	private StadeCRUD C = new StadeCRUD();
	private Stade S;
	private List<Stade> ListStade = new ArrayList<>();
	private GoogleMap map;
	@FXML
	private GoogleMapView mapView;

	private GeocodingService geocodingService;
	@FXML
	private JFXTextField fromTextField;
	@FXML
	private JFXTextField toTextField;
	@FXML
	private JFXTextField i;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		mapView.addMapInializedListener(this);
	}

	@FXML
	private void gonext(ActionEvent event) {
		int x = Integer.parseInt(i.getText());
		x = x + 1;
		i.setText("" + x);
		init();

	}

	@FXML
	private void goback(ActionEvent event) {
		int x = Integer.parseInt(i.getText());
		x = x - 1;
		i.setText("" + x);
		init();
	}

	public void init() {
		try {
			ListStade = C.AfficherTousLesStades();
		} catch (SQLException ex) {
			Logger.getLogger(StadeDisplayController.class.getName()).log(Level.SEVERE, null, ex);
		}
		int x = Integer.parseInt(i.getText());
		if ((ListStade.size() > x) && (x >= 0)) {
			System.out.println("inti");
			System.out.println(ListStade.get(x).toString());
			capcity.setText("" + ListStade.get(x).getCapacity());
			fromTextField.setText(String.valueOf(ListStade.get(x).getGeolat()));
			toTextField.setText(String.valueOf(ListStade.get(x).getGeolong()));
			name.setText(ListStade.get(x).getNom());
			city.setText(ListStade.get(x).getCity());

				Image im = new Image(ListStade.get(x).getImage());
				image.setImage(im);
				image.setVisible(true);
			
			MapOptions mapOptions = new MapOptions();
			mapOptions.center(new LatLong(ListStade.get(x).getGeolat(), ListStade.get(x).getGeolong()))
					.mapType(MapTypeIdEnum.SATELLITE)
					.overviewMapControl(false)
					.panControl(false)
					.rotateControl(false)
					.scaleControl(false)
					.streetViewControl(true)
					.zoomControl(false)
					.zoom(16);

			map = mapView.createMap(mapOptions);
			LatLong Location = new LatLong(ListStade.get(x).getGeolat(), ListStade.get(x).getGeolong());
			MarkerOptions markerOptions1 = new MarkerOptions();
			markerOptions1.position(Location)
					.visible(true)
					//.icon("File:///"+"assets\\hotel.png")
					.title(ListStade.get(x).getNom());

			Marker joeSmithMarker = new Marker(markerOptions1);
			map.addMarker(joeSmithMarker);

			map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
				LatLong latLong = event.getLatLong();
				fromTextField.setText(String.valueOf(latLong.getLatitude()));
				toTextField.setText(String.valueOf(latLong.getLongitude()));

			});

		} else if (x < 0) {
			Alert alert = new Alert(Alert.AlertType.WARNING, "Premier Stade.");
			alert.show();
			x = x + 1;
			i.setText("" + x);
		} else {
			x = x - 1;
			i.setText("" + x);
			Alert alert = new Alert(Alert.AlertType.WARNING, "Dernier Stade.");
			alert.show();
		}

	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		try {
			ListStade = C.AfficherTousLesStades();
		} catch (SQLException ex) {
			Logger.getLogger(StadeDisplayController.class.getName()).log(Level.SEVERE, null, ex);
		}
		i.setText("" + 0);
		int x = Integer.parseInt(i.getText());
		init();
		mapOptions.center(new LatLong(ListStade.get(x).getGeolat(), ListStade.get(x).getGeolong()))
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.zoom(6);
		map = mapView.createMap(mapOptions);
		map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
			LatLong latLong = event.getLatLong();
			fromTextField.setText(String.valueOf(latLong.getLatitude()));
			toTextField.setText(String.valueOf(latLong.getLongitude()));

		});

	}

}
