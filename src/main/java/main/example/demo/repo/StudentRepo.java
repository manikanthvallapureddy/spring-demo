package main.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import main.example.demo.entity.Student;
@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

}
