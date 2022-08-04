package com.catalog.services.validation;

import com.catalog.dto.UserUpdateDTO;
import com.catalog.entities.User;
import com.catalog.repositories.UserRepository;
import com.catalog.resources.exceptions.FieldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerMapping;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    private final HttpServletRequest request;
    private final UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext constraintValidatorContext) {

        @SuppressWarnings("unchecked")
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));
        List<FieldMessage> fieldMessages = new ArrayList<>();
        User user = userRepository.findByEmail(userUpdateDTO.getEmail());
        if (user != null && userId != user.getId()) {
            fieldMessages.add(new FieldMessage("email", "Email já existe"));
        }
        for (FieldMessage fieldMessage : fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(fieldMessage.getMessage()).addPropertyNode(fieldMessage.getFieldName())
                    .addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}
