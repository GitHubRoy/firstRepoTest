package nl.saxion.ap.drink;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import nl.saxion.ap.drink.model.DataModel;

public class GameActivity extends AppCompatActivity {


    private static final int REQUEST_MAIN = 1;

    private ImageView cardView;
    private TextView name;
    private TextView cardsLeftView;
    private ImageView arrowUp;
    private ImageView arrowDown;
    private TextView drinkStreak;

    private Boolean userInput;//higher = true, lower = false;
    private DataModel model = DataModel.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        cardView = (ImageView) findViewById(R.id.card);
        name = (TextView) findViewById(R.id.name);
        cardsLeftView = (TextView) findViewById(R.id.cardsLeftView);
        arrowUp = (ImageView) findViewById(R.id.arrow_higherView);
        arrowDown = (ImageView) findViewById(R.id.arrow_lowerView);
        drinkStreak = (TextView) findViewById(R.id.drinkStreakView);


        if (model.getCardsLeft() == 0) {
            resetViews();
        }
        if(model.getCardsLeft()>=0 ) {
            model.checkNameOrder();
            if (model.getCurrentCard() == model.getDefaultCard()) {
                firstCard();
            } else {
                cardView.setImageResource(model.getCurrentCard().getResource());
                name.setText(model.getCurrentName());
            }

            cardsLeftView.setBackgroundColor(Color.WHITE);
            drinkStreak.setText("Slokken: " + model.getRightStreak());
            cardsLeftView.setText(model.getCardsLeft() + " kaarten over");
            name.setText(model.getCurrentName());
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrowDown.getAlpha() == 0) {
                    nextCard();
                }
            }
        });

        arrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrowDown.getAlpha() != 0) {
                    userInput = true;
                    nextCard();
                    checkAnswer();
                }
            }
        });

        arrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrowDown.getAlpha() != 0) {
                    userInput = false;
                    nextCard();
                    checkAnswer();
                }
            }
        });
    }

    private void firstCard() {
        model.firstCard();
        name.setText(model.getCurrentName());
        cardView.setImageResource(model.getCurrentCard().getResource());
    }

    private void nextCard() {
        model.nextCard();
        name.setText(model.getCurrentName());
        cardView.setImageResource(model.getCurrentCard().getResource());
        if (model.getCurrentCard() != model.getDefaultCard() && model.getCardsLeft() > 0) {
            //if current card is not default card.
            arrowUp.setAlpha(0.85f);
            arrowDown.setAlpha(0.85f);
            cardsLeftView.setAlpha(1f);
            name.setAlpha(1f);
            drinkStreak.setAlpha(1f);
            cardsLeftView.setText(model.getCardsLeft() + " kaarten over");

            if (model.getPreviousCard() != model.getDefaultCard()) {
                //if previous card is not default card.
                int state = -2;
                if (model.getCurrentCard().getValue() == model.getPreviousCard().getValue()) {
                    //new card value is equal
                    state = 0;
                } else if (model.getCurrentCard().getValue() < model.getPreviousCard().getValue()) {
                    //new card value is smaller
                    state = -1;
                } else if (model.getCurrentCard().getValue() > model.getPreviousCard().getValue()) {
                    //new card value is bigger
                    state = 1;
                }
                if (userInput != null) {
                    model.saveTurn(state, userInput);
                    drinkStreak.setText("Slokken: " + model.getRightStreak());
                }
            }
        } else {
            resetViews();
        }
    }

    private void resetViews() {
        arrowUp.setAlpha(0f);
        arrowDown.setAlpha(0f);
        cardsLeftView.setAlpha(0f);
        name.setAlpha(0f);
        drinkStreak.setAlpha(0f);
    }

    private void checkAnswer() {
        if (model.getUsedCards().get(model.getUsedCards().size() - 1).isRight()) {
            if (model.getRightStreak() == model.getStreakEnd()) {
                Intent intent = new Intent(GameActivity.this, QuestActivity.class);
                startActivityForResult(intent, REQUEST_MAIN);
            } else {
                Snackbar.make(cardView, "GOOD!", Snackbar.LENGTH_SHORT).show();
            }
        } else {
            Intent intent = new Intent(GameActivity.this, WrongActivity.class);
            startActivityForResult(intent, REQUEST_MAIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_MAIN) {
            model.setRightStreak(0);
            drinkStreak.setText("Slokken: " + model.getRightStreak());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.playersOption:
                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.historyOption:
                Toast.makeText(GameActivity.this, "W.I.P", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(GameActivity.this, "banaan", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.resetOption:
                model.resetGame();
                finish();
                Toast.makeText(GameActivity.this, "Game Reset", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}