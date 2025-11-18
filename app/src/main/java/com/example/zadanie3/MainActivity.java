package com.example.zadania;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass1;
    private EditText pass2;
    private Button submit;
    private TextView message;
    private TextView errorBox;
    private TextView errorBoxChars;
    private TextView errorBoxNumbers;
    private TextView errorBoxLowercase;
    private TextView errorBoxUppercase;
    private TextView strength;

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
        errorBox = findViewById(R.id.errorBox);
        errorBoxChars = findViewById(R.id.errorBoxChars);
        errorBoxNumbers = findViewById(R.id.errorBoxNumbers);
        errorBoxLowercase = findViewById(R.id.errorBoxLowercase);
        errorBoxUppercase = findViewById(R.id.errorBoxUppercase);
        strength = findViewById(R.id.strength);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String welcomeMessage = "";
                        String errorMessage = "";
                        String strengthMessage = "";
                        String addToPass = "";
                        int passStrength = 0;

                        String checkEmail = String.valueOf(email.getText());
                        String checkPass1 = String.valueOf(pass1.getText());
                        String checkPass2 = String.valueOf(pass2.getText());

                        boolean charsSpecial = false;
                        boolean charsNumber = false;
                        boolean charsUppercase = false;
                        boolean charsLowercase = false;

                        if(!checkEmail.contains("@")){
                            errorMessage = "Niepoprawny adres e-mail";
                        }
                        else if (!checkPass1.equals(checkPass2)) {
                            errorMessage = "Hasła się różnią";
                        }
                        else{
                            String chars = "!@#$%^&*()_+-=?/.,<>{}|[]";
                            String numbers = "1234567890";
                            String uppercase = "QWERTYUIOPASDFGHJKLZXCVBNM";
                            String lowercase = "qwertyuiopasdfghjklzxcvbnm";

                            for (int i = 0; i < checkPass1.length(); i++){
                                String password = checkPass1.charAt(i)+"";

                                if(chars.contains(password)){
                                    charsSpecial = true;
                                }

                                if(numbers.contains(password)){
                                    charsNumber = true;
                                }

                                if(uppercase.contains(password)){
                                    charsUppercase = true;
                                }

                                if(lowercase.contains(password)){
                                    charsLowercase = true;
                                }
                            }
                            if(charsSpecial){
                                passStrength++;
                                errorBoxChars.setText("Są znaki specjalne!");
                                errorBoxChars.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                            }
                            else{
                                errorBoxChars.setText("Brak znaku specjalnego!");
                                errorBoxChars.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                            }

                            if(charsNumber){
                                passStrength++;
                                errorBoxNumbers.setText("Są liczby!");
                                errorBoxNumbers.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                            }
                            else{
                                errorBoxNumbers.setText("Brak liczby!");
                                errorBoxNumbers.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                            }

                            if(charsLowercase){
                                passStrength++;
                                errorBoxLowercase.setText("Są małe litery!");
                                errorBoxLowercase.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));;
                            }
                            else{
                                errorBoxLowercase.setText("Brak małej litery!");
                                errorBoxLowercase.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                            }

                            if(charsUppercase){
                                passStrength++;
                                errorBoxUppercase.setText("Są duże litery!");
                                errorBoxUppercase.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                            }
                            else{
                                errorBoxUppercase.setText("Brak dużej litery!");
                                errorBoxUppercase.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                            }

                            if(checkPass1.length() >= 10){
                                passStrength++;
                            }

                            strengthMessage = "Siła: " + passStrength;
                            welcomeMessage = "Witaj " + checkEmail + "!";
                        }
                        message.setText(welcomeMessage);
                        errorBox.setText(errorMessage);
                        strength.setText(strengthMessage);
                    }
                }
        );
    }
}