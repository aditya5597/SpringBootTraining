package com.aditya.crud1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.crud1.daos.EmployeeRepository;
import com.aditya.crud1.model.Employee;



@RestController
@RequestMapping("/emp")
public class EmployeeController 
{
	@Autowired private EmployeeRepository empRepo;
	
	@GetMapping(value="/find/{id}",produces= {"application/json","application/xml"})
	public Employee find(@PathVariable("id") Integer id) {
		return empRepo.findById(id);
	}
	
	// POST http://localhost:8081/products/save
	@PostMapping(value="/save",consumes="application/json")
	public ResponseEntity<String> save(@RequestBody Employee emp){
		try {
		empRepo.save(emp);
		return new ResponseEntity<>("Saved !",HttpStatus.CREATED);
		}catch(RuntimeException ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	// PUT http://localhost:8081/products/update
	@PutMapping(value="/update",consumes="application/json")
	public ResponseEntity<String> update(@RequestBody Employee emp){
		try {
			empRepo.update(emp);
			return new ResponseEntity<>("Saved !",HttpStatus.CREATED);
			}catch(RuntimeException ex) {
				System.out.println(ex.getMessage());
				return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			}
	}
	
	// DELETE http://localhost:8081/products/{id}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id){
		try {
			empRepo.delete(id);
			return new ResponseEntity<String>("Record Deleted",HttpStatus.OK);
		}catch(Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

}
