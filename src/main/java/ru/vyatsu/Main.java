package ru.vyatsu;

import ru.vyatsu.service.JsonToXmlConverter;
import ru.vyatsu.service.XmlToJsonConverter;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        if (args.length == 2)
        {
            String input = args[0];
            String output = args[1];
            if (input.endsWith(".xml") && output.endsWith(".json"))
            {
                XmlToJsonConverter conv = new XmlToJsonConverter();
                conv.Convert(input, output);

                System.out.println("Преобразование выполнено успешно!");
            }
            else if (input.endsWith(".json") && output.endsWith(".xml"))
            {
                JsonToXmlConverter conv = new JsonToXmlConverter();
                conv.Convert(input, output);

                System.out.println("Преобразование выполнено успешно!");
            }
            else {
                System.out.println("Конвертер предусматривает преобразование из xml в json, либо из json в xml." +
                        "Иные расширения недоступны");
            }

        }
        else {
            System.out.println("Ошибка! Команда должна содержать 2 аргумента: <путь исходного файла> <путь конвертированного файла>");
        }
    }
}