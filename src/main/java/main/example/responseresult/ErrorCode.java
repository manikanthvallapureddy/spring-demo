package main.example.responseresult;

import main.example.demo.Marker;

public enum ErrorCode {
	ACCOUNT_LIMIT_NOT_FOUND("217402", "account.limit.not.found", "Account Limit Category Not Found", 400,
			Marker.BAD_REQUEST);
	String errorCode;

	String errorKey;

	String errorDescription;

	int httpStatusCode;

	Marker errorType;

	private ErrorCode(String errorCode, String errorKey, String errorDescription, int httpStatusCode,
			Marker errorType) {
		this.errorCode = errorCode;
		this.errorKey = errorKey;
		this.errorDescription = errorDescription;
		this.httpStatusCode = httpStatusCode;
		this.errorType = errorType;
	}

}
