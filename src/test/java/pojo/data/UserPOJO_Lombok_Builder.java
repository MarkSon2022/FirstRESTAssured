package pojo.data;

import net.datafaker.Faker;
import pojo.RegisterUserPOJO_Lombok;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {

    public static RegisterUserPOJO_Lombok getUserData(){
        Faker faker= new Faker(new Locale("vi"));
        String phoneNumber= faker.phoneNumber().phoneNumberInternational();
        phoneNumber= phoneNumber.replace(" ","")
                .replace("+1","")
                .replace("34","03")
                .replace("-","");

        return  RegisterUserPOJO_Lombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(phoneNumber)
                .email(faker.internet().emailAddress())
                .userStatus(1)
                .build();
    }
}
