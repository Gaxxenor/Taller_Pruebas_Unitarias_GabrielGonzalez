package edu.unisabana.tyvs.domain.service;

import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegistryTest {

    private final Registry registry = new Registry();

    @Test
    public void validateRegistryResultValid() {
        Person person = new Person("Juan", 1001, 20, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assertions.assertEquals(RegisterResult.VALID, result);
    }

    @Test
    public void validateRegistryResultUnderage() {
        Person person = new Person("Pedro", 1002, 17, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assertions.assertEquals(RegisterResult.INVALID_AGE, result);
    }

    @Test
    public void validateRegistryResultDead() {
        Person person = new Person("Maria", 1003, 30, Gender.FEMALE, false);
        RegisterResult result = registry.registerVoter(person);
        Assertions.assertEquals(RegisterResult.DEAD, result);
    }

    @Test
    public void validateRegistryResultInvalidAgeNegative() {
        Person person = new Person("Carlos", 1004, -5, Gender.MALE, true);
        RegisterResult result = registry.registerVoter(person);
        Assertions.assertEquals(RegisterResult.INVALID_AGE, result);
    }
}