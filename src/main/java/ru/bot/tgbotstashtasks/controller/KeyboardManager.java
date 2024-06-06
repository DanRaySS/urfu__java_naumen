package ru.bot.tgbotstashtasks.controller;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardManager {
    public ReplyKeyboardMarkup createTaskKeyboard(){
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
        return taskKeyboardMarkup;
    }
    public ReplyKeyboardMarkup createMainKeyboard(){
        ReplyKeyboardMarkup mainKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> mainKeyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row = new KeyboardRow();
        row.add("Мои Задачи");
        row.add("Инструкция");
        row.add("Настройки");
        mainKeyboardRows.add(row);
        mainKeyboardMarkup.setKeyboard(mainKeyboardRows);
        return mainKeyboardMarkup;
    }
    public ReplyKeyboardMarkup createYesNoKeyboard(){
        ReplyKeyboardMarkup yesNoKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> yesNoKeyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row = new KeyboardRow();
        row.add("Да");
        row.add("Нет");
        yesNoKeyboardRows.add(row);
        yesNoKeyboardMarkup.setKeyboard(yesNoKeyboardRows);
        return yesNoKeyboardMarkup;
    }
    public ReplyKeyboardMarkup createChoseKeyboard(){
        ReplyKeyboardMarkup choseKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> choseKeyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row = new KeyboardRow();
        row.add("Удалить");
        choseKeyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Мои Задачи");
        choseKeyboardRows.add(row);
        choseKeyboardMarkup.setKeyboard(choseKeyboardRows);
        return choseKeyboardMarkup;
    }

    public ReplyKeyboardMarkup createSettingsKeyboard(){
        ReplyKeyboardMarkup settingsKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> settingsKeyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row = new KeyboardRow();
        row.add("Мои теги");
        settingsKeyboardRows.add(row);
        row = new KeyboardRow();
        row.add("⬅ Главное Меню");
        settingsKeyboardRows.add(row);
        settingsKeyboardMarkup.setKeyboard(settingsKeyboardRows);
        return settingsKeyboardMarkup;
    }
    public ReplyKeyboardMarkup createTagsKeyboard(){
        ReplyKeyboardMarkup tagsKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> tagsKeyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row = new KeyboardRow();
        row.add("Создать тэг");
        tagsKeyboardRows.add(row);
        row = new KeyboardRow();
        row.add("Настройки");
        tagsKeyboardRows.add(row);
        tagsKeyboardMarkup.setKeyboard(tagsKeyboardRows);
        return tagsKeyboardMarkup;
    }

}
