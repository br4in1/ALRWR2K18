/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.TeamCrud;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class PlayersCrudController implements Initializable {

    @FXML
    private TableView<Player> tableT;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> descriptionPhoto;
    @FXML
    private TableColumn<?, ?> description;
    @FXML
    private TableColumn<?, ?> video;
    @FXML
    private TableColumn<?, ?> lastName;
    @FXML
    private TableColumn<?, ?> age;
    @FXML
    private TableColumn<?, ?> club;
    @FXML
    private TableColumn<?, ?> nation;
    @FXML
    private TableColumn<?, ?> height;
    @FXML
    private TableColumn<?, ?> weight;
    @FXML
    private TableColumn<?, ?> position;
    @FXML
    private TableColumn<?, ?> goals;
    @FXML
    private TableColumn<?, ?> profilePhoto;
    @FXML
    private TableColumn<?, ?> blanketPhoto;
    @FXML
    private TableColumn<?, ?> fbLink;
    @FXML
    private TableColumn<?, ?> twitterLink;
    @FXML
    private TableColumn<?, ?> shirtNb;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void newPlayer(MouseEvent event) {
        try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddFormPlayer.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
      }
    }

    @FXML
    private void deletePlayer(MouseEvent event) {
        TeamCrud.RemoveTeam(tableT.getSelectionModel().getSelectedItem().getId());
        tableT.getItems().removeAll(tableT.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void updatePlayer(MouseEvent event) {
                   try {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/UpdateFormPlayer.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            
    } catch(Exception e) {
       e.printStackTrace();
      }
    }
    
}
