package automation.gui.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public final class TestFileResolver {

    private TestFileResolver() {
    }

    public static String resolveAbsolutePath(String classpathResource) {
        URL resourceUrl = TestFileResolver.class.getClassLoader().getResource(classpathResource);
        if (resourceUrl == null) {
            throw new IllegalStateException("Test fixture file not found: " + classpathResource);
        }
        try {
            return new File(resourceUrl.toURI()).getAbsolutePath();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Failed to resolve path for: " + classpathResource, e);
        }
    }
}