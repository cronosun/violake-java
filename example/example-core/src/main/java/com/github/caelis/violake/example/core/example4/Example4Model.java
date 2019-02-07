package com.github.caelis.violake.example.core.example4;

import org.reactivestreams.Publisher;

import java.util.Optional;
import java.util.function.Function;

import io.reactivex.Flowable;
import io.reactivex.processors.BehaviorProcessor;

public final class Example4Model {

    private final Example4Service service;
    private final BehaviorProcessor<Optional<String>> firstName =
            BehaviorProcessor.createDefault(Optional.empty());
    private final BehaviorProcessor<Optional<String>> lastName =
            BehaviorProcessor.createDefault(Optional.empty());
    private final BehaviorProcessor<Optional<String>> eMail =
            BehaviorProcessor.createDefault(Optional.empty());
    private final BehaviorProcessor<Optional<String>> phone =
            BehaviorProcessor.createDefault(Optional.empty());

    private final BehaviorProcessor<Boolean> includePhone = BehaviorProcessor.createDefault(false);

    public Example4Model(Example4Service service) {
        this.service = service;
    }

    public void setFirstName(String firstName) {
        this.firstName.onNext(Optional.of(firstName));
    }

    public Publisher<Optional<String>> firstNameError() {
        return validation(firstName, service::isValidFirstName,
                "Invalid first name");
    }

    public Publisher<String> getFirstName() {
        return emptyIfMissing(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.onNext(Optional.of(lastName));
    }

    public Publisher<String> getLastName() {
        return emptyIfMissing(lastName);
    }

    public Publisher<Optional<String>> lastNameError() {
        return validation(lastName, service::isValidLastName,
                "Invalid last name");
    }

    public void setEMail(String eMail) {
        this.eMail.onNext(Optional.of(eMail));
    }

    public Publisher<Optional<String>> eMailError() {
        return validation(eMail, service::isValidEmail,
                "Does not seem to be a valid email");
    }

    public Publisher<String> getEMail() {
        return emptyIfMissing(eMail);
    }

    public void setPhone(String phone) {
        this.phone.onNext(Optional.of(phone));
    }

    public Publisher<Optional<String>> phoneError() {
        return validation(phone, service::isValidPhone,
                "Does not seem to be a valid phone");
    }

    public Publisher<String> getPhone() {
        return emptyIfMissing(phone);
    }

    public void reset() {
        firstName.onNext(Optional.empty());
        lastName.onNext(Optional.empty());
        eMail.onNext(Optional.empty());
        phone.onNext(Optional.empty());
        includePhone.onNext(false);
    }

    public Publisher<Boolean> isPhoneEntryVisible() {
        return includePhone;
    }

    public Publisher<Boolean> getIncludePhone() {
        return includePhone;
    }

    public void setIncludePhone(boolean includePhone) {
        this.includePhone.onNext(includePhone);
    }

    private Flowable<Optional<String>> validation(
            Publisher<Optional<String>> inputText,
            Function<String, Publisher<Boolean>> validator,
            String errorText) {

        return Flowable.fromPublisher(inputText).switchMap(maybeInputText -> {
            if (maybeInputText.isPresent()) {
                String localInputText = maybeInputText.get();
                return Flowable.fromPublisher(validator.apply(localInputText)).map(isValid -> {
                    if (isValid) {
                        return Optional.empty();
                    } else {
                        return Optional.of(errorText);
                    }
                });
            } else {
                return Flowable.just(Optional.empty());
            }
        });
    }

    private Publisher<String> emptyIfMissing(Flowable<Optional<String>> input) {
        return input.map(text -> text.orElse(""));
    }

    public Publisher<Boolean> canSubmit() {
        // TODO
        return firstName.map(s -> s.isPresent() && !s.get().isEmpty());
    }
}
