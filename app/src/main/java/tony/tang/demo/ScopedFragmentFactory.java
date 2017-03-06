package tony.tang.demo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import java.util.Map;

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

    /**
     * We can't inject collection of fragment [Provider]s directly into [ScopedFragmentFactory]
     * because that would require declaring an injectable field in it:
     *
     * ```
     * class ScopedFragmentFactory @Inject constructor(
     *     private val androidInjector: DispatchingAndroidInjector<Any>
     * ) : FragmentFactory() {
     *
     *     @Inject
     *     lateinit var fragmentProviders: MutableMap<Class<out Fragment>, Provider<Fragment>>
     * }
     * ```
     *
     * If we do so then during injection of the factory into the target activity [dagger.android.AndroidInjector]
     * associated with that activity will try to inject into field of [ScopedFragmentFactory] as well.
     * And this will fail, because fragments and their dependencies are not available to the target
     * activity's [dagger.Subcomponent].
     *
     * Thus we need to postpone injection of the factory dependencies somehow. To do that the [FragmentProviders]
     * class is introduced. Whenever we want to instantiate the fragment we must inject the fresh set
     * of dependencies into [FragmentProviders] instance. That injection will happen using the
     * [dagger.Subcomponent] of target activity's [dagger.Subcomponent] and will give us access to
     * the fragment scoped dependencies.
     */
    public static class FragmentProviders {

        @Inject
        Map<Class<? extends Fragment>, Provider<Fragment>> fragmentProviders;

        public Provider<Fragment> get(Class<? extends Fragment> clazz) {
            return fragmentProviders.get(clazz);
        }
    }
}