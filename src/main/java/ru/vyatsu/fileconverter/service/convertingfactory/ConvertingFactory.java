package ru.vyatsu.fileconverter.service.convertingfactory;

import lombok.experimental.UtilityClass;
import ru.vyatsu.fileconverter.exception.ConvertingException;
import ru.vyatsu.fileconverter.exception.InvalidInputException;
import ru.vyatsu.fileconverter.service.converter.Converter;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

@UtilityClass
public class ConvertingFactory {
    public static Converter createConverter(final String sourceFilePath, final String destinationFilePath) throws InvalidInputException, ConvertingException {
        try {
            if (!isFileValid(sourceFilePath) || !isFileValid(destinationFilePath)) {
                throw new InvalidInputException("Файлы имеют некорректные расширения!");
            }

            if (sourceFilePath.endsWith(".xml")) {
                return new XmlToJsonConverter();
            }
            return new JsonToXmlConverter();
        } catch (InvalidInputException thrown) {
            throw new InvalidInputException("Ошибка пользовательского ввода!", thrown);
        } catch (ConvertingException thrown) {
            throw new ConvertingException(thrown);
        }
    }

    private static boolean isFileValid(String filePath) {
        return filePath != null && (filePath.endsWith(".xml") || filePath.endsWith(".json"));
    }
}
