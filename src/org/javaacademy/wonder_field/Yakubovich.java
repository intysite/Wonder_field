package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.AnswerType;
import org.javaacademy.wonder_field.player.Player;
import org.javaacademy.wonder_field.player.PlayerAnswer;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Yakubovich {
    public void startShow() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void endShow() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void inviteThreePlayers(List<Player> players, int roundNumber) {
        if(roundNumber < Game.NUMBER_OF_GROUP_ROUNDS) {
            System.out.printf("Якубович: приглашаю %d тройку игроков:\n", roundNumber + 1);
            System.out.println(concatenateNames(players));
        } else if (roundNumber == Game.FINAL_ROUND_INDEX) {
            System.out.printf("Якубович: приглашаю победителей групповых этапов: %s\n", concatenateNames(players));
        }
    }

    public void askRoundQuestion(String question) {
        System.out.printf("Якубович: Внимание вопрос!\n%s\n", question);
    }

    public void shoutAboutWinner(Player player, boolean isFinalRound) {
        if(isFinalRound) {
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес! Это %s из %s\n",
                    player.getName(),
                    player.getCity());
            sayPlayerScores(player);
        } else {
            System.out.printf("Якубович: Молодец! %s из %s проходит в финал!\n",
                    player.getName(),
                    player.getCity());
        }
    }

    public boolean isPlayerAnswerCorrect(Player player,
                                         PlayerAnswer playerAnswer,
                                         String correctAnswer,
                                         Tableau tableau) {
        String delimiter = "__________________________________";
        if(playerAnswer.answerType() == AnswerType.LETTER) {
            if(correctAnswer.contains(playerAnswer.answer())) {
                System.out.println("Якубович: Есть такая буква, откройте ее!");
                tableau.showLetter(playerAnswer.answer().charAt(0));
                sayPlayerScores(player);
                System.out.println(delimiter);
                return true;
            } else {
                System.out.println("Якубович: Нет такой буквы! Следующий игрок, крутите барабан!");
                System.out.println(delimiter);
                return false;
            }
        } else if (playerAnswer.answerType() == AnswerType.WORD) {
            if(playerAnswer.answer().equals(correctAnswer)) {
                System.out.printf("Якубович: %s! Абсолютно верно!\n", correctAnswer);
                tableau.showWholeWord();
                sayPlayerScores(player);
                System.out.println(delimiter);
                return true;
            } else {
                System.out.println("Якубович: Неверно! Следующий игрок!");
                System.out.println(delimiter);
            }
        }
        return false;
    }

    public void rotateWheel() {
        System.out.println("Якубович: Вращайте барабан!");
    }

    public void sayRotateResult(int result) {
        if(result == 13) {
            System.out.println("Якубович: Пропуск хода! И мы переходим к следующему игроку!");
        } else if (result == 14) {
            System.out.println("Якубович: Ваши очки удваиваются!");
        } else {
            System.out.printf("Якубович: %d очков на барабане!\n", result);
        }
    }

    public void bringTwoBoxes(Player player) {
        System.out.println("Якубович: Внесите две шкатулки!");
        int result = new Random().nextInt(1,2);
        System.out.println("Якубович: Выберите одну из двух шкатулок, 1 или 2:");
        int decision = Integer.parseInt(Game.SCANNER.nextLine());
        if(decision == result) {
            System.out.println("Якубович: Вы угадали! Вы получаете 100 долларов!");
            player.setMoney(player.getMoney() + 100);
        } else {
            System.out.println("Якубович: Пусто! Играем дальше.");
        }
    }

    public void sayPlayerScores(Player player) {
        System.out.printf("Якубович: У вас %d очков!\n", player.getScores());
    }

    private String concatenateNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }

    public void chooseThings () {
        System.out.println("Якубович: Выбирайте подарки:");
        Arrays.stream(Things.values())
                .map(Enum::toString)
                .forEach(System.out::println);
    }

    public boolean offerSuperGame () {
        System.out.println("Якубович: Желаете сыграть в суперигру?");
        System.out.println("Введите да(д) или нет(н):");
        while (true) {
            String line = Game.SCANNER.nextLine();
            if (line.length() > 1) {
                System.out.println("Ошибка! Введите одну букву!");
            } else if (line.equals("д")) {
                System.out.println("Якубович: Играем суперигру!");
                return true;
            } else {
                return false;
            }
        }
    }

    public void askThreeLetters() {
        System.out.println("Якубович: Назовите три буквы!");
    }

    public void announceSuperWinner(Player player, SuperThing superThing) {
        System.out.println("Якубович: Абсолютно верно! Вы сегодня забираете подарки:");
        player.getThings().stream()
                .map(Enum::toString)
                .forEach(System.out::println);
        System.out.printf("Сумма ваших денег составляет %d долларов\n", player.getMoney());
        System.out.printf("И конечно же главный приз: %s\n", superThing.toString());
    }

    public void sayFailSuperGame(SuperThing superThing) {
        System.out.printf("Якубович: Неверно. Вы могли выиграть %s", superThing.toString());
    }
}
