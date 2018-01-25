package com.tkusevic.CobeApp.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.utils.EmailCheckUtils;
import com.tkusevic.CobeApp.common.utils.ValidationUtils;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.ui.login.LoginActivity;

/**
 * Created by tkusevic on 17.01.2018..
 */

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputName;
    private EditText inputEmail;
    private EditText inputPassord;
    private TextInputLayout passwordLayout;
    private TextInputLayout nameLayout;
    private TextInputLayout emailLayout;
    private Button newRegistration;
    private TextView loginCall;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initUi();
        initListeners();
        initData();
    }

    private void initListeners() {
        newRegistration.setOnClickListener(this);
        loginCall.setOnClickListener(this);
    }

    private void initData() {
        data = App.getData();
    }

    private void initUi() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassord = (EditText) findViewById(R.id.input_password);
        inputName = (EditText) findViewById(R.id.input_name);
        passwordLayout = (TextInputLayout) findViewById(R.id.input_password_layout_registration);
        nameLayout = (TextInputLayout) findViewById(R.id.input_name_layout_registration);
        emailLayout = (TextInputLayout) findViewById(R.id.input_email_layout_registration);
        newRegistration = (Button) findViewById(R.id.newRegistration);
        loginCall = (TextView) findViewById(R.id.loginCall);
    }

    private boolean emailCheck() {
        if (EmailCheckUtils.isValidEmail(inputEmail.getText().toString())) {
            return true;
        } else {
            Toast.makeText(this, R.string.krivi_email, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean registrationCheck() {
        boolean nameCheck = checkNameEmpty();
        boolean passwordCheck = checkPassowordEmpty();
        boolean emailCheck = checkEmailEmpty();
        return nameCheck && emailCheck && passwordCheck;
    }


    private boolean checkNameEmpty() {
        if (!ValidationUtils.isValid(inputName.getText().toString())) {
            nameLayout.setErrorEnabled(false);
            return true;
        } else {
            nameLayout.setError(String.valueOf(R.string.prazno_ime));
            return false;
        }
    }

    private boolean checkPassowordEmpty() {
        if (!inputPassord.getText().toString().trim().isEmpty() && inputPassord.getText() != null) {
            passwordLayout.setErrorEnabled(false);
            return true;
        } else {
            passwordLayout.setError(String.valueOf(R.string.prazan_password));
            return false;
        }
    }

    private boolean checkEmailEmpty() {
        if (!inputEmail.getText().toString().trim().isEmpty() && inputEmail.getText() != null) {
            emailLayout.setErrorEnabled(false);
            return true;
        } else {
            emailLayout.setError(String.valueOf(R.string.prazan_email));
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.newRegistration):
                if (registrationCheck() && emailCheck()) {
                    User user = new User((data.getUsers().size()), inputEmail.getText().toString(), inputName.getText().toString(), inputPassord.getText().toString());
                    data.addUser(user);
                    Toast.makeText(this, R.string.uspjesna_registracija, Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(this, LoginActivity.class);
                    startActivity(myIntent);
                }
                break;
            case (R.id.loginCall):
                Intent myIntent = new Intent(this, LoginActivity.class);
                startActivity(myIntent);
                break;
        }
    }
}
