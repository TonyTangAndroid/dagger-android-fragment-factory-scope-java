package tony.tang.demo;

import dagger.Binds;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoSet;
import dep.ActivityScope;
import dep.AppScopedDependency;
import dep.PrintableDependency;


@dagger.Module
abstract class ActivityContributorModule {

    @Binds
    @IntoSet
    abstract PrintableDependency toPrintable1(AppScopedDependency impl);

    @ActivityScope
    @ContributesAndroidInjector(modules = {ConstructorInjectionDemoActivity.Module.class, FragmentContributorModule.class})
    abstract ConstructorInjectionDemoActivity contributeConstructorInjectionDemoActivity();

}