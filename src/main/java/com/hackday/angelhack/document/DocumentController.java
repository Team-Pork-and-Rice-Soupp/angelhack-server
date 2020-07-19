package com.hackday.angelhack.document;

import com.hackday.angelhack.document.dto.DocumentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/api/workspace/document")
    public ResponseEntity<Document> addDocument(@RequestBody DocumentSaveRequestDto requestDto){
        Document document = documentService.crateDocument(requestDto);
        return new ResponseEntity<>(document, HttpStatus.CREATED);
    }
}
