package com.student.orb.app.config;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrbCleanUp {

	Logger logger = LoggerFactory.getLogger(OrbCleanUp.class);

	private Process process;

	public void setProcess(Process process) {
		this.process = process;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Clean up method invoked");
		process.destroy();
	}
}
