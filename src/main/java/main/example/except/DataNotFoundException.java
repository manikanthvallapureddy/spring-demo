package main.example.except;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import main.example.demo.BaseErrorCode;


@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class DataNotFoundException extends RuntimeException{
	
	
//	private String message;
//	
//	 public DataNotFoundException(String message) {
//         this.message = message;
//     }
//     public String getMessage() {
//         return message;
//     }
//     public void setMessage(String message) {
//         this.message = message;
//     }
     
     private static final long serialVersionUID = 7061712410511294626L;

 	private BaseErrorCode errorCode;

 	private Class<?> exceptionOccuredClass;

 	private Object data;
 	
 	private Object[] arguments;
 	
 	public DataNotFoundException(BaseErrorCode errorCode, Class<?> exceptionOccuredClass) {
 		this.errorCode = errorCode;
 		this.exceptionOccuredClass = exceptionOccuredClass;
 	}
 	
 	public DataNotFoundException(BaseErrorCode errorCode, Class<?> exceptionOccuredClass,Object ...arguments) {
 		this.errorCode = errorCode;
 		this.exceptionOccuredClass = exceptionOccuredClass;
 		this.arguments = arguments;
 	}
}
