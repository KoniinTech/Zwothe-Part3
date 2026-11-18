
package ZwothePoe1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegUserTest {

    private RegUser user;

    public RegUserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        user = new RegUser();

        user.name = "Zwothe";
        user.lastName = "Nedzi";
        user.userName = "Z_01";
        user.passWord = "Password1!";
        user.cellNumber = "+27838968976";
    }

    @After
    public void tearDown() {
    }

    // ==========================================
    // USERNAME VALIDATION TESTS
    // ==========================================

    @Test
    public void testUserNameIsCorrectlyFormatted() {
        assertTrue(LogUser.checkUserName("Z_01"));
    }

    @Test
    public void testUserNameIsIncorrectlyFormatted() {
        assertFalse(LogUser.checkUserName("Zwothe"));
    }

    @Test
    public void testUserNameWithOnlyUnderscore() {
        assertTrue(LogUser.checkUserName("_"));
    }

    @Test
    public void testUserNameWithoutUnderscore() {
        assertFalse(LogUser.checkUserName("Z001"));
    }

    @Test
    public void testUserNameLongerThanFiveCharacters() {
        assertFalse(LogUser.checkUserName("Z_0123"));
    }

    @Test
    public void testUserNameIsEmpty() {
        assertFalse(LogUser.checkUserName(""));
    }

    // ==========================================
    // PASSWORD VALIDATION TESTS
    // ==========================================

    @Test
    public void testPasswordComplexityIsCorrect() {
        assertTrue(
                LogUser.checkPasswordComplexity("Password1!")
        );
    }

    @Test
    public void testPasswordComplexityIsIncorrect() {
        assertFalse(
                LogUser.checkPasswordComplexity("password")
        );
    }

    @Test
    public void testPasswordWithoutCapitalLetter() {
        assertFalse(
                LogUser.checkPasswordComplexity("password1!")
        );
    }

    @Test
    public void testPasswordWithoutNumber() {
        assertFalse(
                LogUser.checkPasswordComplexity("Password!")
        );
    }

    @Test
    public void testPasswordWithoutSpecialCharacter() {
        assertFalse(
                LogUser.checkPasswordComplexity("Password1")
        );
    }

    @Test
    public void testPasswordShorterThanEightCharacters() {
        assertFalse(
                LogUser.checkPasswordComplexity("Pa1!")
        );
    }

    @Test
    public void testPasswordIsEmpty() {
        assertFalse(
                LogUser.checkPasswordComplexity("")
        );
    }

    // ==========================================
    // CELLPHONE VALIDATION TESTS
    // ==========================================

    @Test
    public void testCellPhoneNumberIsCorrectlyFormatted() {
        assertTrue(
                LogUser.checkCellPhoneNumber("+27838968976")
        );
    }

    @Test
    public void testCellPhoneNumberIsIncorrectlyFormatted() {
        assertFalse(
                LogUser.checkCellPhoneNumber("0838968976")
        );
    }

    @Test
    public void testCellPhoneNumberWithLetters() {
        assertFalse(
                LogUser.checkCellPhoneNumber("+27838ABC976")
        );
    }

    @Test
    public void testCellPhoneNumberIsEmpty() {
        assertFalse(
                LogUser.checkCellPhoneNumber("")
        );
    }

    // ==========================================
    // REGISTRATION TESTS
    // ==========================================

    @Test
    public void testSignupUserSuccessful() {

        String result = LogUser.signupUser(user);

        assertEquals(
                "Signup was successful!",
                result
        );
    }

    @Test
    public void testSignupUserWithIncorrectUsername() {

        user.userName = "Zwothe";

        String result = LogUser.signupUser(user);

        assertEquals(
                "Username is not correctly formatted; "
                + "please ensure that your username contains "
                + "an underscore and is no more than five "
                + "characters in length.",
                result
        );
    }

    @Test
    public void testSignupUserWithIncorrectPassword() {

        user.passWord = "password";

        String result = LogUser.signupUser(user);

        assertEquals(
                "Password is not correctly formatted; "
                + "please ensure that the password contains "
                + "at least eight characters, a capital letter, "
                + "a number, and a special character.",
                result
        );
    }

    @Test
    public void testSignupUserWithIncorrectCellNumber() {

        user.cellNumber = "0838968976";

        String result = LogUser.signupUser(user);

        assertEquals(
                "Cell number is incorrectly formatted or does "
                + "not contain an international code; "
                + "please correct the number and try again.",
                result
        );
    }

    @Test
    public void testSignupWithEmptyUsername() {

        user.userName = "";

        String result = LogUser.signupUser(user);

        assertEquals(
                "Username is not correctly formatted; "
                + "please ensure that your username contains "
                + "an underscore and is no more than five "
                + "characters in length.",
                result
        );
    }

    @Test
    public void testSignupWithEmptyPassword() {

        user.passWord = "";

        String result = LogUser.signupUser(user);

        assertEquals(
                "Password is not correctly formatted; "
                + "please ensure that the password contains "
                + "at least eight characters, a capital letter, "
                + "a number, and a special character.",
                result
        );
    }

    // ==========================================
    // LOGIN STATUS TESTS
    // ==========================================

    @Test
    public void testSigninStatusSuccessful() {

        String result =
                LogUser.returnSigninStatus(user, true);

        assertEquals(
                "Welcome Zwothe, Nedzi it is great to see you again.",
                result
        );
    }

    @Test
    public void testSigninStatusUnsuccessful() {

        String result =
                LogUser.returnSigninStatus(user, false);

        assertEquals(
                "Username or password incorrect, please try again.",
                result
        );
    }

    // ==========================================
    // LOGIN VALIDATION TESTS
    // ==========================================

    @Test
    public void testSigninWithIncorrectUsername() {

        String loginInput =
                "Wrong_\nPassword1!\n";

        InputStream originalInput = System.in;

        try {

            System.setIn(
                    new ByteArrayInputStream(
                            loginInput.getBytes()
                    )
            );

            Scanner input = new Scanner(System.in);

            boolean result =
                    LogUser.signinUser(user, input);

            assertFalse(result);

            input.close();

        } finally {

            System.setIn(originalInput);
        }
    }

    @Test
    public void testSigninWithIncorrectPassword() {

        String loginInput =
                "Z_01\nWrongPassword1!\n";

        InputStream originalInput = System.in;

        try {

            System.setIn(
                    new ByteArrayInputStream(
                            loginInput.getBytes()
                    )
            );

            Scanner input = new Scanner(System.in);

            boolean result =
                    LogUser.signinUser(user, input);

            assertFalse(result);

            input.close();

        } finally {

            System.setIn(originalInput);
        }
    }

    // ==========================================
    // INTEGRATION TEST: SIGNUP AND SIGNIN
    // ==========================================

    @Test
    public void testSignupAndSigninIntegration() {

        String signupResult = LogUser.signupUser(user);

        assertEquals(
                "Signup was successful!",
                signupResult
        );

        String loginInput =
                user.userName + "\n"
                + user.passWord + "\n";

        InputStream originalInput = System.in;

        try {

            System.setIn(
                    new ByteArrayInputStream(
                            loginInput.getBytes()
                    )
            );

            Scanner input = new Scanner(System.in);

            boolean signinResult =
                    LogUser.signinUser(user, input);

            assertTrue(signinResult);

            input.close();

        } finally {

            System.setIn(originalInput);
        }
    }

    // ==========================================
    // INTEGRATION TEST: INCORRECT PASSWORD
    // ==========================================

    @Test
    public void testSignupThenSigninWithIncorrectPassword() {

        String signupResult = LogUser.signupUser(user);

        assertEquals(
                "Signup was successful!",
                signupResult
        );

        String loginInput =
                user.userName + "\n"
                + "WrongPassword1!\n";

        InputStream originalInput = System.in;

        try {

            System.setIn(
                    new ByteArrayInputStream(
                            loginInput.getBytes()
                    )
            );

            Scanner input = new Scanner(System.in);

            boolean signinResult =
                    LogUser.signinUser(user, input);

            assertFalse(signinResult);

            input.close();

        } finally {

            System.setIn(originalInput);
        }
    }
}
