import java.io.IOException;

public class App {

    private static int readInputFile(String inputFileName, int memorySize) throws IOException {
        ReadTape readerInput = new ReadTape(inputFileName);

        int numberOfTapes = 1;
        WriteTape createTapes = new WriteTape(numberOfTapes);
        QuickSortArray array = new QuickSortArray(memorySize);
        for (Url url : readerInput) {
            array.add(url);

            if (array.isFull()) {
                array.order();
                createTapes.write(array.getUrls());
                array = new QuickSortArray(memorySize);
                numberOfTapes++;
                createTapes = new WriteTape(numberOfTapes);
            }
        }

        if (!array.isEmpty() && !array.isFull()) {
            array.order();
            createTapes.write(array.getUrls());
            numberOfTapes++;
        }

        readerInput.close();
        return numberOfTapes - 1;
    }

    public static void main(String[] args) throws Exception {
       String inputFileName = "input.txt";
       String outputFileName = "output.txt";
       int memorySize = 5;

        //String inputFileName = (args[0]);
        //String outputFileName = (args[1]);
        //int memorySize = Integer.parseInt(args[2]);

        int numberOfTapes = readInputFile(inputFileName, memorySize);
        Intercalation changes = new Intercalation(numberOfTapes, memorySize, outputFileName);
        changes.order();
    }
}
