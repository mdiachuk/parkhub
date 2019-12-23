package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.SupportTicketType;

import java.util.Optional;

@Repository
public class SupportTicketTypeDAO extends ElementDAO<SupportTicketType> {

    public SupportTicketTypeDAO() {
        super(SupportTicketType.class);
    }

    public Optional<SupportTicketType> findSupportTicketTypeByType(String type) {
        return findOneByFieldEqual("type", type);
    }
}
