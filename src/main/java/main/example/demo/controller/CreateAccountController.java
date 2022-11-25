package main.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import io.swagger.annotations.ApiOperation;
import main.example.demo.entity.RegEntity;
import main.example.demo.request.ChangePasswordRequestDTO;
import main.example.demo.request.ForgotPasswordRequestDTO;
import main.example.demo.request.LoginRequest;
import main.example.demo.request.PhoneNumberRequest;
import main.example.demo.request.PhoneOtpRequest;
import main.example.demo.request.RegRequest;
import main.example.demo.request.UpdateEmailRequest;
import main.example.demo.service.RegService;
import main.example.demo.twilioSms.TwilioConfiguration;
import main.example.demo.twilioSms.ValidateOtp;
import main.example.responseresult.DemoResponse;
import main.example.responseresult.ResponseUtil;
import main.exapmle.demo.response.ChangePasswordResponseDTO;
import main.exapmle.demo.response.ForgotPasswordResponseDTO;
import main.exapmle.demo.response.LoginResponse;
import main.exapmle.demo.response.OtpResponse;
import main.exapmle.demo.response.RegResponse;
import main.exapmle.demo.response.UpdateResponse;
import main.exapmle.demo.response.UploadImageResponse;
import main.exapmle.demo.response.UserResponse;

@RestController
@RequestMapping("/api/v0/reg")
public class CreateAccountController {

	public static final String SUCCESS = "SUCCESS";
	@Autowired
	RegService regService;

	@PostMapping(value = "/save", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
	@ApiOperation("Create a new user")
	public ResponseEntity<DemoResponse<RegResponse>> createAccount(@RequestBody @Validated RegRequest regRequest) {
		RegResponse regResponse = regService.save(regRequest);
		// return ResponseEntity.ok(regResponse);

		return new ResponseEntity<>(ResponseUtil.generateResponse(SUCCESS, regResponse, null), HttpStatus.OK);

	}
	@Autowired
	public TwilioConfiguration twilioConfiguration;

	@PostMapping(value = "/validateSmsOtp",produces = { "application/json" })
	public ResponseEntity<OtpResponse> validateSmsOtp(@RequestBody ValidateOtp validateOtp) {
		System.out.println(twilioConfiguration.getServicesid());
		OtpResponse otpResponse=	regService.validateSmsOtp(validateOtp);
		return ResponseEntity.ok(otpResponse);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<UpdateResponse> updateEmailBasedOnId(@PathVariable(value = "id") Long id,
			@RequestBody UpdateEmailRequest emailRequest) {
		UpdateResponse response = regService.update(emailRequest, id);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/data")
	@ApiOperation("get Data based on email")
	public ResponseEntity<UserResponse> getByEmail(@RequestParam Map<String, Object> map) {

		UserResponse response = regService.getDataBasedOnEmail(map);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	@ApiOperation("Get Customre by id")
	public ResponseEntity<UserResponse> getCustomerData(@PathVariable(value = "id") Long id) {
		UserResponse response = regService.getUsers(id);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	@ApiOperation("delete customer based on id")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable(value = "id") Long id) {
		regService.deleteUser(id);
		Map<String, String> map = new HashMap<>();
		map.put("massage", "Id deleted sucessfully");
		return ResponseEntity.ok(map);
	}

	@GetMapping
	@ApiOperation("Get All Customres")
	public ResponseEntity<List<UpdateResponse>> getTotalCustomerData(@RequestParam(required = false) String searchKey) {// @RequestParam(required
																														// =
																														// false)
																														// String
		// searchKey

//		if (searchKey == null) {
//			searchKey = "%";
//		}
		List<UpdateResponse> response = regService.getTotalUsers(searchKey);// searchKey
		return ResponseEntity.ok(response);
	}

	@GetMapping("/searchKey")
	@ApiOperation("Get All Customres")
	public ResponseEntity<List<UpdateResponse>> getTotalCustomerDataBasedOnSearchKey(
			@RequestBody UpdateEmailRequest searchKey) {// @RequestParam(required
		// false)
		if (searchKey.getSearchKey().isEmpty()) {
			throw new RuntimeCryptoException("element is not null");
		}
		List<UpdateResponse> response = regService.getTotalCustomerDataBasedOnSearchKey(searchKey);// searchKey
		return ResponseEntity.ok(response);
	}

	@GetMapping("/phoneNumber")
	public ResponseEntity<List<UpdateResponse>> getListDataBasedOnEmail(
			@Validated @RequestBody PhoneNumberRequest phoneNumber) {
		List<UpdateResponse> response = regService.getListOfData(phoneNumber);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	@ApiOperation("Login to the user")
	public ResponseEntity<LoginResponse> login(@RequestBody @Validated LoginRequest loginRequest) {
		LoginResponse loginResponse = regService.login(loginRequest);

		return ResponseEntity.ok(loginResponse);

	}

	@GetMapping("/forgotPassword")
	@ApiOperation("ForgotPassword")
	public ResponseEntity<ForgotPasswordResponseDTO> forgotPassword(
			@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
		ForgotPasswordResponseDTO forgotPasswordResponse = regService.forgorPassword(forgotPasswordRequestDTO);

		return ResponseEntity.ok(forgotPasswordResponse);
	}

	@PostMapping("/change-password/{id}")
	@ApiOperation("Change Password")
	public ResponseEntity<ChangePasswordResponseDTO> changePassword(@PathVariable(value = "id") Long id,
			@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
		ChangePasswordResponseDTO changePasswordResponseDTO = regService.changePassword(changePasswordRequestDTO, id);

		return ResponseEntity.ok(changePasswordResponseDTO);

	}

	@GetMapping("/page/{data}/{offset}/{page}")
	@ApiOperation("Sorting  ")
	public ResponseEntity<List<RegEntity>> display(@PathVariable String data) {
		if (data == null || data.isEmpty()) {
			throw new IllegalArgumentException("You have to provide at least one property to sort by");
		}
		List<RegEntity> entities = regService.sortByField(data);
		return ResponseEntity.ok(entities);

	}

	@GetMapping("/page")
	@ApiOperation("Sorting and pagination ")
	public ResponseEntity<Page<RegEntity>> paginations(@RequestBody Pagination pa) {

		Page<RegEntity> entities = regService.sortByFieldWithPagination(pa);
		return ResponseEntity.ok(entities);

	}

	@PostMapping(value = "/upload-image", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<UploadImageResponse> uploadImage(@RequestParam @ModelAttribute MultipartFile file) {

		UploadImageResponse upoadImage = regService.upoadImage(file);
		return ResponseEntity.ok(upoadImage);

	}

}
