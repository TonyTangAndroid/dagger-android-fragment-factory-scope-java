package dep;

import javax.inject.Inject;

@FragmentScope
public class FragmentScopedDependency implements PrintableDependency {

    @Inject
    public FragmentScopedDependency() {
    }
}