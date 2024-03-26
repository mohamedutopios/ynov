package com.example.ynovv.service.impl;

import com.example.ynovv.entity.Publisher;
import com.example.ynovv.repository.PublisherRepository;
import com.example.ynovv.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    @Override
    public Publisher findById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
    }

    @Override
    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher update(Long id, Publisher publisherDetails) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
        publisher.setName(publisherDetails.getName());
        publisher.setBook(publisherDetails.getBook());
        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found with id " + id));
        publisherRepository.delete(publisher);
    }
}
