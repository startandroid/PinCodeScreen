package ru.startandroid.pincode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import ru.startandroid.pincode.app.App;
import ru.startandroid.pincode.pin.mvp.PinCodeActivity;
import ru.startandroid.pincode.storage.Preferences;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Preferences preferences = App.getApp(this).getComponentsHolder().getAppComponent().getPreferences();

        String pin = preferences.getPin();

        // check current PIN
        if (TextUtils.isEmpty(pin)) {
            PinCodeActivity.createPinCode(this);
        } else {
            PinCodeActivity.checkPinCode(this);
        }
        finish();
    }
}
