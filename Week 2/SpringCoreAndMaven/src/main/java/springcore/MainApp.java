package springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        // starting the spring container using our beans.xml file
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // asking spring for the myCar bean instead of creating it ourselves
        Car myCar = (Car) context.getBean("myCar");

        myCar.driveCar();

    }

}
