package dec21;

import framework.InputLoaderParent;
import framework.utils.Grid;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static Grid<Character> loadNumberGrid() {
        Character[][] grid = new Character[][] {{'7','8','9'}, {'4','5','6'}, {'1','2','3'}, {' ','0','A'}};
        return new Grid<>(grid);
    }

    static Grid<Character> loadDirectionalGrid() {
        Character[][] grid = new Character[][] {{' ','^','A'}, {'<','v','>'}};
        return new Grid<>(grid);
    }

    static List<String> loadCodes() {
        return loadLines().stream().map(line -> "A" + line).toList();
    }
}
