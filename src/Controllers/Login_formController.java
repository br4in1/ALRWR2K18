/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Services.UserCrud;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class Login_formController implements Initializable {

	@FXML
	private Pane loginform;
	@FXML
	private JFXTextField username;
	@FXML
	private JFXTextField email;
	@FXML
	private JFXTextField lastname;
	@FXML
	private JFXTextField firstname;
	@FXML
	private JFXPasswordField password;
	@FXML
	private JFXPasswordField passwordConfirm;
	@FXML
	private Pane signupform;
	@FXML
	private JFXComboBox<String> nationality;
	@FXML
	private JFXDatePicker birthdate;
	@FXML
	private JFXButton signupbtn;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		username.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue){
					Boolean exist = UserCrud.findUserByUsername(username.getText());
					if(exist) username.setUnFocusColor(Color.rgb(255, 0, 0, 1));
					else username.setUnFocusColor(Color.rgb(0, 0, 0, 1));
				}
			}
		});
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
	
	public void hideSignUpForm(){
		loginform.setVisible(true);
		signupform.setVisible(false);
	}
	
	public void CheckUsername(int RequestType){
		Boolean exist = UserCrud.findUserByUsername(username.getText());
		if(exist) signupbtn.setDisable(true);
	}
	
	public void SignUpUserStepOne(){
		
	}
}
