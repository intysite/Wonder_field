package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;

public class Runner {
    public static void main(String[] args) {
//        Game game = new Game();
//        game.init();
//        game.start();
        Player player = new Player("Вася", "Чоп");
        player.setScores(2500);
        Yakubovich yakubovich = new Yakubovich();
        yakubovich.chooseThings(player);
    }
}
