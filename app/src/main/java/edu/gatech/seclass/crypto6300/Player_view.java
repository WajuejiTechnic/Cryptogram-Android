package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Player_view extends AppCompatActivity {
    private Button solve_cry;
    private Button view_stats;
    private Button log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view);
        solve_cry = (Button) findViewById(R.id.solve_cry);
        view_stats = (Button) findViewById(R.id.view_stats);
        log_out=(Button)findViewById(R.id.Log_out);
        solve_cry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_solve_cry();
            }
        });

        view_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_view_stats();
            }
        });
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_log_out();
            }
        });
    }

    public void open_solve_cry(){
        Intent intent = new Intent(this, CryptogramListViewActivity.class);
        startActivity(intent);
    }

    public void open_view_stats(){
        Intent intent = new Intent(this, View_stats.class);
        startActivity(intent);
    }
    public void open_log_out(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
