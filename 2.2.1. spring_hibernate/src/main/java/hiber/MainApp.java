package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;



public class MainApp {
   public static void main(String[] args)  {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("IVAN", "IVANOV", "IVAN.@mail.com");
      User user2 = new User("PETR", "PETROV", "PETR@mail.com");
      User user3 = new User("SEMEN", "SEMENOV", "SEMEN@mail.com");
      User user4 = new User("ANTON", "ANTONOV", "ANTON@mail.com");

      Car car1 = new Car("LADA", 6);
      Car car2 = new Car("BMW", 5);
      Car car3 = new Car("AUDI", 4);
      Car car4 = new Car("MAZDA", 3);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
         System.out.println(" _____________________________________________");
      }

      System.out.println(userService.getUserByCar("MAZDA", 3));
      System.out.println(" _____________________________________________");

      try {
         User notFoundUser = userService.getUserByCar("LADA", 11);
      } catch (NoResultException e) {
         System.out.println("NO CAR");
         System.out.println(" _____________________________________________");
      }

      context.close();
   }
}
