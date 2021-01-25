package saver;

import java.io.IOException;
import java.io.PrintWriter;

@SaveTo()
public class SaveText {
    private String text = "Hello World!";

    @Saver
    public void save(String path) {
        if (path == null) {
            throw new IllegalArgumentException("File doesn't exist.");
        }
        try (PrintWriter writer = new PrintWriter(path)) {
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
