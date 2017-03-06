package tony.tang.demo;

import dagger.android.ContributesAndroidInjector;
import dep.ActivityScope;


@dagger.Module
abstract class ActivityContributorModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {FragmentContributorModule.class})
    abstract ConstructorInjectionDemoActivity contributeConstructorInjectionDemoActivity();

}