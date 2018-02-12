/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Gallery;
import Services.GalleryCrud;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AfficherImageController implements Initializable {

    @FXML
    private ImageView img;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GalleryCrud g = new GalleryCrud();
       List<String> Liste = g.AfficherImageFromDB();
        File file = new File(Liste.get(0));
        
        try {
            Image im = new Image(file.toURI().toURL().toExternalForm());
            img.setImage(im);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherImageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
    }    
    
    
}
