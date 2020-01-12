package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.TicketTypeModel;
import ua.com.parkhub.persistence.entities.SupportTicketType;

import java.util.Optional;

@Repository
public class SupportTicketTypeDAO extends ElementDAO<SupportTicketType, TicketTypeModel> {

    public SupportTicketTypeDAO(Mapper<SupportTicketType, TicketTypeModel> entityToModel, Mapper<TicketTypeModel, SupportTicketType>  modelToEntity) {
        super(SupportTicketType.class, modelToEntity, entityToModel);
    }

    public Optional<TicketTypeModel> findSupportTicketTypeByType(String type) {
        return findOneByFieldEqual("type", type);
    }
}
