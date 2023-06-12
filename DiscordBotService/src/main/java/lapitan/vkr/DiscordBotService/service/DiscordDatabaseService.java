package lapitan.vkr.DiscordBotService.service;

import lapitan.vkr.DiscordBotService.entity.Category;
import lapitan.vkr.DiscordBotService.entity.Person;
import lapitan.vkr.DiscordBotService.repository.CategoryRepository;
import lapitan.vkr.DiscordBotService.repository.ChannelRepository;
import lapitan.vkr.DiscordBotService.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiscordDatabaseService {

    CategoryRepository categoryRepository;
    ChannelRepository channelRepository;
    PersonRepository personRepository;

    public DiscordDatabaseService(CategoryRepository categoryRepository, ChannelRepository channelRepository, PersonRepository personRepository) {
        this.categoryRepository = categoryRepository;
        this.channelRepository = channelRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);

        if (categoryRepository.findCategoryByName(name).isPresent()) return;

        categoryRepository.save(category);
    }

    @Transactional
    public void createUser(String name) {
        Person person = new Person();

        if (personRepository.findPersonByName(name).isPresent()) return;

        person.setName(name);
        person.setRole("STUDENT");

        personRepository.save(person);
    }

    @Transactional
    public void changeUserRole(String name, String role) {
        if (personRepository.findPersonByName(name).isEmpty()) return;
        Person person = personRepository.findPersonByName(name).orElseThrow(() ->
                new RuntimeException("Something Went wrong.\ncant find person."));
        person.setRole(role);

        personRepository.save(person);
    }
}
