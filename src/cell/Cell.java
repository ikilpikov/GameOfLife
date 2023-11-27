package cell;

public class Cell {
    private State state;

    public Cell() {
    }

    public Cell(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isAlive() {
        return state == State.ALIVE ? true : false;
    }

    @Override
    public String toString() {
        return state == State.ALIVE ? "*" : ".";
    }
}
