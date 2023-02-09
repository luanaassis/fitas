import java.io.IOException;
import java.util.PriorityQueue;

public class Intercalation {
    private int memorySize;
    private int numberOfTapes;
    private String outputFileName;
    private PriorityQueue<Url> intercalationTapes;
    private int currentTape = 1;

    public Intercalation(int numberOfTapes, int memorySize, String outputFileName) {
        this.memorySize = memorySize;
        this.numberOfTapes = numberOfTapes;
        this.outputFileName = outputFileName;
        this.intercalationTapes = new PriorityQueue<>((a, b) -> {
            if (a.getNumberOfViews() < b.getNumberOfViews()) {
                return +1;
            }

            if (a.getUrl().compareTo(b.getUrl()) < 0) {
                return +1;
            } else {
                return -1;
            }
        });
    }

    private int findIndex(ReadTape[] tapes, int numberOfTape) {
        for (int i = 0; i < tapes.length; i++) {
            if (tapes[i].getFileName().contains(numberOfTape + "")) {
                return i;
            }
        }
        return -1;
    }

    private ReadTape[] createTapes() throws IOException {
        int tapesSize = (this.numberOfTapes <= this.memorySize) ? this.numberOfTapes : this.memorySize;
        ReadTape[] tapes = new ReadTape[tapesSize];

        for (int i = 0; i < tapesSize; i++) {
            if (this.currentTape <= this.numberOfTapes) {
                tapes[i] = new ReadTape("fita-" + this.currentTape + ".txt");
                Url url = tapes[i].readNextLine();
                if (url != null) {
                    this.intercalationTapes.add(url);
                }
                this.currentTape++;
            } else {
                tapes[i] = null;
                continue;
            }
        }

        return tapes;
    }

    public void order() throws IOException {
        WriteTape output = new WriteTape(this.outputFileName);
        ReadTape[] tapes = createTapes();
        while (true) {
            if (this.intercalationTapes.isEmpty()) {
                if (this.currentTape > this.numberOfTapes) {
                    break;
                } else {
                    output.addSection();
                    tapes = createTapes();
                }
            } else {
                Url data = this.intercalationTapes.poll();
                int index = findIndex(tapes, data.getNumberOfTape());
                if (index >= 0) {
                    Url nextUrl = tapes[index].readNextLine();
                    if (nextUrl != null) {
                        this.intercalationTapes.add(nextUrl);
                    }
                }
                output.writeLine(data);
            }
        }
    }
}
