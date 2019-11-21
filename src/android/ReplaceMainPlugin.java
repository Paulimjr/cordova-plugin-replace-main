package outsystems.experts;

import android.content.SharedPreferences;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReplaceMainPlugin extends CordovaPlugin {

    private CallbackContext callbackContext;
    private static final String TAG = "ReplaceMainPlugin";
    private static final String ATTRIBUTES = "attributes";
    private SharedPreferences mSharedPreferences;

    /**
     * Executes the request and returns JSONObject
     *
     * @param action          The action to execute.
     * @param args            JSONArray used to call another application with some parameters.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return True when the action was valid, false otherwise.
     */
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        if (action != null) {

            initializeSharedPreferences();

            switch (Actions.getActionByName(action)) {
                case LOAD_VALUES:
                    loadValues(args);
                    return true;
                case INVALID:
                    callbackContext.error(Actions.INVALID.getDescription());
                    return false;

            }
        } else {
            Log.v(TAG, Actions.INVALID.getDescription());
            callbackContext.error(Actions.INVALID.getDescription());
            return false;
        }

        return true;
    }

    private void initializeSharedPreferences() {
        mSharedPreferences = this.cordova.getActivity().getSharedPreferences(OtherMainActivity.BUNDLE_SPREF,0);

        // TODO: SAVE something to tests... AFTER TESTS YOU CAN REMOVE THIS CODE ABOVE.... DON'T FORGET REMOVE THIS CODE ABOVE, RIGHT :)
        SharedPreferences.Editor sharedPreferences = this.cordova.getActivity().getSharedPreferences(OtherMainActivity.BUNDLE_SPREF, 0).edit();
        sharedPreferences.putInt("PAYMENT_CODE", 123);
        sharedPreferences.putString("PAYMENT_DESCRIPTION", "Just TESTS.....");
        sharedPreferences.apply();
    }

    private void loadValues(JSONArray args) throws JSONException {
        JSONObject object = args.getJSONObject(0);
        JSONArray attributes = object.getJSONArray(ATTRIBUTES);
        JSONObject response = new JSONObject();

        try {
            if (attributes != null && attributes.length() > 0) {
                for (int i = 0; i < attributes.length(); i++) {

                    JSONObject obj = attributes.getJSONObject(i);

                    String key = obj.getString("key");
                    Type type = Type.getTypeByName(obj.getString("type"));

                    switch (type){
                        case BOOL:
                            response.put(key,mSharedPreferences.getBoolean(key, false));
                            break;
                        case LONG:
                            response.put(key,mSharedPreferences.getLong(key, 0));
                            break;
                        case FLOAT:
                            response.put(key,mSharedPreferences.getFloat(key, 0));
                            break;
                        case STRING:
                            response.put(key,mSharedPreferences.getString(key, ""));
                            break;
                        case INTEGER:
                            response.put(key,mSharedPreferences.getInt(key, 0));
                            break;
                    }
                }
            }

            this.callbackContext.success(response);

        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
        }

    }

    private enum Actions {

        LOAD_VALUES("loadValues", "The load value saved in Shared Preferences."),
        INVALID("", "Invalid or not found action!");

        private String action;
        private String description;

        Actions(String action, String description) {
            this.action = action;
            this.description = description;
        }

        public String getDescription() {
            return description;
        }

        public static Actions getActionByName(String action) {
            for (Actions a : Actions.values()) {
                if (a.action.equalsIgnoreCase(action)) {
                    return a;
                }
            }
            return INVALID;
        }
    }

    private enum Type {
        BOOL("bool"),
        LONG("long"),
        FLOAT("float"),
        STRING("string"),
        INTEGER("int");

        private String action;

        Type(String action) {
            this.action = action;
        }

        public static Type getTypeByName(String action) {
            for (Type a : Type.values()) {
                if (a.action.equalsIgnoreCase(action)) {
                    return a;
                }
            }
            return STRING;
        }
    }
}