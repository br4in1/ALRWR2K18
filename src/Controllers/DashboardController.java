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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox pane = FXMLLoader.load(getClass().getResource("/Views/navbar.fxml"));
            VBox gstArticleNavBar = FXMLLoader.load(getClass().getResource("/Views/GestionArticle/navbar.fxml"));

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
                                hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                                    drawer.setSidePane(pane);
                                });
                                break;
                        }
                    });
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
