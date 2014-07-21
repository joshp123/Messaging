package uk.ac.uniofleeds.unijp.messaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

// extra import statements

import android.content.Intent;
import android.widget.EditText;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseInstallation;
import com.parse.PushService;
import com.parse.ParsePush;

public class MyActivity extends Activity {

    public final static String EXTRA_MESSAGE = "uk.ac.uniofleeds.unijp.messaging.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // begin Parse push notifications setup functions

        Parse.initialize(this, "c6rOOGYY9b0acATzLvb3CbxhBQd5wLvJ9euDosuQ", "Bdd5YrYMdDK2nkZfivyRiQjPFdJrL3mBVgWCW4GB");
        PushService.setDefaultPushCallback(this, DisplayMessageActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        PushService.subscribe(this, "test", DisplayMessageActivity.class);
        // subscribe to the "test" channel

        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE).

                Log.d("gbs", ParseInstallation.getCurrentInstallation().toString());

        // end Parse push notifications setup functions

        setContentView(R.layout.activity_my);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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


    /* Called when user clicks the "Send" button in myActivity */
    public void sendMessage(View view)
    {
        // do something with the button

        // create an intent to pass off the message to new activity
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        // pull the text from the textField in myActivity
        EditText myTextEditField = (EditText) findViewById(R.id.edit_message);
        String message = myTextEditField.getText().toString();

        // send it as a push notification via Parse.

        ParsePush pushMessage = new ParsePush();
        pushMessage.setChannel("test");
        pushMessage.setMessage(message);
        pushMessage.sendInBackground();

        intent.putExtra(EXTRA_MESSAGE, message);

        startActivity(intent);
    }
}
