package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.PlayRef;
import edu.gatech.seclass.crypto6300.Model.User;

public class View_stats extends AppCompatActivity {

    private ListView listView;
    private Button backButton;

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
        List<User> data =  cdb.userDao().getAll("player");
        final String[] list_name=new String[data.size()];
        final String[] list_won=new String[data.size()];
        final String[] list_lost=new String[data.size()];
        String[] list_print=new String[data.size()];
        int num_player=data.size();

        for (int i=0; i<num_player;i++){
            list_name[i]=data.get(i).firstname;
            list_won[i]=Integer.toString(data.get(i).won);
            list_lost[i]=Integer.toString(data.get(i).lost);
            list_print[i]=String.format("%-15s %-10s %-5s",list_name[i],list_won[i],list_lost[i]);
        };

        listView = (ListView)findViewById(R.id.leaderboard_list);
        backButton = (Button)findViewById(R.id.backButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextview,list_print);
        listView.setAdapter(adapter);



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Player_view.class));
            }
        });

    }
}