package ru.vyatsu.fileconverter;

import lombok.val;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.fileconverter.service.MenuUtils;
import ru.vyatsu.fileconverter.service.convertingfactory.ConvertingFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            String sourceFilePath;
            String destinationFilePath;
            switch (args.length) {
                case 2 -> {
                    sourceFilePath = args[0];
                    destinationFilePath = args[1];
                }
                case 0 -> {
                    sourceFilePath = MenuUtils.getInputFile();
                    destinationFilePath = MenuUtils.getOutputFile();
                }
                default -> {
                    System.err.println("Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.");
                    LOGGER.error("Неверное количество аргументов при запуске приложения.");
                    return;
                }
            }
            LOGGER.info("Данные о файлах считаны.");

            val converter = ConvertingFactory.createConverter(sourceFilePath, destinationFilePath);

            if (converter == null) {
                LOGGER.error("Некорректные форматы файлов!");
                return;
            }

            converter.convert(sourceFilePath, destinationFilePath);
            LOGGER.info("Файл {} успешно конвертирован в файл {}", sourceFilePath, destinationFilePath);
            System.out.println("Файл " + sourceFilePath + " успешно конвертирован в файл " + destinationFilePath);
        } catch (Exception ex) {
            System.err.println("Произошла ошибка: " + ex.getMessage());
            LOGGER.error("Ошибка: {} {}.\nДетали: {}", ex.getMessage(), ex.getCause(), ex.getStackTrace());
        }
    }
}