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
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class GuideAffichageMapController implements Initializable, MapComponentInitializedListener {

	
	
	private GoogleMap map;
	private GeocodingService geocodingService;
	private GoogleMapView mapView;
	@FXML
	private JFXTextField searchfield;
	@FXML
	private JFXTextField tofield;
	@FXML
	private JFXButton itinerraire;
	@FXML
	private JFXButton filter;
	 protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		mapView.addMapInializedListener(this);
		   to.bindBidirectional(tofield.textProperty());
        from.bindBidirectional(searchfield.textProperty());
	
	}	

	@Override
	public void mapInitialized() {

		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();
	
		 LatLong x = new LatLong(50.00,50.00);
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

		
	}

	@FXML
	private void itineraire(ActionEvent event) {
	}

	@FXML
	private void filterAction(ActionEvent event) {
	}

	 @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
	    directionsService.getRoute(request, (DirectionsServiceCallback) this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
	
}
