/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class Login_formController implements Initializable {

	@FXML
	private Pane loginform;

	@FXML
	private Pane signupform;
	
	@FXML
	private JFXComboBox<String> nationality;
	
	@FXML
	private JFXDatePicker birthdate;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	public void showSignUpForm() {
		loginform.setVisible(false);
		signupform.setVisible(true);
		ObservableList<String> cities = FXCollections.observableArrayList();
		String[] locales1 = Locale.getISOCountries();
		for (String countrylist : locales1) {
			Locale obj = new Locale("", countrylist);
			String[] city = {obj.getDisplayCountry()};
			for (int x = 0; x < city.length; x++) {
				cities.add(obj.getDisplayCountry());
			}
		}
		nationality.setItems(cities);
		nationality.setPromptText("Nationalité");
		Locale.setDefault(Locale.FRANCE);
	}
}
