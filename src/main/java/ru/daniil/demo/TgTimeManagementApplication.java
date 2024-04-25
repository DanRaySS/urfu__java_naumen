package ru.daniil.demo;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgTimeManagementApplication {
	public static void main(String args[])
	{
		try {
			String botToken = "7119824689:AAH0GpkHr925gm9yMedyBhASAWtTWmV0cks";
			TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
			botsApplication.registerBot(botToken, new Bot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
