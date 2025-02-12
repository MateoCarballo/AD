package org.example;

public enum PatronesRegex {

    DIGITOS_0_9("^[0-9]$"),
    DIGITOS_0_3("^[0-3]$"),
    TODOS_NUMEROS("^[0-9]\\d*$"),
    SOLO_NUMEROS_POSITIVOS("^[1-9]\\d*$"),
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
