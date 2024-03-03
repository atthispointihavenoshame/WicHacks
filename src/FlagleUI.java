import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import java.io.FileInputStream;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import javafx.scene.control.TextField;


import java.awt.*;
import java.io.FileInputStream;
import java.util.ArrayList;

public class FlagleUI extends Application implements Observer<FlagleModel, String> {

    /**
     * FlagleModel
     */
    private FlagleModel model;

    /**
     * Mode
     */
    private String mode;

    /**
     * Displays the mode
     */
    private final Text topText = new Text();

    /**
     * Shows the number of attempts
     */
    private final Text attempt = new Text();

    /**
     * Displays the current score updated after gets correct or after
     * user uses all four attempts and moves on to the next flag
     */
    private final Text score = new Text();


    private boolean gameOn;

    public void init() throws FileNotFoundException {
        this.model = new FlagleModel();
        this.model.addObserver(this);
        model.setup();
        score.setText(model.getScore());
        gameOn = false;
    }

    public Label instructions(String choice){
        Label instructions = new Label("Instructions:\n" +
                "\n" +
                "You have four attempts to identify the flag.\n" +
                "At the second failed attempt you can ask for a HINT\n" +
                "Your score will be calculated based on how many attempts it takes you to get the correct answer:\n" +
                "1st attempt - 100 pts\n" +
                "2nd attempt - 75 pts\n" +
                "3rd attempt - 50 pts\n" +
                "4th attempt - 25 pts\n");
        instructions.setAlignment(Pos.CENTER);
        instructions.setFont(Font.font("Verdana", 20));

        return instructions;
    }

    /**
     * The main interface of the game. This shows the flag at the top center
     * then the question which is always the same. Then the text box where the
     * user will input their answer and the enter button to the right which will
     * allow the user to submit their answer. It will then be checked and the
     * attempts will be updated and the maximum score able to achieve will also
     * change accordingly.
     * @return VBox of flag, question and text box
     * @throws FileNotFoundException
     */
    public VBox game() throws FileNotFoundException {
        VBox game = new VBox();
        ArrayList identity = model.getIdentity(mode);
        if (identity == null) {
            gameOn = false;
            //Constructs the screen without a new image view
            //TODO add buttons
        } else {
            ImageView currentFlag = new ImageView((Image) identity.get(2));
            currentFlag.setFitWidth(400);
            currentFlag.setPreserveRatio(true);

            Label what_flag  = new Label("What gender " + mode + " does this flag represent?");
            what_flag.setStyle("-fx-font: 25 lora;");
            what_flag.setWrapText(true);
            what_flag.setAlignment(Pos.CENTER);
            Label errror = new Label();

            HBox user_input = new HBox();
            TextField guess = new TextField();
            guess.setPromptText("Enter your guess here");

            Button enter = new Button("Enter");
            enter.setOnAction(event -> {
                if (guess.getText().equals(identity.get(0))) {
                    //calls method that increases the score if first try
                    //if not first try then decrease score and whatnot
                    //gets new flag
                    switch(model.getAttemptNum()){
                        case 1: model.updateScore(100);
                            score.setText(model.getScore());
                            break;
                        case 2: model.updateScore(75);
                            score.setText(model.getScore());
                            break;
                        case 3: model.updateScore(50);
                            score.setText(model.getScore());
                            break;
                        case 4: model.updateScore(25);
                            score.setText(model.getScore());
                            break;
                        case 5: model.updateScore(0);
                            break;
                        default: errror.setText("YOU'RE WRONG GET GOOD");
                            errror.setFont(Font.font("Verdana", 20));
                            errror.setTextFill(Color.RED);
                    }
                }

                else if (!guess.getText().equals(identity.get(0))) {
                    Button hint = new Button("Hint");
                    hint.setOnAction(event1 -> {
                        getHint(identity);
                    });
                    switch (model.getAttemptNum()) {
                        case 1: model.setAttemptNum(2);
                            break;
                        case 2: model.setAttemptNum(3);
                            game.getChildren().add(hint);
                            break;
                        case 3: model.setAttemptNum(4);
                            game.getChildren().add(hint);
                            break;
                        case 4: model.setAttemptNum(5);
                            game.getChildren().add(hint);
                            break;
                        case 5:
                            //return endFlag(identity);

                    }
                }
            });
            user_input.setAlignment(Pos.BASELINE_CENTER);

            user_input.getChildren().addAll(guess,enter);
            game.getChildren().addAll(currentFlag, what_flag, user_input,errror);
            game.setAlignment(Pos.TOP_CENTER);
            game.setSpacing(25.0);
        }
        return game;
    }

