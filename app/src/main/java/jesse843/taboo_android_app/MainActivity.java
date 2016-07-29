package jesse843.taboo_android_app;

import android.animation.AnimatorInflater;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // keeps track of whether info text is showing or not
    private boolean information_text_showing;
    // information text view
    private TextView infoTextView;
    // fade in animation
    private Animation animFadein;
    // fade out animation
    private Animation animFadeout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing
        information_text_showing = false;
        infoTextView = (TextView) findViewById(R.id.information_text_view);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
    }

    public void settingsOnClick (View v) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void informationOnClick (View v) {
        if (information_text_showing) {
            infoTextView.startAnimation(animFadeout);
            infoTextView.setVisibility(View.INVISIBLE);
            information_text_showing = false;
        }
        else {
            infoTextView.startAnimation(animFadein);
            infoTextView.setVisibility(View.VISIBLE);
            information_text_showing = true;
        }
    }
}