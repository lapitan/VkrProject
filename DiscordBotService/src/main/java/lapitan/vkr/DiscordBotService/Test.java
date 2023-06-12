package lapitan.vkr.DiscordBotService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {

    public static void main(String[] args) {
        String json = "{command:sendVerificationMessage, username:\"lapitaniy1#1711\", url: \"http://localhost:8099/discordLessons/auth/confirm/123\"}";

        JsonObject object = JsonParser.parseString(json).getAsJsonObject();

        String username = object.get("username").toString();

        username = username.replaceAll("\"","");

        String url = object.get("url").toString();

        url = url.replaceAll("\"","");

        System.out.println(username + " " + url);



    }

}
