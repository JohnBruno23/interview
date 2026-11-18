package com.doc.ai.repo;


/*-----------------------------------------------------------------------
 * Coded By : John
 * Date : 17/11/2026
 * Purpose : DocumentStore entity repo
 *-----------------------------------------------------------------------*/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doc.ai.entity.DocumentStore;

@Repository
public interface DocumentRepository
        extends JpaRepository<DocumentStore, Long> {
}