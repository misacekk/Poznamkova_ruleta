package com.example.poznamkovaruleta;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;
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
    @FXML
    private Label labelSkrytaHlaska;

    private final Random random = new Random();

    ArrayList<String> hlasky = new ArrayList<String>();

    @FXML
    public void initialize() {
        listZaci.getItems().add(new String("Pepa"));
        listPoznamky.getItems().add(new String("hraje hry pri hodine"));
        listZaci.getItems().add(new String("Franta"));
        listPoznamky.getItems().add(new String("šikanuje stevena"));
        listZaci.getItems().add(new String("Ondra"));
        listPoznamky.getItems().add(new String("blázní"));
        hlasky.add("Smaž to, nebo tě smažu já!");
        hlasky.add("Máš menší váhu než vzduch.");
        hlasky.add("Na co se to díváš?");
    }

    @FXML
    public void nahodHlasky() {
        int randomHlaska = random.nextInt(hlasky.size());

        labelSkrytaHlaska.setText(hlasky.get(randomHlaska));
        labelSkrytaHlaska.setTextFill(Color.RED);
    }

    @FXML
    public void ulozSoubor() {
        String vybranyZak = labelVybranyZak.getText();
        String vybranaPoznamka = labelVybranaPoznamka.getText();

        File soubor = new File("save.txt");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(soubor,true))) {
                writer.write("Jméno žáka: " + vybranyZak);
                writer.newLine();
                writer.write("Jméno poznámky: " + vybranaPoznamka);
                writer.newLine();
                writer.write("-------------------------");
                writer.newLine();

                System.out.println("Uloženo.");
            } catch (IOException e) {
                System.out.println("Error!");
                e.printStackTrace();
            }
    }

    @FXML
    public void udelejZvuk() {
        try {
            URL url = getClass().getResource("/com/example/poznamkovaruleta/click.wav");
            if (url != null) {
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } else {
                System.err.println("Zvukový soubor nebyl nalezen");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onPridatZaka() {
        if (!textZaci.getText().isBlank()) {
            listZaci.getItems().add(new String(textZaci.getText()));
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
            listPoznamky.getItems().add(new String(textPoznamky.getText()));
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

        int randomZak = random.nextInt(zaci.size());
        int randomPoznamka = random.nextInt(poznamky.size());

        labelVybranyZak.setText(zaci.get(randomZak));
        labelVybranyZak.setTextFill(javafx.scene.paint.Color.GREEN);
        labelVybranaPoznamka.setText(poznamky.get(randomPoznamka));
        labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.GREEN);
        nahodHlasky();
        udelejZvuk();
    }

    @FXML
    void onZaka() {
        List<String> zaci = listZaci.getItems();

        if (zaci.isEmpty()) {
            labelVybranyZak.setText("Něco je prázdné");
            return;
        }

        int randomZak = random.nextInt(zaci.size());

        labelVybranyZak.setText(zaci.get(randomZak));
        labelVybranyZak.setTextFill(javafx.scene.paint.Color.GREEN);
        udelejZvuk();
    }

    @FXML
    void onPoznamka() {
        List<String> poznamky = listPoznamky.getItems();

        if (poznamky.isEmpty()) {
            labelVybranaPoznamka.setText("Něco je prázdné");
            return;
        }

        int randomPoznamka = random.nextInt(poznamky.size());

        labelVybranaPoznamka.setText(poznamky.get(randomPoznamka));
        labelVybranaPoznamka.setTextFill(javafx.scene.paint.Color.GREEN);
        udelejZvuk();
    }
}