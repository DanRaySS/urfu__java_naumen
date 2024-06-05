package ru.daniil.demo;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Math.toIntExact;

@Component
public class Bot implements LongPollingSingleThreadUpdateConsumer {

//    final UsersService usersService;
//    final TaskService taskService;
//    final TagService tagService;

    private final TelegramClient telegramClient;
    public Bot(String botToken) {
        telegramClient = new OkHttpTelegramClient(botToken);
    }

    public boolean greetings = false;

    @Override
    public void consume(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String answer = message_text;
            // Set variables
            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            String user_username = update.getMessage().getChat().getUserName();
            //logging
            log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
//            if (!greetings) {
//                SendMessage message = SendMessage // Create a message object
//                        .builder()
//                        .chatId(chat_id)
//                        .text("Привет, "+user_first_name+". " +"Команды бота в /help")
//                        .build();
//                try {
//                    telegramClient.execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//                greetings = true;
//            }
            if (update.getMessage().getText().equals("/start")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_start_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/help")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_help_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tags")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_tags_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tagadd")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_tagadd_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tagremove")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_tagremove_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tasks")) {
                SendMessage message = SendMessage // Create a message object
                        .builder()
                        .chatId(chat_id)
                        .text("Ваши задачи: ")
                        .build();
                try {
                    telegramClient.execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().startsWith("/taskadd")) {
                String task = update.getMessage().getText().substring(8);
//                SendMessage message = SendMessage // Create a message object
//                        .builder()
//                        .chatId(chat_id)
//                        .text("hi")
//                        .build();
//                try {
//                    telegramClient.execute(message); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
            }
            if (update.getMessage().getText().equals("/taskremove")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_taskremove_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tasknameedit")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_tasknameedit_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().equals("/tasktagremove")) {
                SendMessage startMessage = SendMessage // Create a message object object
                        .builder()
                        .chatId(chat_id)
                        .text(message_text)
                        .replyMarkup(InlineKeyboardMarkup
                                .builder()
                                .keyboardRow(
                                        new InlineKeyboardRow(InlineKeyboardButton
                                                .builder()
                                                .text("Update message text")
                                                .callbackData("update_tasktagremove_msg_text")
                                                .build()
                                        )
                                )
                                .build())
                        .build();
                try {
                    telegramClient.execute(startMessage); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            if (update.getMessage().getText().startsWith("/tasktagadd")) {
                String tag = update.getMessage().getText().substring(11);
                String[] parts = tag.split(", ");
                int tagNum = Integer.parseInt(parts[0]);
                String tagName = parts[1];
//                SendMessage startMessage = SendMessage // Create a message object object
//                        .builder()
//                        .chatId(chat_id)
//                        .text(message_text)
//                        .replyMarkup(InlineKeyboardMarkup
//                                .builder()
//                                .keyboardRow(
//                                        new InlineKeyboardRow(InlineKeyboardButton
//                                                .builder()
//                                                .text("Update message text")
//                                                .callbackData("update_tasktagadd_msg_text")
//                                                .build()
//                                        )
//                                )
//                                .build())
//                        .build();
//                try {
//                    telegramClient.execute(startMessage); // Sending our message object to user
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
            }
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_start_msg_text")) {
                String answer = "Actions after /start....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_help_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tags_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tagadd_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tagremove_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tasks_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_taskadd_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_taskremove_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tasknameedit_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tasktagremove_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (call_data.equals("update_tasktagadd_msg_text")) {
                String answer = "Actions after /help....";
                EditMessageText new_message = EditMessageText.builder()
                        .chatId(chat_id)
                        .messageId(toIntExact(message_id))
                        .text(answer)
                        .build();
                try {
                    telegramClient.execute(new_message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}