package com.student.orb.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.orb.app.config.OrbDetails;

@RestController
@RequestMapping("/")
public class StudentOrbController {
	
	Logger logger = LoggerFactory.getLogger(StudentOrbController.class);
	
	@Autowired
	private OrbDetails orbDetails;
	
	@GetMapping("/orb")
	public OrbDetails getOrbDetails(){
		logger.debug("Inside getOrbDetails");
		return orbDetails;
	}
}
