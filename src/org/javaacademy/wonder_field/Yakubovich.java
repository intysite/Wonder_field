package org.javaacademy.wonder_field;

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
            System.out.printf("Якубович: приглашаю %d тройку игроков: ", roundNumber);
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
            System.out.printf("Якубович: И перед нами победитель Капитал шоу поле чудес! Это %s из %s", name, city);
        } else {
            System.out.printf("Якубович: Молодец! %s из %s проходит в финал!", name, city);
        }
    }

    public void checkPlayerAnswer(PlayerAnswer playerAnswer, String answer, Tableau tableau) {

    }

    private String concatenateNames(List<Player> players) {
        return players.stream().map(Player::getName).collect(Collectors.joining(", "));
    }
}
