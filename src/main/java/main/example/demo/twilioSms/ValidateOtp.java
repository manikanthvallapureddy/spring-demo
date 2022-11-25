package main.example.demo.twilioSms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateOtp {

	private String phoneNumber;
	private String otp;
}
