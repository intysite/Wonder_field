package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;
import org.javaacademy.wonder_field.player.PlayerAnswer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    public static final int NUMBER_OF_PLAYERS = 3;
    public static final int TOTAL_NUMBER_OF_ROUNDS = 4;
    public static final int NUMBER_OF_GROUP_ROUNDS = 3;
    public static final int FINAL_ROUND_INDEX = 3;
    public static final Scanner SCANNER = new Scanner(System.in);
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private final List<Player> winners = new ArrayList<>();
    private final Tableau tableau = new Tableau();
    private final Yakubovich yakubovich = new Yakubovich();

    public void init() {
        System.out.println("Запуск игры \"Поле Чудес\" - подготовка к игре. Вам нужно ввести вопросы и ответы для игры.");

        for (int i = 1; i <= TOTAL_NUMBER_OF_ROUNDS + 1; i++) {
            System.out.printf("Введите вопрос #%d\n", i);
            questions.add(SCANNER.nextLine());
            System.out.printf("Введите ответ вопрос #%d\n", i);
            answers.add(SCANNER.nextLine());
        }

        System.out.println("Инициализация закончена, игра начнется через 5 секунд");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        Stream.generate(() -> "\n")
                .limit(50)
                .forEach(System.out::println);
    }

    private List<Player> createPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PLAYERS; i++) {
            System.out.printf("Игрок №%d представьтесь: имя,город. Например: Иван, Хельсинки}\n", i);
            String[] split = SCANNER.nextLine().split(",");
            players.add(new Player(split[0], split[1]));
        }
        return players;
    }

    private List<String> getPlayersNames(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    private boolean isTableauFullyOpen() {
        return !tableau.isContainedUnknownLetters();
    }

    private boolean moveOfPlayer(String question, Player player) {
        int count = 0;
        while(!isTableauFullyOpen()) {
            yakubovich.rotateWheel();
            if(!WheelOfFortune.rotateWheelOfFortune(player, yakubovich)) {
                return false;
            }
            PlayerAnswer playerAnswer = player.move();
            if(!yakubovich.isPlayerAnswerCorrect(player,
                    playerAnswer,
                    answers.get(questions.indexOf(question)),
                    tableau)) {
                return false;
            }
            count++;
            if(count % 3 == 0) {
                yakubovich.bringTwoBoxes(player);
            }
        }
        return true;
    }

    private void playRound(List<Player> players, int roundNumber) {
        while (true) {
            for (Player player : players) {
                if (moveOfPlayer(questions.get(roundNumber), player)) {
                    if (isTableauFullyOpen()) {
                        winners.add(player);
                        yakubovich.shoutAboutWinner(player,
                                        roundNumber == FINAL_ROUND_INDEX);
                        return;
                    }
                }
            }
        }
    }

    private void playAllGroupRounds() {
        for (int i = 0; i < NUMBER_OF_GROUP_ROUNDS; i++) {
            List<Player> players = createPlayers();
            tableau.initializeTableau(answers.get(i));
            yakubovich.inviteThreePlayers(players, i);
            yakubovich.askRoundQuestion(questions.get(i));
            tableau.showCurrentStatus();
            playRound(players, i);
        }
    }

    private void plaFinalRound() {
        tableau.initializeTableau(answers.get(FINAL_ROUND_INDEX));
        yakubovich.inviteThreePlayers(winners, FINAL_ROUND_INDEX);
        yakubovich.askRoundQuestion(questions.get(FINAL_ROUND_INDEX));
        tableau.showCurrentStatus();
        playRound(winners, FINAL_ROUND_INDEX);
    }

    private void superGame() {
        Player player = winners.get(FINAL_ROUND_INDEX + 1);

    }

    public void start() {
        yakubovich.startShow();
        playAllGroupRounds();
        plaFinalRound();
        yakubovich.endShow();
    }
}