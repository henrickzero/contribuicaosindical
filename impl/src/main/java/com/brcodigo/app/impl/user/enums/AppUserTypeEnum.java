package com.brcodigo.app.impl.user.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@AllArgsConstructor(access = PROTECTED)
@Getter
public enum AppUserTypeEnum {
    USER(Arrays.asList(
            "/api/v1/user/subsidiaries"
    )),
    ADMIN(Arrays.asList(
            "/api/v1/user/subsidiaries"
    ));

    private final List<String> rules;

}
