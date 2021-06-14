package com.bank.repository;

import javax.validation.Valid;

import org.springframework.data.repository.CrudRepository;

import com.bank.request.ContactFormEntry;

public interface ContactRepo extends CrudRepository<ContactFormEntry, Integer> {

//	void save(@Valid ContactFormEntry request);

}