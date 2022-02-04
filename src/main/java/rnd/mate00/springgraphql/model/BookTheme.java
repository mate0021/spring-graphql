package rnd.mate00.springgraphql.model;

public enum BookTheme {

    REPORT("Report"),
    FINANCE("Finance"),
    SPORTS("Sports"),
    HORROR("Horror");

    private final String themeName;

    BookTheme(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }
}
