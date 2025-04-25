package com.umutyenidil.springrestaurant.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data // getters setters equals and hashcode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String id;

    private String username;

    private String givenName;

    private String familyName;
}
