package fr.projet3.oc;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger("loggerForLog4J");
	private String message;
	Log my_log;

	/**
	 * Constructeur de la classe
	 * 
	 */

	public Logging(String message) {
		this.message = message;
		try {
			my_log = new Log("./log4j");
			my_log.logger.setLevel(Level.INFO);
			my_log.logger.info(getMessage());
			// my_log.logger.warning(getMessage());
			// my_log.logger.severe(getMessage());
		} catch (Exception e) {

		}
	}

	public void ChangeInfoLog(String message) {
		my_log.logger.setLevel(Level.INFO);
		my_log.logger.info(message);
	}

	/**
	 * getter recupérant la message à afficher dans le log
	 * 
	 * @return un String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * setter modifiant le le message à afficher dans le log
	 * 
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
