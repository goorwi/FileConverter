package ru.vyatsu.fileconverter.service.converter;

import ru.vyatsu.fileconverter.exception.ConvertingException;

public interface Converter {
    void convert(String sourceFilePath, String destinationFilePath) throws ConvertingException;
}