/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Gallery;
import Entities.Likes;
import Entities.SimpleUser;
import Services.GalleryCrud;
import Services.LikesCrud;
import static com.google.maps.PlacesApi.photo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import static java.awt.Color.red;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.L;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Rating;
import org.jfree.ui.L1R1ButtonPanel;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowallController implements Initializable {

	public static ShowallController thisController;
	List<String> type;
	@FXML
	private Text bienvenue;
	@FXML
	private AnchorPane anch;
	@FXML
	private FlowPane mainShowAll;
	@FXML
	private StackPane mainStack;
	public Gallery current_shown_Gallery;

	private TextToSpeech tts = new TextToSpeech();
	private float VoiceVolume;
	int nbre;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		type = new ArrayList<>();
		type.add("*.png");
		type.add("*.jpg");

	}

	@FXML
	private void Monter(MouseEvent event) throws SQLException, MalformedURLException {
		Gallery ga = new Gallery();
		GalleryCrud g = new GalleryCrud();
		int y = 1;
		List<Gallery> Liste = g.DisplayAll();
		anch.getChildren().clear();
		for (int i = 0; i < Liste.size(); i++) {
			if ("1".equals(Liste.get(i).getEtat())) {

				Image im = new Image(Liste.get(i).getImage());
				ImageView img2 = new ImageView(im);

				img2.setLayoutX(50.0 + y);
				img2.setLayoutY(41.0);
				img2.setFitHeight(170);
				img2.setFitWidth(170);

				Line line = new Line();
				line.setStartX(175);
				line.setStartY(0);
				line.setEndX(0);
				line.setEndY(0);
				line.setLayoutX(45.0 + y);
				line.setLayoutY(220);
				line.setStroke(Color.rgb(178, 61, 61));
				line.setStyle("-fx-stroke-width:" + 3);

				img2.setAccessibleHelp(Liste.get(i).getImage());
				img2.setId(String.valueOf(Liste.get(i).getId()));

				img2.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event2) {
						thisController.current_shown_Gallery = GalleryCrud.findGalleryById(Integer.parseInt(img2.getId()));
						if (event2.getClickCount() == 1) {

							try {
								HBox Hb = new HBox();
								ImageView img22 = new ImageView(img2.getImage());
								img22.setFitHeight(400);
								img22.setFitWidth(500);
								Image im33 = new Image("/Views/like.png");
								Image im44 = new Image("/Views/Dislike.png");
								int photo = Integer.parseInt(img2.getId());
								ImageView btn;
								if(LikesCrud.AlreadyLiked(SimpleUser.current_user.getId(),photo)){
									btn = new ImageView(im44);
								}
								else{
									btn = new ImageView(im33);
								}
								
								btn.setFitHeight(30);
								btn.setFitWidth(30);
								Label Total = new Label();
								LikesCrud L = new LikesCrud();
								
								nbre = L.PhotoLiked(photo);
								Total.setText("            " + nbre);
								btn.setOnMouseClicked((event) -> {

									if (btn.getImage() == im33) {
										tts.setVoice("dfki-poppy-hsmm");
										VoiceVolume = 2;
										tts.speak("You have liked this photo ", VoiceVolume, false, false);
										Likes Liked = new Likes(SimpleUser.current_user.getId(), photo);
										LikesCrud.Like(Liked);
										Total.setText("            " +(nbre+1));
										thisController.nbre++;
										btn.setImage(im44);
										
									} else {
										try {
											tts.setVoice("dfki-poppy-hsmm");
											VoiceVolume = 2;
											tts.speak("You have disliked this photo ", VoiceVolume, false, false);
											LikesCrud.Unlike(SimpleUser.current_user.getId(),photo);
											Total.setText("            " + (nbre-1));
											System.out.println(nbre);
											thisController.nbre--;
											btn.setImage(im33);
										} catch (SQLException ex) {
											Logger.getLogger(ShowallController.class.getName()).log(Level.SEVERE, null, ex);
										}
											
									}

								});
								Label Ville = new Label();
								Ville.setText("Ville : "+thisController.current_shown_Gallery.getVille());
								Ville.setFont(Font.font("Ubuntu", 19));

								Label Lieu = new Label();
								Lieu.setText("Lieu : ");
								Lieu.setFont(Font.font("Ubuntu", 19));

								Label Description = new Label();
								Description.setText("Description : ");
								Description.setFont(Font.font("Ubuntu", 19));
								Label Mention = new Label();
								Mention.setText("      Mention de j'aime");
								Mention.setFont(Font.font("Ubuntu", FontWeight.BOLD, 20));
								Mention.setTextFill(Color.web("#b23d3d"));

								
								Total.setTextFill(Color.web("#b23d3d"));
								Total.setFont(Font.font("Ubuntu", 40));
								Label espace = new Label(" ");
								Separator Sep = new Separator();
								Hb.getChildren().add((img22));
								Hb.setMaxSize(40, 40);
								VBox Vb = new VBox();
								Vb.getChildren().addAll(
										Ville,
										Lieu,
										Description,
										btn,
										Sep,
										Mention,
										Total
									
								);
								Vb.setMinWidth(300);
								Vb.setSpacing(30);
								Vb.setPadding(new Insets(30));
								Hb.getChildren().add(Vb);
								JFXDialogLayout Dialog = new JFXDialogLayout();
								Dialog.setBody(Hb);
								JFXDialog Dialog1;
								Dialog1 = new JFXDialog(mainStack, Dialog, JFXDialog.DialogTransition.TOP);
								Dialog1.show();
							} catch (SQLException ex) {
								Logger.getLogger(ShowallController.class.getName()).log(Level.SEVERE, null, ex);
							}

						}

					}

				}
				);

				y = 230 * (i + 1);
				anch.getChildren()
						.add(line);
				anch.getChildren()
						.add(img2);

			}

		}

	}

}
