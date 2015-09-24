package no.rg.repository;

import no.rg.domain.Order;

/**
 *
 * @author robin
 */
public interface OrderRepository {
    
    public Order create(Order order);
}
