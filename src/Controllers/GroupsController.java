/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class GroupsController implements Initializable {

	@FXML
	private VBox A;
	@FXML
	private VBox B;
	@FXML
	private VBox C;
	@FXML
	private VBox D;
	@FXML
	private VBox E;
	@FXML
	private VBox F;
	@FXML
	private VBox G;
	@FXML
	private VBox H;
	@FXML
	private HBox line1;
	@FXML
	private HBox line2;
	@FXML
	private FlowPane profileContent;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		List<Team> list = TeamCrud.findAllTeam();
		A.getParent().setStyle("-fx-background-color: #fcfcfc;");
		for (Team team : list) {
			switch (team.getWcGroup()) {
				case "A": A.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "B": B.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "C": C.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "D": D.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "E": E.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "F": F.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "G": G.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;
				case "H": H.getChildren().add(team(team.getFlagPhoto(),team.getName()));
				break;

			}
		}
	}
 HBox team(String flag,String name)
			{
				ImageView flag1 = new ImageView(flag);
				flag1.setFitHeight(53);
				flag1.setFitWidth(95);
				Label name1 = new Label(name);
				name1.setFont(new Font("Dusha V5", 16));
				HBox nf = new HBox(20);
				nf.setStyle("-fx-padding: 10 0 0 0;");
				name1.setStyle("-fx-padding: 14 0 0 0;");
				nf.getChildren().add(flag1);
				nf.getChildren().add(name1);
				return nf;
			}


}
