package com.tkusevic.CobeApp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.tkusevic.CobeApp.R;
import com.tkusevic.CobeApp.data.model.Worker;

/**
 * Created by tkusevic on 21.01.2018..
 */

public class WorkerDetailsActivity extends AppCompatActivity {

    private EditText nameWorker;
    private EditText typeWorker;
    private EditText salaryWorker;
    private EditText lastnameWorker;

    private Data data = App.getData();

    Worker worker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_worker);
        Intent i = getIntent();
        worker = (Worker) i.getSerializableExtra("object");
        initUi();
        setExtrasToFields();
    }

    private void setExtrasToFields() {
        nameWorker.setText(worker.getName());
        lastnameWorker.setText(worker.getLastName());
        typeWorker.setText(worker.getType());
        salaryWorker.setText(String.valueOf(worker.getSalary()));
    }

    private void initUi() {
        lastnameWorker = (EditText) findViewById(R.id.lastname_worker_view);
        nameWorker = (EditText) findViewById(R.id.name_worker_view);
        typeWorker = (EditText) findViewById(R.id.type_worker_view);
        salaryWorker = (EditText) findViewById(R.id.salary_worker_view);
    }

    public void goBack(View view) {
        finish();
    }

    public void saveWorker(View view) {
        boolean isFalse = false;
        worker.setName(nameWorker.getText().toString().trim());
        worker.setLastName(lastnameWorker.getText().toString().trim());
        worker.setType(typeWorker.getText().toString().trim());
        try {
            worker.setSalary(Double.parseDouble(salaryWorker.getText().toString()));
        } catch (Throwable throwable) {
            salaryWorker.setError("INVALID INPUT");
            isFalse = true;
        }
        if (!isFalse) {
            for (Worker currentWorker : data.getWorkers()) {
                if (currentWorker.getId() == worker.getId()) {
                    currentWorker.setName(worker.getName().trim());
                    currentWorker.setLastName(worker.getLastName().trim());
                    currentWorker.setType(worker.getType());
                    currentWorker.setSalary(worker.getSalary());
                    Intent returnIntent = new Intent();
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
            }
        }
    }
}
