package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketTypeModel;

import java.util.Optional;

@Repository
public class SupportTicketTypeDAO extends ElementDAO<SupportTicketTypeModel, ua.com.parkhub.model.SupportTicketTypeModel> {

    public SupportTicketTypeDAO(Mapper<SupportTicketTypeModel, SupportTicketTypeModel> entityToModel, Mapper<ua.com.parkhub.model.SupportTicketTypeModel, SupportTicketTypeModel>  modelToEntity) {
        super(SupportTicketTypeModel.class, modelToEntity, entityToModel);
    }

    public Optional<ua.com.parkhub.model.SupportTicketTypeModel> findSupportTicketTypeByType(String type) {
        return findOneByFieldEqual("type", type);
    }
}
