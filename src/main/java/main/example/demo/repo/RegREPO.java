package main.example.demo.repo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import main.example.demo.entity.RegEntity;

@Repository
public interface RegREPO extends JpaRepository<RegEntity, Long> {

	@Query(value = "select * from users", nativeQuery = true)
	List<RegEntity> getAllCustomer();

	Optional<RegEntity> findByEmailAndPassword(String email, String password);

	@Query(value = "select * from users where email=:password", nativeQuery = true)
	RegEntity findByPassword(String password);

	Optional<RegEntity> findByEmailAndFirstNameAndLastName(String email, String firstName, String lastName);

	@Query(value = "select * from users where email=:map", nativeQuery = true)
	Optional<RegEntity> findByEmailData(String map);

	@Query(value = "select * from users where phone_number=:phoneNumber", nativeQuery = true)
	List<RegEntity> findByPhoneNumber(Long phoneNumber);

	Optional<RegEntity> findByEmail(String email);

	@Query(value = "select * from users u  where u.email =?1", nativeQuery = true)
	List<RegEntity> findAll(String searchKey);

//lower(CONCAT(descriptorName,processingPlatform.name,dailyLimit,processedToday,dailyCapAvailable,monthlyLimit,processedMTD,monthlyCapAvailable)) like lower(concat('%', concat(:searchKey, '%'))) ")
	@Query(value = "select * from users where first_name like lower(concat('%',concat(:searchKey,'%') ))\r\n"
			+ "or email like lower(concat('%',concat(:searchKey))) or last_name like lower(concat('%',concat(:searchKey,'%')))", nativeQuery = true)
	List<RegEntity> findByRegEntity(String searchKey);
}
