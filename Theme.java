public class Theme {
    // Attributes
    private String id;
    private String name;
    private String description;

    // Constructor
    public Theme(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // Method to apply the theme
    public void applyTheme() {
        System.out.println("applyTheme() called for Theme: " + name);
    }
}
