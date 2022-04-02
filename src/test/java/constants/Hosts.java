package constants;

public enum Hosts {
    PLANETA_INSTRUMENT("https://planeta-instrument.com.ua/");
    private String path;

    public String getPath() {
        return path;
    }

    Hosts(String path) {
        this.path = path;
    }
}
