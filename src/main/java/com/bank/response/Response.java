package com.bank.response;

import lombok.Data;

//@Getter+@Setter+@ToString+@Equals for pojo class
@Data
public class Response {
	boolean isSuccess;
	String message;
}
