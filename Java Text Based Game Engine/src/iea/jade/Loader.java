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
import java.util.Scanner;
import org.json.JSONException;
import org.json.JSONObject;

public class Loader {
    private static File workingDir = new File(System.getProperty("user.dir"));
    
    private static File dataDir = new File(workingDir + "/data");
    
    private static File gameJson = new File(dataDir + "/game.json");
    private static File gameDataDir = new File(dataDir + "/game");
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
                if (e instanceof JSONException) {
                    
                    System.out.println("The Game file is corrupt or not valid! Please check the JSON syntax or contact the developer.");
                }
            }
            if (game != null) {
                System.out.printf("Loading %s, version %s%n", game.getString("name"), game.getString("version"));
            }
        }
        init();
    }
    
    public static void init() {
        if (game == null) {
            Scanner s = new Scanner(System.in);
            System.out.print("There is no game loaded. Would you like to select a custom file? (y/n)");
            String string = s.next();
            boolean b = string.equalsIgnoreCase("y");
            if (b) {
                System.out.print("Please type the name of the json file for the game:");
                gameJson = new File(dataDir + "/" + s.next());
                preInit();
            } else {
                System.out.println("No game shall be loaded. Closing...");
            }
        }
    }
}

