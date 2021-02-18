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

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RookTest {


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
        dataBoard[1][1] = new Rook(gameModel, PlayerColor.Black, new Position(1,1));
        this.gameModel.setDataBoard(dataBoard);

        List<Position> possibleMoves = dataBoard[1][1].possibleMoves();
        List<Position> expected = fillMoves();
        assertArrayEquals(expected.toArray(),possibleMoves.toArray());

    }

    @Test
    void possibleTakes() {

        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[3][3] = new Rook(gameModel, PlayerColor.White, new Position(3,3));

        dataBoard[2][3] = new Pawn(gameModel, PlayerColor.Black, new Position(2,3));
        dataBoard[6][3] = new Pawn(gameModel, PlayerColor.Black, new Position(2,5));
        dataBoard[3][1] = new Pawn(gameModel, PlayerColor.Black, new Position(3,1));
        dataBoard[3][5] = new Pawn(gameModel, PlayerColor.Black, new Position(3,5));
        this.gameModel.setDataBoard(dataBoard);


        List<Position> expected = Arrays.asList(new Position(6,3),new Position(2,3),
                new Position(3,5) , new Position(3,1));

        List<Position> possibleTakes = dataBoard[3][3].possibleTakes();

        assertArrayEquals(expected.toArray(), possibleTakes.toArray());

    }

    @Test
    void movementText() {
        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[0][0] = new Rook(gameModel, PlayerColor.Black, new Position(0,0));
        this.gameModel.setDataBoard(dataBoard);
        Piece piece = gameModel.getPiece(0,0);

        Position start = new Position(0,0);
        Position end = new Position(4,0);

        assertEquals(piece, this.gameModel.getPiece(start.getLine(),start.getCol()));
        assertEquals(null, this.gameModel.getPiece(4,0));

        this.gameModel.clickPiece(0,0);
        this.gameModel.clickPiece(4,0);

        assertEquals(piece, this.gameModel.getPiece(end.getLine(),end.getCol()));
        assertEquals(null, this.gameModel.getPiece(0,0));
    }


    private List<Position> fillMoves(){
        List<Position> expectedMoves = new ArrayList<>();

        expectedMoves.add(new Position(2,1));
        expectedMoves.add(new Position(3,1));
        expectedMoves.add(new Position(4,1));
        expectedMoves.add(new Position(5,1));
        expectedMoves.add(new Position(6,1));
        expectedMoves.add(new Position(7,1));
        expectedMoves.add(new Position(0,1));
        expectedMoves.add(new Position(1,2));
        expectedMoves.add(new Position(1,3));
        expectedMoves.add(new Position(1,4));
        expectedMoves.add(new Position(1,5));
        expectedMoves.add(new Position(1,6));
        expectedMoves.add(new Position(1,7));
        expectedMoves.add(new Position(1,0));

        return expectedMoves;
    }

}