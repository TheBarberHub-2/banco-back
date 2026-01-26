package com.fpmislata.banco.domain.validation.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fpmislata.banco.domain.service.AuthService;
import com.fpmislata.banco.domain.service.CuentaBancariaService;
import com.fpmislata.banco.domain.service.dto.ClienteDto;
import com.fpmislata.banco.domain.validation.RequireSameUser;
import com.fpmislata.banco.exception.BusinessException;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SameUserAspect {

    @Autowired
    private AuthService authService;

    @Autowired
    private CuentaBancariaService cuentaService;

    @Before("@annotation(requireSameUser)")
    public void checkSameUser(JoinPoint joinPoint, RequireSameUser requireSameUser) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        String token = request.getHeader("token");
        ClienteDto cliente = authService.getByToken(token);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            if (paramNames[i].equals(requireSameUser.paramName())) {
                Long paramValue = Long.valueOf(args[i].toString());
                Long clienteId;

                if (requireSameUser.type() == RequireSameUser.ParamType.CLIENTE) {
                    clienteId = paramValue;
                } else if (requireSameUser.type() == RequireSameUser.ParamType.CUENTA) {
                    clienteId = cuentaService.getClienteByCuenta(paramValue).id();
                } else {
                    clienteId = cuentaService.getClienteByCuenta(cuentaService.getByTarjeta(paramValue).id()).id();
                }

                if (cliente.id() != clienteId) {
                    throw new BusinessException("Acceso denegado");
                }
            }
        }
    }
}
