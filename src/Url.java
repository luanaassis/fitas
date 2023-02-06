public class Url {
    private String url;
    private int numberOfTape;
    private int numberOfViews;

    public Url(String url, int numberOfTape, int numberOfViews) {
        this.url = url;
        this.numberOfTape = numberOfTape;
        this.numberOfViews = numberOfViews;
    }

    public Url(String entity, int numberOfTape) {
        String[] data = entity.split(" ");
        this.url = data[0];
        this.numberOfTape = numberOfTape;
        this.numberOfViews = Integer.parseInt(data[1]);
    }

    public String getUrl() {
        return url;
    }

    public int getNumberOfTape() {
        return numberOfTape;
    }

    public int getNumberOfViews() {
        return numberOfViews;
    }

    public String getEntity() {
        return this.url + " " + this.numberOfViews;
    }
}
