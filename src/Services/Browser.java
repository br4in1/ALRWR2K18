/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mark Allen, Norbert Bartels.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package Services;

import Entities.SimpleUser;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.User;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Browser extends Region {

	public static final String SUCCESS_URL = "http://localhost/";
	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();
	private String code;
	public int success;

	private final String appId;

	private final String appSecret;
	public static User u = null;

	public Browser(String appId, String appSecret) {
		this.appId = appId;
		this.appSecret = appSecret;
		// add the web view to the scene
		getChildren().add(browser);
		success = 0;
	}

	public void showLogin(Stage st) {
		DefaultFacebookClient facebookClient = new DefaultFacebookClient(Version.LATEST);
		ScopeBuilder scopes = new ScopeBuilder();
		scopes.addPermission(FacebookPermissions.EMAIL);
		scopes.addPermission(FacebookPermissions.PUBLIC_PROFILE);
		String loadUrl = facebookClient.getLoginDialogUrl(appId, SUCCESS_URL, scopes);
		webEngine.load(loadUrl + "&display=popup&response_type=code");
		webEngine.getLoadWorker().stateProperty().addListener(
				(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
					if (newValue != Worker.State.SUCCEEDED) {
						return;
					}

					String myUrl = webEngine.getLocation();

					if ("https://www.facebook.com/dialog/close".equals(myUrl)) {
						System.exit(0);
					}
					if (myUrl.startsWith(SUCCESS_URL)) {
						st.hide();
						int pos = myUrl.indexOf("code=");
						code = myUrl.substring(pos + "code=".length());
						FacebookClient.AccessToken token = facebookClient.obtainUserAccessToken(appId,
								appSecret, SUCCESS_URL, code);
						u = new DefaultFacebookClient(token.getAccessToken()).fetchObject("me", User.class,Parameter.with("fields", "first_name,last_name,email,birthday,location,picture"));
						System.out.println(u.toString());
						if (u != null) {
							success = 1;
							if (UserCrud.findUserByEmail(u.getEmail())) {
								
							} else {
								SimpleUser U = new SimpleUser(Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), (u.getLocation()!= null && u.getLocation().getName() != null) ? u.getLocation().getName() : "", true, 0, u.getPicture().getUrl(), u.getEmail(), u.getEmail(), false, "", u.getEmail() + u.getId(), Timestamp.valueOf(LocalDateTime.now()), "ROLE_USER", u.getLastName(), u.getFirstName());
								UserCrud.AddUserToDataBaseStepOne(U);
								SimpleUser.current_user = U;
							}
						}
					}
				});
	}

	@Override
	protected void layoutChildren() {
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height) {
		return 900;
	}

	@Override
	protected double computePrefHeight(double width) {
		return 600;
	}

}
