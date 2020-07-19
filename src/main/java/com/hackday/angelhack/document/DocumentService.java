package com.hackday.angelhack.document;

import com.hackday.angelhack.document.dto.DocumentSaveRequestDto;
import com.hackday.angelhack.workspace.Workspace;
import com.hackday.angelhack.workspace.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final WorkspaceRepository workspaceRepository;

    public Document crateDocument(DocumentSaveRequestDto requestDto){
        Workspace workspace = workspaceRepository.findById(requestDto.getWorkspaceId()).get();
        Document document = new Document();
        document.setContent("");
        document.setTitle(requestDto.getTitle());
        document.setWorkspace(workspace);

        return documentRepository.save(document);
    }
}
