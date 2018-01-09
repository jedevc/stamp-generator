import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.Scanner;

import renderer.Renderer;
import renderer.SVGRenderer;

public class App {
    public static void main(String[] args) {
        App app = new App();

        try {
            app.run();
        } catch (Exception e) {
            System.err.format("Error: %s\n", e.getMessage());
        }
    }

    public void run() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Generating... ");
        generate();
        while (true) {
            String line = scan.nextLine();
            if (line.length() > 0) break;

            System.out.print("Generating... ");
            generate();
        }
    }

    public void generate() throws FileNotFoundException {
        Identicon identicon = new Identicon(4);
        identicon.generate();

        PrintWriter writer = new PrintWriter("output.svg");

        Renderer renderer = new SVGRenderer(400, writer);
        identicon.render(renderer);

        writer.close();
    }
}
