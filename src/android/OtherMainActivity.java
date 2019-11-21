package outsystems.experts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import org.apache.cordova.CordovaActivity;

import java.util.Set;

public class OtherMainActivity extends CordovaActivity {

    protected static final String BUNDLE_SPREF = "BUNDLE_SPREF";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            saveBundleInfo(extras);
        }
        // enable Cordova apps to be started in the background
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Bundle extras = intent.getExtras();
        if (extras != null) {
            saveBundleInfo(extras);
        }

    }

    private void saveBundleInfo(Bundle extras) {

        SharedPreferences.Editor editor = getSharedPreferences(BUNDLE_SPREF, MODE_PRIVATE).edit();
        editor.clear();

        Set<String> keys = extras.keySet();
        if (keys == null) return;

        for (String key : keys) {
            Object value = extras.get(key);
            if(value == null) continue;

            if(value instanceof String){
                editor.putString(key, (String)value);
            }else if(value instanceof Integer){
                editor.putInt(key, (Integer)value);
            }else if(value instanceof Float){
                editor.putFloat(key, (Float)value);
            }else if(value instanceof Boolean){
                editor.putBoolean(key, (Boolean)value);
            }else if(value instanceof Long){
                editor.putLong(key, (Long)value);
            }
        }

        editor.apply();
    }
}
