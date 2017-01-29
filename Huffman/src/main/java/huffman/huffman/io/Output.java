package huffman.huffman.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Raine Rantanen
 */
public class Output {

    OutputStream out;

    public void write(String inputString, File outputFile) throws FileNotFoundException, IOException {
        out = new BufferedOutputStream(new FileOutputStream(outputFile));
        out.write(inputString.getBytes());
        out.flush();
        out.close();
    }
}
