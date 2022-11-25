package main.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegRequest {

	private Long id;
//	@NotNull
//	@NotEmpty(message = "field not empty")
	private String email;

//	@NotNull
//	@Size(max = 10, min = 10, message = "Phone number should not be min and max")
	// @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "firstName is not accepting
	// digits and special characters")
	private String firstName;
	// @Size(max = 256, min = 3, message = "lastName should be greather than 3")
	private String lastName;
	// @Size(max = 12, min = 8, message = "Password should not be grether than {2}
	// and lessThan {1}")
	private String password;

	private PhoneNumberWithCountryCode phoneNumberWithCountryCode;
}
