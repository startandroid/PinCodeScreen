package ru.startandroid.pincode.pin.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import ru.startandroid.pincode.MainActivity;
import ru.startandroid.pincode.R;
import ru.startandroid.pincode.app.App;
import ru.startandroid.pincode.common.Constants;
import ru.startandroid.pincode.pin.dagger.PinCodeActivityModule;

public class PinCodeActivity extends AppCompatActivity implements PinCodeContract.View {

    @BindView(R.id.first_label)
    TextView textViewFirstLabel;

    @BindView(R.id.first_value)
    EditText editTextFirstValue;

    @BindView(R.id.second_label)
    TextView textViewSecondLabel;

    @BindView(R.id.second_value)
    EditText editTextSecondValue;

    @BindView(R.id.third_label)
    TextView textViewThirdLabel;

    @BindView(R.id.third_value)
    EditText editTextThirdValue;

    @Inject
    PinCodeContract.Presenter presenter;


    public static void createPinCode(Context context) {
        startActivity(context, Constants.PinCodeMode.CREATE);
    }

    public static void checkPinCode(Context context) {
        startActivity(context, Constants.PinCodeMode.CHECK);
    }

    public static void changePinCode(Context context) {
        startActivity(context, Constants.PinCodeMode.CHANGE);
    }

    private static void startActivity(Context context, Constants.PinCodeMode pinCodeMode) {
        Intent intent = new Intent(context, PinCodeActivity.class);
        intent.putExtra(Constants.EXTRA_MODE, pinCodeMode);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pincode_activity);
        initView();

        // extract PIN code screen mode from intent
        Constants.PinCodeMode pinCodeMode = (Constants.PinCodeMode)
                getIntent().getSerializableExtra(Constants.EXTRA_MODE);

        // inject activity
        App.getApp(this)
                .getComponentsHolder()
                .getActivityComponent(getClass(), new PinCodeActivityModule(pinCodeMode))
                .inject(this);

        // attach view to presenter
        presenter.attachView(this);

        // view is ready to work
        presenter.viewIsReady();
    }

    private void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        if (isFinishing()) {
            presenter.destroy();
            App.getApp(this).getComponentsHolder().releaseActivityComponent(getClass());
        }
    }

    @Override
    public void showFirst(int labelResId) {
        // set text to label and show it
        textViewFirstLabel.setText(labelResId);
        textViewFirstLabel.setVisibility(View.VISIBLE);

        // show field
        editTextFirstValue.setVisibility(View.VISIBLE);

        // set textChange listener
        RxTextView.afterTextChangeEvents(editTextFirstValue)
                .skipInitialValue()
                .filter(new Predicate<TextViewAfterTextChangeEvent>() {
                    @Override
                    public boolean test(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        return (textViewAfterTextChangeEvent.editable().toString().length() == 4);
                    }
                })
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        presenter.onTextFirst();
                    }
                });
    }

    @Override
    public void showSecond(int labelResId) {
        textViewSecondLabel.setText(labelResId);
        textViewSecondLabel.setVisibility(View.VISIBLE);
        editTextSecondValue.setVisibility(View.VISIBLE);
        RxTextView.afterTextChangeEvents(editTextSecondValue)
                .skipInitialValue()
                .filter(new Predicate<TextViewAfterTextChangeEvent>() {
                    @Override
                    public boolean test(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        return (textViewAfterTextChangeEvent.editable().toString().length() == 4);
                    }
                })
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        presenter.onTextSecond();
                    }
                });
    }

    @Override
    public void showThird(int labelResId) {
        textViewThirdLabel.setText(labelResId);
        textViewThirdLabel.setVisibility(View.VISIBLE);
        editTextThirdValue.setVisibility(View.VISIBLE);
        RxTextView.afterTextChangeEvents(editTextThirdValue)
                .skipInitialValue()
                .filter(new Predicate<TextViewAfterTextChangeEvent>() {
                    @Override
                    public boolean test(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        return (textViewAfterTextChangeEvent.editable().toString().length() == 4);
                    }
                })
                .subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                    @Override
                    public void accept(@NonNull TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) throws Exception {
                        presenter.onTextThird();
                    }
                });
    }

    @Override
    public String getTextFirst() {
        return editTextFirstValue.getText().toString();
    }

    @Override
    public String getTextSecond() {
        return editTextSecondValue.getText().toString();
    }

    @Override
    public String getTextThird() {
        return editTextThirdValue.getText().toString();
    }

    @Override
    public void focusFirst() {
        editTextFirstValue.requestFocus();
    }

    @Override
    public void focusSecond() {
        editTextSecondValue.requestFocus();
    }

    @Override
    public void focusThird() {
        editTextThirdValue.requestFocus();
    }

    @Override
    public void clearAll() {
        editTextFirstValue.setText("");
        editTextSecondValue.setText("");
        editTextThirdValue.setText("");
    }

    @Override
    public void showMessage(int messageResId) {
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void next() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void close() {
        finish();
    }


}
