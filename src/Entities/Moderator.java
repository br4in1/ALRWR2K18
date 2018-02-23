/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Controllers.ModeratorsCrudController;
import Services.UserCrud;
import java.sql.Date;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author br4in
 */
public class Moderator extends User{
	protected String phonenumber;
	public static Moderator current_user = null;

	public Moderator(String phonenumber, String username, String email, Boolean enabled, String salt, String password, Timestamp last_login, String roles, String firstname, String lastname) {
		super(username, email, enabled, salt, password, last_login, roles, firstname, lastname);
		this.phonenumber = phonenumber;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public Button getBanButton(){
		Button ret = new Button((this.enabled) ? "Ban" : "Unban");
		//ret.setPrefWidth(40);
		ret.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(enabled) UserCrud.BanModerator(username);
				else UserCrud.UnbanModerator(username);
				ModeratorsCrudController.thisController.RefreshData(event);
			}
		});
		return ret;
	}
	
	public ImageView getEnabledStatus(){
		ImageView ret = new ImageView((this.enabled) ? "assets/greencircle.png" : "assets/redcircle.png");
		ret.setFitHeight(10);
		ret.setFitWidth(10);
		return ret;
	}
}
