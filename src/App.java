public class App {
    public static void main(String[] args) throws Exception {
        String input = (args[0]);
        String output = (args[1]);
        int memory = Integer.parseInt(args[2]);

        if (memory >= 2) {
            Intercalation control = new Intercalation(input, output, memory);
            int numberOfTapes = control.readInputFile();
            control.order(numberOfTapes);
        } else {
            System.out.println("Mínimo de memória deve ser 2!");
        }
    }
}
