package com.contact.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDto {
    private String personId;
    @Size(max = 15,message = "Size not exceed 15")
    private String personName;
    private String personEmail;
    @Size(max = 10,message = "mobile have only 10 digits")
    private String personMobile;
}
