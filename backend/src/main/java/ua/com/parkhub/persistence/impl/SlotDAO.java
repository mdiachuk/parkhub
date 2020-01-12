package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;

import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Slot;

@Repository
public class SlotDAO extends ElementDAO<Slot, SlotModel> {

    public SlotDAO(Mapper<Slot, SlotModel> entityToModel, Mapper<SlotModel, Slot> modelToEntity) {
        super(Slot.class, modelToEntity, entityToModel);
    }
}

