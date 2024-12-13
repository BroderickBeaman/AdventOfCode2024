package dec13;

import framework.AOCParent;

import java.util.List;
import java.util.Objects;

public class Dec13 extends AOCParent {
    @Override
    public void part1() {
        List<GamePart1> games = InputLoader.loadGames();
        Long solution = games.stream().map(GamePart1::numTokens).filter(Objects::nonNull).mapToLong(l -> l).sum();
        printSolution(solution);
    }

    @Override
    public void part2() {
        List<GamePart2> games = InputLoader.loadGames2();
        Long solution = games.stream().map(GamePart2::numTokens).filter(Objects::nonNull).mapToLong(l -> l).sum();
        printSolution(solution);
    }
}
