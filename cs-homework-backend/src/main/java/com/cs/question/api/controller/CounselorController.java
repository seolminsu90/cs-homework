package com.cs.question.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs.question.api.common.model.ApiResponse;
import com.cs.question.api.common.util.JWTUtil;
import com.cs.question.api.entity.Counselor;
import com.cs.question.api.entity.Question;
import com.cs.question.api.entity.QuestionReceive;
import com.cs.question.api.model.CounselorDTO;
import com.cs.question.api.model.QuestionReceiveDTO;
import com.cs.question.api.service.CounselorService;

@RestController
@RequestMapping("/counselors")
public class CounselorController {
	private final CounselorService counselorService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtil jwtUtil;

	public CounselorController(CounselorService counselorService) {
		this.counselorService = counselorService;
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<Counselor>> counselorLogin(@Valid @RequestBody CounselorDTO dto) {
		Counselor user = counselorService.readUser(dto.getId());

		if (user == null) {
			throw new BadCredentialsException("user not found");
		}

		if (!this.passwordEncoder.matches(dto.getPwd(), user.getPassword())) {
			throw new BadCredentialsException("password is not matched");
		}

		user.setToken(jwtUtil.createToken(user));

		return new ResponseEntity<>(new ApiResponse<>(0, user), HttpStatus.OK);
	}

	@Secured("ROLE_COUNSELOR")
	@GetMapping("/questions")
	public ResponseEntity<ApiResponse<List<Question>>> getQuestion() {
		List<Question> list = counselorService.getQuestion();

		return new ResponseEntity<>(new ApiResponse<>(0, list), HttpStatus.OK);
	}

	@Secured("ROLE_COUNSELOR")
	@GetMapping("/questions-own")
	public ResponseEntity<ApiResponse<List<QuestionReceive>>> getQuestionReceive(
			@AuthenticationPrincipal UserDetails counselor) {
		List<QuestionReceive> list = counselorService.getQuestionReceive(counselor.getUsername());

		return new ResponseEntity<>(new ApiResponse<>(0, list), HttpStatus.OK);
	}

	@Secured("ROLE_COUNSELOR")
	@PostMapping("/questions/{questionId}/receive")
	public ResponseEntity<ApiResponse<QuestionReceive>> receiveQuestion(@AuthenticationPrincipal UserDetails counselor,
			@PathVariable Long questionId) {
		QuestionReceive result = counselorService.receiveQuestion(counselor.getUsername(), questionId);

		return new ResponseEntity<>(new ApiResponse<>(0, result), HttpStatus.CREATED);
	}

	@Secured("ROLE_COUNSELOR")
	@PutMapping("/questions/{questionId}/response")
	public ResponseEntity<ApiResponse<QuestionReceive>> writeResponse(@AuthenticationPrincipal UserDetails counselor,
			@Valid @RequestBody QuestionReceiveDTO questionReceiveDTO, @PathVariable Long questionId) {
		QuestionReceive result = counselorService.writeResponse(questionReceiveDTO, counselor.getUsername(),
				questionId);

		return new ResponseEntity<>(new ApiResponse<>(0, result), HttpStatus.CREATED);
	}
}
