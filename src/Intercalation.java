import java.io.IOException;
import java.util.PriorityQueue;

public class Intercalation {
    private PriorityQueue<Url> intercalationTapes;
    private int numberOfTapes;
    private int memorySize;
    private String outputFileName;

    public Intercalation(int numberOfTapes, int memorySize, String outputFileName) {
        this.numberOfTapes = numberOfTapes;
        this.outputFileName = outputFileName;
        this.memorySize = memorySize;
        this.intercalationTapes = new PriorityQueue<>((a, b) -> {
            if (a.getNumberOfViews() > b.getNumberOfViews()) {
                return -1;
            }

            if (a.getUrl().compareTo(b.getUrl()) < 0) {
                return -1;
            } else {
                return +1;
            }
        });
    }

    private int findIndex(ReadTape[] tapes, int numberOfTape) {
        for (int i = 0; i < tapes.length; i++) {
            if (tapes[i].getFileName().contains(numberOfTape + "")) {
                return i;
            }
        }
        return 0;
    }

    public void order() throws IOException {
        WriteTape output = new WriteTape(this.outputFileName);
        int currentTape = 1;
        ReadTape[] tapes = new ReadTape[this.memorySize];
        System.out.println(this.memorySize);
        for (int i = 0; i < tapes.length; i++) {
            tapes[i] = new ReadTape("fita-" + (i + 1) + ".txt");
            Url currentUrl = tapes[i].readNextLine();
            if (currentUrl != null) {
                this.intercalationTapes.add(currentUrl);
            }
            currentTape++;
        }
        
        for (int i = 0; i < this.intercalationTapes.size(); i++) {
            Url url = this.intercalationTapes.poll();
            System.out.println(url);
        }

        while (currentTape <= numberOfTapes) {
            if (this.intercalationTapes.isEmpty()) {
                break;
            } else {
                Url data = this.intercalationTapes.poll();
                Url nextUrl = tapes[findIndex(tapes, data.getNumberOfTape())].readNextLine();
                if (nextUrl != null) {
                    this.intercalationTapes.add(nextUrl);
                }
                output.writeLine(data);
            }
        }
    }
}