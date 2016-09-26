package jesse843.taboo_android_app;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        round_number = 1;
        turn = true;

        // updating the round number
        updateRoundNum();

        ImageButton pauseButton = (ImageButton)findViewById(R.id.pause_button);
        int color = Color.parseColor("#C23127"); //The color u want
        pauseButton.setColorFilter(color);

        // open up a pause fragment
    }

    public void updateRoundNum () {
        TextView roundNumTextView = (TextView)findViewById(R.id.round_text_view);
        roundNumTextView.setText(getResources().getString(R.string.round_text) + round_number + "/" + numRounds);
    }
}
