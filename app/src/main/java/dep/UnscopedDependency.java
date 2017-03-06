package dep;

import javax.inject.Inject;

public class UnscopedDependency implements PrintableDependency {

    @Inject
    public UnscopedDependency() {
    }
}