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
import com.jfoenix.controls.JFXButton;
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
import javafx.geometry.Orientation;
import javafx.scene.chart.BubbleChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
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

	List<String> type;
	@FXML
	private Text bienvenue;
	@FXML
	private AnchorPane anch;
	private int idPhoto ;
	private Likes like;	

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
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

				Label a = new Label();
				a.setLayoutX(50.0 + y);
				a.setLayoutY(240);

				Label a1 = new Label();
				a1.setLayoutX(50.0 + y);
				a1.setLayoutY(270);

				Label a2 = new Label();
				a2.setLayoutX(50.0 + y);
				a2.setLayoutY(300);

				Label a3 = new Label();
				a3.setLayoutX(50.0 + y);
				a3.setLayoutY(270);

				Label a4 = new Label();
				a4.setLayoutX(50.0 + y);
				a4.setLayoutY(300);

				Label L1 = new Label();
				L1.setLayoutX(50 + y);
				L1.setLayoutY(0);
				img2.setAccessibleHelp(Liste.get(i).getImage());
				
				//img2.setId("" + Liste.get(i).getId() + "" + Liste.get(i).getVille());
				img2.setId(""+Liste.get(i).getId());
				
				img2.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event2) {
						System.out.println(img2.getId());
						if (event2.getClickCount() == 1) {
							a.setText("Cette photo est prise à : " + img2.getId());
						}
						if (event2.getClickCount() == 2) {
							
							img2.setFitWidth(145 * 1.5);
							img2.setFitHeight(145 * 1.5);
							img2.setStyle("-fx-border-color: BLACK");
							double y = img2.getLayoutX() - 50;

							Label L = new Label();
							L.setLayoutX(50 + y);
							L.setLayoutY(0);
							L.setText("vous avez likez cette photo ! :) ");
							L.setStyle("-fx-color: red");
							L1.setVisible(false);
							anch.getChildren().add(L);
							
							Image im2 = new Image("/Views/save.png");
							ImageView btn = new ImageView(im2);
							btn.setLayoutX(180.0 + y);
							btn.setLayoutY(265);
							btn.setFitHeight(30);
							btn.setFitWidth(30);

							Image im3 = new Image("/Views/dislike.png");
							ImageView btn1 = new ImageView(im3);
							btn1.setLayoutX(110.0 + y);
							btn1.setLayoutY(265);
							btn1.setFitHeight(30);
							btn1.setFitWidth(30);
                           
							idPhoto = Integer.parseInt(img2.getId());					
							System.out.println(SimpleUser.current_user.getId()+"aaaa"+idPhoto);
							//like = new Likes(SimpleUser.current_user.getId(),idPhoto);
							
							btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
								@Override
								public void handle(MouseEvent event) {
									Stage fileChooserStage = new Stage();
									FileChooser fileChooser = new FileChooser();
									fileChooser.setTitle("Select an Image");
									fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
									File file = fileChooser.showSaveDialog(fileChooserStage);

								}
							});

							btn1.setOnMouseClicked((event) -> {
								a1.setVisible(false);
								btn.setVisible(false);
								btn1.setVisible(false);
								
							});

							anch.getChildren().add(btn);
							anch.getChildren().add(btn1);
							
						} else {
							img2.setFitHeight(170);
							img2.setFitWidth(170);

						}
					}

				}
				);

				y = 230 * (i + 1);

				anch.getChildren()
						.add(a);
				anch.getChildren()
						.add(a1);
				anch.getChildren()
						.add(a2);
				anch.getChildren()
						.add(a3);
				anch.getChildren()
						.add(a4);
				anch.getChildren()
						.add(line);
				anch.getChildren()
						.add(img2);

			}

		}

	}

	@FXML
	private void monter(MouseEvent event) {
	}

}
