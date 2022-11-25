package main.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import main.example.demo.entity.Student;
import main.example.demo.repo.LaptopRepo;
import main.example.demo.repo.StudentRepo;
import main.example.demo.request.CreateStudentRequest;
import main.example.demo.request.StudentAddressDetails;
import main.example.demo.service.RelationService;
import main.exapmle.demo.response.CreateStudentResponse;
import main.exapmle.demo.response.StudentCustomizedResponse;

@RestController
@RequestMapping("/api/v0/student")
public class RelationController {
	public static final String SUCCESS = "SUCCESS";
	@Autowired
	public RelationService relationService;

	@Autowired
	public StudentRepo repo;

	@Autowired
	public LaptopRepo laptopRepo;

	@PostMapping("/create")
	public ResponseEntity<CreateStudentResponse> createStudentInfo(
			@RequestBody CreateStudentRequest createStudentRequest) {
		CreateStudentResponse createStudentResponse = relationService.createStudentInformation(createStudentRequest);
		return ResponseEntity.ok(createStudentResponse);

	}

	@GetMapping("/getCustomerData")
	public ResponseEntity<List<StudentCustomizedResponse>> getAllUsers() {

		List<StudentCustomizedResponse> student = relationService.getAllUser();
		
		return ResponseEntity.ok(student);
	}

	@PostMapping("/address")
	public ResponseEntity<Student> addUserAddress(
			@RequestBody StudentAddressDetails address) {

		Student message = relationService.addUserAddress(address);

		return ResponseEntity.ok(message);
	}

}
