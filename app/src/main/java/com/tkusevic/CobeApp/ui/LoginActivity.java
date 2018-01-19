package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.DataUtils;
import com.tkusevic.CobeApp.data.model.User;
import com.tkusevic.CobeApp.data.model.Worker;

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
        setContentView(R.layout.activity_login);
        if(data.getWorkers().isEmpty()) {
            DataUtils.loadData();
        }
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
        boolean skipWorker = false;
        int loginType=0;
        boolean isEmailOk = false;
        boolean isPasswordOk = false;
        int id = -1;


        for (User user : data.getUsers()) {
            isEmailOk = user.getEmail().equals(inputEmail.getText().toString().trim());
            isPasswordOk = user.getPassword().equals(inputPassword.getText().toString().trim());
            if (isEmailOk && isPasswordOk) {
                skipWorker=true;
                id = user.getId();
                loginType= AppConstants.USER;
                break;
            }

        }
        if(!skipWorker) {
            for (Worker worker : data.getWorkers()) {
                isEmailOk = worker.getEmail().equals(inputEmail.getText().toString());
                isPasswordOk = worker.getPassword().equals(inputPassword.getText().toString());
                if (isEmailOk && isPasswordOk) {
                    id = worker.getId();
                    loginType = AppConstants.WORKER;
                    break;
                }

            }
        }
        loginValidation(isEmailOk, isPasswordOk, id,loginType);
    }

    private void loginValidation(boolean isEmailOk, boolean isPasswordOk, int id,int loginType) {
        Toast.makeText(this, " " + id + isEmailOk + isPasswordOk + loginType , Toast.LENGTH_SHORT).show();
        if (isEmailOk && isPasswordOk &&loginType == AppConstants.USER) {
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, UserActivity.class);
            myIntent.putExtra("id", id);
            this.startActivity(myIntent);
        }
        else if(isEmailOk && isPasswordOk && loginType == AppConstants.WORKER){
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(this, WorkerActivity.class);
            myIntent.putExtra("id", id);
            this.startActivity(myIntent);
        }
        else {
            Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show();
        }
    }
}
