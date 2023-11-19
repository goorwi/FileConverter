package ru.vyatsu.service;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Scanner;

@UtilityClass
public class MenuService {
    private final Scanner scanner = new Scanner(System.in);

    public String getInputFile() {
        while (true) {
            System.out.println("Введите путь к файлу, который необходимо преобразовать: ");
            try {
                return scanner.nextLine();
            } catch (Exception ex) {
                System.err.println("Введите корректное значение!");
            }
        }
    }

    public String getOutputFile() {
        while (true) {
            System.out.println("Введите путь файла, с преобразованными данными: ");
            try {
                return scanner.nextLine();
            } catch (Exception ex) {
                System.err.println("Введите корректное значение!");
            }
        }
    }
}