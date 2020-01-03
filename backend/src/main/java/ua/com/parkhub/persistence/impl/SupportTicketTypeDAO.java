package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.persistence.entities.SupportTicketTypeModel;

import java.util.Optional;

@Repository
public class SupportTicketTypeDAO extends ElementDAO<SupportTicketTypeModel, ua.com.parkhub.model.SupportTicketTypeModel> {

    public SupportTicketTypeDAO(Mapper<SupportTicketTypeModel, ua.com.parkhub.model.SupportTicketTypeModel> entityToModel, Mapper<ua.com.parkhub.model.SupportTicketTypeModel, SupportTicketTypeModel>  modelToEntity) {
        super(SupportTicketTypeModel.class, modelToEntity, entityToModel);
    }

    public Optional<ua.com.parkhub.model.SupportTicketTypeModel> findSupportTicketTypeByType(String type) {
        return findOneByFieldEqual("type", type);
    }
}
