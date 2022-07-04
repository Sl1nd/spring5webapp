package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRepository;
import guru.springframework.spring5webapp.respositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {
  private final AuthorRepository authorRepository;
  private final BookRepository bookRepository;

  private final PublisherRepository publisherRepository;

  public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
    this.authorRepository = authorRepository;
    this.bookRepository = bookRepository;
    this.publisherRepository = publisherRepository;
  }

  @Override
  public void run(String... args) throws Exception {
      Publisher myPublisher = new Publisher("Jens", "Kleemann");
      publisherRepository.save(myPublisher);

      Author eric = new Author("Eric", "Evans");
      Book ddd = new Book("Domain Driven Design", "2131231");
      eric.getBooks().add(ddd);
      ddd.getAuthors().add(eric);
      ddd.setPublisher(myPublisher);
      myPublisher.getBooks().add(ddd);

      authorRepository.save(eric);
      bookRepository.save(ddd);
      publisherRepository.save(myPublisher);

      Author rod = new Author("Rod", "Johnson");
      Book noEJB = new Book("J2EE Development without EJB", "123123213");
      rod.getBooks().add(noEJB);
      ddd.getAuthors().add(rod);

      noEJB.setPublisher(myPublisher);
      myPublisher.getBooks().add(noEJB);

      authorRepository.save(rod);
      bookRepository.save(noEJB);
      publisherRepository.save(myPublisher);

      System.out.println("Started Bootstrap");
      System.out.println("Number of Books: " + bookRepository.count() );
      System.out.println("Number of Publishers: " + publisherRepository.count() );
      System.out.println("Number of Publishers` Books: " + myPublisher.getBooks().size() );
  }
}
