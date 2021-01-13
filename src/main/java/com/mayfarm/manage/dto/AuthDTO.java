package com.mayfarm.manage.dto;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection = "users")
public class AuthDTO {
	
	@Id
	private String id;
	private String username;
	private String password;
	@DateTimeFormat(pattern="YYYY-MM-DD-hh:mm")
	private Date writeDate;
	private String auth;
	
	@Override
	public String toString() {
		return "AuthDTO [id=" + id + ", username=" + username + ", password=" + password +
				", writeDate=" + writeDate + "]";
	}
}
