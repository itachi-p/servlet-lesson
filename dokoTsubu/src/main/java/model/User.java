package model;

import java.io.Serializable;

public class User implements Serializable {
	private String name; // user name
	private String pass; // password
	
	public User() {}
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	public String getName() { return name; }
	public String getPass() { return pass; }
}
