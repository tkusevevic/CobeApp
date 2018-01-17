package com.tkusevic.CobeApp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.tkusevic.CobeApp.common.utils.DataUtils;
import com.tkusevic.CobeApp.data.model.User;

/**
 * Created by tkusevic on 16.01.2018..
 */

public class LoginActivity extends AppCompatActivity {

    EditText inputEmail;
    EditText inputPassword;
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;


    private final Data data = App.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        DataUtils.loadData();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initUi();
    }

    private void initUi() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        emailLayout = (TextInputLayout) findViewById(R.id.input_email_layout_login);
        passwordLayout = (TextInputLayout) findViewById(R.id.input_password_layout_login);

    }

    public void registrationCall(View view) {
        Intent myIntent = new Intent(this, RegistrationActivity.class);
        this.startActivity(myIntent);
    }


    public void newLogin(View view) {
        boolean isEmailOk = false;
        boolean isPasswordOk = false;
        int id = -1;

        for (User user : data.getUsers()) {
            if (user.getEmail().equals(inputEmail.getText().toString())) {
                isEmailOk = true;
            } else {
                isEmailOk = false;
            }
            if (user.getPassword().equals(inputPassword.getText().toString())) {
                isPasswordOk = true;

            } else {
                isPasswordOk = false;
            }
            if (isEmailOk && isPasswordOk) {
                id = user.getId();
                break;
            }
        }

        if (isEmailOk && isPasswordOk) {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, UserActivity.class);
            myIntent.putExtra("id", id);
            this.startActivity(myIntent);
        } else {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }

    }
}
