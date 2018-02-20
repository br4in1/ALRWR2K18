/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Gallery;
import Services.GalleryCrud;
import Utils.DataSource;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class DisplayIController implements Initializable {

	public ArrayList<Gallery> ran;
	@FXML
	private ImageView img_view;

	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<?, ?> iduser;
	@FXML
	private TableColumn<?, ?> lieu;
	@FXML
	private TableColumn<?, ?> description;
	@FXML
	private TableColumn<?, ?> url;
	@FXML
	private TableColumn<?, ?> etat;
	@FXML
	private TableView<Gallery> tableView;
	@FXML
	private JFXButton supprimer;
	@FXML
	private JFXButton modifier;
	@FXML
	private TextField Etat;
	@FXML
	private TextField Id;
	@FXML
	private TextField iD;
	@FXML
	private PieChart piechart2;
	@FXML
	private TableColumn<?, ?> ville;
	@FXML
	private JFXButton btn1;
	@FXML
	private JFXButton next;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	   afficher();
       changeStat();
	
	}
	public void changeStat()
		{
			GalleryCrud g = new GalleryCrud();
       int x = 0;
	   int y=0;
		try {
			x = g.returnEtat0();
			y = g.returnEtat1();
		} catch (SQLException ex) {
			Logger.getLogger(DisplayIController.class.getName()).log(Level.SEVERE, null, ex);
		}
        ObservableList <PieChart.Data> pieChartDatas = FXCollections.observableArrayList(
               new PieChart.Data("Nombre d'images approuvés "+"("+y+")",y) ,
                new PieChart.Data("Nombre d'images  non approuvés "+"("+x+")",x) 
        );
        piechart2.setData(pieChartDatas);
		}

	public void afficher() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		iduser.setCellValueFactory(new PropertyValueFactory<>("idUser"));
		ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
		lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		url.setCellValueFactory(new PropertyValueFactory<>("image"));
		etat.setCellValueFactory(new PropertyValueFactory<>("etat"));

		ObservableList<Gallery> ol = FXCollections.observableArrayList(GalleryCrud.DisplayAll());
		tableView.setItems(ol);
	}

	@FXML
	private void CLicked(MouseEvent event) throws MalformedURLException {
         GalleryCrud g1 = new GalleryCrud();
		Gallery g = (Gallery) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		String etat = g.getEtat();
		Etat.setText(etat);
		String id = Integer.toString(g.getId());
		Id.setText(id);
		String id1 = Integer.toString(g.getId());
		iD.setText(id1);
		
		// File file = new File(g.getImage());
		Image im = new Image(g.getImage());
		img_view.setImage(im);
        

	}

	@FXML
	private void suprimerI(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation suppression");
		alert.setHeaderText("voulez vous effacer cette image?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			String e = Id.getText();
			int r = Integer.parseInt(e);
			img_view.setImage(null);
			GalleryCrud s = new GalleryCrud();
			s.DeleteImage(r);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Delete succes!");
			alert.show();		
			afficher();
			changeStat();
		}
		String e = Id.getText();
		Notifications  notificationBuilder  = Notifications.create()
		   .title("Avvertissment")
		   .text("Votre photo de l'id "+e+" a été supprimer pour des raison de sécurité")
		   .graphic(null)
		   .hideAfter(Duration.seconds(15))
		   .position(Pos.TOP_CENTER)
		   .onAction(new EventHandler<ActionEvent>() {
	   @Override
	   public void handle(ActionEvent event) {
		   System.out.println("Clickeeeed");
	   }
   });
   notificationBuilder.showError();
   Id.setText("");
	}

	@FXML
	private void modifierI(ActionEvent event) throws SQLException {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation changment d'etat");
		alert.setHeaderText("voulez vous vraiment approuver cette photo?");
		Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK) {
        GalleryCrud g = new GalleryCrud();
		String e = iD.getText();
		int b = Integer.parseInt(e);
		 String i = Etat.getText();
        Gallery p = new Gallery(i);
        g.ModifyEtat(b,p);
        afficher();
		changeStat();
		}
	iD.setText("");
	Etat.setText("");
	}

	@FXML
	private void Actualiser(MouseEvent event) {
		afficher();
		changeStat();
	}

	@FXML
	private void Next(ActionEvent event) throws IOException {
		       
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/DisplayO.fxml"));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();

	}

	
}

	

