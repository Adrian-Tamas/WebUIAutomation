package utils;

import org.junit.Assert;

public class Logger {
	
	private static int logLevel = 0;
	
	public Logger(String logLevel) {
		setLogLevel(logLevel);
	}

	public static void info(String message) {
		if (logLevel >= 2) {
			System.out.println("INFO: " + message);
		}
	}
	
	public static void error(String message) {
		System.out.println("ERROR: " + message);
	}
	
	public static void debug(String message) {
		if (logLevel >= 1) {
			System.out.println("DEBUG: " + message);
		}
	}
	
	public static void startStep(String stepName) {
		System.out.println("Starting step: " + stepName);
	}
	
	public static void endStep(String stepName) {
		System.out.println("Completed step: " + stepName);
	}
	
	public void setLogLevel(String level) {
		if (level.equalsIgnoreCase("info")) {
			logLevel = 2;
		} else if (level.equalsIgnoreCase("debug")) {
			logLevel = 1;
		} else if (level.equalsIgnoreCase("info")) {
			logLevel = 0;
		} else {
			Assert.fail("Unable to set the log level to the expected value");
		}
	}
	
	public int getLogLevel() {
		return logLevel;
	}

	public static void error(Exception e) {
		String error = "";
        for (StackTraceElement algo : e.getStackTrace()) {

            error = error + algo.toString();
        }
        Logger.error(error);

        Throwable cause = e.getCause();
        if (cause != null) {
            do {
                Logger.error(cause.toString());
                cause = cause.getCause();
            } while (cause != null);
        }
		
	}
}
