package com.example.zadania;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass1;
    private EditText pass2;
    private Button submit;
    private TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        submit = findViewById(R.id.submit);
        message = findViewById(R.id.message);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String checkEmail = String.valueOf(email.getText());
                        String checkPass1 = String.valueOf(pass1.getText());
                        String checkPass2 = String.valueOf(pass2.getText());

                        int passStrength = 0;
                        boolean charsSpecial = false;
                        boolean charsNumber = false;
                        boolean charsUppercase = false;
                        boolean charsLowercase = false;
                        String error = "";
                        String addToPass = "";

                        if(!checkEmail.contains("@")){
                            error = "Niepoprawny adres e-mail";
                        }
                        else if (!checkPass1.equals(checkPass2)) {
                            error = "Hasła się różnią";
                        }
                        else{
                            String chars = "!@#$%^&*()_+-=?/.,<>{}|[]";
                            String numbers = "1234567890";
                            String uppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";
                            String lowercase = "qwertyuiopasdfghjklzxcvbnm";

                            for (int i = 0; i < checkPass1.length(); i++){
                                if(chars.contains(checkPass1.charAt(i)+"")){
                                    charsSpecial = true;
                                }

                                if(numbers.contains(checkPass1.charAt(i)+"")){
                                    charsNumber = true;
                                }

                                if(uppercase.contains(checkPass1.charAt(i)+"")){
                                    charsUppercase = true;
                                }

                                if(lowercase.contains(checkPass1.charAt(i)+"")){
                                    charsLowercase = true;
                                }
                            }
                            if(checkPass1.length() >= 10){
                                passStrength++;
                            }
                            if(charsSpecial){
                                passStrength++;
                            }
                            if(charsNumber){
                                passStrength++;
                            }
                            if(charsLowercase){
                                passStrength++;
                            }
                            if(charsUppercase){
                                passStrength++;
                            }
                            if(!charsUppercase){
                                addToPass += "\nBrak dużej litery";
                            }
                            if(!charsLowercase){
                                addToPass += "\nBrak małej litery";
                            }
                            if(!charsNumber){
                                addToPass += "\nBrak liczby";
                            }
                            if(!charsSpecial){
                                addToPass += "\nBrak znaku specjalnego";
                            }
                            error = "Witaj " + checkEmail + "\n Siła hasła = " + passStrength + addToPass;
                        }
                        message.setText(error);
                    }
                }
        );
    }
}