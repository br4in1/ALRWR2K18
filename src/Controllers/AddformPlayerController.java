/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class AddformPlayerController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField video;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXTextField club;
    @FXML
    private ComboBox<String> nation;
    @FXML
    private JFXTextField height;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXComboBox<String> position;
    @FXML
    private JFXTextField goals;
    @FXML
    private JFXTextField shirtNb;
    @FXML
    private JFXTextField profilePhoto;
    @FXML
    private JFXTextField blanketPhoto;
    @FXML
    private JFXTextField descriptionPhoto;
/*
    private int id ;//
    private String name ; //
    private String lastName ; //
    private int age ; //
    private String club ; //
    private int nation ; //
    private double height ;//
    private double weight ;//
    private String position ; //
    private int goals ; //
    private String description ; //
    private String profilePhoto ;//
    private String blanketPhoto ;//
    private String descriptionPhoto ;
    private String fbLink ;
    private String twitterLink ;
    private int shirtNb ; 
    private String video ; 
    
    
    */
    @FXML
    private JFXTextField fbLink;
    @FXML
    private JFXTextField twitterLink;
    Cloudinary cloudinary;
    private File image; //profilephoto
    private File image2; //blanketphoto
    private File image3; //Descriptionphoto
    
    private List<String> list ;
    private List<String> Listposition = new ArrayList<String>();
	@FXML
	private StackPane PlayerSP;
	@FXML
	private ImageView PlayerImageView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
        list = TeamCrud.GetNamelist();
        nation.setItems(FXCollections.observableArrayList(list));
        
        Listposition.add("Forward") ;
        Listposition.add("Backword") ;
        Listposition.add("Middle");
        position.setItems(FXCollections.observableArrayList(Listposition));
		
		
        
       // nation.setItems(FXCollections.observableArrayList(list));
    }    

    @FXML
    private void submit(MouseEvent event) throws IOException {
		
		if (name.getText().trim().isEmpty() ||lastName.getText().trim().isEmpty() || age.getText().trim().isEmpty() || club.getText().trim().isEmpty() || nation.getValue() == null || height.getText().trim().isEmpty() || weight.getText().trim().isEmpty() || position.getValue()== null || goals.getText().trim().isEmpty() || description.getText().trim().isEmpty() || profilePhoto.getText().trim().isEmpty() || blanketPhoto.getText().trim().isEmpty() || descriptionPhoto.getText().trim().isEmpty() || fbLink.getText().trim().isEmpty() || twitterLink.getText().trim().isEmpty() || shirtNb.getText().trim().isEmpty() || video.getText().trim().isEmpty() ) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Error !"));
			content.setBody(new Text("Please fill all the fields"));
			JFXDialog check_player = new JFXDialog(PlayerSP, content, JFXDialog.DialogTransition.CENTER);
			check_player.show();

		} else { //[0-9]{<range>}(\\.[0-9]*)?
				//&& height.getText().matches("-?[0-9](?:\\.[0-9]+)?") && weight.getText().matches("-?[0-9](?:\\.[0-9]+)?")
				if((age.getText().matches("[0-9]*") && goals.getText().matches("[0-9]*") && shirtNb.getText().matches("[0-9]*"))==false)
				{
					JFXDialogLayout content = new JFXDialogLayout();
                               content.setHeading(new Text("Error !"));
                               content.setBody(new Text("Age ,goals and shirt number have to be numbers !"));
                               JFXDialog check_player = new JFXDialog(PlayerSP, content, JFXDialog.DialogTransition.CENTER);
                               check_player.show();
				}
				else
				{//-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?
					if((height.getText().matches("(([1-9][0-9]*)|0)?(\\.[0-9]*)?") && weight.getText().matches("(([1-9][0-9]*)|0)?(\\.[0-9]*)?"))==false)
					{
						JFXDialogLayout content = new JFXDialogLayout();
                               content.setHeading(new Text("Error !"));
                               content.setBody(new Text("Height and weight have to be double !"));
                               JFXDialog check_player = new JFXDialog(PlayerSP, content, JFXDialog.DialogTransition.CENTER);
                               check_player.show();
					}
					else{
						Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //profilephoto
					    Map uploadResult1 = cloudinary.uploader().upload(image2, ObjectUtils.emptyMap()); //blanketphoto
					    Map uploadResult2 = cloudinary.uploader().upload(image3, ObjectUtils.emptyMap());//Descriptionphoto
						System.out.println(fbLink.getText() + "ddddd");
					    PlayerCrud.addPlayer(new Player(name.getText(), lastName.getText(), Integer.parseInt(age.getText()), club.getText(), nation.getValue(), Double.parseDouble(height.getText()), Double.parseDouble(weight.getText()), position.getValue(), Integer.parseInt(goals.getText()), description.getText(), (String) uploadResult.get("url"), (String) uploadResult1.get("url"), (String) uploadResult2.get("url"), fbLink.getText(), twitterLink.getText(), Integer.parseInt(shirtNb.getText()), video.getText()));
					//(String name, String lastName, int age, String club, int nation, double height,                                               double weight, String position,                                             int goals, String description, String profilePhoto, String blanketPhoto, String descriptionPhoto, String fbLink, String twitterLink, int shirtNb, String video) {
					//   TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),(String) uploadResult.get("url"),(String) uploadResult2.get("url"),(String) uploadResult1.get("url"),(String) uploadResult3.get("url"),description.getText(),website.getText(),video.getText()));
   Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Congratulation");
					alert.setContentText("successfully done !");
					alert.showAndWait();
					}
					
				}
		        
		
		}
		
   
    }                                                                                                                                               //Double.parseDouble(text)


    

    @FXML
    private void profilePhoto(MouseEvent event) {
             
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);
                
		profilePhoto.setText(image.getPath());
		Image image = new Image(new File(profilePhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
    }

    @FXML
    private void blanketPhoto(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image2 = fileChooser.showOpenDialog(null);
                
		blanketPhoto.setText(image2.getPath());
		Image image = new Image(new File(blanketPhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
    }

    @FXML
    private void DescriptionPhoto(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image3 = fileChooser.showOpenDialog(null);
                
		descriptionPhoto.setText(image3.getPath());
		Image image = new Image(new File(descriptionPhoto.getText()).toURI().toString());
		PlayerImageView.setImage(image);
    }


    @FXML
    private void choixPosition(ActionEvent event) {
    }

   


    @FXML
    private void choixNation(ActionEvent event) {
        System.out.println(nation.getValue());
    }

    
}
