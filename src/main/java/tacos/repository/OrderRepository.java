package tacos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.models.Order;
import tacos.models.User;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}
