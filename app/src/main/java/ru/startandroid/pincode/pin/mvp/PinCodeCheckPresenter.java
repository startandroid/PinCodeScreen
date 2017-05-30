package ru.startandroid.pincode.pin.mvp;

import ru.startandroid.pincode.R;
import ru.startandroid.pincode.base.mvp.PresenterBase;
import ru.startandroid.pincode.storage.Preferences;

public class PinCodeCheckPresenter extends PresenterBase<PinCodeContract.View> implements PinCodeContract.Presenter {

    private final Preferences preferences;

    public PinCodeCheckPresenter(Preferences preferences) {
        this.preferences = preferences;
    }

    @Override
    public void viewIsReady() {
        getView().showFirst(R.string.enter_pin);
    }

    @Override
    public void onTextFirst() {
        if (getView().getTextFirst().equals(preferences.getPin())) {
            getView().next();
            getView().close();
        } else {
            getView().showMessage(R.string.wrong_pin);
            getView().clearAll();
        }
    }

    @Override
    public void onTextSecond() {
        //do nothing
    }

    @Override
    public void onTextThird() {
        //do nothing
    }
}
