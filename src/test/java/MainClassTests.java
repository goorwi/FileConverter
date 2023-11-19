import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.Main;

import java.io.File;

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
        try {
            val args = new String[]{inputPath};
            Main.main(args);
        } catch (Exception ex) {
            assertEquals("Произошла ошибка: Неверное количество аргументов! Для автоматического режима введите 2 аргумента, для ручного режима не указывайте аргументы.", ex.getMessage());
        }
    }
}
