package tony.tang.demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

@Singleton
@Component(
    modules = {
        AndroidInjectionModule.class,
        ActivityContributorModule.class
    }
)
interface AppComponent extends AndroidInjector<DaggerApplication> {

    @Override
    void inject(DaggerApplication instance);

    @Component.Factory
    interface Factory {

        AppComponent create(@BindsInstance Application application);
    }
}