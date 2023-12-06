package ru.vyatsu.fileconverter.service;

import lombok.experimental.UtilityClass;

import java.util.NoSuchElementException;
import java.util.Scanner;

@UtilityClass
public class MenuUtils {
    private final Scanner SCANNER = new Scanner(System.in);

    public String getInputFile() {
        while (true) {
            System.out.println("Введите путь к файлу, который необходимо преобразовать: ");
            try {
                return SCANNER.nextLine();
            } catch (NoSuchElementException | IllegalStateException thrown) {
                System.err.println("Произошла ошибка ввода. Пожалуйста, попробуйте еще раз.");
            }
        }
    }

    public String getOutputFile() {
        while (true) {
            System.out.println("Введите путь файла, с преобразованными данными: ");
            try {
                return SCANNER.nextLine();
            } catch (NoSuchElementException | IllegalStateException thrown) {
                System.err.println("Произошла ошибка ввода. Пожалуйста, попробуйте еще раз.");
            }
        }
    }
}