public class Url {
    private String url;
    private int numberOfTape;
    private int numberOfViews;

    public Url(String url, int numberOfViews, int numberOfTape) {
        this.url = url;
        this.numberOfTape = numberOfTape;
        this.numberOfViews = numberOfViews;
    }

    public String getUrl() {
        return url;
    }

    public int getNumberOfTape() {
        return numberOfTape;
    }

    public void setNumberOfTape(int numberOfTape) {
        this.numberOfTape = numberOfTape;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public String getEntity() {
        return this.url + " " + this.numberOfViews;
    }

    public static Url fromStringRep(String s) {
        String[] split = s.split(" ");
        String url = split[0];
        int views = Integer.valueOf(split[1]);
        return new Url(url, views, 0);
    }

    @Override
    public String toString() {
        return this.url + " " + this.numberOfViews;
    }
}
