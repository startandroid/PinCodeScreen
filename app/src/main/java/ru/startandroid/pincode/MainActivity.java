package ru.startandroid.pincode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.startandroid.pincode.app.App;
import ru.startandroid.pincode.pin.mvp.PinCodeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.change_pin)
    void onChangePinClick() {
        PinCodeActivity.changePinCode(this);
    }

    @OnClick(R.id.reset_pin)
    void onResetPinClick() {
        App.getApp(this).getComponentsHolder().getAppComponent().getPreferences().setPin("");
    }
}
