package rest_assured.utils;
import org.json.JSONObject;
import org.json.XML;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConvertUtils {
    private static String filePath = "src/test/resources/testdata/";

    public static String convertXmlFileToJsonString(String fileName, String key) {
        try {
            String xmlContent = new String(Files.readAllBytes(Paths.get(filePath + fileName)));

            //Convert XML to JSON
            JSONObject jsonObject = XML.toJSONObject(xmlContent);

            //Extract the inner object (inside the key)
            JSONObject jsonChild = jsonObject.getJSONObject(key);

            //return value
            return jsonChild.toString();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
