package jesse843.taboo_android_app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GameActivity extends AppCompatActivity {
    // settings info
    private String team_name1;
    private String team_name2;
    private int numRounds;
    private int secondsPerRound;

    // scores
    private int team_one_score;
    private int team_two_score;
    private int team_one_round_score;
    private int team_two_round_score;

    // keeps track of rounds
    private int round_number;

    // if turn is true then it is team 1's turn, otherwise vice versa.
    private boolean turn;

    // card textViews
    private TextView guess_word_text_view;
    private TextView taboo_word_1_text_view;
    private TextView taboo_word_2_text_view;
    private TextView taboo_word_3_text_view;
    private TextView taboo_word_4_text_view;
    private TextView taboo_word_5_text_view;

    // countDownTimer
    CountDownTimer roundTimer;

    // cards
    ArrayList<ArrayList<String>> cards;
    // card Indexes that have been shown
    private boolean[] cardsShown;

    // boolean which checks if paused
    private boolean paused;
    private int pauseValue;

    private BufferedReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // gets intent
        Intent intent = getIntent();

        // Initializing Variables
        team_name1 = intent.getStringExtra(GameSettingsActivity.TEAM_ONE_NAME);
        team_name2 = intent.getStringExtra(GameSettingsActivity.TEAM_TWO_NAME);
        numRounds = Integer.parseInt(intent.getStringExtra(GameSettingsActivity.NUM_ROUNDS));
        secondsPerRound = Integer.parseInt(intent.getStringExtra(GameSettingsActivity.SECONDS_PER_ROUND));
        paused = false;
        pauseValue = 0;

        team_one_score = 0;
        team_two_score = 0;
        team_one_round_score = 0;
        team_two_round_score = 0;


        // initialize game buttons
        ImageButton taboo_button = (ImageButton) findViewById(R.id.taboo_button);
        ImageButton correct_button = (ImageButton) findViewById(R.id.correct_button);
        ImageButton pass_button = (ImageButton) findViewById(R.id.pass_button);

        assert taboo_button != null;
        assert correct_button != null;
        assert pass_button != null;


        // getting cards from text file
        try {
            cards = getCards();
        }
        catch (Exception IOException) {
        }
        cardsShown = new boolean[cards.size()];

        // update the card visual
        updateCard();

        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        TextView turn_text_view = (TextView) findViewById(R.id.turn_text_view);
        turn = true;
        displayTeamTurn(turn);
        round_number = 1;

        // updating the round number
        updateRoundNum();

        // update the score
        updateScores();

        try {
            in = new BufferedReader(new FileReader("taboo_cards.txt"));
        }
        catch (FileNotFoundException exception) {
        }

        // displaying whos turn
        if (turn) {
            if (team_name1.equals("")) {
                turn_text_view.setText("Team 1");
            }
            else
                turn_text_view.setText(team_name1);
        }
        else {
            if (team_name2.equals("")) {
                turn_text_view.setText("Team 2");
            }
            else
                turn_text_view.setText(team_name2);
        }

        //pause button
        ImageButton pauseButton = (ImageButton)findViewById(R.id.pause_button);
        int color = Color.parseColor("#C23127"); // coloring the pause button
        pauseButton.setColorFilter(color);

        //countdown timer
        startRoundTimer(false);

        // pause_button clicked
        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, PauseActivity.class);
                startActivityForResult(intent, 1);
                paused = true;
        }
        });


        // game buttons!!!
        // correct button
        correct_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // update score
                if (turn) {
                    team_one_score++;
                    team_one_round_score++;
                }
                else {
                    team_two_score++;
                    team_two_round_score++;
                }
                updateCard();
                updateScores();
            }
        });
        // taboo button
        taboo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // update score
                if (turn) {
                    team_one_score--;
                    team_one_round_score--;
                }
                else {
                    team_two_score--;
                    team_two_round_score--;
                }
                updateScores();
                updateCard();
            }
        });
        // pass button
        pass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCard();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_CANCELED) {
                paused = false;
            }
        }
    }

    public void displayTeamTurn (boolean t) {
        // displaying whos turn
        TextView turn_text_view = (TextView) findViewById(R.id.turn_text_view);
        if (turn) {
            if (team_name1.equals("")) {
                turn_text_view.setText("Team 1");
            }
            else
                turn_text_view.setText(team_name1);
        }
        else {
            if (team_name2.equals("")) {
                turn_text_view.setText("Team 2");
            }
            else
                turn_text_view.setText(team_name2);
        }
    }

    public void updateScores () {
        TextView total_score_text_view = (TextView)findViewById(R.id.total_score_text_view);
        TextView round_score_text_view = (TextView)findViewById(R.id.round_score_text_view);
        if (turn) {
            total_score_text_view.setText("" + team_one_score);
            if (team_one_round_score > 0) {
                round_score_text_view.setText("+" + team_one_round_score);
            }
            else {
                round_score_text_view.setText("" + team_one_round_score);
            }
        }
        else {
            total_score_text_view.setText("" + team_two_score);
            if (team_two_round_score > 0) {
                round_score_text_view.setText("+" + team_two_round_score);
            }
            else {
                round_score_text_view.setText("" + team_two_round_score);
            }
        }
    }

    public void updateCard () {
        boolean doneAllCards = doneAllCards();
        // if done all cards start over
        if (doneAllCards)
            Arrays.fill(cardsShown, Boolean.FALSE);

        // select random cart group
        int randomCardIndex = (int)(Math.random()*cards.size());
        Log.d("abcabc", ""+randomCardIndex);

        // go through the cards to find the first index at which the card group has not been used
        while (cardsShown[randomCardIndex]) {
            randomCardIndex++;
            if (randomCardIndex == cardsShown.length) {
                randomCardIndex -= cardsShown.length;
            }
        }

        // set the card as used
        cardsShown[randomCardIndex] = false;

        // show the card

        TextView cardTitleTextView = (TextView)findViewById(R.id.guess_word);
        cardTitleTextView.setText(cards.get(randomCardIndex).get(0));

        TextView tabooWord1TextView = (TextView)findViewById(R.id.taboo_word1);
        tabooWord1TextView.setText(cards.get(randomCardIndex).get(1));

        TextView tabooWord2TextView = (TextView)findViewById(R.id.taboo_word2);
        tabooWord2TextView.setText(cards.get(randomCardIndex).get(2));

        TextView tabooWord3TextView = (TextView)findViewById(R.id.taboo_word3);
        tabooWord3TextView.setText(cards.get(randomCardIndex).get(3));

        TextView tabooWord4TextView = (TextView)findViewById(R.id.taboo_word4);
        tabooWord4TextView.setText(cards.get(randomCardIndex).get(4));

        TextView tabooWord5TextView = (TextView)findViewById(R.id.taboo_word5);
        tabooWord5TextView.setText(cards.get(randomCardIndex).get(5));
    }

    public boolean doneAllCards () {
        for (int i = 0; i < cardsShown.length; i++) {
            if (cardsShown[i] == true) {
                return false;
            }
        }
        return true;
    }

    public void updateRoundNum () {
        TextView roundNumTextView = (TextView)findViewById(R.id.round_text_view);
        roundNumTextView.setText(getResources().getString(R.string.round_text) + round_number + "/" + numRounds);
        round_number++;
    }

    public void startRoundTimer(boolean started) {
        final TextView round_timer_text_view = (TextView)findViewById(R.id.round_timer_text_view);

        int mills;
        if (!started)
            mills = secondsPerRound*1000+1000;
        else
            mills = secondsPerRound*1000;

        roundTimer = new CountDownTimer(mills, 1000) {

            public void onTick(long millisUntilFinished) {
                if (paused) {
                    pauseValue += 1000;
                }
                round_timer_text_view.setText(""+(millisUntilFinished+pauseValue) / 1000);
                //if (timeLeftInGame == 0) {
                //    round_timer_text_view.setText("Time is out!");
                    //roundTimer.cancel();
                //}
            }

            public void onFinish() {
                if (!turn) {
                    //round_number = round_number + 1;
                    updateRoundNum();

                    if (round_number == numRounds) {
                        // end game
                        // finish();
                    }
                }
                //round_timer_text_view.setText("Time Left in Round: 0");
                roundTimer.cancel();
                turn = !turn;
                displayTeamTurn(turn);
                startRoundTimer(true);
                team_one_round_score = 0;
                team_two_round_score = 0;
            }
        }.start();
    }
    public ArrayList<ArrayList<String>> getCards() throws IOException {
        InputStream is = this.getResources().openRawResource(R.raw.taboo_cards);
        BufferedReader s = new BufferedReader(new InputStreamReader(is));
       // Scanner s = new Scanner(file);
        ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();

        int index = 0;
        while (s.ready()) {
            s.readLine();
            ArrayList<String> textList = new ArrayList<String>();
            out.add(textList);
            for (int i = 0; i < 6; i++) {
                out.get(index).add(s.readLine());
            }
            index++;
        }
        is.close();
        s.close();
        Log.d("YOYOYO", "here");
        return out;
    }
}
