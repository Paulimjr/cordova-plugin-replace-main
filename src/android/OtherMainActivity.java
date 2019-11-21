package outsystems.experts;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import org.apache.cordova.*;

public class OtherMainActivity extends CordovaActivity {

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }
        // Set by <content src="index.html" /> in config.xml
        Toast.makeText(this, "Toast onCreate method!!!", Toast.LENGTH_SHORT).show();
        loadUrl(launchUrl);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "Toast onNewIntent method!!!", Toast.LENGTH_SHORT).show();
    }
}
