package PRRR;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;
import javafx.scene.control.ChoiceBox;

import static com.almasb.fxgl.dsl.FXGL.*;

public class MenuScene extends FXGLMenu {

    UISceneFactory sceneswitcher = new UISceneFactory();

    public MenuScene(MenuType mainMenu) {
        super(mainMenu);
        // TITLE LABEL
        Label titleLabel = new Label("Prince of Jank");

        titleLabel.setFont(new Font(30));

        titleLabel.setMinWidth(500);
        titleLabel.setMaxWidth(700);
        titleLabel.setPrefWidth(600);

        titleLabel.setLayoutX(290);
        titleLabel.setLayoutY(120);

        //NAMEBOX
        VBox playerNameBox = new VBox(80);
           playerNameBox.setSpacing(40);
           playerNameBox.setAlignment(Pos.CENTER);
        TextField input = new TextField();
        playerNameBox.getChildren().add(input);
        FXGL.getGameScene().addUINode(playerNameBox);

        //BUTTONS
        Button startButton = new Button("Start game");

        startButton.setMinWidth(100);
        startButton.setMaxWidth(400);
        startButton.setPrefWidth(200);

        startButton.setMinHeight(15);
        startButton.setMaxHeight(50);
        startButton.setPrefHeight(50);

        startButton.setLayoutX(290);
        startButton.setLayoutY(200);

        startButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrinceGame game = new PrinceGame();
                System.out.println("There is no game yet :(");
                //getContentRoot().getChildren().clear();
                fireNewGame();
            }
        });

            //testbutton
        Button dialogueButton = new Button();

        dialogueButton.setMinWidth(100);
        dialogueButton.setMaxWidth(400);
        dialogueButton.setPrefWidth(200);

        dialogueButton.setMinHeight(15);
        dialogueButton.setMaxHeight(50);
        dialogueButton.setPrefHeight(50);

        dialogueButton.setLayoutX(290);
        dialogueButton.setLayoutY(400);

        dialogueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("I got clicked");

                getDialogService().showConfirmationBox("Congratulations, you beat the game. Would you like to play again?", answer -> System.out.println("hihi"));


            }
        });

        //HIGHSCORES
        TableView<String> table = new TableView<String>();

        Label highscoreLabel = new Label("Highscores");

        highscoreLabel.setFont(new Font(30));

        highscoreLabel.setMinWidth(500);
        highscoreLabel.setMaxWidth(700);
        highscoreLabel.setPrefWidth(600);

        highscoreLabel.setLayoutX(1300);
        highscoreLabel.setLayoutY(120);

            //add the columns
        TableColumn playerCol = new TableColumn("Player");
        TableColumn scoreCol = new TableColumn("Score");
        playerCol.setPrefWidth(250);
        scoreCol.setPrefWidth(250);
        table.getColumns().addAll(playerCol, scoreCol);

            //set
        table.setMinSize(500, 700);
        table.setLayoutX(1150);
        table.setLayoutY(200);

        //BACKGROUND
        Rectangle bg = new Rectangle(1920, 1080);
        bg.setFill(Color.BEIGE);

        //DRAW EVERYTHING
        getContentRoot().getChildren().addAll(bg, playerNameBox, titleLabel, startButton, highscoreLabel, table, dialogueButton);



//    protected Node createBackground(double width, double height){
//
//
//
//    }

    }
}
