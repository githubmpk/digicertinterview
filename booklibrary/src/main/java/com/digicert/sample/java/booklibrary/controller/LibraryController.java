package com.digicert.sample.java.booklibrary.controller;

import com.digicert.sample.java.booklibrary.model.Library;
import com.digicert.sample.java.booklibrary.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    LibraryRepository libraryRepository;

    @GetMapping("/books")
    public ResponseEntity<List<Library>> getAllBooks(@RequestParam(required = false) String title) {
        try {
            List<Library> libraries = new ArrayList<Library>();
            if (title == null)
                libraryRepository.findAll().forEach(libraries::add);
            else
                libraryRepository.findByTitleContaining(title).forEach(libraries::add);

            if (libraries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Library> getBookById(@PathVariable("id") long id) {
        Optional<Library> bookData = libraryRepository.findById(id);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<Library> createBook(@RequestBody Library library) {
        try {
            Library _library = libraryRepository
                    .save(new Library(library.getTitle(), library.getDescription(), library.getPublisher(), false));
            return new ResponseEntity<>(_library, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable("id") long id, @RequestBody Library library) {
        Optional<Library> libraryData = libraryRepository.findById(id);

        if (libraryData.isPresent()) {
            Library _library = libraryData.get();
            _library.setTitle(library.getTitle());
            _library.setDescription(library.getDescription());
            _library.setPublisher(library.getPublisher());
            _library.setIssued(library.isIssued());
            return new ResponseEntity<>(libraryRepository.save(_library), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
        try {
            libraryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("books")
    public ResponseEntity<HttpStatus> deleteAllBooks() {
        try {
            libraryRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/issued")
    public ResponseEntity<List<Library>> findByIssued() {
        try {
            List<Library> libraries = libraryRepository.findByIssued(true);

            if (libraries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(libraries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
