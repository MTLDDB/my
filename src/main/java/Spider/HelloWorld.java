package Spider;

import java.lang.System;

public class HelloWorld {
  public static void main1() {
    System.out.println("Hello, JavaPoet!");
    System.out.println(function());
  }

  public static int function() {
    int total = 0;
    for (int i = 0; i < 10; i++) {
      total += i;
    }
    return total;
  }
}
