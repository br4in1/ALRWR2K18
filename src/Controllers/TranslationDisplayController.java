/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Sof
 */
public class TranslationDisplayController implements Initializable {

	@FXML
	private FlowPane mainMa;
	@FXML
	private ImageView rusia2;
	@FXML
	private ImageView rusia1;
	@FXML
	private ImageView france1;
	@FXML
	private ImageView eng1;
	@FXML
	private ImageView france2;
	@FXML
	private ImageView eng2;
	@FXML
	private TextField from;
	@FXML
	private TextField to;

	private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
	private static final String CLIENT_SECRET = "PUBLIC_SECRET";
	private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";
	private TextToSpeech tts = new TextToSpeech();
	private float VoiceVolume;
	String fromlang="";
	String tolang ="";
	static String res ;
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
	}

	@FXML
	private void rusia1selected(MouseEvent event) {
		rusia1.setOpacity(1);
		france1.setOpacity(0.5);
		eng1.setOpacity(0.5);
		fromlang="ru";
	}

	@FXML
	private void france1selected(MouseEvent event) {
		rusia1.setOpacity(0.5);
		france1.setOpacity(1);
		eng1.setOpacity(0.5);
		fromlang="fr";
	}

	@FXML
	private void eng1selected(MouseEvent event) {
		rusia1.setOpacity(0.5);
		france1.setOpacity(0.5);
		eng1.setOpacity(1);
		fromlang="en";
	}

	@FXML
	private void russia2selected(MouseEvent event) {
		rusia2.setOpacity(1);
		france2.setOpacity(0.5);
		eng2.setOpacity(0.5);
		tolang="ru";
	}

	@FXML
	private void france2selected(MouseEvent event) {
		rusia2.setOpacity(0.5);
		france2.setOpacity(1);
		eng2.setOpacity(0.5);
		tolang="fr";
	}

	@FXML
	private void eng2selected(MouseEvent event) {
		rusia2.setOpacity(0.5);
		france2.setOpacity(0.5);
		eng2.setOpacity(1);
		tolang="en";
	}

	@FXML
	private void translate(MouseEvent event) throws Exception {
		if (!"".equals(from.getText()) &&!"".equals(tolang) && !"".equals(fromlang)) {
			translate(fromlang, tolang, from.getText());
			to.setText(res);
		} else {
			tts.setVoice("cmu-rms-hsmm");
			VoiceVolume = 2;
			tts.speak("Invalid data", VoiceVolume, false, false);
		}
	}

	@FXML
	private void sound1(MouseEvent event) {

		tts.setVoice("cmu-rms-hsmm");
		VoiceVolume = 2;
		tts.speak(from.getText(), VoiceVolume, false, false);
	}

	@FXML
	private void sound2(MouseEvent event) {

		tts.setVoice("cmu-rms-hsmm");
		VoiceVolume = 2;
		tts.speak(to.getText(), VoiceVolume, false, false);
	}

	public static void translate(String fromLang, String toLang, String text) throws Exception {
		// TODO: Should have used a 3rd party library to make a JSON string from an object
		String jsonPayload = new StringBuilder()
				.append("{")
				.append("\"fromLang\":\"")
				.append(fromLang)
				.append("\",")
				.append("\"toLang\":\"")
				.append(toLang)
				.append("\",")
				.append("\"text\":\"")
				.append(text)
				.append("\"")
				.append("}")
				.toString();

		URL url = new URL(ENDPOINT);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		System.out.println(conn);
		System.out.println(url);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
		conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
		conn.setRequestProperty("Content-Type", "application/json");

		OutputStream os = conn.getOutputStream();
		os.write(jsonPayload.getBytes());
		os.flush();
		os.close();

		int statusCode = conn.getResponseCode();
		System.out.println("Status Code: " + statusCode);

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
		));
		String output;
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			res=output;

		}
		conn.disconnect();
	}

}
