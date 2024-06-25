package com.tcw.example_service.controller;

import com.tcloud.entity.Document;
import com.tcloud.entity.WorkRequest;
import com.tcloud.entity.WorkResponse;
import com.tcloud.entity.WorkSuccess;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class CreateFirstProcessController {

    static class FirstProcessObject {
        private String id;
        private int value;

        public FirstProcessObject() {
        }

        public FirstProcessObject(int value) {
            this.id = UUID.randomUUID().toString();
            this.value = value;
        }
    }

    @PostMapping("/create-first-process")
    public ResponseEntity<WorkResponse> handle(@RequestBody @NonNull final WorkRequest workRequest) {
        // 1. Create a first process object with value 1
        final FirstProcessObject firstProcessObject = new FirstProcessObject(1);

        // 2. Save in a document to be consumed later by other states in the workflow
        final Document document = workRequest.getDocument();
        document.putEntity("firstProcessObject", firstProcessObject);

        // 3. Return WorkSuccess indicating a successful result along with the updated document and name
        return ResponseEntity.ok().body(
                WorkSuccess.builder()
                        .document(document)
                        .actionName("FirstProcessCreated")
                        .build()
        );
    }
}
