package ru.ad.lab4.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "bicycle")
public class Bicycle {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "model")
  private String model;

  @Column(name = "brand")
  private String brand;

  @Column(name = "condition")
  private String condition;

  @Column(name = "speeds_count")
  private int speedsCount;

  @Column(name = "price")
  private float price;

  public Bicycle(String model, String brand, String condition, int speedsCount, float price) {
    this.model = model;
    this.brand = brand;
    this.condition = condition;
    this.speedsCount = speedsCount;
    this.price = price;
  }

  public Bicycle() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public int getSpeedsCount() {
    return speedsCount;
  }

  public void setSpeedsCount(int speedsCount) {
    this.speedsCount = speedsCount;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Bicycle{" +
        "id=" + id +
        ", model='" + model + '\'' +
        ", brand='" + brand + '\'' +
        ", condition='" + condition + '\'' +
        ", speedsCount=" + speedsCount +
        ", price=" + price +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bicycle bicycle = (Bicycle) o;
    return getSpeedsCount() == bicycle.getSpeedsCount()
        && Float.compare(bicycle.getPrice(), getPrice()) == 0 && Objects.equals(
        getModel(), bicycle.getModel()) && Objects.equals(getBrand(), bicycle.getBrand())
        && Objects.equals(getCondition(), bicycle.getCondition());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getModel(), getBrand(), getCondition(), getSpeedsCount(), getPrice());
  }
}
