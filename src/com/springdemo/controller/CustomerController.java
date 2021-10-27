package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.dao.CustomerDAO;
import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;

//Controller class for  bussiness logic
@Controller
@RequestMapping("/customer")
public class CustomerController {
	// inject CustomerService
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		// get customers from the Service Layer
		List<Customer> theCustomers = customerService.getCustomers();
				
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	@GetMapping("/showFormForAdd")
	public String showFormAdd(Model themodel)
	{
		//create new model object to bind the form data 
		Customer thecustomer = new Customer();
		themodel.addAttribute("customer",thecustomer);
		return "customer-form";
	}
	@PostMapping("/saveCustomer") // pick data from saveCustomer from  custoemr.jsp page and save  and ridirect to main page //@ModelAttribute  is used  where you are going to post data
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		// save the customer using our service
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";//return list of customer
	}
	@GetMapping("/showFormForUpdate") //(@RequestParam("customerId") used to bind with id  of jsp page
	public String  showFormForUpdate(@RequestParam("customerId") int theId, Model themodel)
	{
		//get the customer from database 
		Customer thecustomer = customerService.getCustomers(theId);
		//set customer as  a model attribute to pre-populate  the form
		themodel.addAttribute("customer", thecustomer);
		//send over to over form 
		return "customer-form";
		
	}
	  @GetMapping("/delete") 
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		  //delete the customer 
		  customerService.deleteCustomer(theId);
		  
		return "redirect:/customer/list" ;//code to redirect back to  list-customer 
	}
}


