public class Theme {
    private String id;
    private String name;
    private String description;

    public Theme(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void applyTheme() {
        System.out.println("applyTheme() called for Theme: " + name);
    }
}
