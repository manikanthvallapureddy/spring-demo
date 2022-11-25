package main.example.demo.twilioSms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sms")
public class TwilioController {

	@Autowired
	public Service service;

	@Autowired
	public SmsSender smsSender;

	@PostMapping
	public void sendSms(@RequestBody SmsRequest request) {
		smsSender.sendSms(request);
	}

	@PostMapping("/otp")
	public ResponseEntity<OtpResponse> sendPhoneOtp(@RequestBody SmsRequest request) {

		OtpResponse otpResponse = smsSender.sendPhoneOtp(request);

		return ResponseEntity.ok(otpResponse);

	}

}
