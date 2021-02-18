package pt.ipbeja.po2.chess.gui;

import com.sun.xml.internal.bind.v2.model.core.NonElement;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class CellButton extends Button {

    private static final Image WHITE_PAWN = new Image("/resources/WHITE_PAWN.png");
    private static final Image WHITE_BISHOP = new Image("/resources/WHITE_BISHOP.png");
    private static final Image WHITE_HORSE = new Image("/resources/WHITE_KNIGHT.png");
    private static final Image WHITE_KING = new Image("/resources/WHITE_KING.png");
    private static final Image WHITE_QUEEN = new Image("/resources/WHITE_QUEEN.png");
    private static final Image WHITE_TOWER = new Image("/resources/WHITE_ROOK.png");

    private static final Image BLACK_PAWN = new Image("/resources/BLACK_PAWN.png");
    private static final Image BLACK_BISHOP = new Image("/resources/BLACK_BISHOP.png");
    private static final Image BLACK_HORSE = new Image("/resources/BLACK_KNIGHT.png");
    private static final Image BLACK_KING = new Image("/resources/BLACK_KING.png");
    private static final Image BLACK_QUEEN = new Image("/resources/BLACK_QUEEN.png");
    private static final Image BLACK_TOWER = new Image("/resources/BLACK_ROOK.png");
    private int row, col;
    private String string;
    private ImageView imageView;


    /**
     * Contructor of the class
     *
     * @param row row coordiantes
     * @param col col coordiantes
     */
    public CellButton(int row, int col) {
        this.imageView = new ImageView();
        this.row = row;
        this.col = col;
        this.setBackGroundColour(row, col);
        this.setGraphic(imageView);
        this.setPrefSize(70, 70);
        this.setPadding(Insets.EMPTY);
    }

    /**
     * sets the back ground colour for the entire button grid
     * @param row coordinate
     * @param col coordinate
     */
    private void setBackGroundColour(int row, int col) {
        if ((row + col) % 2 == 0) {
            this.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        } else {
            this.setBackground(new Background(new BackgroundFill(Color.SADDLEBROWN, null, null)));
        }
    }

    public void highLightMoves(){
        this.setStyle("-fx-border-color: green; -fx-border-width: 3 3 3 3;");
    }

    public void highLightTakes(){
        this.setStyle("-fx-border-color: yellow; -fx-border-width: 3 3 3 3;");
    }

    public void removeHighLight(){
        this.setStyle("-fx-border-color: none;");
    }

    public void setEmpty(){
        this.imageView.setImage(null);
    }

    public void setBlackPawn() {
        this.imageView.setImage(BLACK_PAWN);
    }

    public void setBlackRook() {
        this.imageView.setImage(BLACK_TOWER);
    }

    public void setBlackBishop() {
        this.imageView.setImage(BLACK_BISHOP);
    }

    public void setBlackHorse() {
        this.imageView.setImage(BLACK_HORSE);
    }

    public void setBlackKing() {
        this.imageView.setImage(BLACK_KING);
    }

    public void setBlackQueen() {
        this.imageView.setImage(BLACK_QUEEN);
    }

    public void setWhitePawn() {
        this.imageView.setImage(WHITE_PAWN);
    }

    public void setWhiteRook() {
        this.imageView.setImage(WHITE_TOWER);
    }

    public void setWhiteBishop() {
        this.imageView.setImage(WHITE_BISHOP);

    }

    public void setWhiteHorse() {
        this.imageView.setImage(WHITE_HORSE);

    }

    public void setWhiteKing() {
        this.imageView.setImage(WHITE_KING);
    }

    public void setWhiteQueen() {
        this.imageView.setImage(WHITE_QUEEN);
    }

    /**
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return col
     */
    public int getCol() {
        return col;
    }
}
