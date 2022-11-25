package main.example.demo.validator;

import org.springframework.util.StringUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberValidator {

	public static String getCountryCode(String countryCode, String phoneNumber) {
		validatePhoneCountry(countryCode);
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		StringBuilder sb = new StringBuilder();
		try {
			PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, countryCode.toUpperCase());
			sb.append(String.valueOf(phoneNumberProto.getCountryCode()));
			sb.append(String.valueOf(phoneNumberProto.getNationalNumber()));

		} catch (NumberParseException e) {
			throw new RuntimeException("countryCode  is not valid");
		}
		return sb.toString();
	}
	
	public static String getPhoneNumber(String countryCode, String phoneNumber) {
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		StringBuilder sb = new StringBuilder();
		try {
			PhoneNumber phoneNumberProto = phoneUtil.parse(phoneNumber, countryCode.toUpperCase());
			sb.append(String.valueOf(phoneNumberProto.getCountryCode()));
		} catch (NumberParseException e) {
			throw new RuntimeException("countryCode  is not valid");
		}
		return sb.toString();
	}

	public static void validatePhoneCountry(String countryCode) {
		if (StringUtils.isEmpty(countryCode) || !"US".equalsIgnoreCase(countryCode) ) {
			throw new RuntimeException("countryCode Number is not valid");
		}
	}
}
