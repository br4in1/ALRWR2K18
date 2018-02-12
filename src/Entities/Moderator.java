/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Timestamp;

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
}
