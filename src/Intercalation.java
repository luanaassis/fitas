import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.PriorityQueue;

public class Intercalation {
    private int memorySize;
    private int currentTape;
    private int numberOfTapes;
    private boolean isFirstRound;
    private String outputFileName;

    public Intercalation(int numberOfTapes, int memorySize, String outputFileName) {
        this.currentTape = 1;
        this.isFirstRound = true;
        this.memorySize = memorySize;
        this.numberOfTapes = numberOfTapes;
        this.outputFileName = outputFileName;
    }

    public void order() throws IOException {
        if (currentTape <= numberOfTapes) {
            intercalation();
            order();
        }
    }

    private void intercalation() throws IOException {
        WriteTape output = new WriteTape(outputFileName, isFirstRound);
        PriorityQueue<Url> intercalation = createIntercalationList();
        Map<String, ReadTape> tapes = new HashMap<String, ReadTape>();

        int size = (isFirstRound) ? memorySize : memorySize - 1;
        for (int i = 0; i < size; i++) {
            if (currentTape > numberOfTapes)
                break;

            tapes.put(Integer.toString(currentTape), new ReadTape(currentTape));
            readNextLine(tapes.get(Integer.toString(currentTape)), intercalation);
            currentTape++;
        }

        if (!isFirstRound) {
            switchTapes(output);
            tapes.put(Integer.toString(1), new ReadTape(1));
            readNextLine(tapes.get(Integer.toString(1)), intercalation);
        }

        while (true) {
            if (intercalation.isEmpty()) {
                break;
            } else {
                Url url = intercalation.poll();
                String key = Integer.toString(url.getNumberOfTape());
                readNextLine(tapes.get(key), intercalation);
                output.writeLine(url);
            }
        }

        isFirstRound = false;
    }

    private void readNextLine(ReadTape tape, PriorityQueue<Url> intercalation) {
        Url nextUrl = tape.readNextLine();
        if (nextUrl != null) {
            intercalation.add(nextUrl);
        }
    }

    private void switchTapes(WriteTape output) throws IOException {
        ReadTape outputRead = new ReadTape(outputFileName);

        WriteTape firstTape = new WriteTape(1);
        firstTape.clearTape();

        for (Url url : outputRead) {
            firstTape.writeLine(url);
        }

        outputRead.close();
        output.clearTape();
    }

    private PriorityQueue<Url> createIntercalationList() {
        return new PriorityQueue<>((a, b) -> {
            if (a.getNumberOfViews() > b.getNumberOfViews()) {
                return -1;
            }

            if (a.getNumberOfViews() == b.getNumberOfViews()) {
                if (a.getUrl().compareTo(b.getUrl()) < 0) {
                    return -1;
                }
            }

            return +1;
        });
    }
}
