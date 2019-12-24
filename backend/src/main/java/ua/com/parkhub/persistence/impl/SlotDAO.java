package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.Slot;

@Repository
public class SlotDAO extends ElementDAO<Slot> {
    public SlotDAO() {
        super(Slot.class);
    }
}

