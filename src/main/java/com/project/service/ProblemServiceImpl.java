package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Problem;
import com.project.repository.ProblemsRepository;
@Service
public class ProblemServiceImpl {

	@Autowired
	ProblemsRepository problemsRepository;

	public List<Problem> getAllProblems() {
		List<Problem> findAll = problemsRepository.findAll();
		return findAll;
	}
	public Optional<Problem> getProblemById(Integer id) {
		
		return problemsRepository.findById(id);
		
	}
	public String deleteById(Integer id) {
		String msg="Could not find problem!";
		if(problemsRepository.existsById(id)) {
			problemsRepository.deleteById(id);
			msg="deleted";
		}
		return msg;
	}
	
	public String saveProblem(Problem problems) {
		
		String msg="saved";
 		problemsRepository.save(problems);
		return msg;
	}
}
