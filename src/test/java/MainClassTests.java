import lombok.val;

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
        Main.main(new String[]{inputPath, outputPath});

        val outputFile = new File(outputPath);
        assertTrue(outputFile.exists());
        outputFile.delete();
    }


    @Test
    void testWithIncorrectArgs() {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        Main.main(new String[]{inputPath});

        assertEquals("Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.\r\n", errContent.toString());
    }
}
