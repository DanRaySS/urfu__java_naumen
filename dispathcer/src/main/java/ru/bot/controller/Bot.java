package ru.bot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.models.User;
import ru.bot.repository.TaskRepository;
import ru.bot.repository.UserRepository;
import ru.bot.services.TaskService;
import ru.bot.services.UsersService;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class Bot extends TelegramLongPollingBot {

    final UsersService usersService;
    final TaskService taskService;

    private ReplyKeyboardMarkup currentKeyboardMarkup;
    private ReplyKeyboardMarkup mainMenuKeyboard;
    private ReplyKeyboardMarkup tasksKeyboard;


    private InlineKeyboardMarkup keyboardM1;
    private InlineKeyboardMarkup keyboardM2;




    @Override
    public String getBotUsername() {
        return "timetracker_surkov_bot";
    }

    @Override
    public String getBotToken() {
        return "7391871305:AAFg8orESIaDLPSrbcv5dn8e9ec3fedCue0";
    }

    @Override
    public void onUpdateReceived(Update update) {
//        keyboardM1 = InlineKeyboardMarkup.builder()
//                .keyboardRow(List.of(next)).build();
//
//        keyboardM2 = InlineKeyboardMarkup.builder()
//                .keyboardRow(List.of(back))
//                .keyboardRow(List.of(url))
//                .build();
        createKeyboards();

        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();
        var userName = user.getUserName();
        menuLogic(msg.getText(),id);

        return;                                     //We don't want to echo commands, so we exit

    }
    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content

        sm.setReplyMarkup(mainMenuKeyboard);


        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
    private void createKeyboards(){
        ReplyKeyboardMarkup taskKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> taskKeyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();

        row.add("➕ Добавить задачу");
        row.add("📋 Архив задач");
        taskKeyboardRows.add(row);
        row = new KeyboardRow();
        row.add("👆 Выбрать задачу");
		e517d6238d2cf555afbc5ce32d3bf0ef3cb1bb98
        taskKeyboardRows.add(row);

        taskKeyboardMarkup.setKeyboard(taskKeyboardRows);
        tasksKeyboard = taskKeyboardMarkup;

        ReplyKeyboardMarkup mainKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> mainKeyboardRows = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Мои Задачи");
        row.add("Инструкция");
        row.add("Настройки");
        mainKeyboardRows.add(row);
        mainKeyboardMarkup.setKeyboard(mainKeyboardRows);
        mainMenuKeyboard = mainKeyboardMarkup;



    }
    public void menuLogic(String msg, Long id){
        switch (msg){
            case (" Добавить задачу"):
                addTask();
                break;
            case (" Архив задач"):
                archive();
                break;
            case (" Выбрать задачу"):
                choseTask();
                break;
            case ("Мои Задачи"):
                myTasks(id);
                break;
            case ("/start"):
                startInit(id);
            case ("Главное Меню"):
                sendText(id, "Главное меню");

        }
    }
    public void myTasks(Long user_id ){
        SendMessage sm = SendMessage.builder()
                .chatId(user_id.toString()).text(String.join("\n",taskService.getAllTasks(user_id))).build(); //Who are we sending a message to//Message content

        sm.setReplyMarkup(tasksKeyboard);

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
    public void startInit(long id){
        usersService.addUser(id);
    }
    public void addTask(){

    }
    public void archive(){

    }
    public void choseTask(){

    }

    public void sendMenu(Long who, String txt, InlineKeyboardMarkup kb){

    }
}
