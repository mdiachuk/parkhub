package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.persistence.entities.SupportTicket;

@Repository
public class SupportTicketDAO extends ElementDAO<SupportTicket, SupportTicketModel> {

    public SupportTicketDAO(Mapper<SupportTicket, SupportTicketModel> entityToModel, Mapper<SupportTicketModel, SupportTicket> modelToEntity) {
        super(SupportTicket.class, modelToEntity, entityToModel);
    }
}
