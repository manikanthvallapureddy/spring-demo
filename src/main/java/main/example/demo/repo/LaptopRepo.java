package main.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import main.example.demo.entity.Laptop;

public interface LaptopRepo extends JpaRepository<Laptop, Long> {

}
