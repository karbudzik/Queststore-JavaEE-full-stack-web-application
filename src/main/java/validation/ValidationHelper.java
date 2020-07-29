package validation;

import exception.*;

import javax.servlet.http.HttpServletRequest;

public class ValidationHelper {
    private boolean isInputValid;
    private HttpServletRequest request;
    private Validator validator;

    public ValidationHelper(HttpServletRequest request, Validator validator) {
        isInputValid = true;
        this.request = request;
        this.validator = validator;
    }

    public ValidationHelper helpValidateStringLength(String parameterName, String attributeName, int min, int max) {
        try {
            validator.validateStringLength(request.getParameter(parameterName), min, max);
        } catch (StringLengthFormatException e) {
            request.setAttribute(attributeName, e.getMessage());
            isInputValid = false;
        }
        return this;
    }

    public ValidationHelper helpValidateEmail(String parameterName, String attributeName, int min, int max) {
        try {
            validator.validateEmail(request.getParameter(parameterName), min, max);
        } catch (EmailFormatException e) {
            request.setAttribute(attributeName, e.getMessage());
            isInputValid = false;
        }
        return this;
    }

    public ValidationHelper helpValidateValue(String parameterName, String attributeName, int min, int max) {
        try {
            validator.validateValue(request.getParameter(parameterName), min, max);
        } catch (ValueFormatException e) {
            request.setAttribute(attributeName, e.getMessage());
            isInputValid = false;
        }
        return this;
    }

    public ValidationHelper helpValidateType(String parameterName, String attributeName, String type1, String type2) {
        try {
            validator.validateType(request.getParameter(parameterName), type1, type2);
        } catch (TypeFormatException e) {
            request.setAttribute(attributeName, e.getMessage());
            isInputValid = false;
        }
        return this;
    }

    public ValidationHelper helpValidateDate(String parameterName, String attributeName, String allowedFormat) {
        try {
            validator.validateDate(request.getParameter(parameterName), allowedFormat);
        } catch (DateFormatException e) {
            request.setAttribute(attributeName, e.getMessage());
            isInputValid = false;
        }
        return this;
    }

    public boolean complete() {
        return this.isInputValid;
    }
}
