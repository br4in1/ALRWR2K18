/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import Services.UserCrud;
import java.util.Comparator;
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

	public static UsersStatisticsController thisController;
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
		thisController = this;
		RefreshData(new ActionEvent());
	}

	@FXML
	public void RefreshData(ActionEvent event1) {
		nbloggedin.setText(String.valueOf(UserCrud.GetLoggedInNumber()));
		Integer numberOfUsers = UserCrud.getNumberOfUsers();
		Map<String, Integer> countriesPieData = UserCrud.GetCountriesChartData();
		ObservableList<PieChart.Data> pieChartData1 = FXCollections.observableArrayList();
		Float remaining = 0f;
		for (Entry<String, Integer> s : countriesPieData.entrySet()) {
			pieChartData1.add(new PieChart.Data(s.getKey()+"("+String.valueOf(100*s.getValue()/numberOfUsers)+")", 100*s.getValue()/numberOfUsers));
			remaining += 100*s.getValue()/numberOfUsers;
		}
		pieChartData1.add(new PieChart.Data("Others("+String.valueOf(100 - Math.floor(remaining))+")", 100 - remaining));
		countrieschart.setData(pieChartData1);
		countrieschart.setTitle("Countries Repartition");
		countrieschart.getData().sort(new Comparator<PieChart.Data>() {
			@Override
			public int compare(PieChart.Data o1, PieChart.Data o2) {
				if(o1.getPieValue() - o2.getPieValue() >= 0) return -1;
				return 1;
			}
		});
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
