package com.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="PROBLEMS")
@Data
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer problemId;
	private String problemName;
	private String problemType;
	private ProblemLevel problemLevel;
	private String acceptance;
	@OneToOne(cascade = CascadeType.ALL)
	private ProblemBody problemBody;
}
