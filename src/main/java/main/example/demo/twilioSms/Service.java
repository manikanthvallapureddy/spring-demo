package main.example.demo.twilioSms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	private final TwilioSmsSender twilioSmsSender;

	public Service(@Qualifier("twilio")TwilioSmsSender twilioSmsSender) {
		super();
		this.twilioSmsSender = twilioSmsSender;
	}

	public void sendSms(SmsRequest smsRequest) {
		twilioSmsSender.sendSms(smsRequest);
	}

}
