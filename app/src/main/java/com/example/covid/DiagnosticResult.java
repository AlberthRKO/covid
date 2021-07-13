package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DiagnosticResult extends AppCompatActivity {

    TextView resultTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnostic_result);

        resultTxt = findViewById(R.id.result);

        Bundle get = getIntent().getExtras();
        final String result = get.getString("result");

        resultTxt.setText(result);
    }
}
