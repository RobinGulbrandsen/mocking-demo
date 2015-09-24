package no.rg.domain;

/**
 *
 * @author robin
 */
public class Order {
    
    private String id;

    public Order(String id) {
        setId(id);
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
