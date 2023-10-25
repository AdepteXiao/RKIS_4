package ru.ad.lab4.userInterfaces;

import jakarta.annotation.PreDestroy;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.stereotype.Component;

@Component
public class Inputer {

  private final Scanner scanner = new Scanner(System.in); // Считыватель текста с клавиатуры

  public int getInt() throws InputMismatchException {
    try {
      int val = scanner.nextInt();
      scanner.nextLine();
      return val;
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Ошибка ввода. Введите целое число.");
    }
  }

  public float getFloat() throws InputMismatchException {
    try {
      float val = scanner.nextFloat();
      scanner.nextLine();
      return val;
    } catch (InputMismatchException e) {
      scanner.next();
      throw new InputMismatchException("Ошибка ввода. Введите число с плавающей запятой.");
    }
  }

  public String getString() {
    return scanner.nextLine();
  }


  @PreDestroy
  private void onDestroy() {
    scanner.close();
  }
}
