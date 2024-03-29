package pl.mydojo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.mydojo.app.utils.FakeGenerators;
import pl.mydojo.app.utils.PrepareDb;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);

        if (Arrays.asList(context.getEnvironment().getActiveProfiles()).contains("init-db")) {
            prepareDb(context);
        }
    }

    private static void prepareDb(ApplicationContext context) {
        PrepareDb prepareDb = context.getBean(PrepareDb.class);
        prepareDb.setRoles();
        prepareDb.setBasicAdmin();
        prepareDb.setBasicTrainer();
        prepareDb.setBasicStudent();
        generateFakeUsers(context);
        prepareDb.setBasicVenue();
        prepareDb.setBasicTrainingGroup();
        prepareDb.setPrimarySchedule();
        prepareDb.setSecondarySchedule();
    }

    public static void generateFakeUsers(ApplicationContext context) {
        FakeGenerators fakeGenerators = context.getBean(FakeGenerators.class);
        fakeGenerators.usersGenerator("admin", 1, 30, 35);
        fakeGenerators.usersGenerator("student", 5, 5, 10);
        fakeGenerators.usersGenerator("student", 5, 10, 15);
        fakeGenerators.usersGenerator("student", 5, 15, 20);
        fakeGenerators.usersGenerator("student", 5, 20, 60);
        fakeGenerators.usersGenerator("trainer", 5, 22, 80);
    }
}
