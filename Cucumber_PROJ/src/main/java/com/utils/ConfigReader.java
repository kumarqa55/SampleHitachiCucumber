package com.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private Properties prop;
	
	/**
	 * This Method is used to load properties from Config.properties file
	 * @return
	 */

	public Properties init_Properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("./src/test/resources/com/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

}
