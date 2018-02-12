/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Entities.Team;
import Services.TeamCrud;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class AddFormTeamController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField coach;
    @FXML
    private JFXTextField president;
    @FXML
    private JFXTextField area;
    @FXML
    private JFXTextField participation;
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField wcgroupe;
    @FXML
    private JFXTextField fifarank;
    @FXML
    private JFXTextField flagphoto;
    @FXML
    private JFXTextField logophoto;
    @FXML
    private JFXTextField squadphoto;
    @FXML
    private JFXTextField descriptionphoto;
    @FXML
    private JFXTextField website;
    @FXML
    private JFXTextField video;
    @FXML
    private JFXTextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(MouseEvent event) {
        TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),flagphoto.getText(),logophoto.getText(),squadphoto.getText(),descriptionphoto.getText(),description.getText(),website.getText(),video.getText()));
    }                               //String name, String coach, String president, String area,int participations, Date fifaDate, String wcGroup,                           int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
      
}
