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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
public class UpdateFormTeamController implements Initializable {

	
	
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
	@FXML
	private JFXComboBox<Integer> id;
	@FXML
	private JFXTextField gamesPlayed;
	@FXML
	private JFXTextField goalScored;
	@FXML
	private JFXTextField goalAgainst;
	@FXML
	private JFXTextField points;
	@FXML
	private JFXTextField win;
	@FXML
	private JFXTextField loose;
	@FXML
	private JFXTextField draw;

	private List<Integer> list;

	Cloudinary cloudinary;
	private File image; //flagphoto
	private File image2;
	private File image3;
	private File image4;
	@FXML
	private StackPane TeamSP;
	@FXML
	private ImageView TeamImageView;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		cloudinary = new Cloudinary("cloudinary://212894137142756:7Coi2BsCet7rXqPmDAuBi08ONfQ@dbs7hg9cy");

		list = TeamCrud.GeIdlist();
	//	id.setItems(FXCollections.observableArrayList(list));
		System.out.println(TeamsCrudController.x); 
		id.getItems().add(TeamsCrudController.x);
		id.getSelectionModel().selectFirst();
		
		
		name.setText(TeamCrud.findById(id.getValue()).getName());
		coach.setText(TeamCrud.findById(id.getValue()).getCoach());
		president.setText(TeamCrud.findById(id.getValue()).getPresident());
		area.setText(TeamCrud.findById(id.getValue()).getArea());
		gamesPlayed.setText(Integer.toString(TeamCrud.findById(id.getValue()).getGamesPlayed()));
		goalScored.setText(Integer.toString(TeamCrud.findById(id.getValue()).getGoalScored()));
		goalAgainst.setText(Integer.toString(TeamCrud.findById(id.getValue()).getGoalAgainst()));
		participation.setText(Integer.toString(TeamCrud.findById(id.getValue()).getParticipations()));

		date.setValue(TeamCrud.findById(id.getValue()).getFifaDate().toLocalDate());
		wcgroupe.setText(TeamCrud.findById(id.getValue()).getWcGroup());
		win.setText(Integer.toString(TeamCrud.findById(id.getValue()).getWin()));

