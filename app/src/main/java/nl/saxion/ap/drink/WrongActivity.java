package nl.saxion.ap.drink;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import nl.saxion.ap.drink.model.DataModel;

public class WrongActivity extends AppCompatActivity {

    private TextView formatFor;
    private TextView nameToDrink;
    private TextView drinkStreak;
    private ImageView nextCard;
    private Button returnButton;
    private DataModel model = DataModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nextCard = (ImageView) findViewById(R.id.nextCardWrongActivity);
        formatFor = (TextView) findViewById(R.id.formatForView);
        drinkStreak = (TextView) findViewById(R.id.drinkstreakNumber);
        returnButton = (Button) findViewById(R.id.returnToGameAcButton);
        nameToDrink = (TextView) findViewById(R.id.nameToDrinkView);

        drinkStreak.setText(String.valueOf(model.getRightStreak()));
        if(model.getRightStreak()==1){
            formatFor.setText("Slok voor:");
        }
        nameToDrink.setText(model.getPreviousName());
        nextCard.setImageResource(model.getCurrentCard().getResource());


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
