package ru.startandroid.pincode.pin.dagger;

import dagger.Module;
import dagger.Provides;
import ru.startandroid.pincode.base.dagger.ActivityModule;
import ru.startandroid.pincode.common.Constants;
import ru.startandroid.pincode.pin.mvp.PinCodeChangePresenter;
import ru.startandroid.pincode.pin.mvp.PinCodeCheckPresenter;
import ru.startandroid.pincode.pin.mvp.PinCodeContract;
import ru.startandroid.pincode.pin.mvp.PinCodeCreatePresenter;
import ru.startandroid.pincode.storage.Preferences;

@Module
public class PinCodeActivityModule implements ActivityModule {

    private final Constants.PinCodeMode pinCodeMode;

    public PinCodeActivityModule(Constants.PinCodeMode pinCodeMode) {
        this.pinCodeMode = pinCodeMode;
    }

    @PinCodeActivityScope
    @Provides
    PinCodeContract.Presenter providePinCodePresenter(Preferences preferences) {
        switch (pinCodeMode) {
            case CREATE:
                return new PinCodeCreatePresenter(preferences);
            case CHANGE:
                return new PinCodeChangePresenter(preferences);
            case CHECK:
                return new PinCodeCheckPresenter(preferences);
            default:
                return null;
        }
    }
}
