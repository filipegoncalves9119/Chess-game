package pt.ipbeja.po2.chess.model.Pieces;

import javafx.geometry.Pos;
import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.PlayerColor;
import pt.ipbeja.po2.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class King extends Piece {

    private String colorAndType;
    private PlayerColor playerColor;
    private ChessBoard gameModel;
    private String type;

    public King(ChessBoard board, PlayerColor playerColor, Position position) {
        super(board, playerColor, position);
        this.type = "King";
        this.colorAndType = playerColor + this.type;
        this.playerColor = playerColor;
        this.gameModel = board;
    }

    /**
     * Creates the possible moves for this class piece
     * @return List of possible moves
     */
    @Override
    public List<Position> possibleMoves() {
        int col = this.getPosition().getCol();
        int row = this.getPosition().getLine();
        List<Position> possibleMovement = new ArrayList<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.gameModel.isInside(row + i, col + j)) {
                    if (this.gameModel.getPiece(row + i, col + j) == null) {
                        Position pos = new Position(row + i, col + j);
                        possibleMovement.add(pos);
                    }
                }
            }
        }

        return possibleMovement;
    }

    /**
     * Creates the possible takes for this class piece
     * @return List of possible takes
     */
    @Override
    public List<Position> possibleTakes() {
        List<Position> possibleTakes = new ArrayList<>();

        int col = this.getPosition().getCol();
        int row = this.getPosition().getLine();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (this.gameModel.isInside(row + i, col + j)) {
                    if (this.gameModel.getPiece(row + i, col + j) != null) {
                        if (!this.getColor().equals(this.gameModel.getPiece(row + i, col + j).getColor())) {
                            Position pos = new Position(row + i, col + j);
                            possibleTakes.add(pos);
                        }
                    }
                }
            }
        }
        return possibleTakes;
    }

    public String movementText(Position begin, Position end) {
        return begin+""+end;
    }

    @Override
    public String toString() {
        return this.colorAndType + this.getPosition();
    }

    @Override
    public String getColorAndType() {
        return this.colorAndType;
    }

    @Override
    public String getColor() {
        return this.playerColor + "";
    }

    @Override
    public String getType() {
        return this.type;
    }
}
