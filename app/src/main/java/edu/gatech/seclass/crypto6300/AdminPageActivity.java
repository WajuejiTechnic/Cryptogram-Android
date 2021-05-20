package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

public class AdminPageActivity extends AppCompatActivity {

    private Button createPlayerBtn;
    private Button createCryptogramBtn;
    private Button viewAdminBtn;
    private Button log_outBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page);

        createPlayerBtn = (Button) findViewById(R.id.create_player);
        createPlayerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreatePlayerActivity.class));
            }
        });

        createCryptogramBtn = (Button) findViewById(R.id.create_cryptogram);
        createCryptogramBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CreateCreptogramActivity.class));
            }
        });

        viewAdminBtn = (Button)findViewById(R.id.admin_statistics_view);
        viewAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),View_admin_stats.class));
            }
        });

        log_outBtn = (Button)findViewById(R.id.log_out);
        log_outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
