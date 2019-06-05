package tacos.repository;


import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.models.Taco;

public interface TacoRepository   extends PagingAndSortingRepository<Taco, Long>{

	
}
