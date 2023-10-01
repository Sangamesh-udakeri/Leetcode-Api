package com.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class Resource {

	private String problemName;
	private String problemType;
	private ProblemLevel problemLevel;
	@OneToOne(cascade = CascadeType.ALL)
	private ProblemBody problemBody;
}
