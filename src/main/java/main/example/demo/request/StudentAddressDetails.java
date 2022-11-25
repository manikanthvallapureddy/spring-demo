package main.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.example.demo.entity.Student;
import main.example.demo.entity.StudentAddress;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAddressDetails {

	private Student student;

}
