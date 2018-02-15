package com.yapily.api.client.exceptions;

public class ApiExceptions {

    ApiExceptions() {}

    public static InvalidRecordException invalidRecordException(final Object entity) {
        return new InvalidRecordException(entity);
    }

    public static InvalidRecordException invalidRecordException(String description, final Object entity) {
        return new InvalidRecordException(description, entity);
    }

    public static NotFoundException notFoundException(final Object entity) {
        return new NotFoundException(entity);
    }

    public static InvalidRequestException invalidRequestException(String description) {
        return new InvalidRequestException(description);
    }

    public static UnauthorizedException unauthorizedException(String description) {
        return new UnauthorizedException(description);
    }

    public static PermissionDeniedException permissionDeniedException(String description) {
        return new PermissionDeniedException(description);
    }

    public static PreconditionMissingException preconditionMissing(String description) {
        return new PreconditionMissingException(description);
    }

    public static NoIdentityDataAvailableException noIdentityDataAvailableException(String bankId) {
        return new NoIdentityDataAvailableException(bankId);
    }

}
