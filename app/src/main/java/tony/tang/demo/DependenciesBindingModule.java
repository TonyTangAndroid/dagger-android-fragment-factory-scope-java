package tony.tang.demo;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;
import dep.ActivityScopedDependency;
import dep.FragmentScopedDependency;
import dep.FragmentScopedDependencyConsumer1;
import dep.FragmentScopedDependencyConsumer2;
import dep.PrintableDependency;
import dep.UnscopedDependency;

@Module
abstract class DependenciesBindingModule {

    @Binds
    @IntoSet
    abstract PrintableDependency toPrintable1(UnscopedDependency unscopedDependency);

    @Binds
    @IntoSet
    abstract PrintableDependency activityScopedDependency(ActivityScopedDependency unscopedDependency);


    @Binds
    @IntoSet
    abstract PrintableDependency FragmentScopedDependency(FragmentScopedDependency impl);

    @Binds
    @IntoSet
    abstract PrintableDependency fragmentScopedDependencyConsumer1(FragmentScopedDependencyConsumer1 impl);

    @Binds
    @IntoSet
    abstract PrintableDependency fragmentScopedDependencyConsumer2(FragmentScopedDependencyConsumer2 impl);

}