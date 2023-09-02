package org.apache.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
class GoogleSignInInit extends CordovaPlugin {
    private static final String TAG = "GoogleSignInInit";
    private static final int RC_SIGN_IN = 1;
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("signin")) {
            this.signin();
            return true;
        }
        return false;
    }
    public PluginResult signin(){
        Resources resources = this.cordova.getContext().getResources();
        String serverClientId = resources.getString(cordova.getActivity().getResources().getIdentifier( "server_client_id", "string",
                cordova.getActivity().getPackageName()));
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(serverClientId)
            .requestEmail()
            .build();
        Object mGoogleSignInClient = GoogleSignIn.getClient(this.cordova.getActivity(), gso);
        Intent signInIntent = ((GoogleSignInClient) mGoogleSignInClient).getSignInIntent();
        this.cordova.startActivityForResult(this,signInIntent, this.RC_SIGN_IN);
        return null;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            LOG.d(this.TAG,"Logged success");
            JSONObject data = new JSONObject();
            data.put("token",account.getIdToken());
            this.fireAdEvent("google.signin.DONE",data);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            JSONObject data = new JSONObject();
            this.fireAdEvent("google.signin.FAIL",data);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public void fireAdEvent(String eventName, JSONObject data) {
        String js = new CordovaEventBuilder(eventName).withData(data).build();
        loadJS(js);
    }
    private void loadJS(String js) {
        this.webView.loadUrl(js);
    }
}