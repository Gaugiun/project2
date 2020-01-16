package com.stylefeng.guns.rest.modular.auth.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserToken implements Serializable {

    private String randomKey;

    private String token;

}
