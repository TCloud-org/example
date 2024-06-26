package com.tcw.example_service.controller;

import com.tcloud.entity.Document;
import com.tcloud.entity.WorkRequest;
import com.tcloud.entity.WorkResponse;
import com.tcloud.entity.WorkSuccess;
import com.tcw.example_service.model.FirstProcessObject;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class IncrementProcessValueController {

    @PostMapping("/increment-process-value")
    public ResponseEntity<WorkResponse> handle(@RequestBody @NonNull final WorkRequest workRequest) {
        // 1. Extract the existing first process object
        final Document document = workRequest.getDocument();
        final FirstProcessObject firstProcessObject =
                document.extractEntityByType("firstProcessObject", FirstProcessObject.class);

        // 2. Modify the object by incrementing the value by 1
        firstProcessObject.setValue(firstProcessObject.getValue() + 1);

        // 3. Override the updated object to the document
        document.putEntity("firstProcessObject", firstProcessObject);

        // 4. Return WorkSuccess indicating a successful result along with the updated document and name
        return ResponseEntity.ok().body(
                WorkSuccess.builder()
                        .document(document)
                        .actionName("ProcessValueIncremented")
                        .build()
        );
    }
}
