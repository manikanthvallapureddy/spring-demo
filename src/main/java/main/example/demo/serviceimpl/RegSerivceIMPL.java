package main.example.demo.serviceimpl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

//import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import com.twilio.rest.verify.v2.service.VerificationCheckCreator;
import com.twilio.rest.verify.v2.service.VerificationCreator;

import main.example.demo.controller.Pagination;
import main.example.demo.entity.RegEntity;
import main.example.demo.repo.RegREPO;
import main.example.demo.request.ChangePasswordRequestDTO;
import main.example.demo.request.ForgotPasswordRequestDTO;
import main.example.demo.request.LoginRequest;
import main.example.demo.request.PhoneNumberRequest;
import main.example.demo.request.RegRequest;
import main.example.demo.request.UpdateEmailRequest;
import main.example.demo.service.RegService;
import main.example.demo.twilioSms.TwilioConfiguration;
import main.example.demo.twilioSms.ValidateOtp;
import main.example.demo.utils.AccountStatus;
import main.example.demo.validator.PhoneNumberValidator;
import main.example.des.UploadFileFormat;
import main.exapmle.demo.response.ChangePasswordResponseDTO;
import main.exapmle.demo.response.ForgotPasswordResponseDTO;
import main.exapmle.demo.response.LoginResponse;
import main.exapmle.demo.response.OtpResponse;
import main.exapmle.demo.response.RegResponse;
import main.exapmle.demo.response.UpdateResponse;
import main.exapmle.demo.response.UploadImageResponse;
import main.exapmle.demo.response.UserResponse;

@Service
public class RegSerivceIMPL implements RegService {

	@Autowired
	RegREPO regREPO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public EncryptionUtils encryptionUtils;

	@Override
	public RegResponse save(RegRequest regRequest) {

		RegEntity entity = new RegEntity();
		entity.setEmail(regRequest.getEmail());
		// Optional<RegEntity> findByEmail = regREPO.findByEmail(regRequest.getEmail());

//		if (findByEmail.isPresent()) {
//			throw new RuntimeException("Email already present in the system");
//		}
		entity.setFirstName(regRequest.getFirstName());
		entity.setLastName(regRequest.getLastName());
		String phoneNumber2 = regRequest.getPhoneNumberWithCountryCode().getPhoneNumber();
		String countryCode = regRequest.getPhoneNumberWithCountryCode().getCountrycode();
		PhoneNumberValidator.getCountryCode(countryCode, phoneNumber2);
		entity.setPhoneNumber(phoneNumber2);
		entity.setPassword(passwordEncoder.encode(regRequest.getPassword()));
		entity.setCountrycode(countryCode);
		entity.setStatus(AccountStatus.UNVERIFIED.getStatus());
		RegEntity saveR = regREPO.save(entity);
		smsOtp(phoneNumber2);
		RegResponse regResponse = new RegResponse();
		regResponse.setId(saveR.getId());
		regResponse.setStatus(AccountStatus.UNVERIFIED.getStatus());
		regResponse.setMessage("Account Created successfully");
		return regResponse;
	}

	@Override
	public UpdateResponse update(UpdateEmailRequest emailRequest, Long id) {
		Optional<RegEntity> findById = regREPO.findById(id);
		if (!findById.isPresent()) {
			// throw new DataNotFoundException(" id is not present");
			// System.out.println("Id Is not present");
		}
		RegEntity regEntity = findById.get();
		regEntity.setEmail(emailRequest.getEmail());
		RegEntity save = regREPO.save(regEntity);
		UpdateResponse response = new UpdateResponse();
		response.setId(save.getId());
		response.setEmail(save.getEmail());
		response.setMessage("Message Updated");

		return response;
	}

	@Override
	public UserResponse getUsers(Long id) {
		Optional<RegEntity> findById = regREPO.findById(id);
		RegEntity regEntity = findById.get();
		UserResponse response = new UserResponse();
		response.setId(regEntity.getId());
		response.setEmail(regEntity.getEmail());
		response.setFirstName(regEntity.getFirstName());
		response.setLastName(regEntity.getLastName());
		response.setPhoneNumber(regEntity.getPhoneNumber());

		return response;
	}

	@Override
	public void deleteUser(Long id) {

		regREPO.deleteById(id);

	}

