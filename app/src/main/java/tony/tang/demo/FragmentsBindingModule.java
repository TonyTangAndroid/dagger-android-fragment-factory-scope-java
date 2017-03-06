package tony.tang.demo;

import androidx.fragment.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dep.FragmentKey;

@Module
abstract class FragmentsBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(ConstructorInjectionFragment1.class)
    abstract Fragment bindConstructorInjectionFragment1(ConstructorInjectionFragment1 impl);

    @Binds
    @IntoMap
    @FragmentKey(ConstructorInjectionFragment2.class)
    abstract Fragment bindConstructorInjectionFragment2(ConstructorInjectionFragment2 impl);
}