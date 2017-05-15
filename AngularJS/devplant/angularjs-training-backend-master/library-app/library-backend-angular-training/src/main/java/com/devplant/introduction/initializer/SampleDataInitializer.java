package com.devplant.introduction.initializer;

import com.devplant.introduction.domain.*;
import com.devplant.introduction.exception.BookAlreadyReservedByUserException;
import com.devplant.introduction.exception.BookNotAvailableForReservationException;
import com.devplant.introduction.repository.jpa.*;
import com.devplant.introduction.service.BookReservationService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@Profile("sample-data")
public class SampleDataInitializer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SampleDataInitializer.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookStockRepository bookStockRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BookReservationService bookReservationService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	protected void addSampleData() {

		User user1 = createTestUser("Coba", "Makaio");
		User user2 = createTestUser("Paula", "Nisha");
		User user3 = createTestUser("Simona", "Theunis");
		User user4 = createTestUser("Lysandra", "Thomas");
		User user5 = createTestUser("Adele", "Neun");
		User user6 = createTestUser("Josse", "Quinten");
		User user7 = createTestUser("Niketa", "Anso");

		Author tolkien = new Author();
		tolkien.setName("J. R. R. Tolkien");


		Book lotr1 = new Book();
		lotr1.setName("Lord of the Rings - The Fellowship of the Ring");
		lotr1.setYear(1954);
		lotr1.setAuthor(tolkien);
		lotr1.setSynopsis("A meek Hobbit from the Shire and eight companions set out on a journey to destroy"
						  + " the powerful One Ring and save Middle Earth from the Dark Lord Sauron.");

		Book lotr2 = new Book();
		lotr2.setName("Lord of the Rings - The Two Towers");
		lotr2.setYear(1954);
		lotr2.setAuthor(tolkien);
		lotr2.setSynopsis("While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, "
						  + "the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.");

		Book lotr3 = new Book();
		lotr3.setName("Lord of the Rings - Return of The King");
		lotr3.setYear(1955);
		lotr3.setAuthor(tolkien);
		lotr3.setSynopsis("A meek Hobbit from the Shire and eight companions set out on a journey to destroy"
						  + " the powerful One Ring and save Middle Earth from the Dark Lord Sauron.");

		Author george = new Author();
		george.setName("George R. R. Martin");

		Book got1 = new Book();
		got1.setName("A Game of Thrones");
		got1.setYear(1996);
		got1.setAuthor(george);
		got1.setSynopsis(
				"Summers span decades. Winter can last a lifetime. And the struggle for the Iron Throne has begun."
				+ "As Warden of the north, Lord Eddard Stark counts it a curse when King Robert bestows on "
				+ "him the office of the Hand. His honour weighs him down at court where a true man does what he will,"
				+ " not what he must … and a dead enemy is a thing of beauty."
				+ "The old gods have no power in the south, Stark’s family is split and there is treachery at court"
				+ ". Worse, the vengeance-mad heir of the deposed Dragon King has grown to maturity"
				+ " in exile in the Free Cities. He claims the Iron Throne.");

		Book got2 = new Book();
		got2.setName("A Clash of Kings");
		got2.setYear(1998);
		got2.setAuthor(george);
		got2.setSynopsis("Time is out of joint. The summer of peace and plenty, ten years long, is drawing to a close, "
						 + "and the harsh, chill winter approaches like an angry beast. "
						 + "Two great leaders—Lord Eddard Stark and Robert Baratheon—who held sway over an age of"
						 + " enforced peace are dead...victims of royal treachery. Now, from the ancient citadel "
						 + "of Dragonstone to the forbidding shores of Winterfell, chaos reigns, as pretenders "
						 + "to the Iron Throne of the Seven Kingdoms prepare to stake their claims through tempest,"
						 + " turmoil, and war. ");

