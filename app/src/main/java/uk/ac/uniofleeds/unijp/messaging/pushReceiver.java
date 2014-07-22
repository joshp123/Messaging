package uk.ac.uniofleeds.unijp.messaging;

/**
 * Created by unijp on 22/07/2014.
 */
public class pushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        String message = extras != null ? extras.getString("com.parse.Data") : "";
        JSONObject jObject;
        try {
            jObject = new JSONObject(message);
            Toast toast = Toast.makeText(context, jObject.getString("alert")+ jObject.getString("title")+jObject.getString("action"), duration);
            toast.show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

