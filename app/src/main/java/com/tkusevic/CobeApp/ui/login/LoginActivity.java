package com.tkusevic.CobeApp.ui.login;

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
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.DataUtils;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.ui.register.RegistrationActivity;
import com.tkusevic.CobeApp.ui.user.UserActivity;
import com.tkusevic.CobeApp.ui.worker.WorkerActivity;

/**
 * Created by tkusevic on 16.01.2018..
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputEmail;
    EditText inputPassword;
    TextInputLayout emailLayout;
    TextInputLayout passwordLayout;
    Button newLogin;
    TextView registrationCall;

    boolean isEmailOk = false;
    boolean isPasswordOk = false;
    int loginType = 0;
    int id = -1;

    private final Data data = App.getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (data.getWorkers().isEmpty()) {
            DataUtils.loadData();
        }
        initUi();
        initListeners();
    }

    private void initListeners() {
        registrationCall.setOnClickListener(this);
        newLogin.setOnClickListener(this);
    }

    private void initUi() {
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        emailLayout = (TextInputLayout) findViewById(R.id.input_email_layout_login);
        passwordLayout = (TextInputLayout) findViewById(R.id.input_password_layout_login);
        newLogin = (Button) findViewById(R.id.newLogin);
        registrationCall = (TextView) findViewById(R.id.registrationCall);
    }

    private void loginValidation(boolean isEmailOk, boolean isPasswordOk, int id, int loginType) {
        if (isEmailOk && isPasswordOk) {
            Intent myIntent;
            switch (loginType) {
                case AppConstants.USER_TYPE:
                    myIntent = new Intent(this, UserActivity.class);
                    myIntent.putExtra(AppConstants.ID, id);
                    startActivity(myIntent);
                    break;
                case AppConstants.WORKER_TYPE:
                    myIntent = new Intent(this, WorkerActivity.class);
                    myIntent.putExtra(AppConstants.ID, id);
                    startActivity(myIntent);
                    break;
            }
        } else {
            Toast.makeText(this, R.string.krivi_unos_parametara, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.newLogin):
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                userOrWorker(email, password);
                loginValidation(isEmailOk, isPasswordOk, id, loginType);
                break;
            case (R.id.registrationCall):
                Intent myIntent = new Intent(this, RegistrationActivity.class);
                startActivity(myIntent);
                break;
        }
    }

    //ostavio ovako jer je ovo najjednostavnija metoda kako saznati dal je user ili worker (LOGINTYPE = user ili worker)
    public void userOrWorker(String email, String password) {
        boolean skipWorker = false;
        id = -1;
        for (User user : data.getUsers()) {
            isEmailOk = user.getEmail().equals(email);
            isPasswordOk = user.getPassword().equals(password);
            if (isEmailOk && isPasswordOk) {
                skipWorker = true;
                id = user.getId();
                loginType = AppConstants.USER_TYPE;
                break;
            }
        }
        if (!skipWorker) {
            for (Worker worker : data.getWorkers()) {
                isEmailOk = worker.getEmail().equals(email);
                isPasswordOk = worker.getPassword().equals(password);
                if (isEmailOk && isPasswordOk) {
                    id = worker.getId();
                    loginType = AppConstants.WORKER_TYPE;
                    break;
                }
            }
        }
    }
}
