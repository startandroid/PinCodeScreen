package ru.startandroid.pincode.pin.dagger;

import dagger.Subcomponent;
import ru.startandroid.pincode.base.dagger.ActivityComponent;
import ru.startandroid.pincode.base.dagger.ActivityComponentBuilder;
import ru.startandroid.pincode.pin.mvp.PinCodeActivity;

@PinCodeActivityScope
@Subcomponent(modules = PinCodeActivityModule.class)
public interface PinCodeActivityComponent  extends ActivityComponent<PinCodeActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<PinCodeActivityComponent, PinCodeActivityModule> {

    }
}
