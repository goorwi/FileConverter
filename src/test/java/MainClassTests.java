import lombok.val;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.fileconverter.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainClassTests {
    private final String inputPath = "src\\test\\resources\\data.xml";
    private final String outputPath = "src\\test\\resources\\testData.json";

    @Test
    void testWithCorrectArgs() {
        try {
            val args = new String[]{inputPath, outputPath};
            Main.main(args);

            val outputFile = new File(outputPath);
            assertTrue(outputFile.exists());
            outputFile.delete();
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }


    @Test
    void testWithIncorrectArgs() {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        try {
            val args = new String[]{inputPath};
            Main.main(args);

            assertEquals("Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.\r\n", errContent.toString());
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }
}
