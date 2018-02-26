/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.TeamCrud;
import java.awt.Color;
import java.awt.Font;
import java.net.URL;
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
import javafx.scene.layout.StackPane;
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

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		thisController = this;
	
	}	
	
	public void refreshData(){
		Team t = TeamCrud.findById(current_team_id);
		lastname.setText(t.getName());
						President.setText(t.getPresident());
						coach.setText(t.getCoach());
						fifaDate.setText(t.toString());
						participation.setText(Integer.toString(t.getParticipations()));
						wcGroupe.setText(t.getWcGroup());

						//image view
						TeamImageView.setImage(new Image(t.getDescriptionPhoto()));
						
						// statics 
						PieDataset dataset = createDataset(current_team_id);
						JFreeChart chart = createChart(dataset);

						//	ChartViewer viewer = new ChartViewer(chart);
						//grid.add(imageHouse, 0, 0, 1, 2);
						//staticSwigNode.setScene(new Scene(pane, 250, 150));
						staticSwigNode.setContent(
								new ChartPanel(
										createChart(dataset)
								)
						);
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