	@Override
	public List<UpdateResponse> getTotalUsers(String searchKey) {// String searchKey
		// List<RegEntity> findAll = regREPO.findAll();

		List<RegEntity> findAlls = regREPO.findByRegEntity(searchKey);// searchKey

		List<UpdateResponse> list = new ArrayList<>();
		for (RegEntity entity : findAlls) {

			UpdateResponse response = new UpdateResponse();
			response.setEmail(entity.getEmail());
			response.setId(entity.getId());
			response.setMessage("get User data");
			list.add(response);
		}

		return list;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {

		Optional<RegEntity> findByEmail2 = regREPO.findByEmail(loginRequest.getEmail());
		if (!findByEmail2.isPresent()) {
			throw new RuntimeException("Email is no present in the eco system");
		}
		RegEntity regEntity = findByEmail2.get();
		boolean matches = passwordEncoder.matches(loginRequest.getPassword(), regEntity.getPassword());

		// Encryption encryption=new Encryption();
		// encryption.passwordMaches(null, null)

		if (matches == false) {
			throw new RuntimeException("email or password is empty");
		}
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setMessage("Login Successfully");

		return loginResponse;

	}

	@Override
	public ForgotPasswordResponseDTO forgorPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {

		Optional<RegEntity> findByFirstNameAndLastNameandandEmail = regREPO.findByEmailAndFirstNameAndLastName(
				forgotPasswordRequestDTO.getEmail(), forgotPasswordRequestDTO.getFirstName(),
				forgotPasswordRequestDTO.getLastName());

		if (!findByFirstNameAndLastNameandandEmail.isPresent()) {
			throw new RuntimeException("email or First Name or Last is empty or incorrect");
		}
		RegEntity entity = findByFirstNameAndLastNameandandEmail.get();
//	String encode = passwordEncoder.encode(forgotPasswordRequestDTO.getNewPassword());
		RegEntity entity2 = new RegEntity();
		entity.setPassword(passwordEncoder.encode(forgotPasswordRequestDTO.getNewPassword()));
		regREPO.save(entity);
		ForgotPasswordResponseDTO forgotPasswordResponse = new ForgotPasswordResponseDTO();
		forgotPasswordResponse.setMessage("User Password updated successfully");
		return forgotPasswordResponse;
	}

	@Override
	public ChangePasswordResponseDTO changePassword(ChangePasswordRequestDTO changePasswordRequestDTO, Long id) {

		Optional<RegEntity> findByPassword = regREPO.findById(id);
		RegEntity regEntity = findByPassword.get();

		boolean passwordMaches = passwordEncoder.matches(changePasswordRequestDTO.getPassword(),
				regEntity.getPassword());

		if (passwordMaches == false) {
			throw new RuntimeException("Password is not matched");
		}

		ChangePasswordResponseDTO changePasswordResponseDTO = new ChangePasswordResponseDTO();
		regEntity.setPassword(passwordEncoder.encode(changePasswordRequestDTO.getNewPassword()));
		RegEntity save = regREPO.save(regEntity);

		changePasswordResponseDTO.setEmail(save.getEmail());
		changePasswordResponseDTO.setMessage("Change password updated successfully");
		return changePasswordResponseDTO;
	}

	@Override
	public UserResponse getDataBasedOnEmail(Map<String, Object> map) {
		String object = map.get("email").toString();
		Optional<RegEntity> findByEmailData = regREPO.findByEmailData(object);
		RegEntity regEntity = findByEmailData.get();
		UserResponse response = new UserResponse();

		response.setId(regEntity.getId());
		response.setEmail(regEntity.getEmail());
		response.setFirstName(regEntity.getFirstName());
		response.setLastName(regEntity.getLastName());
		response.setPhoneNumber(regEntity.getPhoneNumber());

		return response;

	}

	@Override
	public List<UpdateResponse> getListOfData(PhoneNumberRequest phoneNumber) {

		List<RegEntity> findByPhoneNumber = regREPO.findByPhoneNumber(phoneNumber.getPhoneNumber());
		if (findByPhoneNumber.isEmpty()) {
			// throw new DataNotFoundException("phone numbers is not empty");
		}

		List<UpdateResponse> response = new ArrayList<>();
		for (RegEntity regEntity : findByPhoneNumber) {
			// List<UpdateResponse> list = new ArrayList<>();
			UpdateResponse responses = new UpdateResponse();
			responses.setEmail(regEntity.getEmail());
			responses.setId(regEntity.getId());
			responses.setMessage("getting data succesfully");
			response.add(responses);
		}

		return response;
	}

	public List<RegEntity> findByPagination(String field) {

		return regREPO.findAll(org.springframework.data.domain.Sort.by(Direction.ASC));

	}

	@Override
	public List<RegEntity> sortByField(String data) {
		// TODO Auto-generated method stub
		// regREPO.findAll(org.springframework.data.domain.Sort.by(Direction.ASC),
		// data);

		return regREPO.findAll(org.springframework.data.domain.Sort.by(Direction.ASC, data));
	}

	@Override
	public Page<RegEntity> sortByFieldWithPagination(Pagination pagination) {
		Page<RegEntity> findAll = regREPO.findAll(PageRequest.of(pagination.getOffset(), pagination.pagesize,
				org.springframework.data.domain.Sort.by(Direction.ASC, pagination.data)));

//		Stream<RegEntity> strea = findAll.get();
//		
//		List<RegEntity> entities=new ArrayList<>();
		UpdateResponse response = new UpdateResponse();
//		strea.setEmail(entities.g);

		return findAll;
	}

	@Value("${upload.filesize.image:11000000}")
	private Long uploadFileImage;

	@Override
	public UploadImageResponse upoadImage(MultipartFile file) {
		if (null == file || file.isEmpty()) {
			throw new RuntimeException("file should not be Empty");
		}

		long size = file.getSize();
		if (size > uploadFileImage) {
			throw new RuntimeException("Upload file size should not be 5 Mb");

		}

		// long size = file.getSize();
		String originalFilename = org.springframework.util.StringUtils.getFilenameExtension(file.getOriginalFilename());
		new UploadFileFormat().fileFormat(originalFilename, file);
		if (size > uploadFileImage) {
			throw new RuntimeException("Upload file size should not be 5 Mb");

		}

		RegEntity regEntity = regREPO.findById(26l).get();
		// String originalFilename = file.getOriginalFilename();

		System.out.println(size + "----------------------------------------------------------");
		// RegEntity entity = new RegEntity();
		UploadImageResponse uploadImageResponse = new UploadImageResponse();
		regEntity.setImage(originalFilename);

		// =Base64.getEncoder().encodeToString(originalFilename);
		RegEntity save = regREPO.save(regEntity);

		uploadImageResponse.setFileName(originalFilename);
		uploadImageResponse.setMessage("File uploaded successfully");
		uploadImageResponse.setSize(size);

		return uploadImageResponse;
	}

	@Override
	public List<UpdateResponse> getTotalCustomerDataBasedOnSearchKey(UpdateEmailRequest searchKey) {

		List<RegEntity> findAll = regREPO.findByRegEntity(searchKey.getSearchKey());

		List<UpdateResponse> list = new ArrayList<>();
		for (RegEntity regEntity : findAll) {
			UpdateResponse response = new UpdateResponse();
			response.setEmail(regEntity.getEmail());
			response.setId(regEntity.getId());
			response.setMessage("get User data");
			list.add(response);
		}

		return list;
	}

	HashMap<String, String> hashMap = new HashMap<>();

	@Autowired
	public TwilioConfiguration twilioConfiguration;

	public void smsOtp(String phoneNumber) {

		com.twilio.type.PhoneNumber to = new com.twilio.type.PhoneNumber(phoneNumber + "");
		com.twilio.type.PhoneNumber from = new com.twilio.type.PhoneNumber(twilioConfiguration.getTrialNumber());
		String ramdonNumber = ramdonNumber();
		String message = "Your Appicationone time password is :" + ramdonNumber;
		MessageCreator creator = Message.creator(to, from, message);
		System.out.println(creator.toString());
		hashMap.put(ramdonNumber, phoneNumber);
		Message create = creator.create();

//		String body = create.getBody();
//		return body;

	}

	public OtpResponse validateSmsOtp(ValidateOtp validateOtp) {// Long phoneNumber, String otp) {

		Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());

//		VerificationCheck verificationCheck = VerificationCheck.creator(twilioConfiguration.getServicesid())
//				.setTo(validateOtp.getPhoneNumber()).setCode(validateOtp.getOtp()).setAmount("").setPayee("").create();
//		System.out.println(verificationCheck);
//		verificationCheck.setVerificationSid(twilioConfiguration.getAccountSid())
//		.setTo(validateOtp.getPhoneNumber()).setCode(validateOtp.getOtp()).create();
		String accountServiceid = twilioConfiguration.getServicesid();
		System.out.println(accountServiceid + "-----------------------------------------------------");
		 Verification verification = Verification.creator(accountServiceid,
		 validateOtp.getPhoneNumber(), "sms").create();

		if ("approved".equals(verification.getStatus()))
			return new OtpResponse("Valid otp");
		else
			return new OtpResponse("invalid code");

	}

	private boolean isPhoneNumberValid(String phoneNumber) {

		PhoneNumberUtil numberUtil = PhoneNumberUtil.getInstance();
		StringBuilder sb = new StringBuilder();
		try {
			PhoneNumber number = numberUtil.parse(phoneNumber, phoneNumber);
			sb.append(number.getCountryCode());
			sb.append(number.getNationalNumber());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}

	public String ramdonNumber() {
		return new DecimalFormat("000000").format(new Random().nextInt(999999));
	}

}
