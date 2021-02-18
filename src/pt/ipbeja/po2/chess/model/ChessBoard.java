package pt.ipbeja.po2.chess.model;

import pt.ipbeja.po2.chess.gui.View;
import pt.ipbeja.po2.chess.model.Pieces.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class ChessBoard {

    private final View VIEW;
    private final int SIZE = 8;
    private Piece[][] dataBoard;
    private PlayerColor playerColor;
    private List<Position> positions;
    private Piece myPiece;
    private boolean playerTurn = false;
    private String stringBlack;
    private String stringWhite;
    private int counter = 1;
    private String stringToSend = "";
    private List<Position> positionsToTake;



    /**
     * class Contructor
     * @param view
     */
    public ChessBoard(View view) {
        this.dataBoard = new Piece[SIZE][SIZE];
        this.VIEW = view;
        this.setPieces();
        this.positions = new ArrayList<>();

    }

    /**
     * calls the methods to set pieces
     */
    private void setPieces() {
        this.setWhitePieces();
        this.setBlackPieces();
    }

    /**
     * set the white pieces in the array of arrays
     */
    private void setBlackPieces() {

        this.dataBoard[0][0] = new Rook(this, this.playerColor.Black, new Position(0, 0));
        this.dataBoard[0][1] = new Knight(this, this.playerColor.Black, new Position(0, 1));
        this.dataBoard[0][2] = new Bishop(this, this.playerColor.Black, new Position(0, 2));
        this.dataBoard[0][3] = new Queen(this, this.playerColor.Black, new Position(0, 3));
        this.dataBoard[0][4] = new King(this, this.playerColor.Black, new Position(0, 4));
        this.dataBoard[0][5] = new Bishop(this, this.playerColor.Black, new Position(0, 5));
        this.dataBoard[0][6] = new Knight(this, this.playerColor.Black, new Position(0, 6));
        this.dataBoard[0][7] = new Rook(this, this.playerColor.Black, new Position(0, 7));
        for (int i = 1; i < 2; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                this.dataBoard[i][j] = new Pawn(this, this.playerColor.Black, new Position(i, j));
            }
        }
    }

    /**
     * set the black pieces in the array of arrays
     */
    private void setWhitePieces() {
        for (int i = 6; i < 7; i++) {
            for (int j = 0; j < this.SIZE; j++) {
                this.dataBoard[i][j] = new Pawn(this, this.playerColor.White, new Position(i, j));
            }
        }
        this.dataBoard[7][0] = new Rook(this, this.playerColor.White, new Position(7, 0));
        this.dataBoard[7][1] = new Knight(this, this.playerColor.White, new Position(7, 1));
        this.dataBoard[7][2] = new Bishop(this, this.playerColor.White, new Position(7, 2));
        this.dataBoard[7][3] = new Queen(this, this.playerColor.White, new Position(7, 3));
        this.dataBoard[7][4] = new King(this, this.playerColor.White, new Position(7, 4));
        this.dataBoard[7][5] = new Bishop(this, this.playerColor.White, new Position(7, 5));
        this.dataBoard[7][6] = new Knight(this, this.playerColor.White, new Position(7, 6));
        this.dataBoard[7][7] = new Rook(this, this.playerColor.White, new Position(7, 7));

    }

    /**
     * Method call to eat or move a piece
     * @param row coordinate
     * @param col coordinate
     */
    public void clickPiece(int row, int col) {
        if (this.getPiece(row, col) != null) {

            if ((this.playerTurn && getPiece(row, col).getColor().equals("White")) || (!this.playerTurn && getPiece(row, col).getColor().equals("Black"))) {
                this.myPiece = getPiece(row, col);
                this.stringToSend = "";

            } else {
                this.eatPiece(row, col);
                this.setStringSend(row, col);
            }
        } else {
            this.movePiece(row, col);
            this.setStringSend(row, col);
        }

    }

    /**
     * Moves a piece from one position to another
     * @param row coordinate
     * @param col coordinate
     */
    private void movePiece(int row, int col) {
        Position position = this.myPiece.getPosition();
        Position targetPosition = new Position(row, col);
        this.saveList(position.getLine(), position.getCol());
        for (int i = 0; i < this.positions.size(); i++) {
            if (this.positions.get(i).equals(targetPosition)) {
                this.dataBoard[position.getLine()][position.getCol()] = null;
                this.dataBoard[targetPosition.getLine()][targetPosition.getCol()] = this.myPiece;
                this.myPiece.setPosition(targetPosition);
                this.playerTurn = !this.playerTurn;
            }
        }
    }

    /**
     * eat a piece when available
     * @param row coordinate
     * @param col coordinate
     */
    private void eatPiece(int row, int col) {
        Position position = this.myPiece.getPosition();
        Position targetPosition = new Position(row, col);
        this.saveList(position.getLine(), position.getCol());
        for (int i = 0; i < positionsToTake.size(); i++) {
            if (this.positionsToTake.get(i).equals(targetPosition)) {
                this.dataBoard[position.getLine()][position.getCol()] = null;
                this.dataBoard[targetPosition.getLine()][targetPosition.getCol()] = this.myPiece;
                this.myPiece.setPosition(targetPosition);
                this.playerTurn = !this.playerTurn;
            }
        }
    }

    /**
     * creates a string with both white and black final moves for each turn
     * @param row coordinate
     * @param col coordinate
     */
    private void setStringSend(int row, int col) {
        if (this.myPiece.getColor().equals("Black")) {
            this.stringBlack = getPieceLetter(row, col) + getLetterPosition(col) + "" + getNumberPosition(row) + "\n";
        }

        if (this.myPiece.getColor().equals("White")) {
            this.stringWhite = getPieceLetter(row, col) + getLetterPosition(col) + getNumberPosition(row);
            this.stringToSend = this.counter + ". " + this.stringWhite + " " + this.stringBlack;
            this.counter++;
        }

    }

    /**
     * sets a String for each type of piece selected
     * @param row coordinate
     * @param col coordinate
     * @return the created string
     */
    private String getPieceLetter(int row, int col) {
        String s = "";
        if(this.getPiece(row,col) != null) {
            if (getPiece(row, col).getType().equals("Pawn")) {
                s = "";
            }
            if (getPiece(row, col).getType().equals("Bishop")) {
                s = "B";
            }
            if (getPiece(row, col).getType().equals("King")) {
                s = "R";
            }
            if (getPiece(row, col).getType().equals("Knight")) {
                s = "C";
            }
            if (getPiece(row, col).getType().equals("Queen")) {
                s = "D";
            }
            if (getPiece(row, col).getType().equals("Rook")) {
                s = "T";
            }
        }
        return s;
    }

    /**
     * sets a String to transform column coordinate into a letter
     * @param col coodinate
     * @return String
     */
    private String getLetterPosition(int col) {
        String s = "";

        if (col == 0) {
            s = "a";
        }
        if (col == 1) {
            s = "b";
        }
        if (col == 2) {
            s = "c";
        }
        if (col == 3) {
            s = "d";
        }
        if (col == 4) {
            s = "e";
        }
        if (col == 5) {
            s = "f";
        }
        if (col == 6) {
            s = "g";
        }
        if (col == 7) {
            s = "h";
        }

        return s;
    }

    /**
     * sets a String to get the row coordinate
     * @param row coordinate
     * @return String
     */
    private int getNumberPosition(int row) {
        int s = 0;
        if (row == 0) {
            s = 8;
        }
        if (row == 1) {
            s = 7;
        }
        if (row == 2) {
            s = 6;
        }
        if (row == 3) {
            s = 5;
        }
        if (row == 4) {
            s = 4;
        }
        if (row == 5) {
            s = 3;
        }
        if (row == 6) {
            s = 2;
        }
        if (row == 7) {
            s = 1;
        }

        return s;
    }

    /**
     * check if the position is inside the board limits
     * @param line coordinate
     * @param col coordinate
     * @return
     */
    public boolean isInside(int line, int col) {
        return 0 <= line && line < 8 &&
                0 <= col && col < 8;
    }

    /**
     * save the lists of possible moves and takes of the current selected piece
     * @param row coordinate
     * @param col coordinate
     */
    private void saveList(int row, int col) {
        this.positions = getPiece(row, col).possibleMoves();
        this.positionsToTake = getPiece(row, col).possibleTakes();
    }

    /**
     * set databoard for Test class
     * @param dataBoard
     */
    public void setDataBoard(Piece[][] dataBoard) {
        this.dataBoard = dataBoard;
    }


    /**
     * @param row coordinate
     * @param col coordinate
     * @return a Piece
     */
    public Piece getPiece(int row, int col) {
        return this.dataBoard[row][col];
    }

    /**
     *
     * @return SIZE
     */
    public int getSIZE() {
        return this.SIZE;
    }

    /**
     *
     * @return StringToSend
     */
    public String getStringToSend() {
        return this.stringToSend;
    }

    /**
     *
     * @return myPiece
     */
    public Piece getMyPiece() {
        return this.myPiece;
    }
}
