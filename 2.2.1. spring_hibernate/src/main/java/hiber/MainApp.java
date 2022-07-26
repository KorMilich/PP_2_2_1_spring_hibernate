package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Жопа", "Лахматовна", "dopa@mail.ru", new Car("mazda", 123)));
        userService.add(new User("Лысина", "Народа", "lusny@mail.ru", new Car("shkoda", 456)));
        userService.add(new User("Раздолбай", "Пофигистович", "lol@mail.ru", new Car("dodge", 789)));
        userService.add(new User("Рыба", "Креветковна", "fish@mail.ru", new Car("suzuki", 111)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getUserCar());
            System.out.println();
        }
        User getUser = userService.getUserByCarModelAndSeries("mazda", 123);
        System.out.println(getUser);

        context.close();
    }
}
