package guru.masm.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.masm.springframework.spring5webapp.domain.Author;
import guru.masm.springframework.spring5webapp.domain.Book;
import guru.masm.springframework.spring5webapp.domain.Publisher;
import guru.masm.springframework.spring5webapp.repositories.AuthorRepository;
import guru.masm.springframework.spring5webapp.repositories.BookRepository;
import guru.masm.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	

	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		
		Publisher p1 = new Publisher("bookman", "street 1", "ny", "ny", "123456");
        publisherRepository.save(p1);

		Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        p1.getBooks().add(ddd);
        ddd.setPublisher(p1);
        
        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(p1);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        p1.getBooks().add(noEJB);
        noEJB.setPublisher(p1);
        
        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(p1);
        
        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        
        System.out.println("Publisher Number of Books: " + p1.getBooks().size());
		
	}

}
