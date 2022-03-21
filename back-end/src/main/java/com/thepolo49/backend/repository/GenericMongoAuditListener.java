package com.thepolo49.backend.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GenericMongoAuditListener extends AbstractMongoEventListener<Object> {

    private static Logger logger = LoggerFactory.getLogger(GenericMongoAuditListener.class);

    @Override
    public void onAfterSave(AfterSaveEvent<Object> event) {
        Object obj = event.getSource();
        logger.info("{} Saved document {}", LocalDateTime.now(), obj);
    }

    @Override
    public void onAfterDelete(AfterDeleteEvent<Object> event) {
        Object obj = event.getSource();
        logger.info("{} Deleted document {}", LocalDateTime.now(), obj);
    }
}
