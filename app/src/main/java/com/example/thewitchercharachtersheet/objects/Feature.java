package com.example.thewitchercharachtersheet.objects;

public class Feature {
    private String featureName;
    private int featureValue;
    private String shortFeatureName;
    private boolean isEnabled = true;
    public Feature(String featureName, int featureValue, String shortFeatureName) {
        this.featureName = featureName;
        this.featureValue = featureValue;
        this.shortFeatureName = shortFeatureName;
    }
    public int getFeatureValue() {
        return featureValue;
    }

    public void setFeatureValue(int featureValue) {
        this.featureValue = featureValue;
    }
    public String getFeatureName() {
        return featureName;
    }
    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getShortFeatureName() {
        return shortFeatureName;
    }
    public void setShortFeatureName(String shortFeatureName) {
        this.shortFeatureName = shortFeatureName;
    }
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
