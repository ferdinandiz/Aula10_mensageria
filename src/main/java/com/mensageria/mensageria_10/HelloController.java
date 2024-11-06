package com.mensageria.mensageria_10;

import com.mensageria.mensageria_10.dao.MessageDAO;
import com.mensageria.mensageria_10.model.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField texto2;

    @FXML
    private Label label;

    @FXML
    void buscarAction(ActionEvent event) {
        Long valor = Long.parseLong(texto2.getText());
        MessageDAO dao = new MessageDAO();
        Optional<Message> Omsg = dao.read(valor);
        Message msg = Omsg.orElse(null);
        if (msg != null) {
            label.setText(msg.getMensagem());
        }
    }


    @FXML
    protected void onHelloButtonClick() {

        String texto = texto2.getText();
        MessageDAO dao = new MessageDAO();
        Message msg = new Message();
        msg.setMensagem(texto);
        dao.create(msg);
        label.setText("Mensagem Enviada!");

//        welcomeText.setText("Welcome to JavaFX Application!");
//        if(!texto2.getText().equals("")){
//            System.err.println("----> "+texto2.getText());
//        }
    }
}