package ru.daniil.demo;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TgTimeManagementApplication {
	public static void main(String args[])
	{
		try {
			var props = new Properties();
			var envFile = Paths.get("path/to/.env");
			var inputStream = Files.newInputStream(envFile);
			props.load(inputStream);
			String botToken = (String) props.get("TOKEN");
			TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
			botsApplication.registerBot(botToken, new Bot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		} catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
