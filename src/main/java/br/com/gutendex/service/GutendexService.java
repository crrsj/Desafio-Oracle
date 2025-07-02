package br.com.gutendex.service;

import br.com.gutendex.entity.Book;
import br.com.gutendex.entity.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class GutendexService {

    private static final String GUTENDEX_URL = "https://gutendex.com/books/";
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;
    public Book fetchAndSaveBook(Integer id) {
        String url = GUTENDEX_URL + id;

        // Busca o livro existente (se houver)
        Book existingBook = bookRepository.findById(id).orElse(null);

        // Faz a chamada à API Gutendex
        Book gutendexBook = restTemplate.getForObject(url, Book.class);

        if(existingBook != null) {
            // Atualiza os campos mantendo a versão
            existingBook.setTitle(gutendexBook.getTitle());
            existingBook.setAuthor(gutendexBook.getAuthor());
            existingBook.setDownloadUrl(gutendexBook.getDownloadUrl());
            return bookRepository.save(existingBook);
        }

        return bookRepository.save(gutendexBook);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

 }
