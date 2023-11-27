package ru.vyatsu.fileconverter.service;

public enum ConvertingType {
    XML_TO_JSON, JSON_TO_XML, INCORRECT;

    public static ConvertingType determineType(final String sourceFilePath, final String destinationFilePath) {
        if (sourceFilePath.endsWith(".xml") && destinationFilePath.endsWith(".json")) return XML_TO_JSON;
        if (sourceFilePath.endsWith(".json") && destinationFilePath.endsWith(".xml")) return JSON_TO_XML;
        return INCORRECT;
    }
}