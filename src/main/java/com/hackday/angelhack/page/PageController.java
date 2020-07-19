package com.hackday.angelhack.page;

import com.hackday.angelhack.common.domain.Message;
import com.hackday.angelhack.document.Document;
import com.hackday.angelhack.document.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/page")
@Controller
public class PageController {

    private final DocumentService documentService;

    @GetMapping("/document/{documentId}")
    public String socket(Model model, @PathVariable Long documentId) {
        Document document = documentService.getDocumentById(documentId);
        model.addAttribute("document", document);
        return "socket";
    }

}
