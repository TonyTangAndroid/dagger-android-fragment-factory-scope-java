package tony.tang.demo;

import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dep.AppScope;

@AppScope
@Component(
    modules = {
        AndroidInjectionModule.class,
        ActivityContributorModule.class
    }
)
interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {

        AppComponent create(@BindsInstance Application application);
    }
}