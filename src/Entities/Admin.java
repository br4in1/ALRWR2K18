/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author br4in
 */
public class Admin extends Moderator{

	public Admin(String phonenumber, String username, String email, Boolean enabled, String salt, String password, Date last_login, String roles, String firstname, String lastname) {
		super(phonenumber, username, email, enabled, salt, password, last_login, roles, firstname, lastname);
	}
}
