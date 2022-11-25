package main.example.demo.service;

import java.util.List;

import main.example.demo.entity.Student;
import main.example.demo.request.CreateStudentRequest;
import main.example.demo.request.StudentAddressDetails;
import main.exapmle.demo.response.CreateStudentResponse;
import main.exapmle.demo.response.StudentCustomizedResponse;

public interface RelationService {

	CreateStudentResponse createStudentInformation(CreateStudentRequest createStudentRequest);

	Student addUserAddress(StudentAddressDetails address);

	List<StudentCustomizedResponse> getAllUser();

}
