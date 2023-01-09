package app.d3v3l.mareu.di;

import app.d3v3l.mareu.service.DummyMaReuApiService;
import app.d3v3l.mareu.service.MaReuApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static final MaReuApiService service = new DummyMaReuApiService();

    /**
     * Get an instance on @{@link MaReuApiService}
     * @return MaReuApiService
     */
    public static MaReuApiService getMaReuApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MaReuApiService}. Useful for tests, so we ensure the context is clean.
     * @return MaReuApiService
     */
    public static MaReuApiService getNewInstanceApiService() {
        return new DummyMaReuApiService();
    }
}
