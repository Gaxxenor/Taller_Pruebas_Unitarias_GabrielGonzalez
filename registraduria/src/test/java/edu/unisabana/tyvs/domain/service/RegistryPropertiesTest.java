package edu.unisabana.tyvs.domain.service;

import net.jqwik.api.constraints.IntRange;
import edu.unisabana.tyvs.domain.model.Gender;
import edu.unisabana.tyvs.domain.model.Person;
import edu.unisabana.tyvs.domain.model.RegisterResult;
import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;

public class RegistryPropertiesTest {

    private final Registry registry = new Registry();

    // Bug 1: Edad inválida (negativa o cero)
    @Property
    public void testInvalidAgeNegativeOrZero(@ForAll @IntRange(min = -100, max = 0) int age) {
        Person person = new Person("InvalidAge", 101, age, Gender.MALE, true);
        Assertions.assertEquals(RegisterResult.INVALID_AGE, registry.registerVoter(person));
    }

    // Bug 2: Persona menor de edad (1 a 17 años)
    @Property
    public void testUnderageReturnsInvalidAge(@ForAll @IntRange(min = 1, max = 17) int age) {
        Person person = new Person("Underage", 102, age, Gender.MALE, true);
        Assertions.assertEquals(RegisterResult.INVALID_AGE, registry.registerVoter(person));
    }

    // Bug 3: Persona fallecida (debe retornar DEAD sin importar edad válida)
    @Property
    public void testDeadPersonReturnsDead(@ForAll @IntRange(min = 18, max = 100) int age) {
        Person person = new Person("DeadPerson", 103, age, Gender.FEMALE, false);
        Assertions.assertEquals(RegisterResult.DEAD, registry.registerVoter(person));
    }

    // Bug 4: Persona válida (edad >= 18 y viva)
    @Property
    public void testValidVoterReturnsValid(@ForAll @IntRange(min = 18, max = 100) int age) {
        Person person = new Person("ValidVoter", 104, age, Gender.MALE, true);
        Assertions.assertEquals(RegisterResult.VALID, registry.registerVoter(person));
    }
}
