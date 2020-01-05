//package ua.com.parkhub.persistence.impl;
//
//import org.springframework.stereotype.Repository;
//import ua.com.parkhub.mappers.Mapper;
//import ua.com.parkhub.model.SupportTicketTypeModel;
//import ua.com.parkhub.persistence.entities.SupportTicketType;
//
//import java.util.Optional;
//
//@Repository
//public class SupportTicketTypeDAO extends ElementDAO<SupportTicketType, SupportTicketTypeModel> {
//
//    public SupportTicketTypeDAO(Mapper<SupportTicketType, SupportTicketTypeModel> entityToModel, Mapper<SupportTicketTypeModel, SupportTicketType>  modelToEntity) {
//        super(SupportTicketType.class, modelToEntity, entityToModel);
//    }
//
//    public Optional<SupportTicketTypeModel> findSupportTicketTypeByType(String type) {
//        return findOneByFieldEqual("type", type);
//    }
//}
