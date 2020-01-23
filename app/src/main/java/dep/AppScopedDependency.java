package dep;

import javax.inject.Inject;

@AppScope
public class AppScopedDependency implements PrintableDependency {

    @Inject
    public AppScopedDependency() {
    }
}