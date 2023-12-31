package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;


import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Game game = new Game();
        game.init();
        game.start();
    }
}
