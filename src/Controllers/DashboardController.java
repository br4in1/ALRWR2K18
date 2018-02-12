/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.lynden.gmapsfx.GoogleMapView;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class DashboardController implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private GoogleMapView mapView;
    private GoogleMap map;
    private GeocodingService geocodingService;
	@FXML
	private Pane paneMere;

    /**
     * @FXML private Pane paneMere; Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox pane = FXMLLoader.load(getClass().getResource("/Views/navbar.fxml"));
            VBox gstArticleNavBar = FXMLLoader.load(getClass().getResource("/Views/GestionArticlesNavbar.fxml"));

            drawer.setSidePane(pane);
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
            if (drawer.isHidden()) {
                for (Node node : pane.getChildren()) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                        switch (node.getAccessibleText()) {
                            case "gestionArticle":
                                drawer.setSidePane(gstArticleNavBar);
                                break;
                            //TO DO: ajouter les autre case des button modules 1-4
                        }
                    });
                }
                for (Node node : gstArticleNavBar.getChildren()) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                        switch (node.getAccessibleText()) {
                            case "menuPrincipale":
                                drawer.setSidePane(pane);
                                break;
                            case "ajouterArticle":
                                System.out.println("ajotuer Article is clicked");
                                break;
                            case "consulterArticles":
                                System.out.println("consulter aarticles is clicked");
                                break;
                        }
                    });
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapView.addMapInializedListener(this);
		
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);
		//paneMere.getChildren().add(mapView);
        //Add markers to the map
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes");

    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        //fredWilkeInfoWindow.open(map, joeSmithMarker);

    }
}
