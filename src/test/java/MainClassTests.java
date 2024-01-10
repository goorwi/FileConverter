import lombok.val;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainClassTests {
    private final String inputPath = "src\\test\\resources\\data.xml";

    @Test
    void testWithCorrectArgs(@TempDir Path tempDir) {
        val tempFile = tempDir.resolve("testData.json").toString();
        Main.main(new String[]{inputPath, tempFile});

        assertTrue(new File(tempFile).exists());
    }

    @Test
    void testWithIncorrectArgs() {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        Main.main(new String[]{inputPath});

        assertEquals("Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.\r\n", errContent.toString());
    }
}
