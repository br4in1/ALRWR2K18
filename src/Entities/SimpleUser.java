/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Controllers.UsersCrudController;
import Controllers.UsersStatisticsController;
import Services.UserCrud;
import java.sql.Date;
import java.sql.Timestamp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author br4in
 */
public class SimpleUser extends User{
	private Date birthdate;
	private Date registrationdate;
	private String nationality;
	private Boolean loggedin;
	private int fidaelitypoints;
	private String profilepicture;
	public static SimpleUser current_user = null;

	public SimpleUser(Date birthdate, Date registrationdate, String nationality, Boolean loggedin, int fidaelitypoints, String profilepicture, String username, String email, Boolean enabled, String salt, String password, Timestamp last_login, String roles, String firstname, String lastname) {
		super(username, email, enabled, salt, password, last_login, roles, firstname, lastname);
		this.birthdate = birthdate;
		this.registrationdate = registrationdate;
		this.nationality = nationality;
		this.loggedin = loggedin;
		this.fidaelitypoints = fidaelitypoints;
		this.profilepicture = profilepicture;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Boolean getLoggedin() {
		return loggedin;
	}

	public void setLoggedin(Boolean loggedin) {
		this.loggedin = loggedin;
	}

	public int getFidaelitypoints() {
		return fidaelitypoints;
	}

	public void setFidaelitypoints(int fidaelitypoints) {
		this.fidaelitypoints = fidaelitypoints;
	}

	public String getProfilepicture() {
		return profilepicture;
	}

	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}
	
	public ImageView getEnabledStatus(){
		ImageView ret = new ImageView((this.enabled) ? "assets/greencircle.png" : "assets/redcircle.png");
		ret.setFitHeight(10);
		ret.setFitWidth(10);
		return ret;
	}
	
	public ImageView getLoggedinStatus(){
		ImageView ret = new ImageView((this.loggedin) ? "assets/greencircle.png" : "assets/redcircle.png");
		ret.setFitHeight(10);
		ret.setFitWidth(10);
		return ret;
	}
	
	public Button getBanButton(){
		Button ret = new Button((enabled) ? "Ban" : "Unban");
		ret.setPrefWidth(70);
		ret.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(enabled){
					UserCrud.BanSimpleUser(username);
					ret.setText("Unban");
				}
				else{
					UserCrud.UnBanSimpleUser(username);
					ret.setText("Ban");
				}
				UsersCrudController.thisController.RefreshData(new ActionEvent());
				UsersStatisticsController.thisController.RefreshData(new ActionEvent());
			}
		});
		return ret;
	}
}
