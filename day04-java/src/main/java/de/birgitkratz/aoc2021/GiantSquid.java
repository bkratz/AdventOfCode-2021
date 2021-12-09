package de.birgitkratz.aoc2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GiantSquid {

    List<BingoBoard> boards = new ArrayList<>();
    String[] numbers;
    int result;

    public GiantSquid(final Stream<String> inputStream) {
        final var stringList = inputStream.toList();

        numbers = stringList.get(0).split(",");

        int index = 2;
        List<String> boardStringList = new ArrayList<>();
        while (index < stringList.size()) {
            final var s = stringList.get(index);
            if (!s.trim().isEmpty()) {
                boardStringList.add(s);
                if (index == stringList.size()-1) {
                    boards.add(new BingoBoard(boardStringList));
                }
            } else {
                boards.add(new BingoBoard(boardStringList));
                boardStringList.clear();
            }
            index = index + 1;
        }

    }
    public int calculateFinalScoreOfFirstWinningBoard() {
        Arrays.stream(numbers).filter(number -> {
            final var n = Integer.parseInt(number);
            final var first = boards.stream().filter(board -> {
                board.markAsDrawn(n);
                if (board.checkColumnIsComplete() || board.checkRowIsComplete()) {
                    int sum = board.sumUnmarkedNumbers();
                    result = n * sum;
                    return true;
                }
                return false;
            }).findFirst().orElse(null);
            return first != null;
        }).findAny().orElse(null);

        return result;
    }

    public int calculateFinalScoreOfLastWinningBoard() {
        Arrays.stream(numbers).filter(number -> {
            final var n = Integer.parseInt(number);
            final var winningBoards = boards.stream().filter(board -> {
                board.markAsDrawn(n);
                if (board.checkColumnIsComplete() || board.checkRowIsComplete()) {
                    int sum = board.sumUnmarkedNumbers();
                    result = n * sum;
                    return true;
                }
                return false;
            }).toList();
            boards.removeAll(winningBoards);
            if (boards.isEmpty()) {
                return true;
            }
            return false;
        }).findAny().orElse(null);

        return result;
    }
}
