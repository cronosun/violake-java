package com.github.caelis.violake.example.core.example4;

import org.reactivestreams.Publisher;

import java.util.Optional;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

import io.reactivex.Flowable;

public final class Example4Service {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-" +
                    "\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09" +
                    "\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9]" +
                    "[0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                    "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-" +
                    "\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$");

    /**
     * Note: We return a publisher, since validation might take some time (might invoke a
     * web service).
     */
    public Publisher<Boolean> isValidEmail(String email) {
        return Flowable.just(EMAIL_PATTERN.matcher(email).matches());
    }

    /**
     * Note: We return a publisher, since validation might take some time (might invoke a
     * web service).
     */
    public Publisher<Boolean> isValidFirstName(String firstName) {
        return Flowable.just(!firstName.isEmpty());
    }

    /**
     * Note: We return a publisher, since validation might take some time (might invoke a
     * web service).
     */
    public Publisher<Boolean> isValidLastName(String lastName) {
        return Flowable.just(!lastName.isEmpty());
    }

    /**
     * Note: We return a publisher, since validation might take some time (might invoke a
     * web service).
     */
    public Publisher<Boolean> isValidPhone(String phone) {
        return Flowable.just(PHONE_PATTERN.matcher(phone).matches());
    }

    /**
     * Maybe validation for one field is not enough ... maybe add more validation.
     * <p>
     * Again: Returns a publisher, since this might invoke a function on a remote
     * server (or a database).
     */
    public Publisher<Optional<String>> crossValidation(
            String firstName,
            String lastName,
            String eMail,
            @Nullable String phoneNumber) {
        if (firstName.isEmpty()) {
            return Flowable.just(Optional.of("First name is empty"));
        }
        if (lastName.isEmpty()) {
            return Flowable.just(Optional.of("Last name is empty"));
        }
        if (firstName.equals(lastName)) {
            return Flowable.just(Optional.of("Seems a bit strange, your first name is the " +
                    "same as your last name"));
        }
        if (firstName.length() < 3 && lastName.length() < 3) {
            return Flowable.just(Optional.of("I believe it's possible to have a very short " +
                    "first name or " +
                    "a very short last name (for example 'Ng') but not both!"));
        }
        if (!EMAIL_PATTERN.matcher(eMail).matches()) {
            return Flowable.just(Optional.of("Email address is invalid"));
        }
        if (phoneNumber != null) {
            if (!PHONE_PATTERN.matcher(phoneNumber).matches()) {
                return Flowable.just(Optional.of("Phone number seems to be invalid"));
            }
        }
        return Flowable.just(Optional.empty());
    }
}
