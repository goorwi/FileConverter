package ru.vyatsu.fileconverter.service;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class MenuUtils {
    private final Scanner SCANNER = new Scanner(System.in);

    public String getInputFile() {
        while (true) {
            System.out.println("Введите путь к файлу, который необходимо преобразовать: ");
            return SCANNER.nextLine();
        }
    }

    public String getOutputFile() {
        while (true) {
            System.out.println("Введите путь файла, с преобразованными данными: ");
            return SCANNER.nextLine();
        }
    }
}