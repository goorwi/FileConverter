import lombok.val;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.vyatsu.fileconverter.service.converter.JsonToXmlConverter;
import ru.vyatsu.fileconverter.service.converter.XmlToJsonConverter;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConvertersTests {
    private final String xmlFilePath = "src\\test\\resources\\data.xml";
    private final String jsonXmlPath = "src\\test\\resources\\data.json";

    @Test
    void testConvertXmlToJson(@TempDir Path tempDir) {
        try {
            String tempFile = tempDir.resolve("testData.json").toString();
            XmlToJsonConverter converter = new XmlToJsonConverter();
            converter.convert(xmlFilePath, tempFile);

            assertTrue(FileUtils.contentEquals(new File(jsonXmlPath), new File(tempFile)));

            new File(tempFile).delete();
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }

    @Test
    void testConvertJsonToXml(@TempDir Path tempDir) {
        try {
            String tempFile = tempDir.resolve("testData.xml").toString();
            JsonToXmlConverter converter = new JsonToXmlConverter();
            converter.convert(jsonXmlPath, tempFile);

            assertTrue(FileUtils.contentEquals(new File(xmlFilePath), new File(tempFile)));

            val outputFile = new File(tempFile);
            outputFile.delete();
        } catch (Exception ex) {
            Assertions.fail(ex.getMessage());
        }
    }
}
