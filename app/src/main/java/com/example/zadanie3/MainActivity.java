package com.example.zadanie3;

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
    private TextView errorBoxLength;
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
        errorBoxLength = findViewById(R.id.errorBoxLength);
        strength = findViewById(R.id.strength);

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String welcomeMessage = "";
                        String errorMessage = "";
                        String strengthMessage = "";
                        String strengthLevel = "";
                        int passStrength = 0;

                        String checkEmail = String.valueOf(email.getText());
                        String checkPass1 = String.valueOf(pass1.getText());
                        String checkPass2 = String.valueOf(pass2.getText());

                        errorBoxChars.setText("");
                        errorBoxNumbers.setText("");
                        errorBoxLowercase.setText("");
                        errorBoxUppercase.setText("");
                        errorBoxLength.setText("");

                        if(!checkEmail.contains("@")){
                            errorMessage = "Niepoprawny adres e-mail";
                        }
                        else if (!checkPass1.equals(checkPass2)) {
                            errorMessage = "Hasła się różnią";
                        }
                        else if (checkPass1.isEmpty() && checkPass2.isEmpty()) {
                            errorMessage = "Podaj hasło";
                        }
                        else{
                            boolean charsSpecial = checkPass1.matches(".*[!@#$%^&*()_+\\-=?.<>\\[\\]{}|/].*");
                            boolean charsNumber = checkPass1.matches(".*[0-9].*");
                            boolean charsUppercase = checkPass1.matches(".*[A-Z].*");
                            boolean charsLowercase = checkPass1.matches(".*[a-z].*");

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
                                errorBoxLength.setText("Hasło ma co najmniej 10 znaków!");
                                errorBoxLength.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_light));
                            }
                            else{
                                errorBoxLength.setText("Hasło jest krótsze niż 10 znaków!");
                                errorBoxLength.setTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_red_light));
                            }

                            for(int i = 1; i <= passStrength; i++){
                                strengthLevel += " |";
                            }

                            strengthMessage = "Siła hasła:" + strengthLevel;
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