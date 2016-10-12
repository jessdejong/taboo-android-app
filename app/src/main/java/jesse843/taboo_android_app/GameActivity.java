package jesse843.taboo_android_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    // settings info
    private String team_name1;
    private String team_name2;
    private int numRounds;
    private int secondsPerRound;

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
        ImageButton pause_button = (ImageButton) findViewById(R.id.pause_button);
        TextView turn_text_view = (TextView) findViewById(R.id.turn_text_view);
        turn = true;
        round_number = 1;

        // displaying whos turn
        if (turn) {
            turn_text_view.setText(team_name1);
        }
        else {
            turn_text_view.setText(team_name2);
        }

        // updating the round number
        updateRoundNum();

        ImageButton pauseButton = (ImageButton)findViewById(R.id.pause_button);
        int color = Color.parseColor("#C23127"); //The color u want
        pauseButton.setColorFilter(color);

        // pause_button clicked
        //pause_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Intent intent = new Intent(GameActivity.this, PauseActivity.class);
        //        startActivity(intent);
        //}
        //});
    }

    public void updateRoundNum () {
        TextView roundNumTextView = (TextView)findViewById(R.id.round_text_view);
        roundNumTextView.setText(getResources().getString(R.string.round_text) + round_number + "/" + numRounds);
    }
}