    public void getHint(ArrayList identity){
        Stage stage = new Stage();

        Label def = new Label((String)identity.get(1));
        def.setStyle("-fx-font: 20 arial;");
        def.setMaxWidth(350);
        def.setWrapText(true);
        def.setAlignment(Pos.CENTER);
        HBox oneThing = new HBox(def);
        oneThing.setAlignment(Pos.CENTER);

        Scene scene = new Scene(oneThing);
        stage.setScene(scene);
        stage.setTitle("Hint");
        stage.setHeight(230);
        stage.setWidth(500);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Displays the important woman the flag the answer and the description.
     * Will have the option to continue.
     * @param identity current identity
     */
    public VBox endFlag(ArrayList identity){
        VBox endDisplay = new VBox();
        ImageView currentFlag = new ImageView((Image) identity.get(2));
        currentFlag.setFitWidth(400);
        currentFlag.setPreserveRatio(true);

        Label answer  = new Label("Answer: " + identity.get(0));
        answer.setStyle("-fx-font: 25 lora;");
        answer.setWrapText(true);
        answer.setAlignment(Pos.CENTER);

        Label description = new Label((String)identity.get(1));
        description.setMaxWidth(400);
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);

        Label woman = new Label();

        endDisplay.getChildren().addAll(currentFlag, answer, description);

        return endDisplay;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //main pane
        BorderPane mainPane = new BorderPane();

        //font styling for score, top text, and attempts
        topText.setStyle("-fx-font: 20 arial;");
        attempt.setStyle("-fx-font: 20 arial;");
        score.setStyle("-fx-font: 20 arial;");


        // Main menu //
        VBox mainMenu = new VBox();
        Label welcome = new Label("Welcome to Flagle!");
        welcome.setFont(Font.font("Verdana", 50));

        // Modes Buttons //
        HBox modes = new HBox();
        Button gender = new Button("Gender");
        Button sexuality = new Button("Sexuality");
        Button all = new Button("All");

        modes.getChildren().addAll(gender,sexuality,all);
        mainMenu.getChildren().addAll(welcome, modes);
        mainPane.setCenter(mainMenu);
        mainMenu.setAlignment(Pos.CENTER);
        modes.setAlignment(Pos.CENTER);

        // Gender //
        gender.setOnAction(event -> {
            mode = FlagleModel.GENDER;
            VBox start = new VBox();
            Label instructions = instructions(mode);
            instructions.setWrapText(true);
            instructions.setMaxWidth(550);
            Button next = new Button("Next");
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    gameOn = true;
                    model.gameStart();
                    game = game();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                mainPane.setCenter(game);
            });
            start.getChildren().addAll(instructions,next);
            start.setAlignment(Pos.CENTER);
            mainPane.setCenter(start);
            topText.setText(mode);
        });


        // Sexuality //
        sexuality.setOnAction(event -> {
            mode = FlagleModel.SEXUALITY;
            VBox start = new VBox();
            Label instructions = instructions(mode);
            instructions.setWrapText(true);
            instructions.setMaxWidth(550);
            Button next = new Button("Next");
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    gameOn = true;
                    model.gameStart();
                    game = game();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                mainPane.setCenter(game);
            });
            start.getChildren().addAll(instructions,next);
            start.setAlignment(Pos.CENTER);
            mainPane.setCenter(start);
            topText.setText(mode);

        });

        // All //
        all.setOnAction(event -> {
            mode = FlagleModel.ALL;
            VBox start = new VBox();
            Label instructions = instructions(mode);
            instructions.setWrapText(true);
            instructions.setMaxWidth(550);
            Button next = new Button("Next");
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    gameOn = true;
                    model.gameStart();
                    game = game();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                mainPane.setCenter(game);
            });
            start.getChildren().addAll(instructions,next);
            start.setAlignment(Pos.CENTER);
            mainPane.setCenter(start);
            topText.setText(mode);

        });


        mainPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFE0D9"), null, null)));

        // Top text and score//
        HBox topDisplay = new HBox();
        HBox scoreDisplay = new HBox();
        Label currScore = new Label("Score: ");
        currScore.setStyle("-fx-font: 20 arial;");
        topDisplay.setSpacing(700);
        scoreDisplay.getChildren().addAll(currScore, score);
        topDisplay.getChildren().addAll(topText, scoreDisplay);
        mainPane.setTop(topDisplay);


        // Attempt //
        HBox bottomDisplay = new HBox();
        bottomDisplay.getChildren().addAll(attempt);
        mainPane.setBottom(bottomDisplay);

        Scene scene = new Scene(mainPane);

        stage.setTitle("Flagle");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();

    }


    @Override
    public void update(FlagleModel flagleModel, String s) {
        if (s.equals(FlagleModel.OVER)) {
            gameOn = false;
        }
        if(s.equals(FlagleModel.ATTEMPT1) || s.equals(FlagleModel.ATTEMPT2) ||
                s.equals(FlagleModel.ATTEMPT3) || s.equals(FlagleModel.ATTEMPT4)){
            attempt.setText(s);
        }else {
            topText.setText(s);
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}