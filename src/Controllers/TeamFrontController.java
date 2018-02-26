/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.FrontTeamBoxController.list;
import Entities.Player;
import Entities.Team;
import Services.PlayerCrud;
import Services.TeamCrud;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamFrontController implements Initializable {

	public static TeamFrontController thisController;
	@FXML
	private FlowPane profileContent;
	@FXML
	private Label lastname;
	@FXML
	private Label President;
	@FXML
	private Label coach;
	@FXML
	private Label fifaDate;
	@FXML
	private Label participation;
	@FXML
	private Label wcGroupe;
	@FXML
	private SwingNode staticSwigNode;

	public static Integer current_team_id;
	@FXML
	private ImageView TeamImageView;
	public static List<Player> listPlayers;
	
	@FXML
	private Label players;
	
	@FXML
	private HBox hboxNav;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
		hboxNav.setStyle("-fx-padding-left: 30px;");
	}

	public void refreshData() {
		hboxNav.getChildren().clear();
		Team t = TeamCrud.findById(current_team_id);
		lastname.setText(t.getName());
		President.setText(t.getPresident());
		coach.setText(t.getCoach());
		fifaDate.setText(t.getFifaDate().toString());
		participation.setText(Integer.toString(t.getParticipations()));
		wcGroupe.setText(t.getWcGroup());

		//image view
		TeamImageView.setImage(new Image(t.getDescriptionPhoto()));

		// statics 
		PieDataset dataset = createDataset(current_team_id);
		JFreeChart chart = createChart(dataset);

		staticSwigNode.setContent(
				new ChartPanel(
						createChart(dataset)
				)
		);

		listPlayers = PlayerCrud.findPlayersByNationFront(t.getName());
		for (int i = 0; i < listPlayers.size(); i++) {
			Label plPhotoLabel = new Label();
			Label plNameLabel = new Label() ;
			plNameLabel.setText(listPlayers.get(i).getName()+" " + listPlayers.get(i).getLastName());
			
			ImageView im = new ImageView(listPlayers.get(i).getProfilePhoto());
			im.setFitHeight(140);
			im.setFitWidth(120);
			plPhotoLabel.setGraphic(im);
			
			VBox vboxNav1 = new VBox();
			vboxNav1.setSpacing(25);
			vboxNav1.getChildren().add(plPhotoLabel);
			vboxNav1.getChildren().add(plNameLabel);
			hboxNav.getChildren().add(vboxNav1);
			hboxNav.setSpacing(30);
			
			//pl.setId(String.valueOf(listPlayers.get(i).getName()));
			//nav.getChildren().add(plPhotoLabel);
			
			//hnav.getChildren().add(pl);
		}
	}

	private static PieDataset createDataset(int x) {
		Team team = TeamCrud.findById(x);
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Win", team.getWin());
		//dataset.setValue("Others", new Double(55.3));
		dataset.setValue("Loose", team.getLoose());
		dataset.setValue("Draw", team.getDraw());
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart(
				"Team Statics", dataset);

		// set a custom background for the chart
		chart.setBorderVisible(false);

		chart.setBackgroundPaint(Color.WHITE);
		// customise the title position and font
		TextTitle t = chart.getTitle();
		//  t.setHorizontalAlignment(HorizontalAlignment.LEFT);
		t.setPaint(new Color(0, 0, 0));
		t.setFont(new Font("Arial", Font.BOLD, 26));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(null);
		plot.setInteriorGap(0.04);
		plot.setOutlineVisible(false);
		float[] hsb = new float[3];
		ColorAdjust ca = new ColorAdjust();

		plot.setSectionPaint("Win", Color.getHSBColor(84, 175, 83));
		plot.setSectionPaint("Draw", Color.getHSBColor(40, 97, 92));
		plot.setSectionPaint("Loose", Color.getHSBColor(120, 81, 89));
		//plot.setSectionPaint("Loose", Color.getHSBColor(100, 100, 100));

		//	plot.setDefaultSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
		//	plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

		// add a subtitle giving the data source
		TextTitle source = new TextTitle("",
				new Font("Team's statics", Font.PLAIN, 12));
		source.setPaint(Color.WHITE);

		chart.addSubtitle(source);
		return chart;

	}
}
