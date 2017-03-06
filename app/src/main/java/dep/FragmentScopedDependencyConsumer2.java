package dep;

import javax.inject.Inject;

public class FragmentScopedDependencyConsumer2 implements PrintableDependency {

    private final FragmentScopedDependency fragmentScopedDependency;

    @Inject
    public FragmentScopedDependencyConsumer2(FragmentScopedDependency fragmentScopedDependency) {
        this.fragmentScopedDependency = fragmentScopedDependency;
    }
}