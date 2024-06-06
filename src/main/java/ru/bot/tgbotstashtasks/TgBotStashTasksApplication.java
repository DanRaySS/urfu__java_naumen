package ru.bot.tgbotstashtasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class TgBotStashTasksApplication {

	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(TgBotStashTasksApplication.class, args);
	}

}
