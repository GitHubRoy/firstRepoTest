package nl.saxion.ap.drink;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import nl.saxion.ap.drink.model.DataModel;

public class MainActivity extends AppCompatActivity {

    private DataModel model = DataModel.getInstance();

    private Button difficultySave;
    private EditText difficultyInput;
    private EditText nameInput;
    private ListView namesView;
    private Button startGame;
    private Button addName;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficultyInput = (EditText) findViewById(R.id.difficultyInput);
        difficultySave = (Button) findViewById(R.id.difficultySaveButton);
        nameInput = (EditText) findViewById(R.id.nameInput);
        namesView = (ListView) findViewById(R.id.namesView);
        startGame = (Button) findViewById(R.id.startGameButton);
        addName = (Button) findViewById(R.id.addNameButton);

        difficultyInput.setText("");
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,model.getNames());
        namesView.setAdapter(adapter);

        addName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nameInput.getText().toString().equals("")){
                    model.addName(nameInput.getText().toString());
                    nameInput.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        namesView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                model.removeName(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        namesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = model.getName(position);
                model.removeName(position);
                model.addName(name);
                adapter.notifyDataSetChanged();
            }
        });

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(model.getNames().size()>1){
                    Intent intent = new Intent(MainActivity.this,GameActivity.class);
                    startActivity(intent);
                }
            }
        });

        difficultySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!difficultyInput.getText().toString().equals("")){
                    try{
                        int difficulty = Integer.parseInt(difficultyInput.getText().toString());
                        model.setStreakEnd(difficulty);
                        difficultyInput.setText("");
                        Snackbar.make(namesView, "Difficulty set to: "+difficulty, Snackbar.LENGTH_SHORT).show();
                    }catch (NumberFormatException nfe){
                        Toast.makeText(MainActivity.this,"ERROR!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
