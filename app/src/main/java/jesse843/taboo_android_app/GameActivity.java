package jesse843.taboo_android_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {
    private String team_name1;
    private String team_name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        String team_name1 = intent.getStringExtra(GameSettingsActivity.TEAM_ONE_NAME);
        String team_name2 = intent.getStringExtra(GameSettingsActivity.TEAM_TWO_NAME);
    }
}
