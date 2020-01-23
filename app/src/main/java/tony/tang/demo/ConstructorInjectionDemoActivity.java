package tony.tang.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Set;

import javax.inject.Inject;

import dagger.Binds;
import dagger.android.AndroidInjection;
import dagger.multibindings.IntoSet;
import dep.ActivityScope;
import dep.ActivityScopedDependency;
import dep.AppScopedDependency;
import dep.PrintableDependency;
import tony.tang.demo.ConstructorInjectionDemoPresenter.UI;

public class ConstructorInjectionDemoActivity extends AppCompatActivity implements UI {

    @Inject
    ScopedFragmentFactory scopedFragmentFactory;

    @Inject
    ActivityScopedDependency activityScopedDependency;

    @Inject
    Set<PrintableDependency> printableDependencies;

    @Inject
    ConstructorInjectionDemoPresenter constructorInjectionDemoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        System.out.println("ConstructorInjectionDemoActivity size:" + printableDependencies.size());
        getSupportFragmentManager().setFragmentFactory(scopedFragmentFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_demo);
        TextView activityDependency = findViewById(R.id.activityDependency);
        activityDependency.setText(activityScopedDependency.toString());
        findViewById(R.id.reinflate).setOnClickListener(v -> inflate());
        constructorInjectionDemoPresenter.create();
    }

    private void inflate() {
        getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment1,
            scopedFragmentFactory.instantiate(getClassLoader(), ConstructorInjectionFragment1.class.getName())
        ).commitNow();

        getSupportFragmentManager().beginTransaction().replace(
            R.id.fragment2,
            scopedFragmentFactory.instantiate(getClassLoader(), ConstructorInjectionFragment2.class.getName())
        ).commitNow();
    }


    @Override
    public void init() {
        inflate();
    }

    @dagger.Module
    abstract static class Module {

        @Binds
        @ActivityScope
        abstract UI bind(ConstructorInjectionDemoActivity activity);

        @Binds
        @IntoSet
        abstract PrintableDependency activityScopedDependency(ActivityScopedDependency impl);

    }
}
