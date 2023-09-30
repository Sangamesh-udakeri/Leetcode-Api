package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.Problem;
import com.project.service.ProblemServiceImpl;

@RestController
@RequestMapping("/admin/problems")
public class ProblemsControlller {

	@Autowired
	ProblemServiceImpl impl;

	@PostMapping("/save")
	public ResponseEntity<String> saveProblem(@RequestBody Problem problems){
		String saveProblem = impl.saveProblem(problems);
		return new ResponseEntity<String>(saveProblem,HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Problem>> getAllProblems(){
		
		List<Problem> allProblems = impl.getAllProblems();
		return new ResponseEntity<List<Problem>>(allProblems,HttpStatus.CREATED);
	}
	@GetMapping("problem/{id}")
	public ResponseEntity<Problem> getProblemById(@PathVariable Integer id) {
	    Optional<Problem> problemById = impl.getProblemById(id);
	    if (!problemById.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }
	    return new ResponseEntity<Problem>(problemById.get(), HttpStatus.OK);
	}

	@DeleteMapping("/problem/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Integer id){
		
		String deleteById = impl.deleteById(id);
		
		if(deleteById==null) {
			
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<String>(deleteById,HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<String> updateProblem(Problem problems){
		String saveProblem = impl.saveProblem(problems);
		return new ResponseEntity<String>(saveProblem,HttpStatus.OK);
	}
}
