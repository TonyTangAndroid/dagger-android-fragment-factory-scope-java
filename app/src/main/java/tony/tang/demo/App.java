package tony.tang.demo;

import java.util.Set;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dep.PrintableDependency;

public class App extends DaggerApplication {

    @Inject
    Set<PrintableDependency> printableDependencies;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationInjector().inject(this);
        System.out.println("App size:" + printableDependencies.size());
    }

    @Override
    protected AndroidInjector<App> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }

}
