/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Hotel;
import Services.HotelCRUD;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class HotelDisplayController implements Initializable {

	Cloudinary cloudinary;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> nom;
	@FXML
	private TableColumn<?, ?> Nb;
	@FXML
	private TableColumn<?, ?> city;
	@FXML
	private TableColumn<?, ?> link;
	@FXML
	private TableColumn<?, ?> latitude;
	@FXML
	private TableColumn<?, ?> longtitude;
	@FXML
	private TableColumn<?, ?> image;
	@FXML
	private TableView<Hotel> table;
	@FXML
	private JFXTextField nomT;
	@FXML
	private JFXTextField nb_etoilesT;
	@FXML
	private JFXTextField cityT;
	@FXML
	private JFXTextField latitudeT;
	@FXML
	private JFXTextField longtitudeT;
	@FXML
	private JFXTextField imageT;
	@FXML
	private JFXTextField linkT;
	@FXML
	private JFXTextField idT;
	private PieChart HotelChart;
	private ObservableList<PieChart.Data> LisChart ; 
	@FXML
	private JFXButton delete;
	@FXML
	private ImageView photo;
	@FXML
	private ImageView testimage;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		afficher();
	}	
	public  void actualiserChart() throws SQLException {
	
		HotelCRUD C = new HotelCRUD();
	//	LisChart.addAll(C.StaticHotel());
		//HotelChart.setData(LisChart);
		//System.out.println(LisChart.toString());
	}
	
    
	public void afficher() 
	{
		
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
		image.setCellValueFactory(new PropertyValueFactory<>("image"));
		link.setCellValueFactory(new PropertyValueFactory<>("Link"));
		latitude.setCellValueFactory(new PropertyValueFactory<>("geolat"));
		longtitude.setCellValueFactory(new PropertyValueFactory<>("geolong"));
		Nb.setCellValueFactory(new PropertyValueFactory<>("nbEtoiles"));
		city.setCellValueFactory(new PropertyValueFactory<>("City"));
		
		HotelCRUD C = new HotelCRUD();
		ObservableList<Hotel> ol = null;
		try {
			ol = FXCollections.observableArrayList(C.AfficherTousLesHotels());
		} catch (SQLException ex) {
			Logger.getLogger(HotelDisplayController.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		table.setItems(ol);
	}

	@FXML
	private void selectionHotel(MouseEvent event) {
		
		Hotel g = (Hotel) table.getItems().get(table.getSelectionModel().getSelectedIndex());
		System.out.println(g.toString());
		idT.setText(""+g.getId());
		nomT.setText(g.getNom());
		imageT.setText(g.getImage());
		linkT.setText(g.getLink());
		latitudeT.setText(""+g.getGeolat());
		longtitudeT.setText(""+g.getGeolong());
		nb_etoilesT.setText(""+g.getNbEtoiles());
		cityT.setText(g.getCity());
		System.out.println(g.getImage());
		
		testimage.setImage(new Image(imageT.getText()));
		testimage.setVisible(true);
	}


	@FXML
	private void Updateing(MouseEvent event) throws SQLException, IOException {
		Cloudinary cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
		File toUpload = new File(imageT.getText());
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
		Hotel p = new Hotel(Integer.parseInt(idT.getText()), nomT.getText(), Double.parseDouble(latitudeT.getText()), Double.parseDouble(longtitudeT.getText()),Integer.parseInt( nb_etoilesT.getText()), linkT.getText(), (String) uploadResult.get("url"), cityT.getText());
		HotelCRUD c = new HotelCRUD();
		c.update(p);
		System.out.println("bon");
		afficher();
		
	}

	@FXML
	private void deleteHotel(MouseEvent event) throws SQLException {
		System.out.println("alo1");
		HotelCRUD c = new HotelCRUD();
		c.supprimerHotel(Integer.parseInt(idT.getText()));
		System.out.println("alo2");
		afficher();
		
	}

	@FXML
	private void Reset(MouseEvent event) throws IOException, SQLException {
		HotelCRUD C = new HotelCRUD();
			Hotel p = new Hotel(5, "test", Double.parseDouble(latitudeT.getText()), Double.parseDouble(longtitudeT.getText()), 5, "no", "no", "no");
			C.updateTest(p);
		latitudeT.setText("");
		longtitudeT.setText("");
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/NouvelLatLong.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
	}

	@FXML
	private void reload(MouseEvent event) throws SQLException {
		HotelCRUD C = new HotelCRUD();
		List<Hotel> p = null ;
		p = C.AfficherTousLesHotels("test");
		latitudeT.setText(""+p.get(0).getGeolat());
		longtitudeT.setText(""+p.get(0).getGeolong());
	}

	private void actualiser(ActionEvent event) throws SQLException {
		//actualiserChart();
	}

	@FXML
	private void deleteHotel(KeyEvent event) {
	}


	
	
}
