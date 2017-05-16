package nl.saxion.ap.drink;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nl.saxion.ap.drink.model.DataModel;

public class QuestActivity extends AppCompatActivity {

    private ImageView nextCard;
    private TextView name;
    private Button returnAc;

    private DataModel model = DataModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nextCard = (ImageView) findViewById(R.id.nextCardQuestActivity);
        name = (TextView) findViewById(R.id.winnerNameQuestView);
        returnAc = (Button) findViewById(R.id.returnToGameAcButtonQuest);

        name.setText(model.getPreviousName());
        nextCard.setImageResource(model.getCurrentCard().getResource());

        returnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
