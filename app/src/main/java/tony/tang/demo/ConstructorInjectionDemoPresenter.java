package tony.tang.demo;

import javax.inject.Inject;

public class ConstructorInjectionDemoPresenter {

    private final UI ui;

    @Inject
    public ConstructorInjectionDemoPresenter(UI ui) {
        this.ui = ui;
    }

    public void create() {
        ui.init();
    }

    public interface UI {
        void init();
    }


}
