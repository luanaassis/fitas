import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;
import java.io.IOException;
import java.io.BufferedReader;

public class ReadTape implements AutoCloseable, Iterable<Url> {
    private BufferedReader reader;
    private String fileName;

    public ReadTape(String fileName) throws IOException {
        this.fileName = fileName;
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String getFileName() {
        return this.fileName;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public Url readNextLine() {
        Iterator<Url> it = iterator();
        Url data = it.next();
        if (data != null) {
            int number = Integer.parseInt(fileName.split("-")[1].split(".txt")[0]);
            data.setNumberOfTape(number);
        }
        return data;
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
