package com.digicert.sample.java.booklibrary.repository;

import com.digicert.sample.java.booklibrary.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    List<Library> findByTitleContaining(String title);

    List<Library> findByPublisherContaining(String publisher);

    List<Library> findByIssued(boolean issued);
}
