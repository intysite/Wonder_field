package org.javaacademy.wonder_field;

import org.javaacademy.wonder_field.player.Player;

import java.util.Random;

public class WheelOfFortune {
    Random random = new Random();

    public boolean rotateWheelOfFortune(Player player, Yakubovich yakubovich) {

        int result = random.nextInt(1, 14) * 100;
        yakubovich.sayRotateResult(result);
        if(result == 1300) {
            return false;
        } else if (result == 1400) {
            player.setScores(player.getScores() * 2);
            return true;
        } else {
            player.setScores(player.getScores() + (result));
        }
        return true;
    }
}
