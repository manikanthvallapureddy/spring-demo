package main.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtils {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// =new BCryptPasswordEncoder();
	// PasswordEncoder encoder = new BCryptPasswordEncoder();

	public String encryptPassword(String password) {
		return encrypt(password);
	}

	private String encrypt(String value) {
		return passwordEncoder().encode(value);
	}

	public String encode(String password) {

		String encode = passwordEncoder().encode(password);
		return encode;

	}

	public boolean passwordMaches(String oldPassword, String newPassword) {
		boolean matches = passwordEncoder().matches(newPassword, oldPassword);

		return matches;

	}

}
