package code.of.advent.diveshj21.day6;

public record PastGuardState(int i, int j, Direction direction) {

    public PastGuardState(GuardState guardState) {
        this(guardState.getI(), guardState.getJ(), guardState.getDirection());
    }
}
