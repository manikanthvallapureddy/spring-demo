package main.example.demo;

public interface BaseErrorCode {
	String getErrorCode();

	String getErrorKey();

	String getErrorDescription();

	int getHttpStatusCode();

	Marker getErrorType();
}
