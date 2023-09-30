package com.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Problem;
import com.project.model.ProblemLevel;
import com.project.model.UserResponse;
import com.project.repository.ProblemsRepository;
@Service
public class UserService {
	@Autowired
	ProblemsRepository problemsRepository;
	public List<Problem> getAllProblems(){
		List<Problem> findAll = problemsRepository.findAll();
		return findAll;
	}
	public Optional<Problem> getProblemById(Integer id) {
		Optional<Problem> findById = problemsRepository.findById(id);
		return findById;
	}
	public List<Problem> getProblemByLevel(ProblemLevel level){
		
		List<Problem> byProblemLevel = problemsRepository.getByProblemLevel(level);
		
		return byProblemLevel;
	}
}
