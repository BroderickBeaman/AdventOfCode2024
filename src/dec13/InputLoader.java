package dec13;

import framework.InputLoaderParent;
import framework.utils.Coordinate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

class InputLoader extends InputLoaderParent {

    static List<GamePart1> loadGames() {
        List<String> lines = loadLines();
        List<GamePart1> games = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i % 4 == 0) {
                Coordinate buttonA = loadButton(lines.get(i));
                Coordinate buttonB = loadButton(lines.get(i+1));
                Coordinate prize = loadPrize(lines.get(i+2));
                games.add(new GamePart1(buttonA, buttonB, prize));
            }
        }

        return games;
    }

    static Coordinate loadButton(String buttonString) {
        Integer buttonX = Integer.valueOf(buttonString.substring(12).split(",")[0]);
        Integer buttonY = Integer.valueOf(buttonString.split("Y+")[1]);
        return new Coordinate(buttonX, buttonY);
    }

    static Coordinate loadPrize(String prizeString) {
        Integer prizeX = Integer.valueOf(prizeString.substring(9).split(",")[0]);
        Integer prizeY = Integer.valueOf(prizeString.split("Y=")[1]);
        return new Coordinate(prizeX, prizeY);
    }

    static List<GamePart2> loadGames2() {
        List<String> lines = loadLines();
        List<GamePart2> games = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (i % 4 == 0) {
                String buttonAString = lines.get(i);
                String buttonBString = lines.get(i+1);
                String prizeString = loadLines().get(i+2);

                Long buttonAX = Long.valueOf(buttonAString.substring(12).split(",")[0]);
                Long buttonAY = Long.valueOf(buttonAString.split("Y+")[1]);

                Long buttonBX = Long.valueOf(buttonBString.substring(12).split(",")[0]);
                Long buttonBY = Long.valueOf(buttonBString.split("Y+")[1]);

                Long prizeX = Long.valueOf(prizeString.substring(9).split(",")[0]) + 10000000000000L;
                Long prizeY = Long.valueOf(prizeString.split("Y=")[1]) + 10000000000000L;

                games.add(new GamePart2(buttonAX, buttonAY, buttonBX, buttonBY, prizeX, prizeY));
            }
        }

        return games;
    }
}
