package tony.tang.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.DispatchingAndroidInjector;

/**
 * This [FragmentFactory] is going to be injected into activities. Injection will be done using
 * target activity's [dagger.Subcomponent] thus it will have access to all unscoped dependencies
 * together with the ones of [javax.inject.Singleton] and [by.ve.demo.di.scopes.ActivityScope] scopes.
 */
public class ScopedFragmentFactory extends FragmentFactory {

    private final FragmentProviders providers = new FragmentProviders();

    private final DispatchingAndroidInjector<FragmentProviders> androidInjector;

    @Inject
    public ScopedFragmentFactory(DispatchingAndroidInjector<FragmentProviders> androidInjector) {
        this.androidInjector = androidInjector;
    }

    /**
     * We need to inject new set of dependencies during every fragment creation because different
     * because each fragment needs it's own set of dependencies. I.e. [by.ve.demo.di.scopes.FragmentScope]
     * dependencies must not be shared across different instances of fragments.
     *
     * Calling [androidInjector#inject] every time during fragment instantiation we guarantee the
     * creation of the fragment's [dagger.Subcomponent] for every instance of fragment. Just like it
     * works with members injection.
     */
    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        androidInjector.inject(providers);
        Class<? extends Fragment> fragmentClass = loadFragmentClass(classLoader, className);
        Provider<Fragment> fragmentProvider = providers.get(fragmentClass);
        if (fragmentProvider != null) {
            return fragmentProvider.get();
        } else {
            return super.instantiate(classLoader, className);
        }

    }

}