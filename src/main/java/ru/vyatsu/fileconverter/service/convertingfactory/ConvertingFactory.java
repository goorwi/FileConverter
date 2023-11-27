package ru.vyatsu.fileconverter.service.convertingfactory;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.exception.InvalidInputException;
import ru.vyatsu.fileconverter.service.Converter;
import ru.vyatsu.fileconverter.service.ConvertingType;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

@UtilityClass
public class ConvertingFactory {
    public static Converter createConverter(final String sourceFilePath, final String destinationFilePath) throws InvalidInputException {
        try {
            if (!isFileValid(sourceFilePath) || !isFileValid(destinationFilePath))
                throw new InvalidInputException("Некорректный ввод файлов");

            return switch (ConvertingType.determineType(sourceFilePath, destinationFilePath)) {
                case XML_TO_JSON -> new XmlToJsonConverter();
                case JSON_TO_XML -> new JsonToXmlConverter();
                case INCORRECT -> null;
            };
        } catch (Exception thrown) {
            throw new InvalidInputException("Ошибка пользовательского ввода!", thrown);
        }
    }

    private static boolean isFileValid(String filePath) {
        return filePath != null && (filePath.endsWith(".xml") || filePath.endsWith(".json"));
    }
}
