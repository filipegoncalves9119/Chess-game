package pt.ipbeja.po2.chess.model.Pieces;

import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.PlayerColor;
import pt.ipbeja.po2.chess.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class Bishop extends Piece {

    private String colorAndType;
    private PlayerColor playerColor;
    private ChessBoard gameModel;
    private String type;


    public Bishop(ChessBoard board, PlayerColor playerColor, Position position) {
        super(board, playerColor, position);
        this.type = "Bishop";
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
        List<Position> possibleMovement = new ArrayList<>();
        int col = this.getPosition().getCol();
        int row = this.getPosition().getLine();
        int i = row + 1;
        int j = col + 1;

        while (i < this.gameModel.getSIZE() && j < this.gameModel.getSIZE() && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {
                Position position = new Position(i, j);
                possibleMovement.add(position);
                i++;
                j++;
            }
        }
        i = row - 1;
        j = col - 1;

        while (i >= 0 && j >= 0 && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {

                Position position = new Position(i, j);
                possibleMovement.add(position);

                i--;
                j--;

            }
        }
        i = row + 1;
        j = col - 1;
        while (i < this.gameModel.getSIZE() && j >= 0 && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {

                Position position = new Position(i, j);
                possibleMovement.add(position);

                i++;
                j--;

            }
        }
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < this.gameModel.getSIZE() && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {

                Position position = new Position(i, j);
                possibleMovement.add(position);

                i--;
                j++;

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

        int i = row + 1;
        int j = col + 1;

        while (i < this.gameModel.getSIZE() && j < this.gameModel.getSIZE() && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {
                i++;
                j++;
            }
        }
        if (this.gameModel.isInside(i, j)) {
            if (!(this.getColor().equals(this.gameModel.getPiece(i, j).getColor()) && this.gameModel.getPiece(i, j) != null)) {
                {
                    Position position = new Position(i, j);
                    possibleTakes.add(position);
                }
            }
        }
        i = row - 1;
        j = col - 1;

        while (i >= 0 && j >= 0 && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {
                i--;
                j--;
            }
        }
        if (this.gameModel.isInside(i, j)) {
            if (!(this.getColor().equals(this.gameModel.getPiece(i, j).getColor()) && this.gameModel.getPiece(i, j) != null)) {
                {
                    Position position = new Position(i, j);
                    possibleTakes.add(position);
                }
            }
        }

        i = row + 1;
        j = col - 1;

        while (i < this.gameModel.getSIZE() && j >= 0 && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {
                i++;
                j--;
            }
        }
        if (this.gameModel.isInside(i, j)) {
            if (!(this.getColor().equals(this.gameModel.getPiece(i, j).getColor()) && this.gameModel.getPiece(i, j) != null)) {
                {
                    Position position = new Position(i, j);
                    possibleTakes.add(position);
                }
            }
        }
        i = row - 1;
        j = col + 1;

        while (i >= 0 && j < this.gameModel.getSIZE() && this.gameModel.getPiece(i, j) == null) {
            if (this.gameModel.isInside(i, j)) {
                i--;
                j++;
            }
        }
        if (this.gameModel.isInside(i, j)) {
            if (!(this.getColor().equals(this.gameModel.getPiece(i, j).getColor()) && this.gameModel.getPiece(i, j) != null)) {
                {
                    Position position = new Position(i, j);
                    possibleTakes.add(position);
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
