package main.example.responseresult;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@Getter
//@AllArgsConstructor
@NoArgsConstructor
public class DemoResponse<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 14567898765434l;
	
	private String Status;
	
	private Date timestamp;

	private T data;

	private ErrorInfo error;

	public DemoResponse(String status, Date timestamp, T data, ErrorInfo error) {
		super();
		Status = status;
		this.timestamp = timestamp;
		this.data = data;
		this.error = error;
	}


}
