package de.birgitkratz.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BingoBoard {

    final int[] boardNumbers;

    public BingoBoard(final List<String> inputList) {
        boardNumbers = initFromListOfString(inputList);
    }

    private int[] initFromListOfString(final List<String> inputList) {
        return inputList.stream()
                .map(row -> row.trim().split("\s+"))
                .map(a -> Arrays.stream(a).mapToInt(Integer::parseInt))
                .flatMapToInt(i -> i)
                .toArray();
    }

    public void markAsDrawn(final int number) {
        IntStream.range(0, boardNumbers.length).forEach(i -> {
            if (boardNumbers[i] == number) {
                boardNumbers[i] = -1;
            }
        });
    }

    public boolean checkRowIsComplete() {
        return IntStream.range(0, 5)
                .anyMatch(i -> Arrays.stream(Arrays.copyOfRange(boardNumbers, i * 5, i * 5 + 5)).sum() == -5);
    }

    public boolean checkColumnIsComplete() {
        return IntStream.range(0, 5)
                .anyMatch(i -> IntStream.range(0, 5).allMatch(k -> boardNumbers[i + k * 5] == -1));
    }

    public int sumUnmarkedNumbers() {
        return Arrays.stream(boardNumbers).filter(number -> number > -1).sum();
    }
}
