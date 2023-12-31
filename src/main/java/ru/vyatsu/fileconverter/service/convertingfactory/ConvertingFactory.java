package ru.vyatsu.fileconverter.service.convertingfactory;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.exception.InvalidInputException;
import ru.vyatsu.fileconverter.service.converter.Converter;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

@UtilityClass
public class ConvertingFactory {
    public static Converter createConverter(final String sourceFilePath, final String destinationFilePath) throws InvalidInputException {
        try {
            if (!isFileValid(sourceFilePath) || !isFileValid(destinationFilePath)) {
                throw new InvalidInputException("Некорректный ввод файлов!");
            }

            if (sourceFilePath.endsWith(".xml")) {
                return new XmlToJsonConverter();
            }
            return new JsonToXmlConverter();
        } catch (InvalidInputException thrown) {
            throw new InvalidInputException("Ошибка пользовательского ввода!", thrown);
        }
    }

    private static boolean isFileValid(String filePath) {
        return filePath != null && (filePath.endsWith(".xml") || filePath.endsWith(".json"));
    }
}
