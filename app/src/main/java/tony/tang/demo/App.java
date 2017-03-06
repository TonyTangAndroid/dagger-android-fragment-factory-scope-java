package tony.tang.demo;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import tony.tang.demo.DaggerAppComponent;

public class App extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

}
