/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class SquadsController implements Initializable {

	@FXML
	private Pane pane;
	@FXML
	private JFXComboBox HomeTeam;
	@FXML
	private JFXComboBox AwayTeam;

	private HashMap<String, Integer> map;
	@FXML
	private TableColumn<Player, String> positionHome;
	@FXML
	private TableColumn<Player, String> nameHome;
	private TableColumn<Player, Circle> iconHome;
	@FXML
	private TableColumn<Player, String> positionAway;
	@FXML
	private TableColumn<Player, String> NameAway;
	@FXML
	private TableView<Player> tablev;

	double orgSceneX, orgSceneY;
	double orgTranslateX, orgTranslateY;

	HashMap<String, Entry<Integer, Integer>> exist;

	EventHandler<MouseEvent> circleOnMousePressedEventHandler
			= new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((VBox) (t.getSource())).getTranslateX();
			orgTranslateY = ((VBox) (t.getSource())).getTranslateY();

			((VBox) (t.getSource())).toFront();
		}
	};

	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler
			= new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((VBox) (t.getSource())).setTranslateX(newTranslateX);
			((VBox) (t.getSource())).setTranslateY(newTranslateY);
		}
	};
	@FXML
	private AnchorPane middle;
	@FXML
	private ImageView field;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		exist = new HashMap<String, Entry<Integer, Integer>>();
		map = TeamCrud.GetNameIdMap();
		HomeTeam.setItems(FXCollections.observableArrayList(map.keySet()));
		AwayTeam.setItems(FXCollections.observableArrayList(map.keySet()));
		positionHome.setCellValueFactory(
				new PropertyValueFactory<>("position"));
		nameHome.setCellValueFactory(
				new PropertyValueFactory<>("fullName"));

		HomeTeam.setOnAction((event) -> {
			ObservableList<Player> OL = FXCollections.observableList(PlayerCrud.findPlayersByNation(map.get(HomeTeam.getSelectionModel().getSelectedItem())));
			tablev.setItems(OL);
			for (int i = 0; i < OL.size(); i++) {
				exist.put(OL.get(i).getLastName(), new AbstractMap.SimpleEntry<Integer, Integer>(0, 0));
			}
		});
	}

	@FXML
	private void spawn(MouseEvent event) {

		Circle circle = new Circle(15.0f, Color.RED);
		circle.setCursor(Cursor.HAND);
		Label name = new Label(tablev.getSelectionModel().getSelectedItem().getLastName());
		VBox playerbox = new VBox(circle, name);
		playerbox.setId(String.valueOf(tablev.getSelectionModel().getSelectedIndex()));
		playerbox.setAlignment(Pos.CENTER);
		name.setAlignment(Pos.CENTER);
		exist.get(name.getText()).setValue(tablev.getSelectionModel().getSelectedIndex());
		if (exist.get(name.getText()).getKey() == 0) {
			playerbox.setOnMousePressed(circleOnMousePressedEventHandler);
			playerbox.setOnMouseDragged(circleOnMouseDraggedEventHandler);
			middle.getChildren().add(playerbox);
			exist.put(name.getText(), new AbstractMap.SimpleEntry<Integer, Integer>(1, tablev.getSelectionModel().getSelectedIndex()));
		}

		playerbox.addEventHandler(MouseEvent.MOUSE_RELEASED, (k1) -> {
			if ((k1.getScreenX() <= tablev.getLayoutX() + 241 + tablev.getWidth()) && k1.getScreenX() >= tablev.getLayoutX() && k1.getScreenY() <= tablev.getLayoutY() + tablev.getHeight() && k1.getScreenY() >= tablev.getLayoutY()) {
				for (Node node : middle.getChildren()) {
					if (node.getId().equals(playerbox.getId())) {
						for (Entry<String, Entry<Integer, Integer>> a : exist.entrySet()) {
							if (a.getValue().getValue().equals(Integer.parseInt(node.getId()))) {
								exist.put(a.getKey(), new AbstractMap.SimpleEntry<Integer, Integer>(0, 0));
							}
						}
						middle.getChildren().remove(node);
					}
				}
			}

		});

		tablev.getSelectionModel().clearSelection();
	}

}
