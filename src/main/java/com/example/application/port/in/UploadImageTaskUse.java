package com.example.application.port.in;

import com.example.domain.model.Task;

public interface UploadImageTaskUse {

    Task SetImage(Task task, String fileName, byte[] content);

    Task uploadImage(long id, String fileName, byte[] content);

}
