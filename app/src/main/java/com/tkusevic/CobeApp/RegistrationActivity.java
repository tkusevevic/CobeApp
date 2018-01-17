package com.tkusevic.CobeApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tkusevic.CobeApp.common.utils.EmailCheckUtils;
import com.tkusevic.CobeApp.data.model.User;

/**
 * Created by tkusevic on 17.01.2018..
 */

public class RegistrationActivity extends AppCompatActivity {
    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassord;
    private Button registration;
    private TextInputLayout passwordLayout;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;

    private final Data data = App.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_layout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initUi();
    }

    private void initUi() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassord = (EditText) findViewById(R.id.input_password);
        registration = (Button) findViewById(R.id.btn_login);
        inputName = (EditText) findViewById(R.id.input_name);
        passwordLayout = (TextInputLayout) findViewById(R.id.input_password_layout_registration);
        nameLayout = (TextInputLayout) findViewById(R.id.input_name_layout_registration);
        emailLayout = (TextInputLayout) findViewById(R.id.input_email_layout_registration);

    }

    public void loginCall(View view) {
        Intent myIntent = new Intent(this, LoginActivity.class);
        this.startActivity(myIntent);
    }

    public void newRegistration(View view) {
        if(registrationCheck() && emailCheck()){
            User user = new User((data.getUsers().size()),inputEmail.getText().toString(),inputName.getText().toString(), inputPassord.getText().toString());
            data.addUser(user);
            Toast.makeText(this,"Uspješna registranica",Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, LoginActivity.class);
            this.startActivity(myIntent);
        }
    }

    private boolean emailCheck() {
        if(EmailCheckUtils.isValidEmail(inputEmail.getText().toString())){
            return true;
        }
        else
        {
            Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean registrationCheck() {
        boolean nameOk, passwordOk, emailOk;
        if (!inputName.getText().toString().trim().isEmpty() && inputName.getText() != null) {
            nameLayout.setErrorEnabled(false);
            nameOk=true;
        }
        else{
            nameLayout.setError("Empty name");
            nameOk=false;
        }
        if(!inputPassord.getText().toString().trim().isEmpty() && inputPassord.getText() != null){
            passwordLayout.setErrorEnabled(false);
            passwordOk=true;
        }
        else{
            passwordLayout.setError("Empty password");
            passwordOk=false;
        }
        if(!inputEmail.getText().toString().trim().isEmpty() && inputEmail.getText() != null) {
            emailLayout.setErrorEnabled(false);
            emailOk=true;
        }
        else{
            emailLayout.setError("Empty email");
            emailOk=false;
        }
        if(nameOk && emailOk && passwordOk){
            return true;
        }
        else{
            return false;
        }
    }
}
