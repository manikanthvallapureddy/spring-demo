package main.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class RegEntity {

	@Id
	// @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq",
	// initialValue = 101, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true)
	private Long id;

	@Column(name = "email")
	private String email;
	@Column(name = "country_code")
	private String countrycode;
	
	
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "pass_word")
	private String password;

	@Column(name = "document")
	private String image;
	
	@Column(name = "status")
	private String status;
}
