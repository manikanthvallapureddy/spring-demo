package main.example.demo.entity;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_address")
public class StudentAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long uid;

	@Column(name = "address_line1")
	private String addressLine;

	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
//@JsonBackReference
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private Student student;

}
