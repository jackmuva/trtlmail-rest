package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Writer;

import java.util.List;

public interface WriterService {
    void deleteWriter(Long id);
    Writer fetchWriterByPenName(String penName);
    List<Writer> fetchWriterByWriterId(Long id);
    List<Writer> fetchWriterByEmail(String email);
    Boolean createWriter(Writer writer);
}
