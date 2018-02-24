/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.GalleryCrud;
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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
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
public class ChartPPController implements Initializable {

	@FXML
	private SwingNode swing2;
	@FXML
	private Button actualiser;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO

		PieDataset dataset = null;
		try {
			dataset = createDataset();
		} catch (SQLException ex) {
			Logger.getLogger(ChartPPController.class.getName()).log(Level.SEVERE, null, ex);
		}
		JFreeChart chart = createChart(dataset);
		swing2.setContent(
				new ChartPanel(
						createChart(dataset)
				)
		);

	}

	private static PieDataset createDataset() throws SQLException {
		GalleryCrud g = new GalleryCrud();
       int x = 0;
	   int y=0;
			x = g.returnEtat0();
			y = g.returnEtat1();
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Le nombre d'image non validé : "+x, new Double(x));
		dataset.setValue("Le nombre de d'image accepté : "+y, new Double(y));
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {
		
        
		JFreeChart chart = ChartFactory.createPieChart(
				"", dataset);

		// set a custom background for the chart
		//chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
				//new Color(20, 20, 20), new Point(400, 200), Color.WHITE));

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
		plot.setSectionPaint("Validé", createGradientPaint(new Color(0, 204, 0), Color.GREEN));
		plot.setSectionPaint("Non validé", createGradientPaint(new Color(255, 200, 200), Color.RED));
		

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
		TextTitle source = new TextTitle("Stat sur les états des images postés",
				new Font("Arial", Font.PLAIN, 30));
		source.setPaint(Color.BLACK);
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

	@FXML
	private void actu(ActionEvent event) throws SQLException {
		
		PieDataset dataset = null;
		try {
			dataset = createDataset();
		} catch (SQLException ex) {
			Logger.getLogger(ChartPPController.class.getName()).log(Level.SEVERE, null, ex);
		}
		JFreeChart chart = createChart(dataset);
		swing2.setContent(
				new ChartPanel(
						createChart(dataset)
				)
		);
	}

}
