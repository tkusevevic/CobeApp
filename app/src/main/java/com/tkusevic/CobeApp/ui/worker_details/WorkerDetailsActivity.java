package com.tkusevic.CobeApp.ui.worker_details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.common.constants.AppConstants;
import com.tkusevic.CobeApp.common.utils.ValidationUtils;
import com.tkusevic.CobeApp.data.database.Data;
import com.tkusevic.CobeApp.data.model.Worker;
import com.tkusevic.CobeApp.ui.App;

/**
 * Created by tkusevic on 21.01.2018..
 */

public class WorkerDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameWorker;
    private EditText typeWorker;
    private EditText salaryWorker;
    private EditText lastNameWorker;

    private Data data;
    Worker worker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_worker);
        getExtrasFromActivity();
        initUi();
        initData();
        setExtrasToFields();
    }

    private void initData() {
        data = App.getData();
    }

    private void getExtrasFromActivity() {
        Intent i = getIntent();
        worker = (Worker) i.getSerializableExtra(AppConstants.WORKER);
    }

    private void setExtrasToFields() {
        nameWorker.setText(worker.getName());
        lastNameWorker.setText(worker.getLastName());
        typeWorker.setText(worker.getType());
        salaryWorker.setText(String.valueOf(worker.getSalary()));
    }

    private void initUi() {
        Button goBack = (Button) findViewById(R.id.goBackWorkerDetails);
        Button saveWorker = (Button) findViewById(R.id.saveWorker);
        lastNameWorker = (EditText) findViewById(R.id.lastname_worker_view);
        nameWorker = (EditText) findViewById(R.id.name_worker_view);
        typeWorker = (EditText) findViewById(R.id.type_worker_view);
        salaryWorker = (EditText) findViewById(R.id.salary_worker_view);

        goBack.setOnClickListener(this);
        saveWorker.setOnClickListener(this);
    }

    private void setWorketAtributes() {
        worker.setName(nameWorker.getText().toString().trim());
        worker.setLastName(lastNameWorker.getText().toString().trim());
        worker.setType(typeWorker.getText().toString().trim());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.saveWorker):
                boolean isSalaryValid;
                setWorketAtributes();
                isSalaryValid = ValidationUtils.isSalaryValid(salaryWorker.getText().toString());
                if (isSalaryValid) {
                    worker.setSalary(Double.valueOf(salaryWorker.getText().toString()));
                    for (Worker currentWorker : data.getWorkers()) {
                        if (currentWorker.getId() == worker.getId()) {
                            currentWorker.setName(worker.getName().trim());
                            currentWorker.setLastName(worker.getLastName().trim());
                            currentWorker.setType(worker.getType());
                            currentWorker.setSalary(worker.getSalary());
                            Intent returnIntent = new Intent();
                            setResult(RESULT_OK, returnIntent);
                            finish();
                        }
                    }
                }
                else{
                    salaryWorker.setError(getString(R.string.krivi_unos_cijene));
                }
                break;
            case (R.id.goBackWorkerDetails):
                finish();
                break;
        }
    }


}
