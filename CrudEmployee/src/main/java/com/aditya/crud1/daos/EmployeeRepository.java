package com.aditya.crud1.daos;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.aditya.crud1.model.Employee;

@Repository
public class EmployeeRepository 
{
	List<Employee> emps = new LinkedList<>();
	
	@PostConstruct
	public void init()
	{
		System.out.println("Generating sample data");
		emps.add(new Employee("Aditya","Chatterjee","GET",100));
		emps.add(new Employee("Ranjeet","Kumar","GET",101));
		emps.add(new Employee("Ashish","Dodamani","Trainee",102));
	}
	
	public Employee findById(int id) {
		Optional<Employee> em = emps.stream().filter(p->p.getEmpid()==id).findFirst();
		if(em.isPresent())
			return em.get();
		else
			return null;
	}
	
	public void save(Employee emp) {
		//check if product already exists!
		Employee temp = findById(emp.getEmpid());
		if(temp!=null) {
			throw new RuntimeException("Product already exists!");
		}
		emps.add(emp);
	}
	public void update(Employee emp) {
		//check if product already exists!
		Employee temp = findById(emp.getEmpid());
		if(temp==null) {
			throw new RuntimeException("Product does not exists!");
		}
		temp.setDesignation(emp.getDesignation());
		temp.setFirstname(emp.getFirstname());
		temp.setLastname(emp.getLastname());
	}	
	
	public void delete(int id) {
		//check if product already exists!
		Employee temp = findById(id);
		if(temp==null) {
			throw new RuntimeException("Product does not exists!");
		}
		emps.remove(temp);
	}
}
