package tony.tang.demo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dep.ActivityScopedDependency;
import tony.tang.demo.R;

public class ConstructorInjectionDemoActivity extends AppCompatActivity {

    @Inject
    ScopedFragmentFactory scopedFragmentFactory;

    @Inject
    ActivityScopedDependency activityScopedDependency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        getSupportFragmentManager().setFragmentFactory(scopedFragmentFactory);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments_demo);
        TextView activityDependency = findViewById(R.id.activityDependency);
        activityDependency.setText(activityScopedDependency.toString());
        inflate();
        findViewById(R.id.reinflate).setOnClickListener(v -> inflate());
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


}
