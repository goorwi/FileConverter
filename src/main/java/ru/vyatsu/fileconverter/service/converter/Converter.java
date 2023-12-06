package ru.vyatsu.fileconverter.service.converter;

import ru.vyatsu.fileconverter.exception.ConvertingException;

public interface Converter {
    void convert(final String sourceFilePath, final String destinationFilePath) throws ConvertingException;
}
