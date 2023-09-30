package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Language;
import com.project.model.Problem;
import com.project.model.ProblemLevel;
import com.project.model.UserResponse;
import com.project.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService service;
	@GetMapping("problems/all")
	public ResponseEntity<List<UserResponse>>getAll(){
		
		List<Problem> allProblems = service.getAllProblems();
		List<UserResponse> list=new ArrayList<>();
		for(Problem p:allProblems) {
			UserResponse response=new UserResponse();
			response.setProblemStatement(p.getProblemName());
			response.setProblemType(p.getProblemType());
			response.setProblemLevel(p.getProblemLevel());
			response.setAcceptance(p.getAcceptance());
			list.add(response);
		}
		return new ResponseEntity<List<UserResponse>>(list,HttpStatus.OK);
	}
	@GetMapping("/problem/{id}")
	public ResponseEntity<Problem> getProblemById(Integer id){
		Optional<Problem> problemById = service.getProblemById(id);
		if(problemById.isPresent()) {
			Problem problem=problemById.get();
			return ResponseEntity.ok(problem);
		}
	return ResponseEntity.notFound().build();
	}
	@GetMapping("/problems/level/{level}")
	public ResponseEntity<List<UserResponse>> getProblemsByLevel(@PathVariable ProblemLevel level ){
		List<Problem> problemByLevel = service.getProblemByLevel(level);
		List<UserResponse> list=new ArrayList<>();
		for(Problem p:problemByLevel) {
			UserResponse response=new UserResponse();
			response.setProblemStatement(p.getProblemName());
			response.setProblemType(p.getProblemType());
			response.setProblemLevel(p.getProblemLevel());
			response.setAcceptance(p.getAcceptance());
			list.add(response);
		}
		return new ResponseEntity<List<UserResponse>>(list,HttpStatus.OK);
	}
	@PostMapping("/problem/submit/language/{language}")
	public ResponseEntity<String> submit(String text,@PathVariable  Language language){
		
		//submitted code test cases will be processed here, it is just demo here not implemented test cases
		if(Math.random()<10000 % 100) {
			return new ResponseEntity<String>("Correct Answer Submitted ",HttpStatus.OK);
		}
		return new ResponseEntity<String>("Wrong Answer Tests Failed",HttpStatus.BAD_REQUEST);
	}
}
