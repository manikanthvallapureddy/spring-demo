package main.example.except;

import main.example.demo.Marker;

public interface BaseErrorCode {
	
	String getErrorCode();

	String getErrorKey();

	String getErrorDescription();

	int getHttpStatusCode();

	Marker getErrorType();

}
