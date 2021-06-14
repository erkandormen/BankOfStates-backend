package com.bank.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class ContactFormEntry {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;
    
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 60)
    @Email(message="Please provide valid Email Address")
    private String email;
    
    @NotBlank
    @Size(min = 3, max = 250)
    private String request;
    
}