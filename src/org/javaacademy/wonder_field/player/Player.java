package org.javaacademy.wonder_field.player;

import org.javaacademy.wonder_field.Game;

public class Player {
    private final String name;
    private final String city;

    public Player(String name, String city) {
        this.name = name;
        this.city = city;
    }

    private String shoutLetter() {
        while (true) {
            String line = Game.SCANNER.nextLine();
            if(line.length() > 1) {
                System.out.println("Ошибка! Введите одну букву!");
            } else if(!line.matches("[а-я]")) {
                System.out.println("Ошибка! это не русская буква, введите русскую букву:");
            } else return line;
        }
    }

    private String sayWord() {
        String line = Game.SCANNER.nextLine();
        System.out.printf("Игрок %s: слово %s\n", name, line);
        return line;
    }

    public PlayerAnswer move() {
        System.out.printf("Ход игрока %s, %s\n", name, city);
        System.out.println("Если хотите букву нажмите 'б' и enter, если хотите слово нажмите 'c' и enter");
        while (true) {
            String line = Game.SCANNER.nextLine();
            if(line.equals("б")) {
                return new PlayerAnswer(AnswerType.LETTER, shoutLetter());
            } else if(line.equals("с")) {
                return new PlayerAnswer(AnswerType.WORD, sayWord());
            }
            System.out.println("Некорректное значение, введите 'б' или 'с'");
        }
    }
}