		loose.setText(Integer.toString(TeamCrud.findById(id.getValue()).getLoose()));
		draw.setText(Integer.toString(TeamCrud.findById(id.getValue()).getDraw()));
		points.setText(Integer.toString(TeamCrud.findById(id.getValue()).getPoints()));
		fifarank.setText(Integer.toString(TeamCrud.findById(id.getValue()).getFifaRank()));
		flagphoto.setText(TeamCrud.findById(id.getValue()).getFlagPhoto());
		logophoto.setText(TeamCrud.findById(id.getValue()).getLogoPhoto());
		squadphoto.setText(TeamCrud.findById(id.getValue()).getSquadPhoto());
		descriptionphoto.setText(TeamCrud.findById(id.getValue()).getDescriptionPhoto());
		website.setText(TeamCrud.findById(id.getValue()).getWebsite());
		description.setText(TeamCrud.findById(id.getValue()).getDescription());
		video.setText(TeamCrud.findById(id.getValue()).getVideo());
	}

	@FXML
	private void submit(MouseEvent event) throws IOException {

		if (id.getValue() == null) {
			JFXDialogLayout content = new JFXDialogLayout();
			content.setHeading(new Text("Error !"));
			content.setBody(new Text("Please select an id"));
			JFXDialog check_username = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
			check_username.show();

		} else {

			//  if(!participation.getText().matches("[0-9]*") && !fifarank.getText().matches("[0-9]*") && win.getText().matches("[0-9]*") && draw.getText().matches("[0-9]*") && loose.getText().matches("[0-9]*") && gamesPlayed.getText().matches("[0-9]*") && goalScored.getText().matches("[0-9]*") && points.getText().matches("[0-9]*"))
			if (!participation.getText().matches("[0-9]*") || !gamesPlayed.getText().matches("[0-9]*") || !fifarank.getText().matches("[0-9]*") || !goalScored.getText().matches("[0-9]*") || !goalAgainst.getText().matches("[0-9]*") || !points.getText().matches("[0-9]*") || !win.getText().matches("[0-9]*") || !loose.getText().matches("[0-9]*") || !draw.getText().matches("[0-9]*")) {//f || t
				JFXDialogLayout content = new JFXDialogLayout();
				content.setHeading(new Text("Error !"));
				content.setBody(new Text("Please check numbers' values!"));
				JFXDialog check_username = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
				check_username.show();
			} else {
				Boolean ok = TeamCrud.findTeamByNameId(name.getText(), id.getValue());
				if (ok) {
					System.out.println("xxxx");
					JFXDialogLayout content = new JFXDialogLayout();
					content.setHeading(new Text("Error !"));
					content.setBody(new Text("Sorry, this team's name already exist !"));
					JFXDialog check_username = new JFXDialog(TeamSP, content, JFXDialog.DialogTransition.CENTER);
					check_username.show();
				} else {
					System.out.println(id.getValue() + " " + " xxxxx ");
					Map uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap()); //flagphoto
					Map uploadResult1 = cloudinary.uploader().upload(image2, ObjectUtils.emptyMap()); //squadphoto
					Map uploadResult2 = cloudinary.uploader().upload(image3, ObjectUtils.emptyMap());//logophoto
					Map uploadResult3 = cloudinary.uploader().upload(image4, ObjectUtils.emptyMap());//descriptionphoto
					System.out.println(id.getValue() + " " + " xxxxx ");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         //  ,`FlagPhoto`, `LogoPhoto`, `SquadPhoto`, `DescriptionPhoto`,                                         
					TeamCrud.updateTeam(new Team(id.getValue(), name.getText(), coach.getText(), president.getText(), area.getText(), Integer.parseInt(gamesPlayed.getText()), Integer.parseInt(goalScored.getText()), Integer.parseInt(goalAgainst.getText()), Integer.parseInt(participation.getText()), Date.valueOf(date.getValue()), wcgroupe.getText(), Integer.parseInt(win.getText()), Integer.parseInt(loose.getText()), Integer.parseInt(draw.getText()), Integer.parseInt(points.getText()), Integer.parseInt(fifarank.getText()), (String) uploadResult.get("url"), (String) uploadResult2.get("url"), (String) uploadResult1.get("url"), (String) uploadResult3.get("url"), description.getText(), website.getText(), video.getText()));
					//TeamCrud.updateTeam(new Team(id.getValue(),name.getText(),coach.getText(),president.getText(),area.getText(),Integer.parseInt(gamesPlayed.getText()),Integer.parseInt(goalScored.getText()),Integer.parseInt(goalAgainst.getText()),Integer.parseInt(participation.getText()),Date.valueOf(date.getValue()),wcgroupe.getText(),Integer.parseInt(win.getText()),Integer.parseInt(loose.getText()),Integer.parseInt(draw.getText()),Integer.parseInt(points.getText()),Integer.parseInt(fifarank.getText()),logophoto.getText(),flagphoto.getText(),squadphoto.getText(),descriptionphoto.getText(),description.getText(),website.getText(),video.getText()));
					//public Team(String name, String coach, String president, String area, int gamesPlayed, int goalScored, int goalAgainst, int participations, Date fifaDate, String wcGroup, int win, int loose, int draw, int points, int fifaRank, String flagPhoto, String logoPhoto, String squadPhoto, String descriptionPhoto, String description, String website, String video) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information Dialog");
					alert.setHeaderText("Congratulation");
					alert.setContentText("successfully done !");
					alert.showAndWait();
				
				}
			}
		}

	}

	@FXML
	private void photo(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image = fileChooser.showOpenDialog(null);

		flagphoto.setText(image.getPath());
		Image image = new Image(new File(flagphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);

	}

	@FXML
	private void photosquad(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image2 = fileChooser.showOpenDialog(null);

		squadphoto.setText(image2.getPath());
Image image = new Image(new File(squadphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);
	}

	@FXML
	private void photologo(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image3 = fileChooser.showOpenDialog(null);

		logophoto.setText(image3.getPath());
		Image image = new Image(new File(logophoto.getText()).toURI().toString());
		TeamImageView.setImage(image);

	}

	@FXML
	private void photodescription(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir une photo");
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		image4 = fileChooser.showOpenDialog(null);

		descriptionphoto.setText(image4.getPath());
		Image image = new Image(new File(descriptionphoto.getText()).toURI().toString());
		TeamImageView.setImage(image);

	}
	

	@FXML
	private void ChoixId(ActionEvent event) {
	}

}
