package com.ishuttle.kodah;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Driver_Activity extends AppCompatActivity {

    TextView NameTV;
    String data="Mr Dummy Data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        NameTV=(TextView)findViewById(R.id.Name_ID);
        NameTV.setText(data);
    }
}
