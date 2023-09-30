package com.project.model;

import lombok.Data;
@Data
public class UserResponse {
	private String problemStatement;
	private ProblemLevel problemLevel;
	private String problemType;
	private String acceptance;
}
