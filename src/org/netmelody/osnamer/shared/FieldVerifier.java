package org.netmelody.osnamer.shared;


public final class FieldVerifier {
    public boolean isValidName(String name) {
        if (name == null) {
            return false;
        }
        return !name.matches(".*[^a-zA-Z0-9 _\\-].*");
    }

    public String getFailureMessage() {
        return "Project name contains invalid characters.";
    }
}
