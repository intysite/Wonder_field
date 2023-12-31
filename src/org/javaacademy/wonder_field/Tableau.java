package org.javaacademy.wonder_field;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tableau {
    private String correctAnswer;
    private Character[] letters;

    public void initializeTableau(String answer) {
        this.correctAnswer = answer.toLowerCase();
        this.letters = Stream.generate(() -> '_')
                .limit(answer.length())
                .toArray(Character[]::new);
    }

    public void showCurrentStatus() {
        if(areAttributesNotEmpty()) {
            Arrays.stream(letters).forEach(System.out::print);
            System.out.println();
        }
    }

    public void showLetter(Character letter) {
        if(areAttributesNotEmpty()) {
            IntStream.range(0, correctAnswer.length())
                    .filter(i -> correctAnswer.charAt(i) == letter)
                    .forEach(i -> letters[i] = Character.toTitleCase(letter));
            showCurrentStatus();
        }
    }

    public void showWholeWord() {
        letters = correctAnswer.chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
        System.out.println(correctAnswer.toUpperCase());
    }

    public boolean isContainedUnknownLetters() {
        return Arrays.toString(letters).contains("_");
    }

    private boolean areAttributesNotEmpty() {
        return !(correctAnswer == null || correctAnswer.isEmpty()
                && letters == null || letters.length == 0);
    }
}
