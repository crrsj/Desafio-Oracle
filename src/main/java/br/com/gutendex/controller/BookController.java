package br.com.gutendex.controller;

import br.com.gutendex.entity.Book;
import br.com.gutendex.service.GutendexService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private GutendexService gutendexService;


    @PostMapping("/fetch/{id}")
    @Operation(summary = "Endpoint responsável por cadastrar od livros buscados na API do gutendex.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<?> fetchAndSaveBook(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(gutendexService.fetchAndSaveBook(id));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Erro de integridade de dados: " + e.getRootCause().getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "Endpoint responsável por buscar todos os livros cadastrados.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(gutendexService.getAllBooks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint responsável por buscar livros pelo id.")
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
            @Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })
    public ResponseEntity<Optional<Book>>getBook(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(gutendexService.getBookById(id));
    }
}