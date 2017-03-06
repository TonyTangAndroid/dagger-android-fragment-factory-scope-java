package dep;

import javax.inject.Inject;

public class FragmentScopedDependencyConsumer1 implements PrintableDependency {

    private final FragmentScopedDependency fragmentScopedDependency;

    @Inject
    public FragmentScopedDependencyConsumer1(FragmentScopedDependency fragmentScopedDependency) {
        this.fragmentScopedDependency = fragmentScopedDependency;
    }
}