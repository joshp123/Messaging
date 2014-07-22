package uk.ac.uniofleeds.unijp.messaging;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import uk.ac.uniofleeds.unijp.messaging.R;
import android.content.Intent;
import android.widget.TextView;
import com.parse.ParseAnalytics;

public class DisplayMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        // ^ set up the Activity with the XML in /layout

        // get data from the Parse GCM push notification that launched it (if present)
        ParseAnalytics.trackAppOpened(getIntent());

        // get the intent from the MyActivity.java main activity, that displays the message
        Intent intent = getIntent();

        // pull the EXTRA_MESSAGE string out of the intent passed by MyActivity
        String message = intent.getStringExtra(MyActivity.EXTRA_MESSAGE);

        // set up a TextView on the screen
        TextView myTextView = new TextView(this);
        myTextView.setTextSize(40);

        // populate the TextView with the
        myTextView.setText(message);

        setContentView(myTextView);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
