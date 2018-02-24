/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Player;
import Entities.sendSMS;
import Services.GameCrud;
import Services.PlayerCrud;
import Services.TeamCrud;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Date;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author simo
 */
public class SquadsController implements Initializable {
Cloudinary cloudinary;
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
	HashMap<String, Entry<Integer, Integer>> exist2;

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
	@FXML
	private TableView<Player> tablev2;

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
		positionAway.setCellValueFactory(
				new PropertyValueFactory<>("position"));
		NameAway.setCellValueFactory(
				new PropertyValueFactory<>("fullName"));
		HomeTeam.setOnAction((event) -> {
			ObservableList<Player> OL = FXCollections.observableList(PlayerCrud.findPlayersByNation(map.get(HomeTeam.getSelectionModel().getSelectedItem())));
			tablev.setItems(OL);
			for (int i = 0; i < OL.size(); i++) {
				exist.put(OL.get(i).getLastName(), new AbstractMap.SimpleEntry<Integer, Integer>(0, 0));

			}
		});
		AwayTeam.setOnAction((event) -> {
			ObservableList<Player> OL2 = FXCollections.observableList(PlayerCrud.findPlayersByNation(map.get(AwayTeam.getSelectionModel().getSelectedItem())));
			tablev2.setItems(OL2);
			for (int i = 0; i < OL2.size(); i++) {
				exist.put(OL2.get(i).getLastName(), new AbstractMap.SimpleEntry<Integer, Integer>(0, 0));

			}
		});
	}

	@FXML
	private void spawn(MouseEvent event) {
		System.out.println("/assets/Players/'" + tablev.getSelectionModel().getSelectedItem().getName() + "'_'" + tablev.getSelectionModel().getSelectedItem().getLastName() + "'.png");
		Circle circle = new Circle(25.0f, Color.RED);
		circle.setCursor(Cursor.HAND);

		Label name = new Label(tablev.getSelectionModel().getSelectedItem().getLastName());
		VBox playerbox = new VBox(circle, name);
		playerbox.setId(String.valueOf(tablev.getSelectionModel().getSelectedIndex()));
		playerbox.setAlignment(Pos.CENTER);
		name.setAlignment(Pos.CENTER);
		exist.get(name.getText()).setValue(tablev.getSelectionModel().getSelectedIndex());
		if (exist.get(name.getText()).getKey() == 0) {
			Image im;
			im = new Image(String.format("/assets/Players/%s_%s.png", tablev.getSelectionModel().getSelectedItem().getName(), tablev.getSelectionModel().getSelectedItem().getLastName()));
			circle.setFill(new ImagePattern(im));
			playerbox.setOnMousePressed(circleOnMousePressedEventHandler);
			playerbox.setOnMouseDragged(circleOnMouseDraggedEventHandler);
			middle.getChildren().add(playerbox);
			exist.put(name.getText(), new AbstractMap.SimpleEntry<Integer, Integer>(1, tablev.getSelectionModel().getSelectedIndex()));
		}

		playerbox.addEventHandler(MouseEvent.MOUSE_CLICKED, (k1) -> {
			if (k1.getClickCount() == 2) {
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

	@FXML
	private void snap(MouseEvent event) throws IOException, ParseException {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<String> choices = new ArrayList<>();
		List<Date> dates = new ArrayList<>(GameCrud.findDatesByTeams(map.get(HomeTeam.getSelectionModel().getSelectedItem()),map.get(AwayTeam.getSelectionModel().getSelectedItem())));
		for (Date date : dates) {
			choices.add(formatter.format(date));
		}
		ChoiceDialog<String> dialog = new ChoiceDialog<>("Date", choices);
		dialog.setTitle("Choose a game");
		dialog.setContentText("Game Date:");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){

		BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
		File file = new File("/Users/simo/Desktop/myfile.png");
		WritableImage snapshot = middle.snapshot(new SnapshotParameters(), null);
		BufferedImage image;
		image = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, bufferedImage);
		try {
			Graphics2D gd = (Graphics2D) image.getGraphics();
			gd.translate(middle.getWidth(), middle.getHeight());
			ImageIO.write(image, "png", file);	
		//	Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
			GameCrud.updateSquad("Squads",file.getAbsolutePath(), (Date) Date.valueOf(result.get()));
			Notifications notificationBuilder
						= Notifications.create().title("Information")
								.text("Squad Sucessfully Added ")
								.hideAfter(Duration.seconds(6))
								.position(Pos.TOP_RIGHT)
								.darkStyle();

				notificationBuilder.showInformation();
				sendSMS.sendSms();
		} catch (IOException ex) {
			Logger.getLogger(SquadsController.class.getName()).log(Level.SEVERE, null, ex);
		};
	}
	}
}
