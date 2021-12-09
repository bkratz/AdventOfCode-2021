package de.birgitkratz.aoc2021;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BingoBoardTest {

    List<String> inputList;
    @BeforeEach
    void init() {
        inputList = new ArrayList<>();
        inputList.add("22 13 17 11  0");
        inputList.add(" 8  2 23  4 24");
        inputList.add("21  9 14 16  7");
        inputList.add(" 6 10  3 18  5");
        inputList.add(" 1 12 20 15 19");
    }
    @Test
    void parseBingoBoard() {
        final var bingoBoard = new BingoBoard(inputList);

        assertThat(bingoBoard.boardNumbers).isEqualTo(
                new int[] {22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19});

    }

    @Test
    void markAsDrawn() {
        final var bingoBoard = new BingoBoard(inputList);

        bingoBoard.markAsDrawn(17);

        assertThat(bingoBoard.boardNumbers).isEqualTo(
                new int[] {22, 13, -1, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19});
    }

    @Test
    void checkRowIsComplete() {
        final var bingoBoard = new BingoBoard(inputList);

        bingoBoard.markAsDrawn(17);
        bingoBoard.markAsDrawn(8);
        bingoBoard.markAsDrawn(2);
        bingoBoard.markAsDrawn(21);

        assertThat(bingoBoard.checkRowIsComplete()).isFalse();

        bingoBoard.markAsDrawn(23);
        bingoBoard.markAsDrawn(24);
        bingoBoard.markAsDrawn(4);

        assertThat(bingoBoard.checkRowIsComplete()).isTrue();
    }

    @Test
    void checkColumnIsComplete() {
        final var bingoBoard = new BingoBoard(inputList);

        bingoBoard.markAsDrawn(22);
        bingoBoard.markAsDrawn(13);
        bingoBoard.markAsDrawn(2);
        bingoBoard.markAsDrawn(9);

        assertThat(bingoBoard.checkColumnIsComplete()).isFalse();

        bingoBoard.markAsDrawn(15);
        bingoBoard.markAsDrawn(12);
        bingoBoard.markAsDrawn(10);

        assertThat(bingoBoard.checkColumnIsComplete()).isTrue();
    }
}