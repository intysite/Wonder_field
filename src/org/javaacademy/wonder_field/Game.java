package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;
import org.javaacademy.wonder_field.player.PlayerAnswer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game {
    private static final int NUMBER_OF_PLAYERS = 3;
    private static final int TOTAL_NUMBER_OF_ROUNDS = 4;
    public static final int NUMBER_OF_GROUP_ROUNDS = 3;
    public static final int FINAL_ROUND_INDEX = 3;
    public static final Scanner SCANNER = new Scanner(System.in);
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private final List<Player> winners = new ArrayList<>();
    private final Tableau tableau = new Tableau();
    private final Yakubovich yakubovich = new Yakubovich();
    private final WheelOfFortune wheelOfFortune = new WheelOfFortune();


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

        System.out.println("\n".repeat(50));
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
            if(!wheelOfFortune.rotateWheelOfFortune(player, yakubovich)) {
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
                        yakubovich.shoutAboutWinner(player, roundNumber == FINAL_ROUND_INDEX);
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

    private void playFinalRound() {
        tableau.initializeTableau(answers.get(FINAL_ROUND_INDEX));
        yakubovich.inviteThreePlayers(winners, FINAL_ROUND_INDEX);
        yakubovich.askRoundQuestion(questions.get(FINAL_ROUND_INDEX));
        tableau.showCurrentStatus();
        playRound(winners, FINAL_ROUND_INDEX);
    }

    private void superGame() {
        Player player = winners.get(FINAL_ROUND_INDEX);
        yakubovich.chooseThings();

        do {
            makeChooseThings(player);
        } while (player.getScores() >= 100 || player.getThings().size() == Things.values().length);

        SuperThing superThing = SuperThing.values()[new Random().nextInt(0, 5)];

        if(yakubovich.offerSuperGame()) {
            tableau.initializeTableau(answers.get(FINAL_ROUND_INDEX + 1));
            yakubovich.askRoundQuestion(questions.get(FINAL_ROUND_INDEX + 1));
            tableau.showCurrentStatus();
            yakubovich.askThreeLetters();

            IntStream.range(0, 3)
                    .mapToObj(e -> player.shoutLetter())
                    .forEach(e -> tableau.showLetter(e.charAt(0)));

            System.out.println("Ваш ответ?");
            String answer = SCANNER.nextLine();
            if(answer.equals(answers.get(FINAL_ROUND_INDEX + 1))) {
                yakubovich.announceSuperWinner(player, superThing);
            } else {
                yakubovich.sayFailSuperGame(superThing);
            }
        }
    }

    private void makeChooseThings(Player player) {
        while (true) {
            System.out.println("Введите номер подарка:");
            int thingsNumber = Integer.parseInt(Game.SCANNER.nextLine()) - 1;
            if(thingsNumber < 0 || thingsNumber > Things.values().length) {
                System.out.println("Некорректный выбор, введите число из списка");
            } else if(player.getScores() < Things.values()[thingsNumber].getCost()) {
                System.out.println("У Вас недостаточно очков для этого подарка");
            } else if(player.getThings().contains(Things.values()[thingsNumber])) {
                System.out.println("У Вас уже еть такой подарок");
            } else {
                Things thing = Things.values()[thingsNumber];
                player.setScores(player.getScores() - thing.getCost());
                player.addThing(thing);
                System.out.println("Вы получаете " + thing.getDescription());
                yakubovich.sayPlayerScores(player);
                break;
            }
        }
    }

    public void start() {
        yakubovich.startShow();
        playAllGroupRounds();
        playFinalRound();
        superGame();
        SCANNER.close();
        yakubovich.endShow();
    }
}