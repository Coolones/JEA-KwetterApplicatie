package Service;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
public class StartUp {

    @Inject
    KweetService kweetService;

    @Inject
    ProfileService profileService;

    public StartUp() {}

    @PostConstruct
    private void initData() {

    }
}
