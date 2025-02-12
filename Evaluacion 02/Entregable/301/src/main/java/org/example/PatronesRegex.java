package org.example;

public enum PatronesRegex {

    DIGITOS_9("^d{9}$"),
    DIGITOS_0_3("^[0-3]$"),
    PATRON_TELEFONO ("^d{9}$");
    private final String pattern;

    PatronesRegex (String pattern){
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean matches(String value){
        return value.matches(pattern);
    }
}
