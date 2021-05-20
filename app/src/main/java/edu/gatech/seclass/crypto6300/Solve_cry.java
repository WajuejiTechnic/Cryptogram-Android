package edu.gatech.seclass.crypto6300;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Solve_cry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_cry);

        TextView crptogram = (TextView) findViewById(R.id.solve_cryptogram);
        crptogram.setText(getIntent().getStringExtra("Key"));

    }


}
