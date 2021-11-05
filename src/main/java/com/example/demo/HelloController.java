package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class HelloController {
    private Game game;

    private int step;

    @FXML
    private TextField messageField;
    @FXML
    private TextArea messagesArea;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public HelloController() {
        this.game = new Game();
        System.out.println(game.getGuessNumber());//читер для отображения загадонного числа
        this.step = 0;
    }

    public void clickSendButton(ActionEvent actionEvent) {
        final String playerNumber = messageField.getText();
        if (playerNumber.isEmpty()){//исключаем ввод пустой строки
            return;
        }
        final Game.BullCowCont bullCowCont = game.calcBullsAndCows(playerNumber);
        final String text = String.format("%d. Введено число %s. Быков %d, Коров %d", ++step, playerNumber, bullCowCont.getBulls(),
                bullCowCont.getCows());
        messagesArea.appendText(text + "\n");//передача текста в основное меню \n переход на следующую строку при новом вводе
        messageField.clear();//очистка поля после отправки
        messageField.requestFocus();//фокус на поле ввода текста
        if (bullCowCont.getBulls() == 4){
            if (isWantToPlayAgain()){
                step = 0;
                messagesArea.clear();
                this.game = new Game();
            }else {
                System.exit(0);
            }
        }
    }

    private boolean isWantToPlayAgain() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Поздравляю, Вы выиграли!\n" +
                        "Загаданное число: " + game.getGuessNumber() + "\n" +
                        "\n" +
                        "Желаете сыграть еще?",
                new ButtonType("Yes", ButtonBar.ButtonData.YES),
                new ButtonType("No", ButtonBar.ButtonData.NO));
        alert.setTitle("Есть вопрос");
        final ButtonType buttonType = alert.showAndWait().get();
        return buttonType.getButtonData() == ButtonBar.ButtonData.YES;
    }

    public void menuExitSelect(ActionEvent actionEvent) {
        System.exit(0);//выход из приложения
    }
}