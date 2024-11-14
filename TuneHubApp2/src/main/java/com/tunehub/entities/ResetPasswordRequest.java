package com.tunehub.entities;

public class ResetPasswordRequest {
	
	String email;
    String newPassword;
    String confirmNewPassword;
	public ResetPasswordRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResetPasswordRequest(String email, String newPassword, String confirmNewPassword) {
		super();
		this.email = email;
		this.newPassword = newPassword;
		this.confirmNewPassword = confirmNewPassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}
	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}
	@Override
	public String toString() {
		return "ResetPasswordRequest [email=" + email + ", newPassword=" + newPassword + ", confirmNewPassword="
				+ confirmNewPassword + "]";
	}
    
}
