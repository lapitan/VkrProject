package lapitan.vkr.DiscordBotService.repository;

import lapitan.vkr.DiscordBotService.entity.Channel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelRepository extends CrudRepository<Channel, Long> {
}
