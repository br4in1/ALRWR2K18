/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Divertissement;
import Entities.Hotel;
import Entities.Stade;
import Services.DivertissmentCRUD;
import Services.HotelCRUD;
import Services.StadeCRUD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
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
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class GuideAffichageMapController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

	private GoogleMap map;
	private GeocodingService geocodingService;
	@FXML
	private GoogleMapView mapView;
	@FXML
	private JFXTextField searchfield;
	@FXML
	private JFXTextField tofield;
	protected DirectionsService directionsService;
	protected DirectionsPane directionsPane;

	protected StringProperty from = new SimpleStringProperty();
	protected StringProperty to = new SimpleStringProperty();
	@FXML
	private JFXCheckBox divertissments;
	@FXML
	private FlowPane mainMap;
	@FXML
	private Pane panMap;
	@FXML
	private JFXCheckBox staduims;
	@FXML
	private JFXCheckBox hotel;
	List<Marker> col = new ArrayList<>();
	List<Marker> stad = new ArrayList<>();
	List<Marker> diver = new ArrayList<>();
	ObservableList<String> TypeVo = FXCollections.observableArrayList("Male", "Female", "None");
	ObservableList<String>LesStade = FXCollections.observableArrayList("None");
	private TextToSpeech tts = new TextToSpeech();
	private float VoiceVolume;
	@FXML
	private JFXComboBox<String> choiceCombo;
	private MarkerOptions markerOptions1;
	private Marker joeSmithMarker;
	@FXML
	private Pane panFiltre;
	@FXML
	private JFXComboBox<String> combobocStades;
	@FXML
	private JFXSlider slider;
	@FXML
	private JFXTextField kilometre;
	private LatLong latLongStadePrincipale;
	/**
	 * Initializes the controller class.
	 */
	@FXML
	private void toTextFieldAction(ActionEvent event) {
		if (!"".equals(searchfield.getText()) && (!"".equals(tofield.getText()))) {
			System.out.println(from.get() + to.get());

			tts.speak("going from " + from.get() + " to " + to.get(), VoiceVolume, false, false);
			DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
			directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));

		} else {
			tts.speak("enter valid data", VoiceVolume, false, false);
		}

	}

	@Override
	public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		StadeCRUD C = new StadeCRUD();
			List<Stade> LisStade = new ArrayList<>();
		try {
			LisStade = C.AfficherTousLesStades();
		} catch (SQLException ex) {
			Logger.getLogger(GuideAffichageMapController.class.getName()).log(Level.SEVERE, null, ex);
		}

			for (int i = 0; i < LisStade.size(); i++) {
				LesStade.add(LisStade.get(i).getNom());
			}
		combobocStades.setItems(LesStade);
		combobocStades.setValue("None");
		choiceCombo.setValue("None");
		choiceCombo.setItems(TypeVo);
		mapView.addMapInializedListener(this);
		to.bindBidirectional(tofield.textProperty());
		from.bindBidirectional(searchfield.textProperty());
		panFiltre.setVisible(false);
		slider.setValue(0);
		kilometre.setText(0+"");
		
	}

	@Override
	public void mapInitialized() {

		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();

		LatLong x = new LatLong(55.759336, 37.629629);
		System.out.println(x);
		mapOptions.center(x)
				.mapType(MapTypeIdEnum.ROADMAP)
				.overviewMapControl(false)
				.panControl(false)
				.rotateControl(false)
				.scaleControl(false)
				.streetViewControl(false)
				.zoomControl(false)
				.zoom(10);

		map = mapView.createMap(mapOptions);
		directionsService = new DirectionsService();
		directionsPane = mapView.getDirec();
		System.out.println(mapView.getDirec().toString());

	}

	@FXML
	private void CocherDivertissments(ActionEvent event) throws SQLException {
		if (divertissments.isSelected()) {
			DivertissmentCRUD C = new DivertissmentCRUD();
			List<Divertissement> LisStade = C.AfficherTousdivertissement();

			for (int i = 0; i < LisStade.size(); i++) {
				System.out.println("slide"+slider.getValue());
				if(slider.getValue()!=0)
				{	double x = distance(latLongStadePrincipale.getLatitude(), latLongStadePrincipale.getLongitude(), LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong(), 'K');
				if(slider.getValue()>x)
				{	System.out.println(x);
					LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/resto.png", "png"))
						.title(LisStade.get(i).getNom());

				Marker joeSmithMarker = new Marker(markerOptions1);
				diver.add(joeSmithMarker);
				}
			}
				else
				{
					LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/resto.png", "png"))
						.title(LisStade.get(i).getNom());

				Marker joeSmithMarker = new Marker(markerOptions1);
				diver.add(joeSmithMarker);
				}
			}
			map.addMarkers(diver);
		} else {
			map.removeMarkers(diver);
			diver.clear();
		}
	}

	@FXML
	private void CocherStade(ActionEvent event) throws SQLException {
		if (staduims.isSelected()) {
			StadeCRUD C = new StadeCRUD();
			List<Stade> LisStade = C.AfficherTousLesStades();
			
			for (int i = 0; i < LisStade.size(); i++) {
				if(slider.getValue()!=0){
				double x = distance(latLongStadePrincipale.getLatitude(), latLongStadePrincipale.getLongitude(), LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong(), 'K');
				if(slider.getValue()>x)
				{
				LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/stade.png", "png"))
						.title(LisStade.get(i).getNom());

				Marker joeSmithMarker = new Marker(markerOptions1);
				stad.add(joeSmithMarker);
			}
				
			}
				else {
				LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/stade.png", "png"))
						.title(LisStade.get(i).getNom());

				Marker joeSmithMarker = new Marker(markerOptions1);
				stad.add(joeSmithMarker);
				}
			}
			map.addMarkers(stad);
		} else {
			map.removeMarkers(stad);
			stad.clear();
		}
	}

	@FXML
	private void cocherhotel(ActionEvent event) throws SQLException {
		if (hotel.isSelected()) {
			HotelCRUD C = new HotelCRUD();
			List<Hotel> Lis = C.AfficherTousLesHotels();

			for (int i = 0; i < Lis.size(); i++) {
				if(slider.getValue()!=0){
				double x = distance(latLongStadePrincipale.getLatitude(), latLongStadePrincipale.getLongitude(), Lis.get(i).getGeolat(), Lis.get(i).getGeolong(), 'K');
				if(slider.getValue()>x)
				{
				LatLong Location = new LatLong(Lis.get(i).getGeolat(), Lis.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.icon(new File("/assets/hotel.png").toURI().toURL().toExternalForm())

						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/hotel.png", "png"))
						.title(Lis.get(i).getNom()+"distance :"+x);
				Marker joeSmithMarker = new Marker(markerOptions1);
				col.add(joeSmithMarker);
				System.out.println("-yes");

			}
			}
				else {
				LatLong Location = new LatLong(Lis.get(i).getGeolat(), Lis.get(i).getGeolong());
				MarkerOptions markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						//.icon(new File("/assets/hotel.png").toURI().toURL().toExternalForm())

						//.visible(true)
						//.icon(MarkerImageFactory.createMarkerImage("file:///C:/Users/hotel.png", "png"))
						.title(Lis.get(i).getNom());
				Marker joeSmithMarker = new Marker(markerOptions1);
				col.add(joeSmithMarker);
				System.out.println("-yes");
				}
			
			}
			map.addMarkers(col);
		} else {
			map.removeMarkers(col);
			col.clear();
		}
	}

	@FXML
	private void VoiceChanged(ActionEvent event) {
		if ("None".equals(choiceCombo.getValue())) {
			VoiceVolume = 0;
		} else if ("Male".equals(choiceCombo.getValue())) {
			tts.setVoice("cmu-rms-hsmm");
			VoiceVolume = 2;
		} else {
			tts.setVoice("dfki-poppy-hsmm");
			VoiceVolume = 2;
		}
	}

	@FXML
	private void Search(KeyEvent event) {
		if ((event.getCode().equals(KeyCode.ENTER)) && (!"".equals(searchfield.getText()))) {
			if (joeSmithMarker != null) {

				map.removeMarker(joeSmithMarker);
			}
			tts.speak("Searching " + searchfield.getText(), VoiceVolume, false, false);
			mapInitialized();
			geocodingService.geocode(searchfield.getText(), new GeocodingServiceCallback() {
				@Override
				public void geocodedResultsReceived(com.lynden.gmapsfx.service.geocoding.GeocodingResult[] grs, GeocoderStatus gs) {
					LatLong latLong = null;
					System.out.println("1");
					if (gs == GeocoderStatus.ZERO_RESULTS) {
						Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
						alert.show();
						return;
					} else if (grs.length > 1) {

						System.out.println("2");
						Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
						alert.show();
						latLong = new LatLong(grs[0].getGeometry().getLocation().getLatitude(), grs[0].getGeometry().getLocation().getLongitude());
						markerOptions1 = new MarkerOptions();
						markerOptions1.position(latLong)
								.visible(true)
								.title("Adresse :" + grs[0].getFormattedAddress());

						joeSmithMarker = new Marker(markerOptions1);
						map.addMarker(joeSmithMarker);
					} else {

						System.out.println("3");
						latLong = new LatLong(grs[0].getGeometry().getLocation().getLatitude(), grs[0].getGeometry().getLocation().getLongitude());
						markerOptions1 = new MarkerOptions();
						markerOptions1.position(latLong)
								.visible(true)
								.title("Adresse :" + grs[0].getFormattedAddress());

						joeSmithMarker = new Marker(markerOptions1);
						map.addMarker(joeSmithMarker);

					}

					map.setCenter(latLong);
				}
			});

		} else {
			tts.speak("please enter a valid place", VoiceVolume, false, false);

		}

	}
	   private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
      double theta = lon1 - lon2;
      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
      dist = Math.acos(dist);
      dist = rad2deg(dist);
      dist = dist * 60 * 1.1515;
      if (unit == 'K') {
        dist = dist * 1.609344;
      } else if (unit == 'N') {
        dist = dist * 0.8684;
        }
      return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
      return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
      return (rad * 180.0 / Math.PI);
    }

	@FXML
	private void localiserStade(ActionEvent event) {
		if (!"None".equals(combobocStades.getValue()))
		{if (joeSmithMarker != null) {

				map.removeMarker(joeSmithMarker);
			}
			StadeCRUD C = new StadeCRUD();
			List<Stade> LisStade = new ArrayList<>();
		try {
			LisStade = C.AfficherTousLesStade(combobocStades.getValue());
		} catch (SQLException ex) {
			Logger.getLogger(GuideAffichageMapController.class.getName()).log(Level.SEVERE, null, ex);
		}
		 latLongStadePrincipale = new LatLong(LisStade.get(0).getGeolat(),LisStade.get(0).getGeolong());
						markerOptions1 = new MarkerOptions();
						markerOptions1.position(latLongStadePrincipale)
								.visible(true)
								.title("Stade Name :" + LisStade.get(0).getNom());

						joeSmithMarker = new Marker(markerOptions1);
						map.addMarker(joeSmithMarker);
						
					map.setCenter(latLongStadePrincipale);
	}
		else {slider.setValue(0);
		 latLongStadePrincipale = new LatLong(0,0);
		
		}
		
	}

	@FXML
	private void kilometrageChanged(MouseEvent event) {
		kilometre.setText(slider.getValue()+"");
	}

	@FXML
	private void afficherFiltre(MouseEvent event) {
		if(panFiltre.isVisible())
			panFiltre.setVisible(false);
		else panFiltre.setVisible(true);
	}
}
