package de.birgitkratz.aoc2021;

import java.util.List;
import java.util.stream.Stream;

class Dive {

    public List<MoveCommand> parseMoveCommands(final Stream<String> inputStream) {
        return inputStream.map(input -> {
                    final var split = input.split(" ");
                    return new MoveCommand(split[0], Integer.parseInt(split[1]));
                })
                .toList();
    }

    public Position applyMovement_part1(final MoveCommand command, Position startPosition) {
        return switch (command.direction()) {
            case "forward" -> new Position(startPosition.horizontal() + command.value(), startPosition.depth(), 0);
            case "down" -> new Position(startPosition.horizontal(), startPosition.depth() + command.value(), 0);
            case "up" -> new Position(startPosition.horizontal(), startPosition.depth() - command.value(), 0);
            default -> null;
        };
    }

    public Position calculatPosition_part1(final Stream<String> inputStream) {
        final Position[] startPosition = {new Position(0, 0, 0)};
        parseMoveCommands(inputStream)
                .forEach(command -> startPosition[0] = applyMovement_part1(command, startPosition[0]));
        return startPosition[0];
    }

    public Position applyMovement_part2(final MoveCommand command, final Position startPosition) {
        return switch (command.direction()) {
            case "forward" -> new Position(startPosition.horizontal() + command.value(), startPosition.depth() + startPosition.aim()*command.value(),
                    startPosition.aim());
            case "down" -> new Position(startPosition.horizontal(), startPosition.depth(), startPosition.aim() + command.value());
            case "up" -> new Position(startPosition.horizontal(), startPosition.depth(), startPosition.aim() - command.value());
            default -> null;
        };
    }

    public Position calculatPosition_part2(final Stream<String> inputStream) {
        final Position[] startPosition = {new Position(0, 0, 0)};
        parseMoveCommands(inputStream)
                .forEach(command -> startPosition[0] = applyMovement_part2(command, startPosition[0]));
        return startPosition[0];
    }
}
