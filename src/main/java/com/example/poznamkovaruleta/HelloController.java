package com.example.poznamkovaruleta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
            labelVybranyZak.setText("Prázdné");
            labelVybranaPoznamka.setText("Prázdné");
            return;
        }

        int randomZak = random.nextInt(zaci.size());
        int randomPoznamka = random.nextInt(poznamky.size());

        labelVybranyZak.setText(zaci.get(randomZak));
        labelVybranaPoznamka.setText(poznamky.get(randomPoznamka));
    }
}