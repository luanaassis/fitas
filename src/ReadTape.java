import java.io.FileReader;
import java.util.Iterator;
import java.io.IOException;
import java.io.BufferedReader;

public class ReadTape implements AutoCloseable, Iterable<Url> {
    private int numberOfTape;
    private BufferedReader reader;
    private Iterator<Url> interator;

    public ReadTape(String fileName) throws IOException {
        this.numberOfTape = 0;
        this.interator = iterator();
        this.reader = new BufferedReader(new FileReader(fileName));
    }

    public ReadTape(int numberOfTape) throws IOException {
        this.interator = iterator();
        this.numberOfTape = numberOfTape;
        this.reader = new BufferedReader(new FileReader("fita-" + numberOfTape + ".txt"));
    }

    public int getNumberOfTape() {
        return this.numberOfTape;
    }

    public Url readNextLine() {
        Url data = interator.next();
        if (data != null) {
            data.setNumberOfTape(numberOfTape);
        }
        return data;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    @Override
    public Iterator<Url> iterator() {
        return new Iterator<Url>() {
            private String nextLine;

            @Override
            public boolean hasNext() {
                if (nextLine == null) {
                    try {
                        nextLine = reader.readLine();
                        if (nextLine != null && nextLine.equals("")) {
                            nextLine = null;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return nextLine != null;
            }

            @Override
            public Url next() {
                if (!hasNext())
                    return null;
                Url url = Url.fromStringRep(nextLine);
                nextLine = null;
                return url;
            }
        };
    }
}
