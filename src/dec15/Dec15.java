package dec15;

import framework.AOCParent;
import framework.utils.Coordinate;
import framework.utils.Direction;
import framework.utils.Grid;

import java.util.*;

public class Dec15 extends AOCParent {
    @Override
    public void part1() {
        Grid<ElementP1> warehouse = InputLoader.loadWarehouseP1();
        List<Direction> commands = InputLoader.loadCommands();

        Coordinate robot = warehouse.findValue(ElementP1.ROBOT).getFirst();

        for (Direction command : commands) {
            Coordinate next = robot.addCoordinate(command.toCoordinate());
            ElementP1 atNext = warehouse.get(next);

            if (atNext.equals(ElementP1.WALL)) {
                continue;
            } else if (atNext.equals(ElementP1.EMPTY)) {
                warehouse.set(ElementP1.ROBOT, next);
                warehouse.set(ElementP1.EMPTY, robot);
                robot = next;
                continue;
            }

            // Next space is a box
            if (moveBox(warehouse, next, command)) {
                warehouse.set(ElementP1.ROBOT, next);
                warehouse.set(ElementP1.EMPTY, robot);
                robot = next;
            }
        }

        long solution = computeScoreP1(warehouse);
        System.out.println(warehouse);
        printSolution(solution);
    }

    private boolean moveBox(Grid<ElementP1> warehouse, Coordinate box, Direction direction) {
        Coordinate next = box.addCoordinate(direction.toCoordinate());
        ElementP1 atNext = warehouse.get(next);

        if (atNext.equals(ElementP1.WALL)) {
            return false;
        } else if (atNext.equals(ElementP1.EMPTY)) {
            warehouse.set(ElementP1.BOX, next);
            warehouse.set(ElementP1.EMPTY, box);
            return true;
        }

        if (moveBox(warehouse, next, direction)) {
            warehouse.set(ElementP1.BOX, next);
            warehouse.set(ElementP1.EMPTY, box);
            return true;
        }

        return false;
    }

    private Long computeScoreP1(Grid<ElementP1> warehouse) {
        List<Coordinate> boxes = warehouse.findValue(ElementP1.BOX);
        return boxes.stream().mapToLong(box -> box.row() * 100L + box.col()).sum();
    }

    @Override
    public void part2() {
        Grid<ElementP2> warehouse = InputLoader.loadWarehouseP2();
        List<Direction> commands = InputLoader.loadCommands();

        Coordinate robot = warehouse.findValue(ElementP2.ROBOT).getFirst();

        for (Direction command : commands) {
            Coordinate next = robot.addCoordinate(command.toCoordinate());
            ElementP2 atNext = warehouse.get(next);

            if (atNext.equals(ElementP2.WALL)) {
                continue;
            } else if (atNext.equals(ElementP2.EMPTY)) {
                warehouse.set(ElementP2.ROBOT, next);
                warehouse.set(ElementP2.EMPTY, robot);
                robot = next;
                continue;
            }

            // Next space is a box. If one of the boxes in the chain can't move, a BoxMoveException will be thrown
            // and nothing will move
            try {

                // We are always considering the coordinate of the box as the coordinate of the left half.
                Coordinate boxCoordinate = atNext.equals(ElementP2.BOX_LEFT) ? next : next.addCol(-1);
                Stack<Coordinate> boxesToMove = new Stack<>();
                moveBox(warehouse, boxesToMove, boxCoordinate, command);

                switch (command) {
                    case E -> {
                        while (!boxesToMove.isEmpty()) {
                            Coordinate box = boxesToMove.pop();
                            warehouse.set(ElementP2.EMPTY, box);
                            warehouse.set(ElementP2.BOX_LEFT, box.addCol(1));
                            warehouse.set(ElementP2.BOX_RIGHT, box.addCol(2));
                        }
                    }
                    case W -> {
                        while (!boxesToMove.isEmpty()) {
                            Coordinate box = boxesToMove.pop();
                            warehouse.set(ElementP2.EMPTY, box.addCol(1));
                            warehouse.set(ElementP2.BOX_RIGHT, box);
                            warehouse.set(ElementP2.BOX_LEFT, box.addCol(-1));
                        }
                    }
                    case N, S -> {
                        while (!boxesToMove.isEmpty()) {
                            Coordinate boxLeft = boxesToMove.pop();
                            Coordinate boxRight = boxLeft.addCol(1);
                            warehouse.set(ElementP2.EMPTY, boxLeft);
                            warehouse.set(ElementP2.EMPTY, boxRight);
                            warehouse.set(ElementP2.BOX_LEFT, boxLeft.addCoordinate(command.toCoordinate()));
                            warehouse.set(ElementP2.BOX_RIGHT, boxRight.addCoordinate(command.toCoordinate()));
                        }
                    }
                }

                warehouse.set(ElementP2.ROBOT, next);
                warehouse.set(ElementP2.EMPTY, robot);
                robot = next;
            } catch (BoxMoveException e) {

            }
        }

        System.out.println(warehouse);

        List<Coordinate> boxLocations = warehouse.findValue(ElementP2.BOX_LEFT);
        long solution = boxLocations.stream().mapToLong(box -> {
            long rowValue = box.row() * 100;
            long colValue = box.col();
            return rowValue + colValue;
        }).sum();

        printSolution(solution);
    }

