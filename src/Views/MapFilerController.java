/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class MapFilerController implements Initializable {

	@FXML
	private JFXCheckBox hotel;
	@FXML
	private JFXCheckBox stades;
	@FXML
	private JFXCheckBox divertissement;
	@FXML
	private JFXCheckBox event;
	@FXML
	private ToggleGroup typeMap;
	@FXML
	private JFXRadioButton Male;
	@FXML
	private JFXRadioButton Female;
	@FXML
	private JFXRadioButton None;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}	
	
}
