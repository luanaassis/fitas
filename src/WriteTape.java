import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class WriteTape {
    private File tape;

    public WriteTape(int numberOfTape) {
        this.tape = new File("fita-" + numberOfTape + ".txt");
    }

    public WriteTape(String fileName, boolean clearFile) {
        this.tape = new File(fileName);

        if (clearFile) 
            clearTape();
    }

    public void writeLine(Url url, boolean breakLine) {
        try {
            FileWriter fw = new FileWriter(this.tape, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String text = url.toString();
            if (breakLine) {
                text = System.lineSeparator() + text;
            }

            bw.write(text);

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void clearTape() {
        try {
            FileWriter fw = new FileWriter(this.tape, false);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("");

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void write(Url[] array) {
        try {
            FileWriter fw = new FileWriter(this.tape, false);
            BufferedWriter bw = new BufferedWriter(fw);

            String text = "";
            for (Url url : array) {
                text += url.getEntity() + System.lineSeparator();
            }
            text = text.substring(0, text.length() - 1);
            bw.write(text);

            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
