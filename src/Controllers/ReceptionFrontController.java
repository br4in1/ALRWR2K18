/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.DivertissmentCRUD;
import Services.HotelCRUD;
import com.jfoenix.controls.JFXTextField;
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
public class ReceptionFrontController implements Initializable, MapComponentInitializedListener {

	@FXML
	private FlowPane mainMap;
	@FXML
	private ImageView grandhotel;
	@FXML
	private Pane panHotel;
	@FXML
	private Pane panMap;
	@FXML
	private ImageView minihotel;
	List<Hotel>ListH=new ArrayList<>();
	HotelCRUD C = new HotelCRUD();
	@FXML
	private AnchorPane anchor1;
	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField stars;
	@FXML
	private JFXTextField city;
	@FXML
	private JFXTextField link;
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
		panHotel.setVisible(false);
		try {
			ListH = C.AfficherTousLesHotels();
		} catch (SQLException ex) {
			Logger.getLogger(DivertissementDisplay2Controller.class.getName()).log(Level.SEVERE, null, ex);
		}

		for (int i = 0; i < ListH.size(); i++) {
			System.out.println(ListH.get(i).getNom());
			JFXTextField T = new JFXTextField();
			T.setText(ListH.get(i).getNom());
			T.setId("" + ListH.get(i).getId());
			T.setLayoutX(40);
			T.setLayoutY(30 + 20 * i);
			T.centerShapeProperty();
			
			anchor1.getChildren().add(T);
			T.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {

					try {
						HotelCRUD C = new HotelCRUD();
						List<Hotel> ListD = new ArrayList<>();
						ListD = C.AfficherTousLesHotels(Integer.parseInt(T.getId()));
						panHotel.setVisible(true);
						name.setText(ListD.get(0).getNom());
						link.setText(ListD.get(0).getLink());
						city.setText(ListD.get(0).getCity());
						stars.setText(ListD.get(0).getNbEtoiles()+"");
						File file = new File(ListD.get(0).getImage());
						MapOptions mapOptions = new MapOptions();
			mapOptions.center(new LatLong(ListD.get(0).getGeolat(), ListD.get(0).getGeolong()))
					.mapType(MapTypeIdEnum.ROADMAP)
					.overviewMapControl(false)
					.panControl(false)
					.rotateControl(false)
					.scaleControl(false)
					.streetViewControl(true)
					.zoomControl(false)
					.zoom(14);

			map = mapView.createMap(mapOptions);
			LatLong Location = new LatLong(ListD.get(0).getGeolat(), ListD.get(0).getGeolong());
			MarkerOptions markerOptions1 = new MarkerOptions();
			markerOptions1.position(Location)
					.visible(true)
					.title(ListD.get(0).getNom());

			Marker joeSmithMarker = new Marker(markerOptions1);
			map.addMarker(joeSmithMarker);
						try {
							Image im = new Image(file.toURI().toURL().toExternalForm());
							minihotel.setImage(im);
							minihotel.setVisible(true);
						} catch (MalformedURLException ex) {
							
						}
							} catch (SQLException ex) {
						Logger.getLogger(DivertissementDisplay2Controller.class.getName()).log(Level.SEVERE, null, ex);
					}

				}

			});
		}
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

	@FXML
	private void close(MouseEvent event) {
		
		panHotel.setVisible(false);
	}

	@FXML
	private void browse(ActionEvent event) {
	}

	@FXML
	private void browse(MouseEvent event) {
	}
	
}
