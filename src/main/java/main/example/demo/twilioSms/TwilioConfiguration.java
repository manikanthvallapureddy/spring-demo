package main.example.demo.twilioSms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Configuration
@ConfigurationProperties("twilio")
public class TwilioConfiguration {

	private String accountSid;
	private String authToken;
	private String trialNumber;
	private String servicesid;
	@Override
	public String toString() {
		return "TwilioConfiguration [accountSid=" + accountSid + ", authToken=" + authToken + ", trialNumber="
				+ trialNumber + ", servicesid=" + servicesid + "]";
	}

	public String getAccountSid() {
		return accountSid;
	}
	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getTrialNumber() {
		return trialNumber;
	}
	public void setTrialNumber(String trialNumber) {
		this.trialNumber = trialNumber;
	}
	public String getServicesid() {
		return servicesid;
	}
	public void setServicesid(String servicesid) {
		this.servicesid = servicesid;
	}
	
	

}
