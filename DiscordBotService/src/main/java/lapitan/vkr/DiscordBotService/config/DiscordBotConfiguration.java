package lapitan.vkr.DiscordBotService.config;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordBotConfiguration {

    @Value("${discord.bot.token}")
    private String token;

    @Bean
    public JDA api(){
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);

        jdaBuilder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_PRESENCES, GatewayIntent.DIRECT_MESSAGES);
        jdaBuilder.setMemberCachePolicy(MemberCachePolicy.ALL);

        return jdaBuilder.build();
    }

}
