package main.example.demo.request;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberRequest {

	
	//@Size(max = 10,min = 10, message = "{Phone number should not be min and max 10 digits}")
	private Long phoneNumber;
}
