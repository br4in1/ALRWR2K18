/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import com.jfoenix.controls.JFXButton;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.imageio.ImageIO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import javafx.embed.swing.SwingNode;
import javafx.scene.effect.ColorAdjust;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import Services.TeamCrud;
import com.jfoenix.controls.JFXComboBox;
import java.util.List;
import javafx.collections.FXCollections;


/**
 * FXML Controller class
 *
 * @author Moez
 */
public class TeamStaticsController implements Initializable {

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private PieChart pieChart;
	@FXML
	private JFXButton screen;
	@FXML
	private AnchorPane view;
	private Pane pane;
	@FXML
	private SwingNode staticSwigNode;
	@FXML
	private JFXComboBox<Integer> id;
private List<Integer> list;
	@Override
	public void initialize(URL url, ResourceBundle rb) {



			list = TeamCrud.GeIdlist();
		id.setItems(FXCollections.observableArrayList(list));	
	}

	@FXML
	private void doScreen(ActionEvent event) {
		//System.out.println("dddd");
		try {
			Robot robot = new Robot();

			//Rectangle rectange = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()) ;
			Rectangle rectange = new Rectangle(250, 125, 700, 450);

			BufferedImage image = robot.createScreenCapture(rectange);
			Image myImage = SwingFXUtils.toFXImage(image, null);
			SecureRandom o = new SecureRandom();
			o.nextLong();
			System.out.println(o);
			ImageIO.write(image, "jpg", new File(o + ".jpg"));

		} catch (Exception ex) {
			Logger.getLogger(TeamStaticsController.class.getName()).log(Level.SEVERE, null, ex);

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
		chart.setBackgroundPaint(null);
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
		
		
	plot.setSectionPaint("Win",Color.getHSBColor(84, 175, 83) );
		plot.setSectionPaint("Draw",Color.getHSBColor(40, 97, 92));
		plot.setSectionPaint("Loose", Color.getHSBColor(120, 81, 89));
		//plot.setSectionPaint("Loose", Color.getHSBColor(100, 100, 100));
		
		plot.setDefaultSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
		plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));


		// add a subtitle giving the data source
		TextTitle source = new TextTitle("",
				new Font("Team's statics", Font.PLAIN, 12));
		source.setPaint(Color.WHITE);

		chart.addSubtitle(source);
		return chart;

	}

	

	@FXML
	private void ChoixChart(ActionEvent event) {
			//createDataset();
			
					PieDataset dataset = createDataset(id.getValue());
		JFreeChart chart = createChart(dataset);
	
	//	ChartViewer viewer = new ChartViewer(chart);
		//grid.add(imageHouse, 0, 0, 1, 2);
		staticSwigNode.setContent(
      new ChartPanel(
			  createChart(dataset)
      )      
    );
	}


}
