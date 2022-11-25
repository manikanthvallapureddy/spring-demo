package main.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import main.example.demo.controller.Pagination;
import main.example.demo.entity.RegEntity;
import main.example.demo.request.ChangePasswordRequestDTO;
import main.example.demo.request.ForgotPasswordRequestDTO;
import main.example.demo.request.LoginRequest;
import main.example.demo.request.PhoneNumberRequest;
import main.example.demo.request.PhoneOtpRequest;
import main.example.demo.request.RegRequest;
import main.example.demo.request.UpdateEmailRequest;
import main.example.demo.twilioSms.ValidateOtp;
import main.exapmle.demo.response.ChangePasswordResponseDTO;
import main.exapmle.demo.response.ForgotPasswordResponseDTO;
import main.exapmle.demo.response.LoginResponse;
import main.exapmle.demo.response.OtpResponse;
import main.exapmle.demo.response.RegResponse;
import main.exapmle.demo.response.UpdateResponse;
import main.exapmle.demo.response.UploadImageResponse;
import main.exapmle.demo.response.UserResponse;

public interface RegService {

	RegResponse save(RegRequest regRequest);

	UpdateResponse update(UpdateEmailRequest emailRequest, Long id);

	UserResponse getUsers(Long id);

	void deleteUser(Long id);

	List<UpdateResponse> getTotalUsers(String serchKey);//

	LoginResponse login(LoginRequest loginRequest);

	ForgotPasswordResponseDTO forgorPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO);

	ChangePasswordResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, Long id);

	UserResponse getDataBasedOnEmail(Map<String, Object> map);

	List<UpdateResponse> getListOfData(PhoneNumberRequest phoneNumber);

	List<RegEntity> sortByField(String data);

	Page<RegEntity> sortByFieldWithPagination(Pagination pagination);

	UploadImageResponse upoadImage(MultipartFile file);

	List<UpdateResponse> getTotalCustomerDataBasedOnSearchKey(UpdateEmailRequest searchKey);


	OtpResponse validateSmsOtp(ValidateOtp validateOtp);

}
