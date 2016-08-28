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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_settings);

        // initializing variables
        numRoundsSeekBar = (SeekBar)findViewById(R.id.num_rounds_seek_bar);
        numRoundsTextView = (TextView)findViewById(R.id.num_rounds_choose_text_view);

    }
}
