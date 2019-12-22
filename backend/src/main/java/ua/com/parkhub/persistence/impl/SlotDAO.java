package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.SlotEntity;

@Repository
public class SlotDAO extends ElementDAO<SlotEntity> {

    public SlotDAO() {
        super(SlotEntity.class);
    }
}

