package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.model.Problem;
import java.util.List;
import com.project.model.ProblemLevel;


public interface ProblemsRepository extends JpaRepository<Problem, Integer>{

	public List<Problem> getByProblemLevel(ProblemLevel problemLevel);
}
