/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import Services.UserCrud;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class UsersStatisticsController implements Initializable {

	@FXML
	private JFXButton refreshBtn;
	@FXML
	private PieChart countrieschart;
	@FXML
	private PieChart agechart;
	@FXML
	private Label nbloggedin;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		RefreshData(new ActionEvent());
	}

	@FXML
	private void RefreshData(ActionEvent event1) {
		nbloggedin.setText(String.valueOf(UserCrud.GetLoggedInNumber()));
		Map<String, Integer> countriesPieData = UserCrud.GetCountriesChartData();
		ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList();
		for (Entry<String, Integer> s : countriesPieData.entrySet()) {
			pieChartData1.add(new PieChart.Data(s.getKey(), s.getValue()));
		}
		countrieschart.setData(pieChartData1);
		pieChartData1.stream().forEach(pieData -> {
			pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				Bounds b1 = pieData.getNode().getBoundsInLocal();
				double newX = (b1.getWidth()) / 2 + b1.getMinX();
				double newY = (b1.getHeight()) / 2 + b1.getMinY();
				// Make sure pie wedge location is reset
				pieData.getNode().setTranslateX(0);
				pieData.getNode().setTranslateY(0);
				TranslateTransition tt = new TranslateTransition(
						Duration.millis(500), pieData.getNode());
				tt.setByX(newX);
				tt.setByY(newY);
				tt.setAutoReverse(true);
				tt.setCycleCount(2);
				tt.play();
			});
		});
		Map<Integer, Integer> agesPieData = UserCrud.GetAgesChartData();
		ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
		for (Entry<Integer, Integer> s : agesPieData.entrySet()) {
			pieChartData2.add(new PieChart.Data(String.valueOf(s.getKey())+" yo", s.getValue()));
		}
		agechart.setData(pieChartData2);
		agechart.setTitle("Ages Repartition");
		pieChartData2.stream().forEach(pieData -> {
			pieData.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
				Bounds b1 = pieData.getNode().getBoundsInLocal();
				double newX = (b1.getWidth()) / 2 + b1.getMinX();
				double newY = (b1.getHeight()) / 2 + b1.getMinY();
				// Make sure pie wedge location is reset
				pieData.getNode().setTranslateX(0);
				pieData.getNode().setTranslateY(0);
				TranslateTransition tt = new TranslateTransition(
						Duration.millis(500), pieData.getNode());
				tt.setByX(newX);
				tt.setByY(newY);
				tt.setAutoReverse(true);
				tt.setCycleCount(2);
				tt.play();
			});
		});
	}

}
