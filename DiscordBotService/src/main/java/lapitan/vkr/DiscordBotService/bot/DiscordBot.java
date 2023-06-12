package lapitan.vkr.DiscordBotService.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

@Component
public class DiscordBot {

    JDA jda;

    public DiscordBot(JDA jda) {
        this.jda = jda;
    }


    //{command:sendVerificationMessage, username:lapitaniy1#1711, url: http://localhost:8099/discordLessons/auth/confirm/123}
    public void sendVerificationMessage(String username, String url) {
        String[] strings = username.split("#");

        User user = jda.getUserByTag(strings[0], strings[1]);

        if (user == null) {
            return;
        }

        user.openPrivateChannel()
                .flatMap(privateChannel ->
                        privateChannel.sendMessage("Привет!\nЧтобы подтвердить свой аккаунт перейди по ссылке:\n"
                                + url))
                .queue();
    }

    public void createSubjectCategory(String name) {
        List<Guild> guilds = jda.getGuilds();
        Guild guild = guilds.get(0);

        if (guild.getCategoriesByName(name, true).size() > 0) return;

        Category category = guild.createCategory(name)
                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .complete();

        category.createTextChannel("лекции " + name)
                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .complete();

        category.createVoiceChannel("лекции " + name)
                .addPermissionOverride(guild.getPublicRole(), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .complete();
    }

    public void addRole(String username, String roleName) {
        Guild guild = jda.getGuilds().get(0);
        Role role = guild.getRolesByName(roleName, true).get(0);

        String[] strings = username.split("#");
        Member member = guild.getMemberByTag(strings[0], strings[1]);

        if (member == null) return;
        if (member.getRoles().contains(role)) return;

        guild.addRoleToMember(member, role).queue();
    }

    public void addUserToCategory(String username, String categoryName) {
        Guild guild = jda.getGuilds().get(0);

        String[] strings = username.split("#");
        Member member = guild.getMemberByTag(strings[0], strings[1]);

        if (member == null) return;

        /*List<VoiceChannel> channels;

        do {
            guild = jda.getGuilds().get(0);
            channels = guild.getVoiceChannelsByName("лекции " + categoryName, true);
        } while (channels.isEmpty());*/

        VoiceChannel channel = guild.getVoiceChannelsByName("лекции " + categoryName, true).get(0);

        channel.upsertPermissionOverride(member).
                setPermissions(EnumSet.of(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT, Permission.VOICE_SPEAK), null).
                complete();

        String textChannelName = categoryName.toLowerCase().replaceAll(" ", "-");

       /* List<TextChannel> textChannels;

        do {
            guild = jda.getGuilds().get(0);
            textChannels = guild.getTextChannelsByName("лекции-" + textChannelName, true);
        } while (textChannels.isEmpty());*/

        TextChannel textChannel = guild.getTextChannelsByName("лекции-" + textChannelName, true).get(0);

        textChannel.upsertPermissionOverride(member).
                setPermissions(EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND), null).
                complete();

/*        Objects.requireNonNull(guild.getVoiceChannelsByName("лекции " + categoryName, true).get(0).
                        getPermissionOverride(member)).getManager().
                grant(EnumSet.of(Permission.VIEW_CHANNEL, Permission.VOICE_CONNECT, Permission.VOICE_SPEAK)).queue();

        Objects.requireNonNull(guild.getTextChannelsByName("лекции " + categoryName, true).get(0).
                        getPermissionOverride(member)).getManager().
                grant(EnumSet.of(Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND)).queue();*/
    }

    public void test() {

        User user = jda.getUserByTag("lapitaniy1", "1711");

        List<Guild> guilds = jda.getGuilds();

        Guild guild = guilds.get(0);

        List<User> users = jda.getUsers();

//        user.openPrivateChannel()
//                .flatMap(privateChannel ->
//                        privateChannel.sendMessage("ZDAROVA!"))
//                .queue();

        guild.createCategory("Abobi").queue();

        List<Category> categories = guild.getCategoriesByName("kavovi", true);

        Category category = categories.get(0);

        List<Role> roles = guild.getRolesByName("everyone", true);

        System.out.println("abobs");

//        List<TextChannel> abobi = jda.getTextChannelsByName("абобы", true);
//        List<TextChannel> abobiPrivate = jda.getTextChannelsByName("абобыприват", true);
//        List<TextChannel> kavo = jda.getTextChannelsByName("kavo", true);
//        List<TextChannel> kavoPrivate = jda.getTextChannelsByName("kavoprivate", true);
//
//        abobi.forEach(textChannel -> {
//            textChannel.sendMessage("абобы").queue();
//            textChannel.sendMessage("abobi").queue();
//        });
//
//        abobiPrivate.forEach(textChannel -> {
//            textChannel.sendMessage("абобы").queue();
//            textChannel.sendMessage("abobi").queue();
//        });
//
//        kavoPrivate.forEach(textChannel -> {
//            textChannel.sendMessage("абобы").queue();
//            textChannel.sendMessage("abobi").queue();
//        });
//
//        kavo.forEach(textChannel -> {
//            textChannel.sendMessage("абобы").queue();
//            textChannel.sendMessage("abobi").queue();
//        });

    }
}
