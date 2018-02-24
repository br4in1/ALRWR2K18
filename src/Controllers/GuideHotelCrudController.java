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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.management.Notification;
import static org.apache.james.mime4j.field.Fields.date;
import sun.java2d.pipe.AlphaColorPipe;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class GuideHotelCrudController implements Initializable, MapComponentInitializedListener {

	/**
	 * Initializes the controller class.
	 */
	private StringProperty address = new SimpleStringProperty();

	private GoogleMap map;
	private GeocodingService geocodingService;
	ObservableList<String> TypeDate = FXCollections.observableArrayList("Hotel", "Stade", "Divertissement", "Event");

	@FXML
	private GoogleMapView mapView;
	@FXML
	private JFXTextField searchField;
	@FXML
	private JFXButton filtrer;
	@FXML
	private JFXComboBox<String> comboboxType;
	@FXML
	private JFXTextField fromTextField;
	@FXML
	private JFXTextField toTextField;
	@FXML
	private JFXButton enregistrer;
	@FXML
	private JFXDrawer drawer;
	int x = 0;
	int y = 0;
	int z = 0;
	private MarkerOptions markerOptions1;
	private Marker joeSmithMarker;
	private TextToSpeech tts = new TextToSpeech();
	private float VoiceVolume;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Marker> col = new ArrayList<>();
		List<Marker> stad = new ArrayList<>();
		List<Marker> diver = new ArrayList<>();
		// TODO
		mapView.addMapInializedListener(this);

		comboboxType.setValue("Hotel");
		comboboxType.setItems(TypeDate);

		VBox box = null;

		try {
			box = FXMLLoader.load(getClass().getResource("/Views/MapFiler.fxml"));
		} catch (IOException ex) {
			Logger.getLogger(GuideHotelCrudController.class.getName()).log(Level.SEVERE, null, ex);
		}
		drawer.setSidePane(box);
		for (Node node : box.getChildren()) {
			node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
				switch (node.getId()) {
					case "valider":
						break;
					case "hotel":
						if (x == 0) {
							try {
								HotelCRUD C = new HotelCRUD();
								List<Hotel> Lis = C.AfficherTousLesHotels();

								for (int i = 0; i < Lis.size(); i++) {
									LatLong Location = new LatLong(Lis.get(i).getGeolat(), Lis.get(i).getGeolong());
									MarkerOptions markerOptions1 = new MarkerOptions();
									markerOptions1.position(Location)
											//.icon(new File("/assets/hotel.png").toURI().toURL().toExternalForm())

											.visible(true)
											.title(Lis.get(i).getNom());
									Marker joeSmithMarker = new Marker(markerOptions1);
									col.add(joeSmithMarker);

								}
								map.addMarkers(col);

							} catch (SQLException ex) {
								Logger.getLogger(GuideHotelCrudController.class.getName()).log(Level.SEVERE, null, ex);
							}
							//map.addMarkers(col);
							x = 1;
						} else {
							x = 0;
							map.removeMarkers(col);
						}

						break;

					case "stades":

						if (y == 0) {
							try {
								StadeCRUD C = new StadeCRUD();
								List<Stade> LisStade = C.AfficherTousLesStades();

								for (int i = 0; i < LisStade.size(); i++) {
									LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
									MarkerOptions markerOptions1 = new MarkerOptions();
									markerOptions1.position(Location)
											.visible(true)
											.title(LisStade.get(i).getNom());

									Marker joeSmithMarker = new Marker(markerOptions1);
									stad.add(joeSmithMarker);
								}
								map.addMarkers(stad);
							} catch (SQLException ex) {
								Logger.getLogger(GuideHotelCrudController.class.getName()).log(Level.SEVERE, null, ex);
							}
							//map.addMarkers(col);
							y = 1;
						} else {
							y = 0;
							map.removeMarkers(stad);
						}

						break;

					case "divertissement":

						if (z == 0) {
							try {
								DivertissmentCRUD C = new DivertissmentCRUD();
								List<Divertissement> LisStade = C.AfficherTousdivertissement();

								for (int i = 0; i < LisStade.size(); i++) {

									LatLong Location = new LatLong(LisStade.get(i).getGeolat(), LisStade.get(i).getGeolong());
									MarkerOptions markerOptions1 = new MarkerOptions();
									markerOptions1.position(Location)
											.visible(true)
											.title(LisStade.get(i).getNom());

									Marker joeSmithMarker = new Marker(markerOptions1);
									diver.add(joeSmithMarker);

								}
								map.addMarkers(diver);

							} catch (SQLException ex) {
								Logger.getLogger(GuideHotelCrudController.class.getName()).log(Level.SEVERE, null, ex);
							}
							//map.addMarkers(col);
							z = 1;
						} else {
							z = 0;
							map.removeMarkers(diver);
						}

						break;
					case "Male":
						tts.setVoice("cmu-rms-hsmm");
						VoiceVolume = 2;
						break;
					case "Female":

						tts.setVoice("dfki-poppy-hsmm");
						VoiceVolume = 2;
						break;
					case "None":
						VoiceVolume = 0;
						break;

				}
			});
		}

	}

	@Override
	public void mapInitialized() {
		geocodingService = new GeocodingService();
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(55.7107770438838, 37.592959000000064))
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

	@FXML
	private void Filtrer(MouseEvent event) {
		if (drawer.isHidden()) {
			drawer.open();
		} else {
			drawer.close();
		}

	}

	@FXML
	private void Chercher(KeyEvent event) throws SQLException {
		List<Hotel> ListHotel = new ArrayList<>();
		HotelCRUD C = new HotelCRUD();
		List<Stade> ListStade = new ArrayList<>();
		StadeCRUD S = new StadeCRUD();

		if (event.getCode().equals(KeyCode.ENTER)) {
			if (joeSmithMarker != null) {

				map.removeMarker(joeSmithMarker);
			}

			tts.speak(searchField.getText(), VoiceVolume, false, false);
			ListHotel = C.AfficherTousLesHotels(searchField.getText());
			ListStade = S.AfficherTousLesStade(searchField.getText());
			if (ListHotel.size() != 0) {
				MapOptions mapOptions = new MapOptions();
				mapOptions.center(new LatLong(ListHotel.get(0).getGeolat(), ListHotel.get(0).getGeolong()))
						.mapType(MapTypeIdEnum.ROADMAP)
						.overviewMapControl(false)
						.panControl(false)
						.rotateControl(false)
						.scaleControl(false)
						.streetViewControl(false)
						.zoomControl(false)
						.zoom(6);

				map = mapView.createMap(mapOptions);
				LatLong Location = new LatLong(ListHotel.get(0).getGeolat(), ListHotel.get(0).getGeolong());
				markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						.visible(true)
						.title("Hotel :" + ListHotel.get(0).getNom());

				joeSmithMarker = new Marker(markerOptions1);
				map.addMarker(joeSmithMarker);

			} else if (ListStade.size() != 0) {
				MapOptions mapOptions = new MapOptions();
				mapOptions.center(new LatLong(ListStade.get(0).getGeolat(), ListStade.get(0).getGeolong()))
						.mapType(MapTypeIdEnum.ROADMAP)
						.overviewMapControl(false)
						.panControl(false)
						.rotateControl(false)
						.scaleControl(false)
						.streetViewControl(false)
						.zoomControl(false)
						.zoom(6);

				map = mapView.createMap(mapOptions);
				LatLong Location = new LatLong(ListStade.get(0).getGeolat(), ListStade.get(0).getGeolong());
				markerOptions1 = new MarkerOptions();
				markerOptions1.position(Location)
						.visible(true)
						.title("Stade : " + ListStade.get(0).getNom());

				joeSmithMarker = new Marker(markerOptions1);
				map.addMarker(joeSmithMarker);

			} else {
				geocodingService.geocode(searchField.getText(), new GeocodingServiceCallback() {
					@Override
					public void geocodedResultsReceived(com.lynden.gmapsfx.service.geocoding.GeocodingResult[] grs, GeocoderStatus gs) {
						LatLong latLong = null;
						if (gs == GeocoderStatus.ZERO_RESULTS) {
							Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
							alert.show();
							return;
						} else if (grs.length > 1) {
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
							latLong = new LatLong(grs[0].getGeometry().getLocation().getLatitude(), grs[0].getGeometry().getLocation().getLongitude());
							markerOptions1 = new MarkerOptions();
							markerOptions1.position(latLong)
									.visible(true)
									.title("Adresse :" + grs[0].getFormattedAddress());

							joeSmithMarker = new Marker(markerOptions1);
							map.addMarker(joeSmithMarker);

						}

						fromTextField.setText(String.valueOf(grs[0].getGeometry().getLocation().getLatitude()));
						toTextField.setText(String.valueOf(grs[0].getGeometry().getLocation().getLongitude()));
						map.setCenter(latLong);
					}
				});

			}
			map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event2) -> {
				LatLong latLong = event2.getLatLong();
				fromTextField.setText(String.valueOf(latLong.getLatitude()));
				toTextField.setText(String.valueOf(latLong.getLongitude()));

			});
		}
	}

	@FXML
	private void EnregistrerObjet(MouseEvent event) throws SQLException, IOException {
		if (("Hotel".equals(comboboxType.getValue())) && (!"".equals(fromTextField.getText()))) {
			HotelCRUD C = new HotelCRUD();
			Hotel p = new Hotel(5, "test", Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), 5, "no", "no", "no");
			C.updateTest(p);
			try {
				String x = toTextField.getText();
			} catch (NumberFormatException e) {
			}

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddHotel.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} else if (("Stade".equals(comboboxType.getValue())) && (!"".equals(fromTextField.getText()))) {
			StadeCRUD C = new StadeCRUD();
			Stade p = new Stade(5, "test", Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), 5, "no", "no");
			C.updateTest(p);
			try {
				String x = toTextField.getText();
			} catch (NumberFormatException e) {
			}
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddStade.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} else if (("Divertissement".equals(comboboxType.getValue())) && (!"".equals(fromTextField.getText()))) {
			DivertissmentCRUD C = new DivertissmentCRUD();
			Date date = new Date(2008, 5, 5);
			Divertissement p = new Divertissement(5, "test", Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), Timestamp.valueOf("2016-11-16 06:55:40.11"), Timestamp.valueOf("2016-11-16 06:55:40.11"), "5", "no", "no");
			C.updateTest(p);
			try {
				String x = toTextField.getText();
			} catch (NumberFormatException e) {
			}

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddDivertissement.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		}

	}

}
