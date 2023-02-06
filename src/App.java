public class App {
    public static void main(String[] args) throws Exception {
        String inputFileName = "input.txt";
        String outputFileName = "output.txt";
        int memorySize = 2;

        FileControl controle = new FileControl(inputFileName, outputFileName, memorySize);

        controle.readInputFile();
    }
}
