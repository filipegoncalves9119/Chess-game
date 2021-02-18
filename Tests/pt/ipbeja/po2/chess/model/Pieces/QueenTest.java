package pt.ipbeja.po2.chess.model.Pieces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.PlayerColor;
import pt.ipbeja.po2.chess.model.Position;
import pt.ipbeja.po2.chess.model.ViewTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {

    private ChessBoard gameModel;
    private ViewTest viewTest;

    @BeforeEach
    void setUp() {
        this.viewTest = new ViewTest();
        this.gameModel = new ChessBoard(viewTest);
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void possibleMoves() {

        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[1][3] = new Queen(gameModel, PlayerColor.Black, new Position(1,3));
        this.gameModel.setDataBoard(dataBoard);

        List<Position> possibleMoves = dataBoard[1][3].possibleMoves();
        List<Position> expected = fillMoves();
        assertArrayEquals(expected.toArray(), possibleMoves.toArray());

    }

    @Test
    void possibleTakes() {

        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[3][3] = new Queen(gameModel, PlayerColor.Black, new Position(3,3));

        dataBoard[2][2] = new Pawn(gameModel, PlayerColor.White, new Position(2,2));
        dataBoard[2][3] = new Pawn(gameModel, PlayerColor.White, new Position(2,3));
        dataBoard[2][4] = new Pawn(gameModel, PlayerColor.White, new Position(2,4));
        dataBoard[3][2] = new Pawn(gameModel, PlayerColor.White, new Position(3,2));
        dataBoard[3][4] = new Pawn(gameModel, PlayerColor.White, new Position(3,4));
        dataBoard[4][2] = new Pawn(gameModel, PlayerColor.White, new Position(4,2));
        dataBoard[5][3] = new Pawn(gameModel, PlayerColor.White, new Position(4,3));
        dataBoard[5][5] = new Pawn(gameModel, PlayerColor.White, new Position(5,5));
        this.gameModel.setDataBoard(dataBoard);

        List<Position> expected = Arrays.asList(new Position(5,5),new Position(2,2),
                new Position(4,2),new Position(2,4),
                new Position(5,3),new Position(2,3),
                new Position(3,4),new Position(3,2));

        List<Position> possibleTakes = dataBoard[3][3].possibleTakes();

        assertArrayEquals(expected.toArray(),possibleTakes.toArray());

    }

    @Test
    void movementText() {
        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[3][3] = new Queen(gameModel, PlayerColor.Black, new Position(3,3));
        this.gameModel.setDataBoard(dataBoard);
        Piece piece = gameModel.getPiece(3,3);

        Position start = new Position(3,3);
        Position end = new Position(3,6);

        assertEquals(piece, this.gameModel.getPiece(start.getLine(),start.getCol()));
        assertEquals(null, this.gameModel.getPiece(3,6));

        this.gameModel.clickPiece(3,3);
        this.gameModel.clickPiece(3,6);

        assertEquals(piece, this.gameModel.getPiece(end.getLine(),end.getCol()));
        assertEquals(null, this.gameModel.getPiece(3,3));
    }



    private List<Position> fillMoves(){
        List<Position> expectedMoves = new ArrayList<>();
        expectedMoves.add(new Position(2,4));
        expectedMoves.add(new Position(3,5));
        expectedMoves.add(new Position(4,6));
        expectedMoves.add(new Position(5,7));
        expectedMoves.add(new Position(0,2));
        expectedMoves.add(new Position(2,2));
        expectedMoves.add(new Position(3,1));
        expectedMoves.add(new Position(4,0));
        expectedMoves.add(new Position(0,4));
        expectedMoves.add(new Position(2,3));
        expectedMoves.add(new Position(3,3));
        expectedMoves.add(new Position(4,3));
        expectedMoves.add(new Position(5,3));
        expectedMoves.add(new Position(6,3));
        expectedMoves.add(new Position(7,3));
        expectedMoves.add(new Position(0,3));
        expectedMoves.add(new Position(1,4));
        expectedMoves.add(new Position(1,5));
        expectedMoves.add(new Position(1,6));
        expectedMoves.add(new Position(1,7));
        expectedMoves.add(new Position(1,2));
        expectedMoves.add(new Position(1,1));
        expectedMoves.add(new Position(1,0));

        return expectedMoves;
    }



}