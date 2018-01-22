package com.codingdojo.nrampton.employees.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.nrampton.employees.models.Employee;
import com.codingdojo.nrampton.employees.services.EmployeeService;

@Controller
@RequestMapping("/")
public class HomeController {
	
	private EmployeeService _es;
	
		public HomeController(EmployeeService _es) {
		super();
		this._es = _es;
	}



	@RequestMapping("")
	public String showIndex(Model model) {
		model.addAttribute("employees", _es.getAllEmployees());
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewEmployeeForm() {
		return "employeeForm";
	}
	
	@PostMapping("/new")
	public String createNewEmployee(@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name) {
		Employee employee = new Employee(first_name, last_name);
		_es.createEmployee(employee);
		return "redirect:/";
	}
	
	@RequestMapping("/{id}")
	public String showEmployee(Model model, @PathVariable("id") Long id) {
		model.addAttribute("employee", _es.getEmployeeById(id));
		return "employeeDisplay";
	}
}
