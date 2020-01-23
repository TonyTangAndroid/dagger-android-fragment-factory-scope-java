package tony.tang.demo;

import androidx.fragment.app.Fragment;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

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
public class FragmentProviders {

    @Inject
    Map<Class<? extends Fragment>, Provider<Fragment>> fragmentProviders;

    public Provider<Fragment> get(Class<? extends Fragment> clazz) {
        return fragmentProviders.get(clazz);
    }
}
