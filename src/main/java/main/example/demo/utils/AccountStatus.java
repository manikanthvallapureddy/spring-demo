package main.example.demo.utils;

import lombok.Getter;

@Getter
public enum AccountStatus {
	
	UNVERIFIED("Unverified", "Unverified"),
	ACTIVE("Active", "Activated");
	
	private String status;

	private String displayName;
	
	
	AccountStatus( String status, String displayName) {
		this.status = status;
		this.displayName = displayName;

	}
	

}
