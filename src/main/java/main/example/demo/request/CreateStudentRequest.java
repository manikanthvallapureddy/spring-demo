package main.example.demo.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.example.demo.entity.Laptop;
import main.example.demo.entity.Student;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

	private Student student;
	private List<Laptop> laptop;

}
