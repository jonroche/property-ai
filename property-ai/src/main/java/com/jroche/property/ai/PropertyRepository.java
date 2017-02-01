package com.jroche.property.ai;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jonathan Roche
 */
@Repository
public interface PropertyRepository extends CrudRepository<Property, Long> {

}
