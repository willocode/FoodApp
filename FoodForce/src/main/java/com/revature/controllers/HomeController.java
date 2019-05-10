package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Customer;
import com.revature.beans.Employee;
import com.revature.beans.Food;
import com.revature.beans.LoginResponse;
import com.revature.beans.User;
import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.service.EmployeeService;

@RestController
public class HomeController {
	
	@Autowired
	private EmployeeService eserv;
	
//	public HomeController(EmployeeService eserv) {
//		this.eserv = eserv;
//	}
	
	@GetMapping("home")
	public String home(final HttpSession session) {
		User u = (User) session.getAttribute("user");
		if (u != null) {
			return "hello, "+u.getFirstName();
		} else {
			return "welcome";
		}
	}
	
	
//	public LoginResponse login() {
//		LoginResponse lr = new LoginResponse();
//		lr.setMessage("sucess");
//		
//		Customer emp = new Customer();
//		emp.setFirstName("firstName");
//		emp.setLastName("lastname");
//		
//		lr.setUser(emp);
//		return lr;
//	}
	
	@RequestMapping("/food")
	public Food food() {
		Food food = new Food();
		food.setId(1);
		food.setDescription("desc");
		food.setName("name");
		food.setType("pizza");
		return food;
		
	}
	
//	@GetMapping(value="/{id}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
//		Employee emp = eserv.getEmployeeById(id);
//		if (emp == null) {
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//		} else {
//			return new ResponseEntity<>(emp, HttpStatus.OK);
//		}
//	}
//	
//	@GetMapping(value="/")
//	public ResponseEntity<List<Employee>> allEmployees() {
//		return new ResponseEntity<>(eserv.allEmployees(), HttpStatus.OK);
//	}

}
