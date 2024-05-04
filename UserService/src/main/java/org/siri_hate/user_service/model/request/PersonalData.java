package org.siri_hate.user_service.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonalData {

    String name;

    String phone;

    String email;

    LocalDate birthDay;

}
