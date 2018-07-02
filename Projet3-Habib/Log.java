package fr.projet3.oc;

import java.io.*;
import java.util.logging.*;

public class Log {

	public Logger logger;
	FileHandler fh;

	/**
	 * Constructeur de la classe
	 * 
	 */
	public Log(String file_name) throws SecurityException, IOException {

		File f = new File(file_name);

		if (!f.exists()) {
			f.createNewFile();
		}
		
		fh = new FileHandler(file_name, true);
		logger = Logger.getLogger("test");
		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}
}
