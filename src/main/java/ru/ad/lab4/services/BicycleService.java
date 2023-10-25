package ru.ad.lab4.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ad.lab4.models.Bicycle;
import ru.ad.lab4.repositories.BicycleRepo;

@Service
@Transactional(readOnly = true)
public class BicycleService {

  private final BicycleRepo bicycleRepo;

  @Autowired
  public BicycleService(BicycleRepo bicycleRepo) {
    this.bicycleRepo = bicycleRepo;
  }

  public List<Bicycle> findAll() {
    return bicycleRepo.findAll();
  }

  public Bicycle findOne(int id) {
    return bicycleRepo.findById(id).orElse(null);
  }

  @Transactional
  public void save(Bicycle bicycle) {
    bicycleRepo.save(bicycle);
  }

  @Transactional
  public void update(int id, Bicycle updBicycle) {
    updBicycle.setId(id);
    bicycleRepo.save(updBicycle);
  }

  @Transactional
  public void delete(int id) {
    bicycleRepo.deleteById(id);
  }

  public List<Bicycle> findByPrice(float price) {
    return bicycleRepo.findByPriceLessThan(price);
  }
}
