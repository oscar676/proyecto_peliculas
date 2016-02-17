package com.ipartek.formacion.oscarfonticoba.listeners;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class InitListener
 *
 */
public class InitListener implements ServletContextListener {

	private final static Logger log = Logger.getLogger(InitListener.class);

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			Properties prop = new Properties();
			prop.load(this.getClass().getResourceAsStream("/log4j.properties"));
			PropertyConfigurator.configure(prop);
			log.trace("Contexto Servlet arrancado");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
