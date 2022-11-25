package main.example.demo.twilioSms;

import java.util.logging.Logger;

import org.springframework.context.annotation.Configuration;

import com.twilio.Twilio;


@Configuration
public class TwilioInitializer {
	
	 private final static Logger LOGGER = Logger.getLogger(TwilioInitializer.class.getName());

	private final TwilioConfiguration twilioConfiguration;

	public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
		this.twilioConfiguration = twilioConfiguration;
		Twilio.init(twilioConfiguration.getAccountSid(), 
				twilioConfiguration.getAuthToken());
	}
	

}
