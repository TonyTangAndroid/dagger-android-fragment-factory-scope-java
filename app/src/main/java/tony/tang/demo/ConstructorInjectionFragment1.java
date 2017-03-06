package tony.tang.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dep.PrintableDependency;
import tony.tang.demo.R;

public class ConstructorInjectionFragment1 extends Fragment {


    private final Set<PrintableDependency> printableDependencies;

    @Inject
    ConstructorInjectionFragment1(Set<PrintableDependency> printableDependencies) {
        super(R.layout.fragment_dependencies);
        this.printableDependencies = printableDependencies;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView fragmentName = view.findViewById(R.id.fragmentName);
        fragmentName.setText(getClass().getSimpleName());

        TextView dependenciesView = view.findViewById(R.id.dependenciesView);
        dependenciesView.setText(printableDependencies
            .stream().map(Object::toString).sorted().collect(Collectors.joining("\n")));
    }
}
