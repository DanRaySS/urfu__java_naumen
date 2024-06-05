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
import ru.bot.models.Tag;
import ru.bot.models.User;
import ru.bot.repository.TaskRepository;
import ru.bot.repository.UserRepository;
import ru.bot.services.TagService;
import ru.bot.services.TaskService;
import ru.bot.services.UsersService;

import java.util.List;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class Bot extends TelegramLongPollingBot {

    final UsersService usersService;
    final TaskService taskService;
    final TagService tagService;

    private ReplyKeyboardMarkup mainMenuKeyboard;
    private ReplyKeyboardMarkup tasksKeyboard;

    private ReplyKeyboardMarkup yesNoKeyboard;

    String message;
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
        createKeyboards();
        var msg = update.getMessage();
        message = msg.getText();
        var user = msg.getFrom();
        var id = user.getId();
        var userName = user.getUserName();
        menuLogic(msg.getText(),id,userName);

        return;                                     //We don't want to echo commands, so we exit

    }
    public void sendText(Long who, String what, ReplyKeyboardMarkup keyboard){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        sm.setReplyMarkup(keyboard);

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
        row.add("👆 Выбрать задачу");
        row.add("➕ Добавить задачу");
        row.add("📋 Архив задач");
        taskKeyboardRows.add(row);
        row = new KeyboardRow();
        row.add("⬅ Главное Меню");
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

        ReplyKeyboardMarkup yesNoKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> yesNoKeyboardRows = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Да");
        row.add("Нет");
        yesNoKeyboardRows.add(row);
        yesNoKeyboardMarkup.setKeyboard(yesNoKeyboardRows);
        yesNoKeyboard = yesNoKeyboardMarkup;



    }
    public void menuLogic(String msg, Long id, String user){
        switch (msg){
            case ("➕ Добавить задачу"):
                User userInfo = new User(id, null, null);
                addTask(id, msg, userInfo);
                break;
            case ("📋 Архив задач"):
                archive();
                break;
            case ("👆 Выбрать задачу"):
                choseTask();
                break;
            case ("Мои Задачи"):
                if(taskService.getAllTasks(id).isEmpty()){
                    sendText(id,"Нет задач",tasksKeyboard);
                }
                else {
                    sendText(id,String.join("\n",taskService.getAllTasks(id)),tasksKeyboard);
                }

                break;
            case ("/start"):
                sendText(id, "Привет "+ user, mainMenuKeyboard);
                usersService.addUser(id);
            case ("⬅ Главное Меню"):
                sendText(id, "Главное меню",mainMenuKeyboard);

        }
    }


    public void addTask(Long id, String msg, User userInfo){
        if (msg.equals("➕ Добавить задачу")) {
            sendText(id, "Введите название", tasksKeyboard);
            return;
        }

        if(userInfo.lastSummary == null || userInfo.lastSummary.trim().isEmpty()){
            userInfo.lastSummary = msg;
            sendText(id,"Введите описание",tasksKeyboard);
            return;
        }

        if(userInfo.lastDesc == null || userInfo.lastDesc.trim().isEmpty()){
            userInfo.lastDesc = msg;
            sendText(id,"Введите теги",tasksKeyboard);
            return;
        }

        String tags = message;
        List<Tag> tagList =new ArrayList<>();
        if (!tags.isEmpty()){
            for (String tag: tags.split(" ")){
                tagList.add(tagService.getTagBySummary(tag));
            }
        }
        taskService.createTask(summary,description,id,tagList);
    }
    public void archive(){

    }
    public void choseTask(){

    }
}
