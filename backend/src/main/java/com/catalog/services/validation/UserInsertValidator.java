package com.catalog.services.validation;

import com.catalog.dto.UserInsertDTO;
import com.catalog.entities.User;
import com.catalog.repositories.UserRepository;
import com.catalog.resources.exceptions.FieldMessage;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    private final UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();
        User user = userRepository.findByEmail(userInsertDTO.getEmail());
        if (user != null) {
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
