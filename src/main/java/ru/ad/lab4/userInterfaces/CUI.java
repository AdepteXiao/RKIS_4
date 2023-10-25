package ru.ad.lab4.userInterfaces;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ad.lab4.models.Bicycle;
import ru.ad.lab4.services.BicycleService;

@Component
public class CUI implements menuEnum{

  private final BicycleService bicycleService;

  private final Inputer inputer;

  private final PrintStream out = new PrintStream(System.out, true, StandardCharsets.UTF_8);


  @Autowired
  public CUI(BicycleService bicycleService, Inputer inputer) {
    this.bicycleService = bicycleService;
    this.inputer = inputer;
  }


  public void menu() {

    int choice;

    do {
      this.out.println("""
          1. Добавить велосипед
          2. Вывести все велосипеды
          3. Редактировать велосипед
          4. Удалить велосипед
          5. Найти велосипеды ниже определенной цены
          6. Выход
          """);

      choice = inputer.getInt();
      switch (choice) {
        case ADD_BICYCLE -> adder();
        case PRINT_BICYCLES -> printer();
        case EDIT_BICYCLE -> editor();
        case DELETE_BICYCLE -> deleter();
        case FIND_BY_MAX_PRICE -> priceFilter();
        default -> {
          if (choice != EXIT) {
            this.out.println("Некорректный ввод");
          }
        }
      }

    } while (choice != EXIT);

  }


  private void adder() {
    Bicycle bicycle = new Bicycle();
    boolean isInputValid = false;

    while (!isInputValid) {
      try {
        out.println("Введите модель:");
        bicycle.setModel(inputer.getString());

        out.println("Введите бренд:");
        bicycle.setBrand(inputer.getString());

        out.println("Введите состояние (новый|б/у):");
        bicycle.setCondition(inputer.getString());

        out.println("Введите количество скоростей:");
        bicycle.setSpeedsCount(inputer.getInt());

        out.println("Введите цену:");
        bicycle.setPrice(inputer.getFloat());

        bicycleService.save(bicycle);
        isInputValid = true;
        out.println("Объект успешно добавлен в базу данных.");
      } catch (InputMismatchException e) {
        out.println("Произошла ошибка: " + e.getMessage());
        inputer.getString();
      }
    }
  }


  private void printer() {
    printBicycleList(bicycleService.findAll());
  }

  public void editor() {
    int id;
    Bicycle bicycle;

    try {
      out.println("Введите id велосипеда");
      id = inputer.getInt();
      bicycle = bicycleService.findOne(id);
      if (bicycle == null) {
        throw new InputMismatchException("Велосипеда с данным id не существует");
      }
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }

    int choice;
    do {
      out.println("""
          1. Изменить модель
          2. Изменить бренд
          3. Изменить состояние
          4. Изменить кол-во скоростей
          5. Изменить цену
          6. Выйти в меню
          """);
      choice = inputer.getInt();
      switch (choice) {
        case EDIT_MODEl -> changeModelHandler(bicycle);
        case EDIT_BRAND -> changeBrandHandler(bicycle);
        case EDIT_CONDITION -> changeConditionHandler(bicycle);
        case EDIT_SPEEDS_COUNT -> changeSpeedsCountHandler(bicycle);
        case EDIT_PRICE -> changePriceHandler(bicycle);
        case EDIT_EXIT -> out.println("Редактирование завершено");
        default -> out.println("Некорректный ввод!");
      }
    } while (choice != EDIT_EXIT);
    bicycleService.update(id, bicycle);
  }

  private void changeModelHandler(Bicycle bicycle) {
    out.println("Введите новую модель:");
    try {
      String newModel = inputer.getString();
      bicycle.setModel(newModel);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeBrandHandler(Bicycle bicycle) {
    out.println("Введите новый бренд:");
    try {
      String newBrand = inputer.getString();
      bicycle.setBrand(newBrand);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeConditionHandler(Bicycle bicycle) {
    out.println("Введите новое состояние:");
    try {
      String newCondition = inputer.getString();
      bicycle.setCondition(newCondition);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changeSpeedsCountHandler(Bicycle bicycle) {
    out.println("Введите новое количество скоростей");
    try {
      int newSpeedsCount = inputer.getInt();
      bicycle.setSpeedsCount(newSpeedsCount);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void changePriceHandler(Bicycle bicycle) {
    out.println("Введите новую цену:");
    try {
      int newPrice = inputer.getInt();
      bicycle.setPrice(newPrice);
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
    }
  }

  private void deleter() {
    int id;
    Bicycle bicycle;

    try {
      out.println("Введите id велосипеда");
      id = inputer.getInt();
      bicycle = bicycleService.findOne(id);
      if (bicycle == null) {
        throw new InputMismatchException("Велосипеда с данным id не существует");
      }
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }
    bicycleService.delete(id);
  }

  private void priceFilter() {
    float price;
    try {
      out.println("Введите максимальную цену");
      price = inputer.getFloat();
    } catch (InputMismatchException e) {
      out.println(e.getMessage());
      return;
    }
    List<Bicycle> bicycles = bicycleService.findByPrice(price);
    printBicycleList(bicycles);
  }



  private void printBicycleList(List<Bicycle> bicycles) {
    for (Bicycle bicycle : bicycles) {
      out.println(bicycle);
    }
  }

}
