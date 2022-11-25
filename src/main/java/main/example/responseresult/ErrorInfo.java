package main.example.responseresult;

import java.util.List;

public class ErrorInfo {
	private String errorCode;
	private String errorDescription;
	List<String> fieldErrors;
	public ErrorInfo(String errorCode, String errorDescription, List<String> fieldErrors) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.fieldErrors = fieldErrors;
	}
}
