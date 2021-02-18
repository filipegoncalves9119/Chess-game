package pt.ipbeja.po2.chess.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pt.ipbeja.po2.chess.model.ChessBoard;
import pt.ipbeja.po2.chess.model.Position;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Filipe Gonçalves
 * @version 29/05/2019
 */
public class Board extends VBox implements View {
    private int row, col, chosenPieces;
    private CellButton buttons[][];
    private ChessBoard gameModel;
    private GridPane gridPane = new GridPane();
    private HBox hBox1 = new HBox();
    private HBox bottomBox = new HBox();
    private VBox leftBox = new VBox();
    private VBox rightBox = new VBox();
    private HBox topBox = new HBox();
    private HBox movesBox = new HBox();
    private List<Position> possibleMoves = new ArrayList<>();
    private List<Position> possibleTakes = new ArrayList<>();
    private List<Position> prevMoves = new ArrayList<>();
    private TextArea textArea = new TextArea();

    /**
     * class constructor
     */
    public Board() {
        this.gameModel = new ChessBoard(this);
        this.drawBoard();
        this.setLabels();
        this.movesBox.getChildren().add(this.textArea);
        this.hBox1.getChildren().addAll(this.leftBox, this.gridPane, this.rightBox, this.movesBox);
        this.getChildren().addAll(this.topBox, this.hBox1, this.bottomBox);

    }

    /**
     * draws the board with buttons
     */
    private void drawBoard() {
        chessHandler bh = new chessHandler();
        this.buttons = new CellButton[this.gameModel.getSIZE()][this.gameModel.getSIZE()];

        for (int row = 0; row < this.gameModel.getSIZE(); row++) {
            for (int col = 0; col < this.gameModel.getSIZE(); col++) {
                CellButton btn = new CellButton(row, col);
                this.buttons[row][col] = btn;
                btn.setOnAction(bh);
                this.gridPane.add(btn, col, row);
            }
        }
        this.updateButtonImages();
    }


    /**
     * set the image / text on the buttons
     */
    public void updateButtonImages() {
        this.buildPiecesLayOut();
        this.updateBoard();
    }

    private void updateBoard() {
        if (this.chosenPieces == 2) {
            this.updateWhiteImage();
            this.updateBlackImage();
        }
        if (this.chosenPieces == 1) {
            this.updateWhiteText();
            this.updateBlackText();
        }

    }

    /**
     * Asks user for an input given couple choices.
     *
     * @return the choice
     */
    private String choosePiecesLayOut() {
        String s = "";
        List<String> choices = new ArrayList<>();
        choices.add("Piece with text");
        choices.add("Piece with images");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Piece with images", choices);
        dialog.setTitle("Chess");
        dialog.setHeaderText("Choose between images or text pieces:");
        dialog.setContentText("Choose:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            s = result.get();
        }
        return s;
    }

    /**
     * Receives a string according to user choice and sets a variable with a new value.
     */
    private void buildPiecesLayOut() {
        String s = this.choosePiecesLayOut();
        if (s.equals("Piece with text")) {
            this.chosenPieces = 1;
        }
        if (s.equals("Piece with images")) {
            this.chosenPieces = 2;
        } else this.chosenPieces = 1;
    }


    /**
     * Highlights possible moves / takes
     * Clear previous moves / takes
     */
    private void highLightMoves() {

        if (!this.prevMoves.equals(this.possibleMoves) && this.gameModel.getMyPiece() != this.gameModel.getPiece(this.row, this.col)) {
            for (int i = 0; i < this.gameModel.getSIZE(); i++) {
                for (int j = 0; j < this.gameModel.getSIZE(); j++) {
                    this.buttons[i][j].removeHighLight();
                }
            }
        }

        for (int i = 0; i < this.possibleMoves.size(); i++) {
            int row = this.possibleMoves.get(i).getLine();
            int col = this.possibleMoves.get(i).getCol();
            this.buttons[row][col].highLightMoves();
        }
        for (int i = 0; i < this.possibleTakes.size(); i++) {
            int row = this.possibleTakes.get(i).getLine();
            int col = this.possibleTakes.get(i).getCol();
            this.buttons[row][col].highLightTakes();
        }
    }

    /**
     * Method call to set Hbox and Vbox with the coordinate labels
     */
    private void setLabels() {
        this.setSideBox(this.leftBox);
        this.setSideBox(this.rightBox);
        this.setTopBotBoxes(this.topBox);
        this.setTopBotBoxes(this.bottomBox);


    }

    /**
     * set side boxes with numbers from 1 to 8
     * @param vbox xbox to set
     */
    private void setSideBox(VBox vbox) {
        for (int i = this.gameModel.getSIZE(); i >= 1; i--) {
            String s = i + "";
            Label label = new Label(s);
            label.setAlignment(Pos.CENTER);
            label.setPrefSize(40, 70);
            label.setFont(Font.font(null, FontWeight.BOLD, 20));
            vbox.getChildren().add(label);
        }
    }

    /**
     * set top and bottom boxes labels
     * @param hBox hbox to set
     */
    private void setTopBotBoxes(HBox hBox) {
        String[] array = stringToAdd();

        for (int i = 0; i < array.length; i++) {
            String s = array[i];
            Label label = new Label(s);
            label.setAlignment(Pos.CENTER_RIGHT);
            label.setPrefSize(70, 40);
            label.setFont(Font.font(null, FontWeight.BOLD, 20));
            hBox.getChildren().add(label);
        }
    }


