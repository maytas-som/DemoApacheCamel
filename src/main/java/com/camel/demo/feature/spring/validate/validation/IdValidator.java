package com.camel.demo.feature.spring.validate.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IdValidator implements ConstraintValidator<IsValidId, String> {

    private static String REGEX_MATCH_WORD_THAT_CONTAIN_NUMBER_AND_NOT_CONTAIN_SPACE = "^([0-9]{1,5})*$";
    private static Pattern patternMatchWordContainNumberAndNotContainSpace = Pattern.compile(REGEX_MATCH_WORD_THAT_CONTAIN_NUMBER_AND_NOT_CONTAIN_SPACE);

    Logger log = LogManager.getLogger(this.getClass());

    @Override
    public void initialize(IsValidId isValidId) {
        log.info(">>>"+isValidId);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        log.info(">>>"+s+"/"+patternMatchWordContainNumberAndNotContainSpace.matcher(s).find());
        return patternMatchWordContainNumberAndNotContainSpace.matcher(s).find();
    }
}
