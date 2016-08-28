package jesse843.taboo_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class GameSettingsActivity extends AppCompatActivity {
    // num rounds SeekBar
    private SeekBar numRoundsSeekBar;

    // num rounds choose TextView
    private TextView numRoundsTextView;

    // time in round SeekBar
    private SeekBar timeInRoundSeekBar;

    // time in round TextView
    private TextView timeInRoundTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        // initializing variables
        numRoundsSeekBar = (SeekBar)findViewById(R.id.num_rounds_seek_bar);
        numRoundsTextView = (TextView)findViewById(R.id.num_rounds_choose_text_view);
        timeInRoundSeekBar = (SeekBar)findViewById(R.id.time_in_round_seek_bar);
        timeInRoundTextView = (TextView)findViewById(R.id.time_in_round_choose_text_view);

        numRoundsSeekBar.setProgress(5);
        numRoundsTextView.setText(getResources().getString(R.string.num_rounds_choose_text) + " " + numRoundsSeekBar.getProgress());

        timeInRoundSeekBar.setProgress(30);
        timeInRoundTextView.setText(getResources().getString(R.string.time_in_round_choose_text) + " " + timeInRoundSeekBar.getProgress());

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
    }
}
