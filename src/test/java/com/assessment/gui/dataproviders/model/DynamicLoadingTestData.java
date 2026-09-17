package com.assessment.gui.dataproviders.model;

public class DynamicLoadingTestData {

    private String scenario;
    private String exampleNumber;
    private String expectedText;

    public String getScenario() {
        return scenario;
    }

    public String getExampleNumber() {
        return exampleNumber;
    }

    public String getExpectedText() {
        return expectedText;
    }

    @Override
    public String toString() {
        return scenario;
    }
}