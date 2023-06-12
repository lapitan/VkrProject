package lapitan.vkr.DiscordBotService.messageConsumer;

import lapitan.vkr.DiscordBotService.commandProcessor.CommandProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Slf4j
public class RabbitMQConsumer {

    CommandProcessor commandProcessor;

    public RabbitMQConsumer(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @RabbitListener(queues = "DiscordBotQueue")
    public void processMyQueue(String message) {
        log.info("Received from DiscordBotQueue: "+ new String(message.getBytes()));

        commandProcessor.resolveCommand(message);

    }
}

