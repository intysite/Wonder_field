package org.javaacademy.wonder_field;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Game {
    public static final int NUMBER_OF_PLAYERS = 3;
    public static final int TOTAL_NUMBER_OF_ROUNDS = 4;
    public static final int NUMBER_OF_GROUP_ROUNDS = 3;
    public static final int FINAL_ROUND_INDEX = 4;
    public static final Scanner SCANNER = new Scanner(System.in);
    private List<String> questions = new ArrayList<>();
    private List<String> answers = new ArrayList<>();
    private Tableau tableau = new Tableau();

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");

        for (int i = 1; i <= TOTAL_NUMBER_OF_ROUNDS; i++) {
            System.out.printf("Введите вопрос #%d\n", i);
            questions.add(SCANNER.nextLine());
            System.out.printf("Введите ответ вопрос #%d\n", i);
            answers.add(SCANNER.nextLine());
        }

        System.out.println("Иницализация закончена, игра начнется через 5 секунд");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Stream.generate(() -> "\n").limit(50).forEach(System.out::println);
    }
}
