package util;

import java.awt.Color;
import javax.swing.JTextField;

public class ValidarCampo {

    //---- VALIDA CEP COM TRAÇO E DELIMITA TAMANHO 

    public JTextField validarCep(JTextField cep) {
        //Expressões regulares
        boolean validar = cep.getText().matches("[0-9]{5}(-)[0-9]{3}");
        if (cep.getText().length() == 5) {
            cep.setText(cep.getText() + "-");
        }
        if (cep.getText().length() > 8) {
            cep.setText(cep.getText().substring(0, 9));
            validar = true;
        }
        if (validar == false) {
            cep.setForeground(Color.red);
        } else {
            cep.setForeground(Color.blue);
        }
        return cep;
    }

    //---RETIRA O TRAÇO - DO CEP PARA ARMAZENAR INTEIRO NO BANCO DE DADOS 
    public int ajustaCepInt(Object cep) {
        String cepInt;
        cepInt = cep.toString().substring(0, 5) + cep.toString().substring(6, 9);
        return Integer.parseInt(cepInt);
    }
<<<<<<< HEAD
    
    
    public JTextField ValidarTelefone(JTextField telefone) {
    String valor = telefone.getText().trim();

    // Verifica se contém apenas números e tem 10 ou 11 dígitos
    if (valor.matches("\\d{10,11}")) {
        telefone.setBackground(Color.WHITE); // válido: cor normal
    } else {
        telefone.setBackground(Color.PINK);  // inválido: cor de erro
    }
    return telefone;
  }

    
=======
>>>>>>> e31d81004d2b48d840391e7bdfe4e8788b4aa961

}