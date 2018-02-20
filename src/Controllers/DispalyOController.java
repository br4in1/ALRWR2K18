/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Gallery;
import Entities.Opinions;
import Services.GalleryCrud;
import Services.OpinionsCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class DispalyOController implements Initializable {

	@FXML
	private TableView<Opinions> table_view2;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> idUser;
	@FXML
	private TableColumn<?, ?> avis;
	@FXML
	private TableColumn<?, ?> notes;
	@FXML
	private JFXTextArea Avis;
	@FXML
	private TextField Id;
	@FXML
	private JFXButton supprimer;
	@FXML
	private LineChart<String, Integer> line;
	@FXML
	private JFXButton actualiser;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		affichage();
		

	
	}
	public void changeStat1()
		{
			XYChart.Series<String, Integer> series = new XYChart.Series<>();
		HashMap<Integer, String> data;

		try {
			data = OpinionsCrud.AfficherEtoiles();
			for (Entry<Integer, String> e : data.entrySet()) {
				series.getData().add(new XYChart.Data(String.valueOf(e.getKey()), Integer.parseInt(e.getValue())));
			}

			line.getData().addAll(series);
		} catch (SQLException ex) {
			Logger.getLogger(DispalyOController.class.getName()).log(Level.SEVERE, null, ex);
		}
		}

	public void affichage() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		idUser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
		avis.setCellValueFactory(new PropertyValueFactory<>("avis"));
		notes.setCellValueFactory(new PropertyValueFactory<>("nbreEtoiles"));

		ObservableList<Opinions> ol = FXCollections.observableArrayList(OpinionsCrud.DisplayAllOpinions());
		table_view2.setItems(ol);
	}

	@FXML
	private void click(MouseEvent event) {
		OpinionsCrud g1 = new OpinionsCrud();
		//List<String> Liste = g1.DisplayImageFromDB();

		Opinions o = table_view2.getSelectionModel().getSelectedItem();
		String avis = o.getAvis();
		Avis.setText(avis);
		String id = Integer.toString(o.getId());
		Id.setText(id);

	}

	@FXML
	private void suprimerO(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation suppression");
		alert.setHeaderText("voulez vous effacer cette avis ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String e = Id.getText();
			int r = Integer.parseInt(e);
			Avis.setText(null);
			OpinionsCrud s = new OpinionsCrud();
			s.DeleteOpinion(r);
			alert.setTitle("Information ");
			alert.setHeaderText(null);
			alert.setContentText("Suppression effectué !");
			alert.show();			
			affichage();
			changeStat1();
		}
		Id.setText(null);
	}

	@FXML
	private void Actu(ActionEvent event) {
		    affichage();
			changeStat1();
	}

}
