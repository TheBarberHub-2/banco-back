package com.fpmislata.banco.domain.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireSameUser {
    String paramName();

    ParamType type();

    enum ParamType {
        CLIENTE,
        CUENTA,
        TARJETA
    }
}
