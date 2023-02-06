import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileControl {
    private int memorySize;
    private int numberOfFiles;
    private FileReader inputFile;

    public FileControl(String inputFileName, String outputFileName, int memorySize) throws FileNotFoundException {
        this.inputFile = new FileReader(inputFileName);
        this.memorySize = memorySize;
        this.numberOfFiles = 1;
    }

    public void readInputFile() {
        Scanner keymap = new Scanner(this.inputFile);
        int controlRead = 0;
        WriteTape createTapes = new WriteTape(numberOfFiles++);
        Url[] array = new Url[this.memorySize];

        while (keymap.hasNext()) {
            array[controlRead] = new Url(keymap.nextLine(), numberOfFiles);
            controlRead++;

            if (controlRead == array.length) {
                new QuickSort().order(array);
                createTapes.write(array);
                array = new Url[this.memorySize];
                createTapes = new WriteTape(numberOfFiles++);
                controlRead = 0;
            }
        }
        keymap.close();
    }
}
