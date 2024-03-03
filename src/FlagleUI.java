import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.FileNotFoundException;

import javafx.scene.control.TextField;


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

    private boolean end = false;
    /**
     * CSS styling
     */
    private final String asfont = "-fx-font: 20 arial;";
    private final String bstyle = "-fx-font: 20 arial; -fx-background-color: #e7cdd3; fx-text-fill: #6467ae";
    private javafx.geometry.Insets padding = new Insets(10);

    public void init() throws FileNotFoundException {
        this.model = new FlagleModel();
        this.model.addObserver(this);
        model.setup();
        score.setText(model.getScore());
        end = model.isNextFlag();
    }

    public Label instructions(String choice) {
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
     *
     * @return VBox of flag, question and text box
     * @throws FileNotFoundException
     */
    public VBox game(BorderPane mainPane, ArrayList identity) throws FileNotFoundException {
        VBox game = new VBox();
        Button menu = new Button("Go to Menu");
        menu.setStyle(bstyle);
        menu.setPadding(padding);
        menu.setOnAction(event -> {
            try {
                model.setup();
                mainMenu(mainPane);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

        if (identity == null) {
            //Constructs the screen without a new image view
            //TODO add buttons
            VBox feeneesh = new VBox();
            ImageView congrats;
            Image gif;
            Label endMessage = new Label();
            endMessage.setStyle("-fx-font: 25 lora;");
            endMessage.setAlignment(Pos.CENTER);
            int currScore = Integer.parseInt(model.getScore());
            if(currScore == 800){
                gif = new Image("GIFS/Perfect!.gif");
                congrats = new ImageView(gif);
                endMessage.setText("PERFECT SCORE! You're amaze!!");
            } else if(currScore < 800 && currScore > 600){
                gif = new Image("GIFS/YOU SLAYED.gif");
                congrats = new ImageView(gif);
                endMessage.setText("You SLAYYEDDD!");
            } else if(currScore <= 600 && currScore > 400){
                gif = new Image("GIFS/You Did well.gif");
                congrats = new ImageView(gif);
                endMessage.setText("You did well :)");
            } else if(currScore <= 400 && currScore > 200){
                gif = new Image("GIFS/Maybe next time.gif");
                congrats = new ImageView(gif);
                endMessage.setText("Maybe next time...?");
            } else{
                gif = new Image("GIFS/Familiarize yourself.gif");
                congrats = new ImageView(gif);
                endMessage.setText("Familiarize yourself a little more <3");
            }
            congrats.setFitWidth(300);
            congrats.setPreserveRatio(true);


            feeneesh.getChildren().addAll(congrats,endMessage,menu);
            feeneesh.setSpacing(25);
            feeneesh.setAlignment(Pos.TOP_CENTER);
            mainPane.setCenter(feeneesh);
        } else {
            ImageView currentFlag = new ImageView((Image) identity.get(2));
            currentFlag.setFitWidth(400);
            currentFlag.setPreserveRatio(true);
            if (!end) {
                model.gameStart();
                Label what_flag = new Label("What " + mode + " does this flag represent?");
                what_flag.setStyle("-fx-font: 25 lora;");
                what_flag.setWrapText(true);
                what_flag.setAlignment(Pos.CENTER);
                Label errror = new Label();

                HBox options = new HBox();
                options.getChildren().add(menu);
                options.setAlignment(Pos.CENTER);
                options.setSpacing(25);

                HBox user_input = new HBox();
                TextField guess = new TextField();
                guess.setPromptText("Enter your guess here");

                Button enter = new Button("Enter");
                enter.setStyle("-fx-font: 20 arial; -fx-background-color: #e7cdd3; fx-text-fill: #6467ae");
                enter.setOnDragDetected(event -> {
                    enter.setStyle("-fx-font: 20 arial; -fx-background-color: #e7cdd3; fx-text-fill: #6467ae");

                });

                enter.setOnAction(event -> {
                    if (guess.getText().equalsIgnoreCase((String)identity.get(0))) {
                        //calls method that increases the score if first try
                        //if not first try then decrease score and whatnot
                        //gets new flag
                        switch (model.getAttemptNum()) {
                            case 1:
                                model.updateScore(100);
                                score.setText(model.getScore());
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));
                                break;
                            case 2:
                                model.updateScore(75);
                                score.setText(model.getScore());
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));
                                break;
                            case 3:
                                model.updateScore(50);
                                score.setText(model.getScore());
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));
                                break;
                            case 4:
                                model.updateScore(25);
                                score.setText(model.getScore());
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));
                                break;
                            case 5:
                                model.updateScore(0);
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));
                                break;
                            default:
                                model.setNextFlag(true);
                                errror.setText("YOU'RE WRONG GET GOOD");
                                errror.setFont(Font.font("Verdana", 20));
                                errror.setTextFill(Color.RED);
                                mainPane.setCenter(endFlag(mainPane, identity));
                        }
                    } else if (!guess.getText().equalsIgnoreCase((String)identity.get(0))) {

                        Button hint = new Button("Hint");
                        hint.setStyle(bstyle);
                        hint.setPadding(padding);
                        hint.setOnAction(event1 -> {
                            getHint(identity);
                        });

                        switch (model.getAttemptNum()) {
                            case 1:
                                model.setAttemptNum(2);
                                options.getChildren().add(hint);
                                break;
                            case 2:
                                model.setAttemptNum(3);
                                break;
                            case 3:
                                model.setAttemptNum(4);
                                break;
                            case 4:
                                model.setAttemptNum(5);
                                break;
                            case 5:
                                model.setNextFlag(true);
                                enter.setOnAction(event1 -> {
                                });
                                mainPane.setCenter(endFlag(mainPane, identity));

                        }
                    }
                });

                user_input.setAlignment(Pos.BASELINE_CENTER);

                user_input.getChildren().addAll(guess, enter);
                game.getChildren().addAll(currentFlag, what_flag, user_input, errror, options);
                game.setAlignment(Pos.TOP_CENTER);
                game.setSpacing(25.0);
            }
            mainPane.setCenter(game);
        }

        return game;

    }

    public void getHint(ArrayList identity) {
        Stage stage = new Stage();

        Label def = new Label((String) identity.get(1));
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

    public VBox endFlag(BorderPane mainPane, ArrayList identity) {
        VBox endDisplay = new VBox();
        ImageView currentFlag = new ImageView((Image) identity.get(2));
        currentFlag.setFitWidth(400);
        currentFlag.setPreserveRatio(true);

        Label answer = new Label("Answer: " + identity.get(0));
        answer.setStyle("-fx-font: 25 lora;");
        answer.setWrapText(true);
        answer.setAlignment(Pos.CENTER);

        Label description = new Label((String) identity.get(1));
        description.setMaxWidth(400);
        description.setWrapText(true);
        description.setAlignment(Pos.CENTER);

        Button cont = new Button("Continue");
        cont.setStyle(bstyle);
        cont.setPadding(padding);
        cont.setOnAction(event -> {
            try {
                ArrayList identity2 = model.getIdentity(mode);
                end = identity2 == null;
                game(mainPane, identity2);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
        Label woman = null;
        ImageView womanImage = null;
        Label womanD = null;
        if(identity.size() == 6) {
            woman = new Label((String) identity.get(3));
            woman.setStyle(asfont);
            womanImage = new ImageView((Image) identity.get(5));
            womanImage.setFitWidth(400);
            womanImage.setFitHeight(500);
            womanImage.setPreserveRatio(true);
            womanD = new Label((String) identity.get(4));
            womanD.setMaxWidth(500);
            womanD.setWrapText(true);
            womanD.setStyle("-fx-font: 20 lora;");
            endDisplay.getChildren().addAll(currentFlag, answer, description,cont,womanImage, woman, womanD);

        }else{
            endDisplay.getChildren().addAll(currentFlag, answer, description,cont);
        }
        endDisplay.setAlignment(Pos.TOP_CENTER);
        endDisplay.setSpacing(25);

        return endDisplay;
    }

    public void mainMenu(BorderPane mainPane) throws FileNotFoundException {

        // Main menu //
        VBox mainMenu = new VBox();
        Label welcome = new Label("Welcome to Pridle: Guess the LGBTQ+ Flag!");
        welcome.setMaxWidth(500);
        welcome.setWrapText(true);
        welcome.setFont(Font.font("Verdana", 50));

        // Modes Buttons //
        HBox modes = new HBox();
        Button gender = new Button("Gender");
        gender.setStyle(bstyle);
        gender.setPadding(padding);

        Button sexuality = new Button("Sexuality");
        sexuality.setStyle(bstyle);
        sexuality.setPadding(padding);

        Button all = new Button("All");
        all.setStyle(bstyle);
        all.setPadding(padding);
        modes.setSpacing(25);

        modes.getChildren().addAll(gender, sexuality, all);
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
            next.setStyle(bstyle);
            next.setPadding(padding);
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    ArrayList identity = model.getIdentity(mode);
                    model.gameStart();
                    end = false;
                    game(mainPane, identity);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            start.getChildren().addAll(instructions, next);
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
            next.setStyle(bstyle);
            next.setPadding(padding);
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    ArrayList identity = model.getIdentity(mode);
                    model.gameStart();
                    end = false;
                    game(mainPane, identity);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            start.getChildren().addAll(instructions, next);
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
            next.setStyle(bstyle);
            next.setPadding(padding);
            next.setOnAction(Event -> {
                VBox game = null;
                try {
                    ArrayList identity = model.getIdentity(mode);
                    model.gameStart();
                    end = false;
                    game(mainPane, identity);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            start.getChildren().addAll(instructions, next);
            start.setAlignment(Pos.CENTER);
            mainPane.setCenter(start);
            topText.setText(mode);

        });


    }


    @Override
    public void start(Stage stage) throws Exception {

        //main pane
        BorderPane mainPane = new BorderPane();
        mainPane.setPrefHeight(660);
        mainPane.setPrefWidth(980);
        ScrollPane mee = new ScrollPane(mainPane);
        mee.setFitToHeight(true);

        //font styling for score, top text, and attempts
        topText.setStyle(asfont);
        attempt.setStyle(asfont);
        score.setStyle(asfont);

        mainMenu(mainPane);

        mainPane.setBackground(new Background(new BackgroundFill(Color.valueOf("#FFE0D9"), null, null)));

        // Top text and score//
        HBox topDisplay = new HBox();
        HBox scoreDisplay = new HBox();
        Label currScore = new Label("Score: ");
        currScore.setStyle(asfont);
        topDisplay.setSpacing(700);
        scoreDisplay.getChildren().addAll(currScore, score);
        topDisplay.getChildren().addAll(topText, scoreDisplay);
        mainPane.setTop(topDisplay);


        // Attempt //
        HBox bottomDisplay = new HBox();
        bottomDisplay.getChildren().addAll(attempt);
        mainPane.setBottom(bottomDisplay);

        Scene scene = new Scene(mee);

        stage.setTitle("Pridle: Guess the LGBTQ+ Flag!");
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();

    }


    @Override
    public void update(FlagleModel flagleModel, String s) {
        if (s.equals(FlagleModel.OVER)) {
        } else if (s.equals(FlagleModel.END)) {
            end = true;
        }
        if(s.equals(FlagleModel.MAIN_MENU)){
            score.setText("0");
        }
        if (s.equals(FlagleModel.ATTEMPT1) || s.equals(FlagleModel.ATTEMPT2) ||
                s.equals(FlagleModel.ATTEMPT3) || s.equals(FlagleModel.ATTEMPT4)) {
            attempt.setText(s);
        } else {
            topText.setText(s);
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}