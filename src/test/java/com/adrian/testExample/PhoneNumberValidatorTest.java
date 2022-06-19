package com.adrian.testExample;

import com.adrian.testExample.utils.PhoneNumberValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberValidatorTest {

    private PhoneNumberValidator phoneNumberValidator;

    @BeforeEach
    void setUp() {
        phoneNumberValidator = new PhoneNumberValidator();
    }

    @ParameterizedTest
    @CsvSource({
            "+447000000000,true",
            "+44700000000088878, false",
            "447000000000, false"
    }) // each line is one test
    @DisplayName("Validate phone number") // informative data
    void itShouldValidatePhoneNumber(String phoneNumber, boolean expected) {
        // When
        boolean isValid = phoneNumberValidator.test(phoneNumber);

        // Then
        assertThat(isValid).isEqualTo(expected);
    }

}