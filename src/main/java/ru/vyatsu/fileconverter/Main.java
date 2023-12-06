package ru.vyatsu.fileconverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vyatsu.fileconverter.service.MenuUtils;
import ru.vyatsu.fileconverter.service.convertingfactory.ConvertingFactory;

import java.util.Arrays;

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

            ConvertingFactory.createConverter(sourceFilePath, destinationFilePath).convert(sourceFilePath, destinationFilePath);
            LOGGER.info("Файл {} успешно конвертирован в файл {}", sourceFilePath, destinationFilePath);
            System.out.printf("Файл '%s' успешно конвертирован в файл '%s'%n", sourceFilePath, destinationFilePath);
        } catch (Exception thrown) {
            System.err.printf("Произошла ошибка: %s%n", thrown.getMessage());
            System.err.printf("Трассировка стека: %s%n", Arrays.stream(thrown.getStackTrace()).toList());
            LOGGER.error("Ошибка: {}", thrown.getMessage());
        }
    }
}