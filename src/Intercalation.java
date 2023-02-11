import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.util.PriorityQueue;

public class Intercalation {
    private int memorySize;
    private int currentTape;
    private String inputFileName;
    private boolean isFirstRound;
    private String outputFileName;

    public Intercalation(String inputFileName, String outputFileName, int memorySize) {
        this.currentTape = 1;
        this.isFirstRound = true;
        this.memorySize = memorySize;
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public int readInputFile() throws IOException {
        ReadTape readerInput = new ReadTape(inputFileName);
        int numberOfTapes = 1;
        WriteTape createTapes = new WriteTape(numberOfTapes);
        QuickSortArray array = new QuickSortArray(memorySize);
        for (Url url : readerInput) {
            array.add(url);

            if (array.isFull()) {
                createTapes.write(array.getUrls());
                array = new QuickSortArray(memorySize);
                numberOfTapes++;
                createTapes = new WriteTape(numberOfTapes);
            }
        }

        if (!array.isEmpty() && !array.isFull()) {
            createTapes.write(array.getUrls());
            numberOfTapes++;
        }

        readerInput.close();
        return numberOfTapes - 1;
    }

    public void order(int numberOfTapes) throws IOException {
        if (currentTape <= numberOfTapes) {
            intercalation(numberOfTapes);
            order(numberOfTapes);
        }
    }

    private void intercalation(int numberOfTapes) throws IOException {
        WriteTape output = new WriteTape(outputFileName, isFirstRound);
        PriorityQueue<Url> intercalationList = createIntercalationList();
        Map<String, ReadTape> tapes = new HashMap<String, ReadTape>();

        int size = (isFirstRound) ? memorySize : memorySize - 1;
        for (int i = 0; i < size; i++) {
            if (currentTape > numberOfTapes)
                break;
            tapes.put(Integer.toString(currentTape), new ReadTape(currentTape));
            getNextUrlIntercalation(tapes.get(Integer.toString(currentTape)), intercalationList);
            currentTape++;
        }

        if (!isFirstRound) {
            switchTapes(output);
            tapes.put(Integer.toString(1), new ReadTape(1));
            getNextUrlIntercalation(tapes.get(Integer.toString(1)), intercalationList);
        }

        boolean breakLine = false;
        while (true) {
            if (intercalationList.isEmpty()) {
                break;
            } else {
                Url url = intercalationList.poll();
                String key = Integer.toString(url.getNumberOfTape());
                getNextUrlIntercalation(tapes.get(key), intercalationList);
                output.writeLine(url, breakLine);
                breakLine = true;
            }
        }

        isFirstRound = false;
    }

    private void getNextUrlIntercalation(ReadTape tape, PriorityQueue<Url> intercalation) {
        Url nextUrl = tape.readNextLine();
        if (nextUrl != null) {
            intercalation.add(nextUrl);
        }
    }

    private void switchTapes(WriteTape output) throws IOException {
        ReadTape outputRead = new ReadTape(outputFileName);

        WriteTape firstTape = new WriteTape(1);
        firstTape.clearTape();

        boolean breakLine = false;
        for (Url url : outputRead) {
            firstTape.writeLine(url, breakLine);
            breakLine = true;
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
