package main.example.demo.controller;

public class Demo {

	public static void main(String[] args) {

		System.out.println(System.getenv("TEMP"));

		String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
//	    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
//	    private static final String VERIFICATION_SID = System.getenv("VERIFICATION_SID");

		System.out.println(ACCOUNT_SID);
	}

}
