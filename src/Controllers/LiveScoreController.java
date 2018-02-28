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
		//wv.getEngine().load("http://alrwr-2k18.eb2a.com/#inscore_ifheight_xdc_5280");
		wv.getEngine().loadContent("<html><center><iframe id=\"inscore-xdc-135961\" src=\"http://www.livescore.in/free/135961/\" width=\"624\" height=\"500\" frameborder=\"0\" scrolling=\"no\"></iframe>\n" +
"\n" +
"<script type=\"text/javascript\">/*<![CDATA[*/try{function inscore_135961_xdc(){this.elm = null;this.hash = null;var times_resized = 0;var times_not_resized = 0;this.resize = function(){times_resized == 1023 && (times_resized = 0);times_not_resized == 1023 && (times_not_resized = 0);if(this.getElm() && location.hash && location.hash != this.hash){this.hash = location.hash;var reggg = new RegExp(\".*inscore_ifheight_xdc_([0-9]{2,5}).*\");if(result=reggg.exec(location.hash)){this.getElm().style.height = (typeof result[1] == \"undefined\" ? \"10000\":(result[1] > 500 && result[1] <= 30000 ? parseInt(result[1]):500)) + \"px\";times_resized ++;}} else if(location.hash && location.hash == this.hash) times_not_resized ++;else return resize_later(75);resize_later(250);};var resize_later = function(time){setTimeout(function(){ inscore_135961_xdc_run.resize(); }, time);};this.getElm = function(){try {(typeof this.elm == \"undefined\" || this.elm === null || !this.elm) && (this.elm = document.getElementById(\"inscore-xdc-135961\"));} catch(e) { this.elm = null; }return this.elm;};var show_links = function(){if((times_resized >= 1 || times_not_resized >= 2) && (obj = document.getElementById(\"freescore_links_135961\"))){obj.style.visibility = \"visible\";obj.style.display = \"block\";} else show_links_later();};var show_links_later = function() { setTimeout(function(){ show_links(); }, 100); };if (typeof window.postMessage == \"undefined\"){show_links_later();resize_later();}else{var ev = function(event){try{var data = JSON.parse(event.data);}catch (e){return;}if (data instanceof Array && data[0] == 135961 && typeof data[1] != \"undefined\"){eval(data[1]);}};if (window.addEventListener){window.addEventListener( \"message\", ev);}else if ( window.attachEvent ) {window.attachEvent(\"onmessage\", ev);}setTimeout(function(){ document.getElementById(\"inscore-xdc-135961\").contentWindow.postMessage(JSON.stringify([\"135961\", \"run\"]), \"*\"); }, 2000);show_links_later();resize_later();}};var inscore_135961_xdc_run = new inscore_135961_xdc();}catch(e){document.getElementById(\"inscore-xdc-135961\").style.height = \"10000px\";}\n" +
"/*]]>*/</script></center></html>", "text/html");
	}
}
