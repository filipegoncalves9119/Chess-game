package pt.ipbeja.po2.chess.model.Pieces;


import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.PlayerColor;
import pt.ipbeja.po2.chess.model.Position;

import java.util.List;
import java.util.Objects;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public abstract class Piece {

    private ChessBoard chessBoard;
    private Position position;
    private PlayerColor playerColor;

    public Piece(ChessBoard board, PlayerColor playerColor, Position position){
        this.chessBoard = board;
        this.position = position;
        this.playerColor = playerColor;
    }

    public abstract List<Position> possibleMoves();

    public abstract List<Position> possibleTakes();

    public abstract String movementText(Position position, Position position1);

    public abstract String getColorAndType();

    public abstract String getColor();

    public abstract String getType();

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(chessBoard, piece.chessBoard) &&
                Objects.equals(position, piece.position) &&
                playerColor == piece.playerColor;
    }

    @Override
    public int hashCode() {

        return Objects.hash(chessBoard, position, playerColor);
    }
}
