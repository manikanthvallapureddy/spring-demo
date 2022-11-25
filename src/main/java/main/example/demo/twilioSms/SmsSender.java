package main.example.demo.twilioSms;

public interface SmsSender {

	void sendSms(SmsRequest request);

	OtpResponse sendPhoneOtp(SmsRequest request);
}
