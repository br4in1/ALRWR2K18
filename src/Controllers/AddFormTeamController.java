/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class AddFormTeamController implements Initializable {

<<<<<<< HEAD
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
	private JFXTextField flagphoto;//
	@FXML
	private JFXTextField logophoto;//
	@FXML
	private JFXTextField squadphoto;//
	@FXML
	private JFXTextField descriptionphoto;//
	@FXML
	private JFXTextField website;
	@FXML
	private JFXTextField video;
	@FXML
	private JFXTextArea description;

	Cloudinary cloudinary;
	private File image;
	private File image2;
	private File image3;
	private File image4;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
	}

	@FXML
	private void submit(MouseEvent event) throws IOException {
		Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
		//(String) uploadResult.get("url")
		TeamCrud.addTeam(new Team(name.getText(), coach.getText(), president.getText(), area.getText(), Integer.parseInt(participation.getText()), Date.valueOf(date.getValue()), wcgroupe.getText(), Integer.parseInt(fifarank.getText()), (String) uploadResult.get("url"), (String) uploadResult.get("url"), (String) uploadResult.get("url"), (String) uploadResult.get("url"), description.getText(), website.getText(), video.getText()));
		//    TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),flagphoto.getText(),logophoto.getText(),squadphoto.getText(),descriptionphoto.getText(),description.getText(),website.getText(),video.getText()));

	}
	//String name, String coach, String president, String area,int participations, Date fifaDate, String wcGroup,                           int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video

	@FXML
=======
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
    private JFXTextField flagphoto;//
    @FXML
    private JFXTextField logophoto;//
    @FXML
    private JFXTextField squadphoto;//
    @FXML
    private JFXTextField descriptionphoto;//
    @FXML
    private JFXTextField website;
    @FXML
    private JFXTextField video;
    @FXML
    private JFXTextArea description;
    
    Cloudinary cloudinary;
    private File image; //flagphoto
    private File image2; //squadphoto
    private File image3; //logophoto
    private File image4;//descriptionphoto
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");
    }    

    @FXML
    private void submit(MouseEvent event) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //flagphoto
        Map uploadResult1 = cloudinary.uploader().upload(image2, ObjectUtils.emptyMap()); //squadphoto
        Map uploadResult2 = cloudinary.uploader().upload(image3, ObjectUtils.emptyMap());//logophoto
        Map uploadResult3 = cloudinary.uploader().upload(image4, ObjectUtils.emptyMap());//descriptionphoto
        //(String) uploadResult.get("url")                                                                                                                                                                                      //,`FlagPhoto`, `LogoPhoto`, `SquadPhoto`, `DescriptionPhoto`,
        TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),(String) uploadResult.get("url"),(String) uploadResult2.get("url"),(String) uploadResult1.get("url"),(String) uploadResult3.get("url"),description.getText(),website.getText(),video.getText()));
      // TeamCrud.addTeam(new Team(name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(fifarank.getText()),flagphoto.getText(),logophoto.getText(),squadphoto.getText(),descriptionphoto.getText(),description.getText(),website.getText(),video.getText()));
    
    
    } 
   //String name, String coach, String president, String area,int participations, Date fifaDate, String wcGroup,                           int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video
    @FXML
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda
	private void photo(MouseEvent event) {
            
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);

		flagphoto.setText(image.getPath());

	}

	@FXML
	private void photosquad(MouseEvent event) {
            
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image2 = fileChooser.showOpenDialog(null);
<<<<<<< HEAD

		squadphoto.setText(image2.getPath());
	}

	@FXML
=======
                
		squadphoto.setText(image2.getPath());      

	} 
        
     @FXML
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda
	private void photologo(MouseEvent event) {
            
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image3 = fileChooser.showOpenDialog(null);
<<<<<<< HEAD

		logophoto.setText(image3.getPath());

	}

	@FXML
=======
                
		logophoto.setText(image3.getPath()); 
                
	} 
        
        @FXML
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda
	private void photodescription(MouseEvent event) {
            
		
        FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image4 = fileChooser.showOpenDialog(null);
<<<<<<< HEAD

		descriptionphoto.setText(image4.getPath());

	}

=======
                
		descriptionphoto.setText(image4.getPath());       
	
        
        } 
        
>>>>>>> bf3b485093a7fc602321465bb773f42727787eda
}
