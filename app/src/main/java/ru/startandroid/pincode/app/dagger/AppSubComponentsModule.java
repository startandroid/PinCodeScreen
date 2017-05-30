package ru.startandroid.pincode.app.dagger;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import ru.startandroid.pincode.base.dagger.ActivityComponentBuilder;
import ru.startandroid.pincode.pin.dagger.PinCodeActivityComponent;
import ru.startandroid.pincode.pin.mvp.PinCodeActivity;

@Module(subcomponents = {PinCodeActivityComponent.class})
public class AppSubComponentsModule {

    @Provides
    @IntoMap
    @ClassKey(PinCodeActivity.class)
    ActivityComponentBuilder provideSplashViewBuilder(PinCodeActivityComponent.Builder builder) {
        return builder;
    }

}
