package com.example.poznamkovaruleta;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.List;
import java.util.Random;

public class HelloController {

    @FXML
    private TextField textZaci;
    @FXML
    private ListView<String> listZaci;

    @FXML
    private TextField textPoznamky;
    @FXML
    private ListView<String> listPoznamky;

    @FXML
    private Label labelVybranyZak;
    @FXML
    private Label labelVybranaPoznamka;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        listZaci.getItems().add("Pepa");
        listPoznamky.getItems().add("hraje hry pri hodine");
    }

    /**
     * Pomocná metoda pro přehrání krátkého .wav zvuku
     */
    private void prehrajZvuk() {
        try {
            URL url = getClass().getResource("/com/example/poznamkovaruleta/click.wav");
            if (url != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } else {
                System.err.println("Zvukový soubor click.wav nebyl nalezen!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPridatZaka() {
        if (!textZaci.getText().isBlank()) {
            listZaci.getItems().add(textZaci.getText());
            textZaci.clear();
        }
    }

    @FXML
    private void onSmazatZaka() {
        String vybrany = listZaci.getSelectionModel().getSelectedItem();
        if (vybrany != null) listZaci.getItems().remove(vybrany);
    }

    @FXML
    private void onPridatPoznamku() {
        if (!textPoznamky.getText().isBlank()) {
            listPoznamky.getItems().add(textPoznamky.getText());
            textPoznamky.clear();
        }
    }

    @FXML
    private void onSmazatPoznamku() {
        String vybrany = listPoznamky.getSelectionModel().getSelectedItem();
        if (vybrany != null) listPoznamky.getItems().remove(vybrany);
    }

    @FXML
    void onUdelitPoznamku() {
        List<String> zaci = listZaci.getItems();
        List<String> poznamky = listPoznamky.getItems();

        if (zaci.isEmpty() || poznamky.isEmpty()) {
            labelVybranyZak.setText("Něco je prázdné");
            labelVybranaPoznamka.setText("Něco je prázdné");
            return;
        }

        labelVybranyZak.setTextFill(javafx.scene.paint.Color.ORANGE);
        labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.ORANGE);

        // Animace losování pro obojí naráz
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    int docasnyZak = random.nextInt(zaci.size());
                    int docasnaPoznamka = random.nextInt(poznamky.size());

                    labelVybranyZak.setText(zaci.get(docasnyZak));
                    labelVybranaPoznamka.setText(poznamky.get(docasnaPoznamka));

                    prehrajZvuk();
                })
        );

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            int randomZak = random.nextInt(zaci.size());
            int randomPoznamka = random.nextInt(poznamky.size());

            labelVybranyZak.setText(zaci.get(randomZak));
            labelVybranyZak.setTextFill(javafx.scene.paint.Color.GREEN);

            labelVybranaPoznamka.setText(poznamky.get(randomPoznamka));
            labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.GREEN);
        });

        timeline.play();
    }

    @FXML
    void onZaka() {
        List<String> zaci = listZaci.getItems();

        if (zaci.isEmpty()) {
            labelVybranyZak.setText("Něco je prázdné");
            return;
        }

        labelVybranyZak.setTextFill(javafx.scene.paint.Color.ORANGE);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    int docasnyIndex = random.nextInt(zaci.size());
                    labelVybranyZak.setText(zaci.get(docasnyIndex));
                    prehrajZvuk();
                })
        );

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            int randomZak = random.nextInt(zaci.size());
            labelVybranyZak.setText(zaci.get(randomZak));
            labelVybranyZak.setTextFill(javafx.scene.paint.Color.GREEN);
        });

        timeline.play();
    }

    @FXML
    void onPoznamka() {
        List<String> poznamky = listPoznamky.getItems();

        if (poznamky.isEmpty()) {
            labelVybranaPoznamka.setText("Něco je prázdné");
            return;
        }

        labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.ORANGE);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    int docasnyIndex = random.nextInt(poznamky.size());
                    labelVybranaPoznamka.setText(poznamky.get(docasnyIndex));
                    prehrajZvuk();
                })
        );

        timeline.setCycleCount(10);
        timeline.setOnFinished(event -> {
            int randomPoznamka = random.nextInt(poznamky.size());
            labelVybranaPoznamka.setText(poznamky.get(randomPoznamka));
            labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.GREEN);
        });

        timeline.play();
    }
}