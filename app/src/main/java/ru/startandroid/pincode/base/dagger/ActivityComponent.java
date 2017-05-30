package ru.startandroid.pincode.base.dagger;

public interface ActivityComponent<A> {
    void inject(A activity);
}
