package tony.tang.demo;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dep.FragmentScope;

/**
 * This module most be included into the method, which [ContributesAndroidInjector] into an activity,
 * which will show the fragments.
 */
@Module
abstract class FragmentContributorModule {

    /**
     * This method will force Dagger to generate a [dagger.Subcomponent] for the activity's [dagger.Subcomponent],
     * which lets us inject [by.ve.demo.di.scopes.ActivityScope] and [FragmentScope] dependencies
     * into [ScopedFragmentFactory.FragmentProviders]. That allows [ScopedFragmentFactory] to inject
     * scoped dependencies into the produced fragments.
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = {DependenciesBindingModule.class, FragmentInjectorModule.class})
    abstract FragmentProviders contributeFragmentFactory();
}