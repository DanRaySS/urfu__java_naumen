package ru.daniil.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Token {
	public static String getToken() {
		try {
			var props = new Properties();
			var envFile = Paths.get("path/to/.env");
			var inputStream = Files.newInputStream(envFile);
			props.load(inputStream);
			return (String) props.get("TOKEN");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
