package main.example.responseresult;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class ResponseUtil {


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static DemoResponse generateResponse(String Status, Object data, ErrorInfo errorInfo) {
		return new DemoResponse(Status, null, data, errorInfo);

	}
	
	public static boolean isObjectNull(Object obj) {
		return obj == null;
	}
}

