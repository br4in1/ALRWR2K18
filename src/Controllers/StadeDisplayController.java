/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Stade;
import Services.StadeCRUD;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
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
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class StadeDisplayController implements Initializable, MapComponentInitializedListener {

	@FXML
	private ImageView image;
	@FXML
	private JFXTextField name;
	@FXML
	private JFXTextField capcity;
	@FXML
	private JFXTextField id;
	@FXML
	private JFXTextField city;
	@FXML
	private Button next;
	@FXML
	private Button previw;
	@FXML
	private JFXButton delete;
	@FXML
	private JFXTextField idT;
	@FXML
	private Pane panMap;
	private StadeCRUD C = new StadeCRUD();
	private Stade S;
	private List<Stade> ListStade = new ArrayList<>();
	Cloudinary cloudinary;
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
	@FXML
	private JFXTextField path;
	List<String> type;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		mapView.addMapInializedListener(this);
	}

	@Override
	public void mapInitialized() {
		type = new ArrayList<>();
		type.add("*.png");
		type.add("*.jpg");
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
			id.setText("" + ListStade.get(x).getId());
			path.setText(ListStade.get(x).getImage());
			File file = new File(ListStade.get(x).getImage());

			
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

	@FXML
	private void deleteHotel(KeyEvent event) {

	}

	@FXML
	private void deleteHotel(MouseEvent event) {

	}

	@FXML
	private void deletestade(ActionEvent event) throws SQLException {
		C.SupprimerStade(Integer.parseInt(id.getText()));
		id.setText("");
		image.setVisible(false);
		name.setText("");
		city.setText("");
		fromTextField.setText("");
		toTextField.setText("");
		capcity.setText("");
		int x = Integer.parseInt(i.getText());
		x = x - 1;
		i.setText("" + x);

		Alert alert = new Alert(Alert.AlertType.WARNING, "Stade Supprimé avec succés.");
		alert.show();

	}

	@FXML
	private void Updateing(MouseEvent event) {
	}

	@FXML
	private void saveupdate(ActionEvent event) throws SQLException, IOException {
		if ((name.getText() != "") && (fromTextField.getText() != "") && (toTextField.getText() != "") && (capcity.getText() != "") && (city.getText() != "") && (path.getText() != "")) {
			System.out.println(path.getText());
			File toUpload = new File(path.getText());
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());

			Stade s = new Stade(Integer.parseInt(id.getText()), name.getText(), Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), Integer.parseInt(capcity.getText()), (String) uploadResult.get("url"), city.getText());
			C.update(s);
			TextToSpeech tts = new TextToSpeech();
			tts.setVoice("dfki-poppy-hsmm");
			tts.speak("staduim updated with success", 2.0f, false, false);

		} else {
			TextToSpeech tts = new TextToSpeech();
			tts.setVoice("dfki-poppy-hsmm");
			tts.speak("Error while updating, reset your data", 2.0f, false, false);

		}
	}

	@FXML
	private void Reset(MouseEvent event) {
	}

	@FXML
	private void resetlocation(ActionEvent event) {

		init();
	}

	@FXML
	private void Browse(MouseEvent event) {
		FileChooser F = new FileChooser();
		F.getExtensionFilters().add(new FileChooser.ExtensionFilter("Images", type));
		File Fc = F.showOpenDialog(null);
		if (Fc != null) {
			path.setText(Fc.getAbsolutePath());
		}
		File file = new File(path.getText());

		try {
			Image im = new Image(file.toURI().toURL().toExternalForm());
			image.setImage(im);
			image.setVisible(true);
		} catch (MalformedURLException ex) {
		}
	}

}
