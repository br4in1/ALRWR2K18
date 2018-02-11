/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class DashboardController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Pane mainField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox pane = FXMLLoader.load(getClass().getResource("/Views/navbar.fxml"));
            VBox gstArticleNavBar = FXMLLoader.load(getClass().getResource("/Views/GestionArticlesNavbar.fxml"));
            ScrollPane ajouterArticle = FXMLLoader.load(getClass().getResource("/Views/AjouterArticle.fxml"));
            
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
                /* Sub menu for Gestion Article*/
                for (Node node : gstArticleNavBar.getChildren()) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (k) -> {
                        switch (node.getAccessibleText()) {
                            case "menuPrincipale":
                                drawer.setSidePane(pane);
                                break;
                            case "ajouterArticle":
                                if (!mainField.getChildren().contains(ajouterArticle))
                                    mainField.getChildren().add(ajouterArticle); 
                                System.out.println("ajotuer Article is clicked");
                                break;
                            case "consulterArticles":
                                System.out.println("consulter aarticles is clicked");
                                break;
                        }
                    });
                }
                
                /* end Sub menu for Gestion Article */
            }
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
