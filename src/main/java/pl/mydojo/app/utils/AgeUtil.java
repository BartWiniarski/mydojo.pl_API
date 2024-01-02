package pl.mydojo.app.utils;

import pl.mydojo.app.entities.User;

import java.time.LocalDate;
import java.time.Period;

public class AgeUtil {

    public static int calculateAge(User user) {
        int age = 0;
        LocalDate dob = user.getDob();

        if (dob != null) {
            LocalDate currentDate = LocalDate.now();
            if (currentDate.isBefore(dob)) {
                age = Period.between(dob, currentDate).getYears() - 1;
            } else {
                age = Period.between(dob, currentDate).getYears();
            }
        }

        return age;
    }
}
