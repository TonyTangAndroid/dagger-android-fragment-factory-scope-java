package tony.tang.demo;

import dagger.Module;

@Module(includes = {ConstructorInjectionFragment1.ModuleV1.class,
    ConstructorInjectionFragment2.ModuleV2.class,

})
abstract class FragmentInjectorModule {


}