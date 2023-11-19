package ru.vyatsu.service;

public enum ConvertingType {
    XML_TO_JSON, JSON_TO_XML, INCORRECT;

    public static ConvertingType determineType(String input, String output) {
        if (input.endsWith(".xml") && output.endsWith(".json")) return XML_TO_JSON;
        if (input.endsWith(".json") && output.endsWith(".xml")) return JSON_TO_XML;
        return INCORRECT;
    }
}