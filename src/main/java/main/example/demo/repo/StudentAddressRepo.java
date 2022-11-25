package main.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.example.demo.entity.StudentAddress;
@Repository
public interface StudentAddressRepo extends JpaRepository<StudentAddress, Long> {

}
