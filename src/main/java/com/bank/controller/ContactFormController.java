package com.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.repository.ContactRepo;
import com.bank.repository.UserRepo;
import com.bank.request.ContactFormEntry;
import com.bank.response.Response;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/contact")
public class ContactFormController {
	
	@Autowired
	ContactRepo contactRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@PostMapping("/entry")
	public ResponseEntity<Response> postContact(@Valid @RequestBody ContactFormEntry request) {
		Response response = new Response();
		contactRepo.save(request);
		response.setMessage("The form is sent successfully!");
		response.setSuccess(true);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
