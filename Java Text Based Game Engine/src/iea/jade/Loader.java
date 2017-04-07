/** The loader for the TBGE
 * @author Jade Krabbe
 * @version a0.0.1
 */
package iea.jade;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.json.JSONObject;

public class Loader {
    private static File workingDir = new File(System.getProperty("user.dir"));
    
    private static File dataDir = new File(workingDir + "/data");
    
    private static File gameJson = new File(dataDir + "/game.json");
    private static JSONObject game;
    
    
    public static void preInit() {
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }
        if (!gameJson.exists()) {
            System.out.println("There is no game.");
        } else {
            try {
                URI uri = gameJson.toURI();
                List<String> lines = Files.readAllLines(Paths.get(uri), Charset.defaultCharset());
                String t = "";
                for (String line : lines) {
                    t = t + line;
                }
                game = new JSONObject(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            System.out.printf("Loading %s%n", game.getString("name"));
        }
    }
}
