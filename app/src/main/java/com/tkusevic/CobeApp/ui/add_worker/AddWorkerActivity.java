package com.tkusevic.CobeApp.ui.add_worker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.utils.ValidationUtils;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.App;
import com.tkusevic.CobeApp.data.database.Data;

/**
 * Created by tkusevic on 22.01.2018..
 */

public class AddWorkerActivity extends AppCompatActivity implements View.OnClickListener {

    private Data data;

    private EditText nameWorker;
    private EditText typeWorker;
    private EditText salaryWorker;
    private EditText lastNameWorker;
    private EditText passwordWorker;
    private EditText emailWorker;
    private Button goBack;
    private Button addWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_worker);
        initUI();
        getData();
        initListeners();
    }

    private void initUI() {
        lastNameWorker = (EditText) findViewById(R.id.lastname_worker_view_add);
        nameWorker = (EditText) findViewById(R.id.name_worker_view_add);
        typeWorker = (EditText) findViewById(R.id.type_worker_view_add);
        salaryWorker = (EditText) findViewById(R.id.salary_worker_view_add);
        emailWorker = (EditText) findViewById(R.id.email_worker_view_add);
        passwordWorker = (EditText) findViewById(R.id.password_worker_view_add);
        addWorker = (Button) findViewById(R.id.addWorker);
        goBack = (Button) findViewById(R.id.goBackWorkerAdd);
    }

    private void initListeners() {
        goBack.setOnClickListener(this);
        addWorker.setOnClickListener(this);
    }

    private void getData() {
        data = App.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.addWorker):
                if (ValidationUtils.isSalaryValid(salaryWorker.getText().toString()) && ValidationUtils.isEmailValid(emailWorker.getText().toString())) {
                    Worker worker=new Worker();
                    worker.setId(data.getWorkers().size() + 1);
                    worker.setName(nameWorker.getText().toString().trim());
                    worker.setLastName(lastNameWorker.getText().toString().trim());
                    worker.setType(typeWorker.getText().toString().trim());
                    worker.setSalary(Double.valueOf(salaryWorker.getText().toString()));
                    worker.setEmail(emailWorker.getText().toString().trim());
                    worker.setPassword(passwordWorker.getText().toString().trim());
                    data.addWorker(worker);
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK, returnIntent);
                    finish();
                } else {
                    Toast.makeText(this, R.string.krivi_unos_parametara, Toast.LENGTH_SHORT).show();
                }
                break;
            case (R.id.goBackWorkerAdd):
                finish();
                break;
        }
    }

}
