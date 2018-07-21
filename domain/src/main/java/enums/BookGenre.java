package enums;

public enum BookGenre {
    Horror("Horror"),
    Action("Action"),
    Fantasy("Fantasy"),
    Historical("Historical"),
    SciFiction("SciFiction"),
    Romance("Romance");

    private String text;

    BookGenre(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
