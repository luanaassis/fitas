import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class WriteTape {
    private File tape;

    public WriteTape(int numberOfTape) {
        this.tape = new File("fita-" + numberOfTape + ".txt");
    }

    public WriteTape(String fileName) {
        this.tape = new File(fileName);
    }

    public void writeLine(Url url) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.tape, true));

            String text = "\n" + url.toString();

            bw.write(text);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addSection() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.tape, true));
            bw.write("\n");
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void write(Url[] array) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.tape, false));

            String text = "";
            for (Url url : array) {
                text += url.getEntity() + "\n";
            }
            text = text.substring(0, text.length() - 1);

            bw.write(text);
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
