package com.camel.demo.feature.spring.validate.validation;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.regex.Pattern;

public class IdValidator implements ConstraintValidator<IsValidId, String> {

    private static String REGEX_MATCH_WORD_THAT_CONTAIN_NUMBER_AND_NOT_CONTAIN_SPACE = "^([0-9]{1,5})*$";
    private static Pattern patternMatchWordContainNumberAndNotContainSpace = Pattern.compile(REGEX_MATCH_WORD_THAT_CONTAIN_NUMBER_AND_NOT_CONTAIN_SPACE);

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void initialize(IsValidId isValidId) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return StringUtils.equals(value, null) ? true : patternMatchWordContainNumberAndNotContainSpace.matcher(value).find();
    }
}
