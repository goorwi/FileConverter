package ru.vyatsu.service;

public interface Convertable {
    void convert(String input, String output) throws ConvertingException;
}
