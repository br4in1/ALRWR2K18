/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author br4in
 */
public class LiveScoreController implements Initializable {

	@FXML
	private WebView wv;
	@FXML
	private FlowPane nav;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		wv.getEngine().load("http://alrwr-2k18.eb2a.com/");
	}
}
