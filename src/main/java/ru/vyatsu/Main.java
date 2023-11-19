package ru.vyatsu;

import lombok.val;

import ru.vyatsu.service.ConvertingException;
import ru.vyatsu.service.ConvertingType;
import ru.vyatsu.service.MenuService;
import ru.vyatsu.service.converters.JsonToXmlConverter;
import ru.vyatsu.service.converters.XmlToJsonConverter;

import java.util.logging.Logger;

public class Main {
    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws ConvertingException {
        try {
            String input, output;
            switch (args.length) {
                case 2 -> {
                    input = args[0];
                    output = args[1];
                }
                case 0 -> {
                    input = MenuService.getInputFile();
                    output = MenuService.getOutputFile();
                }
                default ->
                        throw new ConvertingException("Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.");
            }

            val convertingType = ConvertingType.determineType(input, output);
            val convertable = switch (convertingType) {
                case XML_TO_JSON -> new XmlToJsonConverter();
                case JSON_TO_XML -> new JsonToXmlConverter();
                case INCORRECT -> null;
            };

            if (convertable == null) throw new ConvertingException("Некорректные форматы файлов!");
            convertable.convert(input, output);
            logger.info("Преобразование прошло успешно!");
        } catch (Exception ex) {
            throw new ConvertingException("Произошла ошибка: " + ex.getMessage());
        }
    }
}