/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import javafx.embed.swing.SwingNode;
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
 * @author dell
 */
public class ChartPController implements Initializable {

	@FXML
	private SwingNode swing1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		
			PieDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
	//	ChartViewer viewer = new ChartViewer(chart);
		//grid.add(imageHouse, 0, 0, 1, 2);
		swing1.setContent(
      new ChartPanel(
			  createChart(dataset)
      )      
    );
		
		
		
	}	
	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Win", new Double(27.8));
		dataset.setValue("Others", new Double(55.3));
		dataset.setValue("Loose", new Double(16.8));
		dataset.setValue("Draw", new Double(17.1));
		return dataset;
	}
	
	
	private static JFreeChart createChart(PieDataset dataset) {

		JFreeChart chart = ChartFactory.createPieChart(
				"Smart Phones Manufactured / Q3 2011", dataset);

		// set a custom background for the chart
		chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
				new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

		// customise the title position and font
		TextTitle t = chart.getTitle();
		//  t.setHorizontalAlignment(HorizontalAlignment.LEFT);
		t.setPaint(new Color(240, 240, 240));
		t.setFont(new Font("Arial", Font.BOLD, 26));

		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setBackgroundPaint(null);
		plot.setInteriorGap(0.04);
		plot.setOutlineVisible(false);

		// use gradients and white borders for the section colours
		plot.setSectionPaint("Others", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
		plot.setSectionPaint("Win", createGradientPaint(new Color(255, 200, 200), Color.RED));
		plot.setSectionPaint("Draw", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
		plot.setSectionPaint("Loose", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
		
		
		//plot.setDefaultSectionOutlinePaint(Color.WHITE);
		plot.setSectionOutlinesVisible(true);
	//	plot.setDefaultSectionOutlineStroke(new BasicStroke(2.0f));

		// customise the section label appearance
		plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
		plot.setLabelLinkPaint(Color.WHITE);
		plot.setLabelLinkStroke(new BasicStroke(2.0f));
		plot.setLabelOutlineStroke(null);
		plot.setLabelPaint(Color.WHITE);
		plot.setLabelBackgroundPaint(null);

		// add a subtitle giving the data source
		TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523",
				new Font("Courier New", Font.PLAIN, 12));
		source.setPaint(Color.WHITE);
//        source.setPosition(RectangleEdge.BOTTOM);
		//source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		chart.addSubtitle(source);
		return chart;

	}
	private static RadialGradientPaint createGradientPaint(Color c1, Color c2) {
		Point2D center = new Point2D.Float(0, 0);
		float radius = 200;
		float[] dist = {0.0f, 1.0f};
		return new RadialGradientPaint(center, radius, dist,
				new Color[]{c1, c2});
	}
	
	
	
	
	
	
}
