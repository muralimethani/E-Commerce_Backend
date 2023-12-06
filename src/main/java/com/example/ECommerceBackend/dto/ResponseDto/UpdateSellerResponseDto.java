package com.example.ECommerceBackend.dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSellerResponseDto {

    int id;

    String name;

    int age;

    String mobNo;

    String emailId;
}
