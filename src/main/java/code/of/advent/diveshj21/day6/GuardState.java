package code.of.advent.diveshj21.day6;

import lombok.Getter;

@Getter
public class GuardState {
    private int i;
    private int j;
    private Direction direction;

    public GuardState(int i, int j, Direction direction) {
        this.i = i;
        this.j = j;
        this.direction = direction;
    }

    public void turn() {
        direction = switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }

    public void move() {
        i = direction.getIChange().apply(i);
        j = direction.getJChange().apply(j);
    }

    public int checkNextI() {
        return direction.getIChange().apply(i);
    }

    public int checkNextJ() {
        return direction.getJChange().apply(j);
    }

    public Direction checkNextDirection() {
        return switch (direction) {
            case UP -> Direction.RIGHT;
            case RIGHT -> Direction.DOWN;
            case DOWN -> Direction.LEFT;
            case LEFT -> Direction.UP;
        };
    }
}
