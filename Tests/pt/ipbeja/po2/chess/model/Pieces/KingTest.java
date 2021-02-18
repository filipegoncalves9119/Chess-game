package pt.ipbeja.po2.chess.model.Pieces;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.PlayerColor;
import pt.ipbeja.po2.chess.model.Position;
import pt.ipbeja.po2.chess.model.ViewTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {

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
        dataBoard[3][3] = new King(gameModel, PlayerColor.Black, new Position(3,3));
        this.gameModel.setDataBoard(dataBoard);

        List<Position> expected = Arrays.asList(new Position(2,2), new Position(2,3),new Position(2,4),
                new Position(3,2),new Position(3,4),new Position(4,2),
                new Position(4,3),new Position(4,4));

       List<Position> possibleMoves = dataBoard[3][3].possibleMoves();

        assertArrayEquals(expected.toArray(), possibleMoves.toArray());



    }

    @Test
    void possibleTakes() {

        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[3][3] = new King(gameModel, PlayerColor.Black, new Position(3,3));

        dataBoard[2][2] = new Pawn(gameModel, PlayerColor.White, new Position(2,2));
        dataBoard[2][3] = new Pawn(gameModel, PlayerColor.White, new Position(2,3));
        dataBoard[2][4] = new Pawn(gameModel, PlayerColor.White, new Position(2,4));
        dataBoard[3][2] = new Pawn(gameModel, PlayerColor.White, new Position(3,2));
        dataBoard[3][4] = new Pawn(gameModel, PlayerColor.White, new Position(3,4));
        dataBoard[4][2] = new Pawn(gameModel, PlayerColor.White, new Position(4,2));
        dataBoard[4][3] = new Pawn(gameModel, PlayerColor.White, new Position(4,3));
        dataBoard[4][4] = new Pawn(gameModel, PlayerColor.White, new Position(4,4));
        this.gameModel.setDataBoard(dataBoard);

        List<Position> expected = Arrays.asList(new Position(2,2), new Position(2,3),new Position(2,4),
                new Position(3,2),new Position(3,4),new Position(4,2),
                new Position(4,3),new Position(4,4));

        List<Position> possibleTakes = dataBoard[3][3].possibleTakes();

        assertArrayEquals(expected.toArray(),possibleTakes.toArray());

    }

    @Test
    void movementText() {
        Piece[][] dataBoard = new Piece[gameModel.getSIZE()][gameModel.getSIZE()];
        dataBoard[3][3] = new King(gameModel, PlayerColor.Black, new Position(3,3));
        this.gameModel.setDataBoard(dataBoard);
        Piece piece = gameModel.getPiece(3,3);

        Position start = new Position(3,3);
        Position end = new Position(3,4);

        assertEquals(piece, this.gameModel.getPiece(start.getLine(),start.getCol()));
        assertEquals(null, this.gameModel.getPiece(3,4));

        this.gameModel.clickPiece(3,3);
        this.gameModel.clickPiece(3,4);

        assertEquals(piece, this.gameModel.getPiece(end.getLine(),end.getCol()));
        assertEquals(null, this.gameModel.getPiece(3,3));
    }



}