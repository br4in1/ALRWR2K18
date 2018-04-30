/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Controllers.FrontTeamBoxController.list;
import Entities.Team;
import Services.TeamCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class FrontTeamBoxController implements Initializable {

	@FXML
	public VBox nav;
	public static List<Team> list;
	public static FrontTeamBoxController thisController;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		thisController = this;
		list = TeamCrud.findAllTeam() ;
		for(int i = 0;i < list.size();i++){
			Label eq = new Label();
			eq.setText(list.get(i).getName());
			ImageView im = new ImageView(list.get(i).getFlagPhoto());
			im.setFitHeight(25);
			im.setFitWidth(35);
			eq.setGraphic(im);
			eq.setId(String.valueOf(list.get(i).getId()));
			eq.setMinHeight(43);
			eq.setMinWidth(233);
			eq.setMaxHeight(43);
			eq.setMaxWidth(233);
			eq.setStyle("-fx-padding: 0 0 0 10;");
			nav.getChildren().add(eq);
			eq.setOnMouseEntered(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					eq.setStyle("-fx-padding: 0 0 0 10; -fx-background-color: #66ae2e; -fx-background-radius : 3px; -fx-text-fill: #fff;");
				}
				
			});
			
			eq.setOnMouseExited(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
					eq.setStyle("-fx-padding: 0 0 0 10; -fx-background-color: none; -fx-background-radius : none; -fx-text-fill: #000;");
				}
				
			});
		}
	}	
	
}
