package com.cs.question.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cs.question.api.common.model.ApiResponse;
import com.cs.question.api.entity.Question;
import com.cs.question.api.model.QuestionDTO;
import com.cs.question.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/questions")
	public ResponseEntity<ApiResponse<List<Question>>> getQuestion(@RequestParam String customerId) {
		List<Question> list = customerService.findQuestion(customerId);

		return new ResponseEntity<>(new ApiResponse<>(0, list), HttpStatus.OK);
	}

	@PostMapping(value = "/questions")
	public ResponseEntity<ApiResponse<Question>> writeQuestion(@Valid @RequestBody QuestionDTO dto) {
		Question result = customerService.writeQuestion(dto);

		return new ResponseEntity<>(new ApiResponse<>(0, result), HttpStatus.CREATED);
	}
}
