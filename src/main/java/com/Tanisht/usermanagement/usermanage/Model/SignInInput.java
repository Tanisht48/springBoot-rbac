package com.Tanisht.usermanagement.usermanage.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignInInput {
        private  String userEmail;
        private String userPassword;
}
