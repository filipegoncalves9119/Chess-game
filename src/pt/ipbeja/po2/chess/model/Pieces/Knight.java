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
public class Knight extends Piece {

    private String colorAndType;
    private PlayerColor playerColor;
    private ChessBoard gameModel;
    private String type;


    public Knight(ChessBoard board, PlayerColor playerColor, Position position) {
        super(board, playerColor, position);
        this.type = "Knight";
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

        if(this.gameModel.isInside(row-2,col-1) && this.gameModel.getPiece(row-2,col-1) == null) {
            Position position = new Position(row - 2, col - 1);
            possibleMovement.add(position);
        }
        if(this.gameModel.isInside(row-2,col+1) && this.gameModel.getPiece(row-2,col+1) == null) {
            Position position = new Position(row - 2, col + 1);
            possibleMovement.add(position);
        }
        if(this.gameModel.isInside(row+2,col-1) && this.gameModel.getPiece(row+2,col-1) == null) {
            Position position = new Position(row + 2, col - 1);
            possibleMovement.add(position);
        }
        if(this.gameModel.isInside(row+2,col+1) && this.gameModel.getPiece(row+2,col+1) == null) {
            Position position = new Position(row+2,col+1);
            possibleMovement.add(position);}

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

        if(this.gameModel.isInside(row-2,col-1) && this.gameModel.getPiece(row-2,col-1) != null) {
            if(!this.getColor().equals(this.gameModel.getPiece(row-2,col-1).getColor())){
            Position position = new Position(row - 2, col - 1);
            possibleTakes.add(position);
            }
        }
        if(this.gameModel.isInside(row+2,col+1) && this.gameModel.getPiece(row+2,col+1) != null) {

            if(!this.getColor().equals(this.gameModel.getPiece(row+2,col+1).getColor())){
                Position position = new Position(row + 2, col + 1);
                possibleTakes.add(position);
            }
        }
        if(this.gameModel.isInside(row-2,col+1) && this.gameModel.getPiece(row-2,col+1) != null) {

            if(!this.getColor().equals(this.gameModel.getPiece(row-2,col+1).getColor())){
                Position position = new Position(row - 2, col + 1);
                possibleTakes.add(position);
            }
        }
        if(this.gameModel.isInside(row+2,col-1) && this.gameModel.getPiece(row+2,col-1) != null) {

            if(!this.getColor().equals(this.gameModel.getPiece(row+2,col-1).getColor())){
                Position position = new Position(row + 2, col - 1);
                possibleTakes.add(position);
            }
        }


        return possibleTakes;
    }

    public String movementText(Position begin, Position end){
        return begin+""+end;
    }

    @Override
    public String toString() {
        return this.colorAndType + this.getPosition();
    }

    @Override
    public String getColorAndType(){
        return this.colorAndType;
    }

    @Override
    public String getColor() {
       return this.playerColor+"";
    }

    @Override
    public String getType() {
        return this.type;
    }


}
