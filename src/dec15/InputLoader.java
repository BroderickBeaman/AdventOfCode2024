package dec15;

import framework.InputLoaderParent;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.ArrayList;
import java.util.List;

class InputLoader extends InputLoaderParent {
    static Grid<ElementP1> loadWarehouseP1() {
        List<String> lines = loadLines("warehouse.txt");
        int rows = lines.size();
        int cols = lines.get(0).length();
        ElementP1[][] grid = new ElementP1[rows][cols];
        for (int row = 0; row < rows; row++) {
            String line  = lines.get(row);
            for (int col = 0; col < cols; col++) {
                grid[row][col] = ElementP1.fromCharacter(line.charAt(col));
            }
        }

        return new Grid<>(grid);
    }

    static Grid<ElementP2> loadWarehouseP2() {
        List<String> lines = loadLines("warehouse.txt");
        int rows = lines.size();
        int cols = lines.get(0).length();
        ElementP2[][] grid = new ElementP2[rows][cols * 2];
        for (int row = 0; row < rows; row++) {
            String line  = lines.get(row);
            for (int col = 0; col < cols; col++) {
                Character character = line.charAt(col);
                switch (character) {
                    case '#':
                        grid[row][col * 2] = ElementP2.WALL;
                        grid[row][(col * 2) + 1] = ElementP2.WALL;
                        break;
                    case '.':
                        grid[row][col * 2] = ElementP2.EMPTY;
                        grid[row][(col * 2) + 1] = ElementP2.EMPTY;
                        break;
                    case 'O':
                        grid[row][col * 2] = ElementP2.BOX_LEFT;
                        grid[row][(col * 2) + 1] = ElementP2.BOX_RIGHT;
                        break;
                    case '@':
                        grid[row][col * 2] = ElementP2.ROBOT;
                        grid[row][(col * 2) + 1] = ElementP2.EMPTY;
                        break;
                    default:
                        throw new IllegalArgumentException("Unexpected Character: " + character);
                }
            }
        }

        return new Grid<>(grid);
    }

    static List<Direction> loadCommands() {
        List<String> lines = loadLines("commands.txt");
        List<Direction> commands = new ArrayList<>();
        int rows = lines.size();
        int cols = lines.get(0).length();

        for (int row = 0; row < rows; row++) {
            String line  = lines.get(row);
            for (int col = 0; col < cols; col++) {
                commands.add(fromChar(line.charAt(col)));
            }
        }

        return commands;
    }

    static Direction fromChar(Character character) {
        return switch(character) {
            case '^' -> Direction.N;
            case '>' -> Direction.E;
            case 'v' -> Direction.S;
            case '<' -> Direction.W;
            default -> throw new IllegalStateException("Unsupported value: " + character);
        };
    }
}
