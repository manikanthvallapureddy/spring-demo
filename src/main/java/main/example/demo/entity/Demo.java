package main.example.demo.entity;

import org.apache.tomcat.util.codec.binary.StringUtils;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Demo {

	public static void main(String[] args) throws NumberParseException {

//		PhoneNumberUtil numberUtil=PhoneNumberUtil.getInstance();
//		StringBuilder builder=new StringBuilder();
//		
//		PhoneNumber number=numberUtil.parse("us", "6787654567");
//		builder.append("us");
//		builder.append("6787654567");
//		System.out.println(builder.toString());
//	}
		
		
		String c="US";
		if(org.springframework.util.StringUtils.isEmpty(c) || !"Us".equalsIgnoreCase(c)) {
			System.out.println("Ok");
		}else {
			System.out.println("not ok");
		}
		
		
		

}
}