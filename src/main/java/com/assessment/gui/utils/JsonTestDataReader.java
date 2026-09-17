package com.assessment.gui.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class JsonTestDataReader {

    private static final Gson GSON = new Gson();

    private JsonTestDataReader() {
    }

    public static <T> List<T> readList(String classpathResource, Class<T> elementType) {
        try (InputStream input = JsonTestDataReader.class.getClassLoader().getResourceAsStream(classpathResource)) {
            if (input == null) {
                throw new IllegalStateException("Test data file not found: " + classpathResource);
            }
            Type listType = TypeToken.getParameterized(List.class, elementType).getType();
            return GSON.fromJson(new InputStreamReader(input, StandardCharsets.UTF_8), listType);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read test data file: " + classpathResource, e);
        }
    }
}