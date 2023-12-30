package org.javaacademy.wonder_field;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tableau {
    private String correctAnswer;
    private Character[] letters;

    public void initializeTableau(String answer) {
        this.correctAnswer = answer;
        this.letters = Stream.generate(() -> '_').limit(answer.length()).toArray(Character[]::new);
    }

    public void showCurrentStatus() {
        if(areAttributesNotEmpty()) {
            Arrays.stream(letters).forEach(System.out::print);
        }
    }

    public void showLetter(Character letter) {
        if(areAttributesNotEmpty()) {
            if(correctAnswer.contains(letter.toString())) {
                IntStream.range(0, correctAnswer.length())
                         .filter(i -> correctAnswer.charAt(i) == letter)
                         .forEach(i -> letters[i] = Character.toTitleCase(letter));
            } else System.out.println("Нет такой буквы!");
        }
        showCurrentStatus();
    }

    private boolean areAttributesNotEmpty() {
        return !(correctAnswer == null || correctAnswer.isEmpty()
                && letters == null || letters.length == 0);
    }
}
