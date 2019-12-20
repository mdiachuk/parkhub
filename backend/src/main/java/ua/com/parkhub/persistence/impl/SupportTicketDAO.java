package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.persistence.entities.SupportTicket;

@Repository
public class SupportTicketDAO extends ElementDAO<SupportTicket> {

    public SupportTicketDAO() {
        super(SupportTicket.class);
    }

}
