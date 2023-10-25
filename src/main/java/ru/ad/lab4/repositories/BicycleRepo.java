package ru.ad.lab4.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ad.lab4.models.Bicycle;

@Repository
public interface BicycleRepo extends JpaRepository<Bicycle, Integer> {

  List<Bicycle> findByPriceLessThan(float price);

}
