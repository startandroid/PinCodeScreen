package ru.startandroid.pincode.pin.mvp;

import ru.startandroid.pincode.base.mvp.MvpPresenter;
import ru.startandroid.pincode.base.mvp.MvpView;

public interface PinCodeContract {

    interface View extends MvpView {

        // show field with label
        void showFirst(int labelResId);
        void showSecond(int labelResId);
        void showThird(int labelResId);

        // get text from field
        String getTextFirst();
        String getTextSecond();
        String getTextThird();

        // set focus on field
        void focusFirst();
        void focusSecond();
        void focusThird();

        // clear all fields
        void clearAll();

        // show message to user
        void showMessage(int messageResId);

        // go to next screen
        void next();

        // close screen
        void close();
    }


    interface Presenter extends MvpPresenter<View> {

        // field is filled
        void onTextFirst();
        void onTextSecond();
        void onTextThird();
    }


}
