package com.mycompany.sofienepidev;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
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
import com.mycompany.Entities.Hotel;
import com.mycompany.Services.HotelCRUD;
import com.sun.prism.PhongMaterial.MapType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.input.ZoomEvent;
import java.text.DecimalFormat;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class FXMLController implements Initializable , MapComponentInitializedListener, DirectionsServiceCallback{
    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    protected DirectionsRenderer directionsRenderer = null;
    
    private Label label;
    @FXML
    private GoogleMapView mapView;
    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private Button clearButton;
    private GoogleMap map;
    ObservableList<String> TypeDate = FXCollections.observableArrayList("Hotel","Stade","Divertissement","Event") ; 
    
     private DecimalFormat formatter = new DecimalFormat("###.00000");
    @FXML
    private TextField searchField;
      private GeocodingService geocodingService;

     private StringProperty address = new SimpleStringProperty();
    @FXML
    private ComboBox<String> comboboxType;
    @FXML
    private Button enregistrer;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
        
        address.bind(searchField.textProperty());
        comboboxType.setValue("Hotel");
        comboboxType.setItems(TypeDate);
    }    

    @FXML
    private void toTextFieldAction(ActionEvent event) {
        DirectionsRequest request = new DirectionsRequest(from.get(), to.get(), TravelModes.DRIVING);
        directionsRenderer = new DirectionsRenderer(true, mapView.getMap(), directionsPane);
        directionsService.getRoute(request, this, directionsRenderer);
    }
    
    

    @FXML
    private void clearDirections(ActionEvent event) {
        directionsRenderer.clearDirections();
    }

    @Override
    public void mapInitialized() {
         geocodingService = new GeocodingService();
     
       LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
        
        
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

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);
        
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);
        
        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);
        
        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);
        
        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);
        
        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);
        
        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
      
        
            map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
              
            LatLong latLong = event.getLatLong();
            fromTextField.setText(String.valueOf(latLong.getLatitude()));
            toTextField.setText(String.valueOf(latLong.getLongitude()));
              MarkerOptions markerposi = new MarkerOptions();
          Marker positi = new Marker(markerposi);
           
        markerposi.position(latLong);
       
        map.addMarker( positi );
        
        });
            
           
           
                    }
   
    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  


   

    private void rechercheplace(ActionEvent event) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
            .apiKey("AIzaSyDoju9IuTy8Jn9LVhD5RMWuFbwMV4RF6e8")
            .build();
        GeocodingResult[] results =  GeocodingApi.geocode(context,
            searchField.getText()).await();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(results[0].addressComponents));
       
        geocodingService.geocode(address.get(), new GeocodingServiceCallback() {
            public void geocodedResultsReceived(GeocodingResult[] results, GeocoderStatus status) {
               LatLong latLong = null;
            
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].geometry.location.lat , results[0].geometry.location.lng);
            } else {latLong = new LatLong(results[0].geometry.location.lat , results[0].geometry.location.lng); }
            Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                System.out.println("lat lng"+results[0].geometry.location.lat + results[0].geometry.location.lng);
            map.setCenter(latLong);
            }

            @Override
            public void geocodedResultsReceived(com.lynden.gmapsfx.service.geocoding.GeocodingResult[] grs, GeocoderStatus gs) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
         
         
         
    }

    private void alo(DragEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
    }

    @FXML
    private void zoomTest2(MouseEvent event) {
    }

    @FXML
    private void zoomTest(TouchEvent event) {
    }

    @FXML
    private void aalo(KeyEvent event) {if (event.getCode().equals(KeyCode.ENTER))
            {
               /* System.out.println(searchField.getText());
                 geocodingService.geocode(searchField.getText(), new GeocodingServiceCallback() {
            public void geocodedResultsReceived(GeocodingResult[] results, GeocoderStatus status) {
               LatLong latLong = null;
             System.out.println("1");
            if( status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                return;
            } else if( results.length > 1 ) {
                System.out.println("2");
                Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                alert.show();
                latLong = new LatLong(results[0].geometry.location.lat , results[0].geometry.location.lng);
            } else {
                System.out.println("3");
                latLong = new LatLong(results[0].geometry.location.lat , results[0].geometry.location.lng); }
            
                System.out.println("lat lng"+results[0].geometry.location.lat + results[0].geometry.location.lng);
            map.setCenter(latLong);
            }
        });
                */
            }}

    @FXML
    private void Enregistrer(ActionEvent event) throws IOException, SQLException {
        if("Hotel".equals(comboboxType.getValue()))
        {
            HotelCRUD C = new HotelCRUD();
            Hotel p = new Hotel(5, "test", Double.parseDouble(fromTextField.getText()), Double.parseDouble(toTextField.getText()), 5, "no", "no", "no");
            C.ajouterHotel(p);
            try {
            String x = toTextField.getText() ;
             System.out.println(Double.parseDouble(x));
            }
             catch (NumberFormatException e)
             {
       System.out.println("not a number"); }
               
        Parent Hotel_Page = FXMLLoader.load(getClass().getResource("/fxml/Ajout_Hotel.fxml"));
        Scene scene = new Scene(Hotel_Page);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene.getStylesheets().add("/styles/Styles.css");
        app_stage.setTitle("Enregistrement d'Hotel");
        app_stage.setScene(scene);
        app_stage.show();
    }
        }
}

    

  

