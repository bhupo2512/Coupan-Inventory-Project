/**
 * 
 */
package com.cvm.dto;

/**
 * @author Bhushan
 *
 */
public class User {
	private String nameOfPerson;
	private String userName;
	private String password;
	private String status;
	/**
	 * @return the nameOfPerson
	 */
	public String getNameOfPerson() {
		return nameOfPerson;
	}
	/**
	 * @param nameOfPerson the nameOfPerson to set
	 */
	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @param nameOfPerson
	 * @param userName
	 * @param password
	 * @param status
	 */
	public User(String nameOfPerson, String userName, String password, String status) {
		super();
		this.nameOfPerson = nameOfPerson;
		this.userName = userName;
		this.password = password;
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "User [nameOfPerson=" + nameOfPerson + ", userName=" + userName + ", password=" + password + ", status="
				+ status + "]";
	}
	/**
	 * 
	 */
	public User() {
		super();
	}
	
	
}
