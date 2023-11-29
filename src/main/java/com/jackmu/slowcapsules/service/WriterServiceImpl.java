package com.jackmu.slowcapsules.service;

import com.jackmu.slowcapsules.model.Writer;
import com.jackmu.slowcapsules.repository.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WriterServiceImpl implements WriterService{
    @Autowired
    private WriterRepository writerRepository;

    public Writer saveWriter(Writer writer){
        return writerRepository.save(writer);
    }

    public void deleteWriter(Long id){
        writerRepository.deleteById(id);
    }

    public List<Writer> fetchWriterByPenName(String penName){
        return writerRepository.findAllByPenName(penName);
    }
    public List<Writer> fetchWriterByWriterId(Long id) {
        return writerRepository.findAllByWriterId(id);
    }
    public List<Writer> fetchWriterByEmail(String email) {
        return writerRepository.findAllByEmail(email);
    }
}
