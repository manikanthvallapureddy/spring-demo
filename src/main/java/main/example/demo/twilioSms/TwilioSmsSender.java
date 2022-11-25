package main.example.demo.twilioSms;

import java.util.Random;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {
	@Autowired
	private TwilioConfiguration twilioConfiguration;

//	public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
//		
//		this.twilioConfiguration = twilioConfiguration;
//	}
	private boolean isPhoneNumberValid(String phoneNumber)  {
		
		PhoneNumberUtil numberUtil=PhoneNumberUtil.getInstance();
		StringBuilder sb = new StringBuilder();
		try {
		PhoneNumber number=numberUtil.parse(phoneNumber, phoneNumber);
		sb.append(number.getCountryCode());
		sb.append(number.getNationalNumber());
		}catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public void sendSms(SmsRequest smsRequest) {
		if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
			com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(smsRequest.getPhoneNumber());
			com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
			String message = smsRequest.getMessage();
			MessageCreator creator = Message.creator(to, from, message);
			creator.create();
		} else {
			throw new RuntimeException("Phone number is not valid");
		}
	}

	@Override
	public OtpResponse sendPhoneOtp(SmsRequest request) {
		com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(request.getPhoneNumber());
		com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
		// String message=request.getMessage();
		String ramdonNumber = Integer.toString(ramdonNumber());
		MessageCreator creator = Message.creator(to, from, ramdonNumber);
		creator.create();

		OtpResponse otpResponse = new OtpResponse();
		otpResponse.setSmsOtp("Your application Otp is " + ramdonNumber);

		return otpResponse;
	}

	public int ramdonNumber() {
		Random random = new Random();
		int nextInt = random.nextInt(1000000);
		return nextInt;
	}

}
