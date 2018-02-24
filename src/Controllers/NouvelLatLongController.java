/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.HotelCRUD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sun.font.TrueTypeFont;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class NouvelLatLongController implements Initializable, MapComponentInitializedListener {
	
	private GoogleMap map;
	private GeocodingService geocodingService;
	@FXML
	private GoogleMapView mapView;
	@FXML
	private JFXTextField fromTextField;
	@FXML
	private JFXTextField toTextField;
	@FXML
	private JFXButton enregistrer;
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
			mapView.addMapInializedListener(this);
		
	}	

	@Override
	public void mapInitialized() {

		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
		HotelCRUD C = new HotelCRUD();
		List<Hotel> p = null ;
		try {
			p = C.AfficherTousLesHotels("test");
		} catch (SQLException ex) {
			Logger.getLogger(NouvelLatLongController.class.getName()).log(Level.SEVERE, null, ex);
		}
		 LatLong x = new LatLong(p.get(0).getGeolat(),p.get(0).getGeolong());
		 System.out.println(x);
		mapOptions.center(x)
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.zoom(12);
		
		map = mapView.createMap(mapOptions);

		map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
			LatLong latLong = event.getLatLong();
			fromTextField.setText(String.valueOf(latLong.getLatitude()));
			toTextField.setText(String.valueOf(latLong.getLongitude()));
		});
		
		
		
				List<Hotel> Lis = null;
		try {
			Lis = C.AfficherTousLesHotels("test");
		} catch (SQLException ex) {
			Logger.getLogger(NouvelLatLongController.class.getName()).log(Level.SEVERE, null, ex);
		}
									for (int i = 0; i < Lis.size(); i++) {
										LatLong Location = new LatLong(Lis.get(i).getGeolat(), Lis.get(i).getGeolong());
										MarkerOptions markerOptions1 = new MarkerOptions();
										markerOptions1.position(Location)
												  .visible(true)
												 .title(Lis.get(i).getNom());
										Marker joeSmithMarker = new Marker(markerOptions1);
										map.addMarker(joeSmithMarker);
									}

	}

	@FXML
	private void enregistrer(MouseEvent event) throws SQLException {
		HotelCRUD C = new HotelCRUD();
			Hotel p = new Hotel(5, "test", Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), 5, "no", "no", "no");
			C.updateTest(p);
			   Stage stage = (Stage) enregistrer.getScene().getWindow();
    // do what you have to do
    stage.close();
	}

}
