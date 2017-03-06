package dep;

import javax.inject.Inject;

@ActivityScope
public class ActivityScopedDependency implements PrintableDependency {

    @Inject
    public ActivityScopedDependency() {
    }
}