package main.exapmle.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegResponse {

	private Long id;

	private String message;
	
	private String status;
	
	private int otp;
}
