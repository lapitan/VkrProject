package lapitan.vkr.DiscordBotService.commandProcessor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lapitan.vkr.DiscordBotService.bot.DiscordBot;
import lapitan.vkr.DiscordBotService.service.DiscordDatabaseService;
import org.springframework.stereotype.Component;

@Component
public class CommandProcessor {

    DiscordBot discordBot;
    DiscordDatabaseService discordDatabaseService;

    public CommandProcessor(DiscordBot discordBot, DiscordDatabaseService discordDatabaseService) {
        this.discordBot = discordBot;
        this.discordDatabaseService = discordDatabaseService;
    }

    public void resolveCommand(String message) {

        JsonObject object = JsonParser.parseString(message).getAsJsonObject();

        String command = object.get("command").toString().replace("\"", "");

        switch (command) {
            case "sendVerificationMessage" -> {
                String username = object.get("username").toString().replace("\"", "");
                String url = object.get("url").toString().replace("\"", "");
                discordBot.sendVerificationMessage(username, url);
            }
            case "confirm" -> {
                String username = object.get("username").toString().replace("\"", "");

                discordDatabaseService.createUser(username);
                discordBot.addRole(username, "STUDENT");
            }
            case "createSubjectCategory" -> {
                String subjectName = object.get("name").toString().replace("\"", "");
                discordBot.createSubjectCategory(subjectName);
                discordDatabaseService.createCategory(subjectName);
            }
            case "addRole" -> {
                String username = object.get("username").toString().replace("\"", "");
                String role = object.get("role").toString().replace("\"", "");
                discordDatabaseService.changeUserRole(username, role);
                discordBot.addRole(username, role);
            }
            case "addUserToCategory" ->{
                String username = object.get("username").toString().replace("\"", "");
                String categoryName = object.get("categoryName").toString().replace("\"", "");

                discordBot.addUserToCategory(username, categoryName);
            }
            case "test" -> discordBot.test();
        }

//        String username = object.get("username").toString();
//
//        username = username.replaceAll("\"","");
//
//        System.out.println(username);
//
//        test();
    }

    private void test() {
        discordBot.test();
    }

}
