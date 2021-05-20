package edu.gatech.seclass.crypto6300;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import edu.gatech.seclass.crypto6300.Model.AppDatabase;
import edu.gatech.seclass.crypto6300.Model.User;

public class View_admin_stats extends AppCompatActivity {

    private ListView listViewAdmin;
    private Button backButtonAdmin;

    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_view_stats);
        AppDatabase cdb=AppDatabase.getDatabase(getApplicationContext());
        List<User> data =  cdb.userDao().getAll("player");
        final String[] list_name=new String[data.size()];
        final String[] list_won=new String[data.size()];
        final String[] list_lost=new String[data.size()];
        final String[] list_uname=new String[data.size()];
        final String[] list_diff=new String[data.size()];
        String[] list_print=new String[data.size()];
        int num_player=data.size();

        for (int i=0; i<num_player;i++){
            list_name[i]=data.get(i).firstname;
            list_won[i]=Integer.toString(data.get(i).won);
            list_lost[i]=Integer.toString(data.get(i).lost);
            list_uname[i]=data.get(i).username;
            list_diff[i]=data.get(i).dlevel;
            list_print[i]=String.format("%-10s %-4s %-2s %-9s %-5s",list_name[i],list_won[i],list_lost[i],list_uname[i],list_diff[i]);
        };

        listViewAdmin = (ListView)findViewById(R.id.leaderboard_list_admin);
        backButtonAdmin = (Button)findViewById(R.id.backButtonAdmin);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.mytextview,list_print);
        listViewAdmin.setAdapter(adapter);



        backButtonAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminPageActivity.class));
            }
        });

    }
}