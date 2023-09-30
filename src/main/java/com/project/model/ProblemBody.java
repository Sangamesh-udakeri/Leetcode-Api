package com.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="PROBLEM_BODY")
public class ProblemBody {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bodyId;
	private String  description;
	private String  exmp1;
	private String  exmp2;
	private String  constrainsts;
}
