package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.SupportTicketType;

@Repository
public class SupportTicketTypeDAO extends ElementDAO<SupportTicketType> {

    public SupportTicketTypeDAO() {
        super(SupportTicketType.class);
    }
}
