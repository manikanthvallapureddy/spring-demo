package main.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.example.demo.entity.Student;
import main.example.demo.entity.StudentAddress;
import main.example.demo.repo.LaptopRepo;
import main.example.demo.repo.StudentRepo;
import main.example.demo.request.CreateStudentRequest;
import main.example.demo.request.StudentAddressDetails;
import main.example.demo.service.RelationService;
import main.exapmle.demo.response.CreateStudentResponse;
import main.exapmle.demo.response.StudentCustomizedResponse;

@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	public StudentRepo studentRepo;

	@Autowired
	public LaptopRepo laptopRepo;

	@Autowired
	public StudentRepo addressRepo;

	@Override
	public CreateStudentResponse createStudentInformation(CreateStudentRequest createStudentRequest) {

		Student save = studentRepo.save(createStudentRequest.getStudent());
		System.out.println("Ok");

		CreateStudentResponse createStudentResponse = new CreateStudentResponse();
		createStudentResponse.setMessage("Student Data Added successfully");

		return createStudentResponse;
	}

	@Override
	public Student addUserAddress(StudentAddressDetails address) {

		Student save = studentRepo.save(address.getStudent());

//		Optional<Student> findById = studentRepo.findById(address.getId());
//		Student student = findById.get();
		// StudentAddressDetails addressDetails=new StudentAddressDetails();
//		student.getStudentAddress();

//		StudentAddress addressDetails=new StudentAddress();
//		
//		Student student=new Student();
//		
//		StudentAddress address2=new StudentAddress();
//		student.setId(100);
//		student.setMarkes(student.getMarkes());
//		student.setStudentname(student.getStudentname());
//		addressDetails.setAddressLine(student.getStudentAddress().getAddressLine());
//		addressDetails.setCity(student.getStudentAddress().getCity());
//		addressDetails.setCountry(student.getStudentAddress().getCountry());
//		addressDetails.setState(student.getStudentAddress().getState());

		return save;
	}

	@Override
	public List<StudentCustomizedResponse> getAllUser() {

		List<Student> findAll = studentRepo.findAll();

		List<StudentCustomizedResponse> customizedResponses = new ArrayList<>();
		for (Student student2 : findAll) {
			StudentCustomizedResponse customizedResponse = new StudentCustomizedResponse();
			customizedResponse.setId(student2.getId());
			customizedResponse.setStudentname(student2.getStudentname());
			customizedResponses.add(customizedResponse);
		}
		return customizedResponses;
	}

}
