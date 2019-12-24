package ua.com.parkhub.persistence.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.NullCustomerException;
import ua.com.parkhub.persistence.entities.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class CustomerDAO extends ElementDAO<Customer> {

    @Autowired
    public CustomerDAO() {
        super(Customer.class);
    }

    public Optional<Customer> findCustomerByPhoneNumber(String phoneNumber) {
        return findOneByFieldEqual("phoneNumber", phoneNumber);
    }

    @Transactional
    public Optional<Customer> findElementByPhone(String phone) throws NullCustomerException{
        try {
            CriteriaBuilder criteriaBuilder = this.emp.getCriteriaBuilder();
            CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
            Root<Customer> itemRoot = criteriaQuery.from(Customer.class);
            Predicate predicate
                    = criteriaBuilder.equal(itemRoot.get("phoneNumber"), phone);
            criteriaQuery.where(predicate);
            return Optional.of(Optional.ofNullable(this.emp.createQuery(criteriaQuery).getSingleResult())).orElseThrow(NullCustomerException::new);
        }catch (Exception e){
            throw new NullCustomerException("Failed to find customer by phone number");
        }
    }
}