		Book got3 = new Book();
		got3.setName("A Storm of Swords");
		got3.setYear(2000);
		got3.setAuthor(george);
		got3.setSynopsis("Of the five contenders for power, one is dead, another in disfavor,"
						 + " and still the wars rage as alliances are made and broken. Joffrey "
						 + "sits on the Iron Throne, the uneasy ruler of the Seven Kingdoms."
						 + " His most bitter rival, Lord Stannis, stands defeated and disgraced, "
						 + "victim of the sorceress who holds him in her thrall. Young Robb still rules the"
						 + " North from the fortress of Riverrun. Meanwhile, making her way across a "
						 + "blood-drenched continent is the exiled queen, Daenerys, mistress of the only"
						 + " three dragons still left in the world. And as opposing forces manoeuver for the final"
						 + " showdown, an army of barbaric wildlings arrives from the outermost limits of civilization,"
						 + " accompanied by a horde of mythical Others—a supernatural army of the living dead whose "
						 + "animated corpses are unstoppable. As the future of the land hangs in the balance,"
						 + " no one will rest until the Seven Kingdoms have exploded in a veritable storm of swords... ");

		Book got4 = new Book();
		got4.setName("A Feast for Crows");
		got4.setYear(2005);
		got4.setAuthor(george);
		got4.setSynopsis("After centuries of bitter strife, the seven powers dividing the land have beaten one another "
						 + "into an uneasy truce. But it's not long before the survivors, outlaws, renegades, and "
						 + "carrion eaters of the Seven Kingdoms gather. Now, as the human crows assemble over a"
						 + " banquet of ashes, daring new plots and dangerous new alliances are formed while surprising"
						 + " faces—some familiar, others only just appearing—emerge from an ominous twilight of past"
						 + "struggles and chaos to take up the challenges of the terrible times ahead. Nobles and "
						 + "commoners, soldiers and sorcerers, assassins and sages, are coming together to stake"
						 + "their fortunes...and their lives. For at a feast for crows, many are the guests—but"
						 + " only a few are the survivors.");

		Book got5 = new Book();
		got5.setName("A Dance with Dragons");
		got5.setYear(2011);
		got5.setAuthor(george);
		got5.setSynopsis("In the aftermath of a colossal battle, the future of the Seven Kingdoms hangs in"
						 + " the balance—beset by newly emerging threats from every direction. In the east, "
						 + "Daenerys Targaryen, the last scion of House Targaryen, rules with her three dragons "
						 + "as queen of a city built on dust and death. But Daenerys has thousands of enemies, "
						 + "and many have set out to find her. As they gather, one young man embarks upon his "
						 + "own quest for the queen, with an entirely different goal in mind.");

		List<Book> books = Lists.newArrayList(lotr1, lotr2, lotr3, got1, got2, got3, got4, got5);

		books.forEach(book -> book
				.setStocks(IntStream.range(0, new Random().nextInt(3)+3).mapToObj(i -> new BookStock(book)).collect(Collectors.toList())));

		bookRepository.save(books);

		List<User> users = Lists.newArrayList(user1, user2, user3, user4, user5, user6, user7);
		userRepository.save(users);

		bookRepository.findAll().forEach(book -> LOGGER.debug("Created Book : " + book.toString()));
		authorRepository.findAll().forEach(author -> LOGGER.debug("Created Author : " + author.toString()));
		bookStockRepository.findAll().forEach(bookStock -> LOGGER.debug("Created Stock : " + bookStock.toString()));

		// do some random Reservations

		for (int i = 0; i < 2; i++) {
			List<Book> allBooks = bookRepository.findAll();
			try {
				allBooks.forEach(book -> bookReservationService.reserveBook(new Date().toInstant().toEpochMilli(), book.getId(),
						users.get(new Random().nextInt(users.size())).getUsername()));
			} catch (BookAlreadyReservedByUserException e) {
				LOGGER.debug("Book already reserved by user");
			} catch (BookNotAvailableForReservationException e) {
				LOGGER.debug("Book is not available anymore");
			}
		}

		bookStockRepository.findAll().forEach(stock -> LOGGER.debug(stock.toString()));

		for (User user : users) {
			User foundUser = userRepository.findOneByUsernameIgnoreCase(user.getUsername());
			log.info("Found user : " + foundUser + " - " + user.getUsername());
		}

	}

	private User createTestUser(String firstName, String lastName) {

		User user = new User(firstName, lastName,
				firstName.toLowerCase() + "." + lastName.toLowerCase() + "@devplant.ro",
				passwordEncoder.encode("test"));
		user.setEnabled(true);
		return user;
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("Adding sample data ... ");
		addSampleData();
	}
}