    /**
     * creates an array with the letters needed for the top and bottom labels
     * @return the array created
     */
    private String[] stringToAdd() {
        String[] array = new String[8];
        array[0] = "a";
        array[1] = "b";
        array[2] = "c";
        array[3] = "d";
        array[4] = "e";
        array[5] = "f";
        array[6] = "g";
        array[7] = "h";

        return array;
    }

    /**
     * Creates a buffered file to save the moves done so far
     * Gets the moves from the TextArea
     */
    private void writer()
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Moves.txt")));
            writer.write(this.textArea.getText());
            writer.close();
        }
        catch (Exception e) {e.printStackTrace();
        }
    }

    /**
     * Event handler
     */
    class chessHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {

            CellButton button = (CellButton) event.getSource();
            row = button.getRow();
            col = button.getCol();

            if (gameModel.getPiece(row, col) != null) {
                possibleTakes = gameModel.getPiece(row, col).possibleTakes();
                possibleMoves = gameModel.getPiece(row, col).possibleMoves();


                highLightMoves();

            }
            gameModel.clickPiece(row, col);

            if(!gameModel.getStringToSend().equals("")) {
                String s = gameModel.getStringToSend();
                textArea.appendText(s);
                writer();
            }

            prevMoves = possibleMoves;

            updateBoard();
        }
    }


    /**
     * update the buttons where the white pieces are with images.
     */
    private void updateWhiteImage() {
        for (int i = 0; i < this.gameModel.getSIZE(); i++) {
            for (int j = 0; j < this.gameModel.getSIZE(); j++) {
                if (this.gameModel.getPiece(i, j) == null) {
                    this.buttons[i][j].setEmpty();
                } else {
                    switch (this.gameModel.getPiece(i, j).getColorAndType()) {
                        case "WhiteRook":
                            this.buttons[i][j].setWhiteRook();
                            break;
                        case "WhitePawn":
                            this.buttons[i][j].setWhitePawn();
                            break;
                        case "WhiteKing":
                            this.buttons[i][j].setWhiteKing();
                            break;
                        case "WhiteQueen":
                            this.buttons[i][j].setWhiteQueen();
                            break;
                        case "WhiteBishop":
                            this.buttons[i][j].setWhiteBishop();
                            break;
                        case "WhiteKnight":
                            this.buttons[i][j].setWhiteHorse();
                            break;
                    }
                }
            }
        }
    }

    /**
     * update the buttons where the black pieces are with images.
     */
    private void updateBlackImage() {
        for (int i = 0; i < this.gameModel.getSIZE(); i++) {
            for (int j = 0; j < this.gameModel.getSIZE(); j++) {
                if (this.gameModel.getPiece(i, j) == null) {
                    this.buttons[i][j].setEmpty();
                } else {
                    switch (this.gameModel.getPiece(i, j).getColorAndType()) {
                        case "BlackRook":
                            this.buttons[i][j].setBlackRook();
                            break;
                        case "BlackPawn":
                            this.buttons[i][j].setBlackPawn();
                            break;
                        case "BlackKing":
                            this.buttons[i][j].setBlackKing();
                            break;
                        case "BlackQueen":
                            this.buttons[i][j].setBlackQueen();
                            break;
                        case "BlackBishop":
                            this.buttons[i][j].setBlackBishop();
                            break;
                        case "BlackKnight":
                            this.buttons[i][j].setBlackHorse();
                            break;
                    }
                }
            }
        }
    }

    /**
     * update the buttons where the white pieces are with text.
     */
    private void updateWhiteText() {
        for (int i = 0; i < this.gameModel.getSIZE(); i++) {
            for (int j = 0; j < this.gameModel.getSIZE(); j++) {
                if (this.gameModel.getPiece(i, j) == null) {
                    this.buttons[i][j].setText("");
                } else {
                    switch (this.gameModel.getPiece(i, j).getColorAndType()) {
                        case "WhiteRook":
                            this.buttons[i][j].setText("B T");
                            break;
                        case "WhitePawn":
                            this.buttons[i][j].setText("B p");
                            break;
                        case "WhiteKing":
                            this.buttons[i][j].setText("B R");
                            break;
                        case "WhiteQueen":
                            this.buttons[i][j].setText("B D");
                            break;
                        case "WhiteBishop":
                            this.buttons[i][j].setText("B B");
                            break;
                        case "WhiteKnight":
                            this.buttons[i][j].setText("B C");
                            break;
                    }
                }
            }
        }
    }

    /**
     * update the buttons where the black pieces are with text.
     */
    private void updateBlackText() {
        for (int i = 0; i < this.gameModel.getSIZE(); i++) {
            for (int j = 0; j < this.gameModel.getSIZE(); j++) {
                if (this.gameModel.getPiece(i, j) == null) {
                    this.buttons[i][j].setText("");
                } else {
                    switch (this.gameModel.getPiece(i, j).getColorAndType()) {
                        case "BlackRook":
                            this.buttons[i][j].setText("P T");
                            break;
                        case "BlackPawn":
                            this.buttons[i][j].setText("P p");
                            break;
                        case "BlackKing":
                            this.buttons[i][j].setText("P R");
                            break;
                        case "BlackQueen":
                            this.buttons[i][j].setText("P D");
                            break;
                        case "BlackBishop":
                            this.buttons[i][j].setText("P B");
                            break;
                        case "BlackKnight":
                            this.buttons[i][j].setText("P C");
                            break;
                    }
                }
            }
        }
    }


}

