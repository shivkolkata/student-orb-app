package com.student.orb.app.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class StudOrbBinder {

	Logger logger = LoggerFactory.getLogger(StudOrbBinder.class);

	@Autowired
	private OrbInitializer orbInitializer;
	
	@Bean
	public OrbDetails orbDetails() {
		OrbDetails orbDetails = getOrgDetails();
		orbInitializer.startORB(orbDetails);
		logger.info("Orb Started successfully !!");
		return orbDetails;
		
	}

	private OrbDetails getOrgDetails() {
		OrbDetails orbDetails = new OrbDetails();
		try {
			InetAddress ip = InetAddress.getLocalHost();
			orbDetails.setCanonicalHostName(ip.getCanonicalHostName());
			orbDetails.setHostAddress(ip.getHostAddress());
			orbDetails.setHostName(ip.getHostName());
			orbDetails.setPort(1050);
			logger.debug("HostAddress = {}", orbDetails.getHostAddress());
			logger.debug("CanonicalHostName = {}", orbDetails.getCanonicalHostName());
		} catch (UnknownHostException e) {
			logger.error("Unable to get IP = {}", e.getLocalizedMessage());
		}
		return orbDetails;
	}


}