    private void moveBox(
            Grid<ElementP2> warehouse,
            Stack<Coordinate> boxesToMove,
            Coordinate location,
            Direction direction
    ) {
        Coordinate boxLeft = location;
        Coordinate boxRight = location.addCol(1);

        if (direction.equals(Direction.E)) {
            Coordinate next = boxRight.addCoordinate(direction.toCoordinate());
            ElementP2 atNext = warehouse.get(next);
            if (atNext.equals(ElementP2.WALL)) {
                throw new BoxMoveException();
            }

            if (atNext.equals(ElementP2.EMPTY)) {
                boxesToMove.push(boxLeft);
                return;
            }

            boxesToMove.push(boxLeft);
            moveBox(warehouse, boxesToMove, boxLeft.addCol(2), direction);
        } else if (direction.equals(Direction.W)) {
            Coordinate next = boxLeft.addCoordinate(direction.toCoordinate());
            ElementP2 atNext = warehouse.get(next);
            if (atNext.equals(ElementP2.WALL)) {
                throw new BoxMoveException();
            }

            if (atNext.equals(ElementP2.EMPTY)) {
                boxesToMove.push(boxLeft);
                return;
            }

            boxesToMove.push(boxLeft);
            moveBox(warehouse, boxesToMove, boxLeft.addCol(-2), direction);
        } else {
            Coordinate nextLeft = boxLeft.addCoordinate(direction.toCoordinate());
            Coordinate nextRight = boxRight.addCoordinate(direction.toCoordinate());
            ElementP2 atNextLeft = warehouse.get(nextLeft);
            ElementP2 atNextRight = warehouse.get(nextRight);

            if (atNextLeft.equals(ElementP2.WALL) || atNextRight.equals(ElementP2.WALL)) {
                throw new BoxMoveException();
            }

            boxesToMove.push(boxLeft);

            // One box directly above or below
            if (atNextRight.equals(ElementP2.BOX_RIGHT)) {
                moveBox(warehouse, boxesToMove, nextLeft, direction);
                return;
            }

            // Box above or below and to the left
            if (atNextLeft.equals(ElementP2.BOX_RIGHT)) {
                moveBox(warehouse, boxesToMove, nextLeft.addCol(-1), direction);
            }

            // Box above or below and to the right
            if (atNextRight.equals(ElementP2.BOX_LEFT)) {
                moveBox(warehouse, boxesToMove, nextRight, direction);
            }
        }
    }
}
