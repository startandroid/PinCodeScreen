package ru.startandroid.pincode.app.dagger;

import dagger.Component;
import ru.startandroid.pincode.app.ComponentsHolder;
import ru.startandroid.pincode.storage.Preferences;

@AppScope
@Component(modules = {AppModule.class, AppSubComponentsModule.class})
public interface AppComponent {
    void injectComponentsHolder(ComponentsHolder componentsHolder);
    Preferences getPreferences();
}
