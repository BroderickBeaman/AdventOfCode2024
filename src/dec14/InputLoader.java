package dec14;

import framework.InputLoaderParent;
import framework.utils.Coordinate;

import java.util.List;

class InputLoader extends InputLoaderParent {
    static List<Robot> loadRobots() {
        List<String> lines = loadLines();
        return lines.stream().map(InputLoader::robotFromString).toList();
    }

    static Robot robotFromString(String line) {
        String[] parts = line.split(" ");

        String posString = parts[0];
        String velString = parts[1];

        int posX = Integer.valueOf(posString.substring(posString.indexOf('=') + 1, posString.indexOf(',')));
        int posY = Integer.valueOf(posString.substring(posString.indexOf(',') + 1));

        Coordinate position = new Coordinate(posY, posX);

        int velX = Integer.valueOf(velString.substring(velString.indexOf('=') + 1, velString.indexOf(',')));
        int velY = Integer.valueOf(velString.substring(velString.indexOf(',') + 1));

        Coordinate velocity = new Coordinate(velY, velX);

        return new Robot(position, velocity);
    }

}
