import org.json.JSONArray;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello World!");

       /* FileManager file1 = new FileManager();
        file1.readFile("/home/yrsa/IdeaProjects/MountApp/src/test.txt");
*/
        JsonManager json1 = new JsonManager("eu", "Burning Legion", "Vyrsa", "en_GB");

       // json1.getJsonCollectedMounts();
        json1.getJsonAllMounts();

    }


}
