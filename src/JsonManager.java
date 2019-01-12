import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.security.util.Length;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class JsonManager {
    private String region;
    private String server;
    private String characterName;
    private String localization;
    private String apiUrl;

    public JsonManager(String reg, String realm, String charName, String locale) {
        region = reg;
        server = realm;
        characterName = charName;
        localization = locale;

        if (server.contains(" ")) {
            server = server.replaceAll("\\s", "%20");
        }
        apiUrl = "https://" + region + ".api.blizzard.com/wow/character/" + server +
                "/" + characterName + "?fields=mounts&locale=" +
                localization + "&access_token=US28Pfh3xIsTDvJsGzG75UR8rvP98F78Z5";
    }

    public JSONArray getJsonCollectedMounts() {
        try {
            URL url = new URL(apiUrl);
            StringBuffer response = getResponse(url);

            JSONObject myResponse = new JSONObject(response.toString());
            JSONObject mounts_obj = new JSONObject((myResponse.getJSONObject("mounts").toString()));
            JSONArray mounts_collected = mounts_obj.getJSONArray("collected");
            List <Mount> collectedMounts = mountList(mounts_collected);

            return mounts_collected;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public JSONArray getJsonAllMounts() {
        try {
          URL url = new URL("https://us.api.blizzard.com/wow/mount/?locale=en_US&access_token=US28Pfh3xIsTDvJsGzG75UR8rvP98F78Z5");
            StringBuffer response = getResponse(url);
            JSONObject myResponse = new JSONObject(response.toString());
            JSONArray allMounts = myResponse.getJSONArray("mounts");
            System.out.println(allMounts);
            List<Mount> listmounts=mountList(allMounts);
            return allMounts;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public List<Mount> mountList(JSONArray mounts) {
        List<Mount> mountsList = new LinkedList<Mount>();

        String nameM;
        String iconM;
        for (int i = 0; i < mounts.length(); i++) {
            nameM = mounts.getJSONObject(i).getString("name");
            try {
                iconM = mounts.getJSONObject(i).getString("icon");
            }
            catch (JSONException e){
                iconM="noIcon";
            }
            Mount mount_obj = new Mount(nameM, iconM);
            mountsList.add(mount_obj);

            System.out.println("mount name: " + mount_obj.mountName);
            System.out.println("mount icon url: " + mount_obj.mountIconUrl + "\n");

        }
        return mountsList;
    }

    public StringBuffer getResponse(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
                System.out.println(response);
            }
            in.close();

            return response;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
