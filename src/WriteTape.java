import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class WriteTape {
    private File tape;

    public WriteTape(int numberOfTape) {
        this.tape = new File("fita-" + numberOfTape + ".txt");
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
