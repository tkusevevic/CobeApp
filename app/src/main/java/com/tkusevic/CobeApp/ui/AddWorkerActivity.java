package com.tkusevic.CobeApp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.utils.EmailCheckUtils;
import com.tkusevic.CobeApp.data.model.Worker;

/**
 * Created by tkusevic on 22.01.2018..
 */

public class AddWorkerActivity extends AppCompatActivity implements View.OnClickListener {

    private Data data = App.getData();

    Worker worker = new Worker();

    private EditText nameWorker;
    private EditText typeWorker;
    private EditText salaryWorker;
    private EditText lastnameWorker;
    private EditText passwordWorker;
    private EditText emailWorker;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        initUI();

    }

    private void initUI() {
        lastnameWorker = (EditText) findViewById(R.id.lastname_worker_view_add);
        nameWorker = (EditText) findViewById(R.id.name_worker_view_add);
        typeWorker = (EditText) findViewById(R.id.type_worker_view_add);
        salaryWorker = (EditText) findViewById(R.id.salary_worker_view_add);
        emailWorker = (EditText) findViewById(R.id.email_worker_view_add);
        passwordWorker = (EditText) findViewById(R.id.password_worker_view_add);
        saveButton = (Button) findViewById(R.id.addWorkerBtn);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addWorkerBtn) {
        }
    }

    private boolean isSalaryValid() {
        try {
            double tryy = Double.parseDouble(salaryWorker.getText().toString());
            return true;
        } catch (Throwable throwable) {
            salaryWorker.setError("Invalid Error");
            return false;
        }
    }

    private boolean isEmailValid() {
        if (EmailCheckUtils.isValidEmail(emailWorker.getText().toString().trim())) {
            return true;
        } else {
            emailWorker.setError("Invalid email");
            return false;
        }
    }

    public void addUser(View view) {
        if (isSalaryValid() && isEmailValid()) {
            worker.setId(data.getWorkers().size() + 1);
            worker.setName(nameWorker.getText().toString().trim());
            worker.setLastName(lastnameWorker.getText().toString().trim());
            worker.setType(typeWorker.getText().toString().trim());
            worker.setSalary(Double.valueOf(salaryWorker.getText().toString()));
            worker.setEmail(emailWorker.getText().toString().trim());
            worker.setPassword(passwordWorker.getText().toString().trim());
            data.addWorker(worker);
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            finish();
        }
    }

    public void goBack(View view) {
        finish();
    }


}
