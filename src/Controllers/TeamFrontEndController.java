/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Team;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.jfoenix.controls.JFXButton;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBuilder;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
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
public class TeamFrontEndController implements Initializable {

	@FXML
	private TableView<Team> tableT;
	@FXML
	private TableColumn<?, ?> id;
	@FXML
	private TableColumn<Team, ImageView> flag;
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> area;
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

	private List<Integer> listId;
	Integer ID;
	@FXML
	private SwingNode staticSwigNode;
	@FXML
	private ImageView TeamImageView;
	@FXML
	private JFXButton PlayersView;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		listDisplay();
		//	tableT.getSelectionModel().setCellSelectionEnabled(true);

		//listener ID selected 
		final ObservableList<TablePosition> selectedCells = tableT.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener<TablePosition>() {
			@Override
			public void onChanged(ListChangeListener.Change change) {
				for (TablePosition pos : selectedCells) {
					if (tableT.getSelectionModel().getSelectedItem() != null) {
						ID = tableT.getSelectionModel().getSelectedItem().getId();
						President.setText(TeamCrud.findById(ID).getPresident());
						coach.setText(TeamCrud.findById(ID).getCoach());
						fifaDate.setText(TeamCrud.findById(ID).getFifaDate().toString());
						participation.setText(Integer.toString(TeamCrud.findById(ID).getParticipations()));
						wcGroupe.setText(TeamCrud.findById(ID).getWcGroup());
						
						//image view
					//	Image image = new Image(new File(TeamCrud.findById(ID).getSquadPhoto()).toString());
						TeamImageView.setImage(new Image(TeamCrud.findById(ID).getSquadPhoto()));
					//	System.out.println("flag url " + image);
						
						// statics 
						PieDataset dataset = createDataset(ID);
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
			}
		;
		});
	
	
		if (tableT.getSelectionModel().getSelectedItem() != null) {
			ID = tableT.getSelectionModel().getSelectedItem().getId();
			coach.setText(TeamCrud.findById(ID).getCoach());
			System.out.println("coach " + coach.getText());
		}

		System.out.println("ID " + ID);
	}

	private void listDisplay() {

		//	TableColumn<File, Image> imageColumn = TableColumnBuilder.<File, Image>create().text("Image").build();
		flag.setCellValueFactory(
				new PropertyValueFactory<>("image"));

		id.setCellValueFactory(
				new PropertyValueFactory<>("id"));
		name.setCellValueFactory(
				new PropertyValueFactory<>("name"));

		area.setCellValueFactory(
				new PropertyValueFactory<>("area"));
		ObservableList<Team> OL = FXCollections.observableList(TeamCrud.findAllTeam());
		tableT.setItems(OL);
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
