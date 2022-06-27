package com.student.orb.app.config;

import java.io.IOException;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrbInitializer {
	
	Logger logger = LoggerFactory.getLogger(OrbInitializer.class);
	
	@Autowired
	private OrbCleanUp orbCleanUp;
	
	@Async
	public void startORB(OrbDetails orbDetails) {
		boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
		logger.debug("isWindows={}",isWindows);
		ProcessBuilder builder = new ProcessBuilder();
		if (isWindows) {
		    builder.command("cmd.exe", "orbd", "-ORBInitialPort", String.valueOf(orbDetails.getPort()), "-ORBInitialHost",orbDetails.getHostAddress());
		} else {
			builder.command("orbd", "-ORBInitialPort",String.valueOf(orbDetails.getPort()), "-ORBInitialHost", orbDetails.getHostAddress());
		}
		try {
			Process process = builder.start();
			orbCleanUp.setProcess(process);
			StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), logger::debug);
			Executors.newSingleThreadExecutor().submit(streamGobbler);
			int exitCode = process.waitFor();
			logger.debug("exitCode={}",exitCode);
		} catch (IOException e) {
			logger.error("Could not build Process builder = {}", e.getLocalizedMessage());
		} catch (InterruptedException e) {
			logger.error("Process Interrupted = {}", e.getLocalizedMessage());
		}
	}
}
