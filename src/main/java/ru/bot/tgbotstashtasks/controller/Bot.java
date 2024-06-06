package ru.bot.tgbotstashtasks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bot.tgbotstashtasks.models.State;
import ru.bot.tgbotstashtasks.services.TagService;
import ru.bot.tgbotstashtasks.services.TaskService;
import ru.bot.tgbotstashtasks.services.UsersService;

@RequiredArgsConstructor
@Component
public class Bot extends TelegramLongPollingBot {

    final UsersService usersService;
    final TaskService taskService;
    final TagService tagService;

    KeyboardManager keyboardManager= new KeyboardManager();
    private ReplyKeyboardMarkup mainMenuKeyboard;
    private ReplyKeyboardMarkup tasksKeyboard;
    private ReplyKeyboardMarkup yesNoKeyboard;
    private ReplyKeyboardMarkup choseKeyboard;
    private ReplyKeyboardMarkup settingsKeyboard;
    private ReplyKeyboardMarkup tagsKeyboard;

    Long userId;
    State state = State.NULL;

    String summary;
    String description;
    String tags;
    String chose;
    String tag;
    String INSTRUCTION = "Добро пожаловать в 'Телеграм-бот для хранения задач'!\n";


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
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            userId = update.getMessage().getFrom().getId();
            long chatId = update.getMessage().getChatId();
            String memberName = update.getMessage().getFrom().getFirstName();
            System.out.println("||UPDATE||---- " + messageText);
            if (!messageText.contains("null") && chatId == userId ) {
                switch (state) {
                    case SUMMARY -> {
                        summary = messageText;
                        addTask();
                    }
                    case DESCRIPTION -> {
                        description = messageText;
                        addTask();
                    }
                    case TAGS -> {
                        tags = messageText;
                        addTask();
                    }
                    case CHOSE -> {
                        chose = messageText;
                        choseTask();
                    }
                    case ADD_TAG -> {
                        tag = messageText;
                        addTag();
                    }
                }
            }

            menuLogic(messageText, userId, memberName);

        }

        return;                                     //We don't want to echo commands, so we exit

    }

    public void menuLogic(String msg, Long id, String user) {
        switch (msg) {
            case ("➕ Добавить задачу"):
                addTask();
                break;
            case ("📋 Архив задач"):
                returnTask();
                break;
            case ("👆 Выбрать задачу"):
                choseTask();
                break;
            case ("Мои Задачи"):
                returnTask();
                break;
            case ("/start"):
                sendText(id, "Привет " + user, mainMenuKeyboard);
                usersService.addUser(id);
                break;
            case ("⬅ Главное Меню"):
                sendText(id, "Главное меню", mainMenuKeyboard);
                break;
            case ("Удалить"):
                delTask();
                break;
            case ("Настройки"):
                sendText(userId,"Меню Настройки",settingsKeyboard);
                break;
            case ("Мои теги"):
                if(!tagService.getAllTagsName(userId).isEmpty()){
                    sendText(userId,tagService.getAllTagsName(userId),tagsKeyboard);
                }
                else {
                    sendText(userId,"Нет тэгов",tagsKeyboard);
                }
                break;
            case ("Создать тэг"):
                addTag();
                break;
            case ("Инструкция"):
                sendText(userId,INSTRUCTION,mainMenuKeyboard);
                break;
        }
    }
    public void addTask() {
        if (state == State.NULL) {
            sendText(userId, "Введите название", tasksKeyboard);
            state = State.SUMMARY;
            return;
        }
        if (state == State.SUMMARY) {
            sendText(userId, "Введите описание", tasksKeyboard);
            state = State.DESCRIPTION;
            return;
        }
        if (state == State.DESCRIPTION) {
            if (tagService.getAllTagsName(userId).isEmpty()) {
                state = State.NULL;
                sendText(userId, "У вас нет тэгов. Добавьте их через Главное меню: 'Настройки' ➡️ 'Мои тэги' ➡️ 'Создать тэг'.", tasksKeyboard);
            } else {
                sendText(userId, "Укажите название тэга из списка:\n" + tagService.getAllTagsName(userId), tasksKeyboard);
                state = State.TAGS;
                return;
            }
        }
        if (state == State.TAGS) {
            state = State.NULL;
            taskService.createTask(summary, description, userId,tagService.getTagBySummary(tag));
            sendText(userId, "Задание " + "'" + summary + "'" + " успешно добавленно", tasksKeyboard);
        }

    }

    public void choseTask() {
        if (state == State.NULL) {
            sendText(userId, "Введите номер задачи", tasksKeyboard);
            state = State.CHOSE;
            return;
        }
        if (state == State.CHOSE) {
            sendText(userId, "Вы выбрали задание: " + chose, tasksKeyboard);
            sendText(userId, taskService.getTaskById(Long.parseLong(chose), userId), choseKeyboard);
            state = State.NULL;
        }
    }

    public void returnTask() {
        if (taskService.getAllTasks(userId).isEmpty()) {
            sendText(userId, "Нет задач", tasksKeyboard);
        } else {
            sendText(userId, String.join("\n", taskService.getAllTasks(userId)), tasksKeyboard);
        }
    }

    public void delTask() {
        sendText(userId, "Задача " + "'" + chose + "'" + " успешно удалена", tasksKeyboard);
        taskService.delTaskById(userId,Long.parseLong(chose));
        returnTask();
    }

    public void addTag(){
        if (state == State.NULL) {
            sendText(userId, "Введите название тэга", tagsKeyboard);
            state = State.ADD_TAG;
            return;
        }

        if (state == State.ADD_TAG && !tag.contains(" ")) {
            sendText(userId, "Вы добавили тэг: " + tag, settingsKeyboard);
            tagService.addTag(userId,tag,false);
            state = State.NULL;
        }
        else{
            sendText(userId, "Неправильно, попробуйте ещё ", tagsKeyboard);
        }
    }

    public void sendText(Long who, String what, ReplyKeyboardMarkup keyboard) {
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

    private void createKeyboards() {
        tasksKeyboard = keyboardManager.createTaskKeyboard();

        mainMenuKeyboard = keyboardManager.createMainKeyboard();

        yesNoKeyboard = keyboardManager.createYesNoKeyboard();

        choseKeyboard = keyboardManager.createChoseKeyboard();

        settingsKeyboard = keyboardManager.createSettingsKeyboard();

        tagsKeyboard = keyboardManager.createTagsKeyboard();
    }

}
