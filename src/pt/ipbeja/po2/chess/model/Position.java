package pt.ipbeja.po2.chess.model;

import java.util.Objects;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class Position {
    private final int line, col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    /**
     * @return the line
     */
    public int getLine() {
        return this.line;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return this.col;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return line == position.line &&
                col == position.col;
    }

    @Override
    public int hashCode() {

        return Objects.hash(line, col);
    }

    @Override
    public String toString() {
        return "Position{" +
                "line=" + line +
                ", col=" + col +
                '}';
    }
}