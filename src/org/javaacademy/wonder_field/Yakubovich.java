package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.AnswerType;
import org.javaacademy.wonder_field.player.Player;
import org.javaacademy.wonder_field.player.PlayerAnswer;

import java.util.List;
import java.util.stream.Collectors;

public class Yakubovich {
    public void startShow() {
        System.out.println("Якубович: Здравствуйте, уважаемые дамы и господа! Пятница! В эфире капитал-шоу «Поле чудес»!");
    }

    public void endShow() {
        System.out.println("Якубович: Мы прощаемся с вами ровно на одну неделю! Здоровья вам, до встречи!");
    }

    public void inviteThreePlayers(List<Player> players, int roundNumber) {
        if(roundNumber <= Game.NUMBER_OF_GROUP_ROUNDS) {
            System.out.printf("Якубович: приглашаю %d тройку игроков: ", roundNumber + 1);
            System.out.println(concatenateNames(players));
        } else if (roundNumber == Game.FINAL_ROUND_INDEX) {
            System.out.printf("Якубович: приглашаю победителей групповых этапов: %s", concatenateNames(players));
        }
    }

    public void askRoundQuestion(String question) {
        System.out.printf("Якубович: Внимание вопрос!\n%s\n", question);
    }

    public void shoutAboutWinner(String name, String city, boolean isFinalRound) {
        if(isFinalRound) {
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес! Это %s из %s\n", name, city);
        } else {
            System.out.printf("Якубович: Молодец! %s из %s проходит в финал!\n", name, city);
        }
    }

    public boolean isPlayerAnswerCorrect(PlayerAnswer playerAnswer, String correctAnswer, Tableau tableau) {
        String delimiter = "__________________________________";
        if(playerAnswer.answerType() == AnswerType.LETTER) {
            if(correctAnswer.contains(playerAnswer.answer())) {
                System.out.println("Якубович: Есть такая буква, откройте ее!");
                tableau.showLetter(playerAnswer.answer().charAt(0));
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
                System.out.println(delimiter);
                return true;
            } else {
                System.out.println("Якубович: Неверно! Следующий игрок!");
                System.out.println(delimiter);
            }
        }
        return false;
    }

    private String concatenateNames(List<Player> players) {
        return players.stream().map(Player::getName).collect(Collectors.joining(", "));
    }
}
