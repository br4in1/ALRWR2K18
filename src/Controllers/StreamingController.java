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
 * @author simo
 */
public class StreamingController implements Initializable {

	@FXML
	private FlowPane profileContent;
	@FXML
	private WebView wv1;
	@FXML
	private WebView wv2;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		wv1.getEngine().loadContent("<html><iframe width=\"972\" height=\"316\" src=\"https://www.youtube.com/embed/WoeBleqcc2E\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe></html>", "text/html");
		wv2.getEngine().load("http://us20.chatzy.com/28652836672604");
	}	
	
}
