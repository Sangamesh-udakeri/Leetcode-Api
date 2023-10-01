package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.Problem;
import com.project.model.Resource;
import com.project.repository.ProblemsRepository;

@Service
public class ProblemDownloadServiceImpl {

	@Autowired
	ProblemsRepository problemsRepository;
	public Optional<Problem> downloadProblemById(Integer id) {
		return problemsRepository.findById(id);
	}
}
