package jesse843.taboo_android_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        // initializing variables
        SeekBar numRoundsSeekBar = (SeekBar)findViewById(R.id.num_rounds_seek_bar);
        final TextView numRoundsTextView = (TextView)findViewById(R.id.num_rounds_choose_text_view);
        SeekBar timeInRoundSeekBar = (SeekBar)findViewById(R.id.time_in_round_seek_bar);
        final TextView timeInRoundTextView = (TextView)findViewById(R.id.time_in_round_choose_text_view);
        Button play_button = (Button)findViewById(R.id.to_game_button);

        assert play_button != null;

        numRoundsSeekBar.setProgress(5);
        numRoundsTextView.setText(getResources().getString(R.string.num_rounds_choose_text) + " " + numRoundsSeekBar.getProgress());

        timeInRoundSeekBar.setProgress(30);
        timeInRoundTextView.setText(getResources().getString(R.string.time_in_round_choose_text) + " " + timeInRoundSeekBar.getProgress());

        // numRoundsSeekBar setOnSeekBarChangeListener
        numRoundsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                numRoundsTextView.setText(getResources().getString(R.string.num_rounds_choose_text) + " " + (progress+1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // timeInRoundSeekBar setOnSeekBarChangeListener
        timeInRoundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timeInRoundTextView.setText(getResources().getString(R.string.time_in_round_choose_text) + " " + (progress+1));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // play button clicked
        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameSettingsActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
