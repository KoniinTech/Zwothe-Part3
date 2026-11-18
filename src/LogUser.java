
package ZwothePoe1;

import java.util.Scanner;

public class LogUser {

    // CHECK USERNAME
    public static boolean checkUserName(String userName) {

        return userName != null
                && userName.contains("_")
                && userName.length() <= 5;
    }

    // CHECK PASSWORD COMPLEXITY
    public static boolean checkPasswordComplexity(String passWord) {

        if (passWord == null || passWord.length() < 8) {
            return false;
        }

        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;

        for (int i = 0; i < passWord.length(); i++) {

            char character = passWord.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }

            if (Character.isDigit(character)) {
                hasNumber = true;
            }

            if (!Character.isLetterOrDigit(character)) {
                hasSpecialCharacter = true;
            }
        }

        return hasCapital && hasNumber && hasSpecialCharacter;
    }

    // CHECK SOUTH AFRICAN INTERNATIONAL CELLPHONE NUMBER
    public static boolean checkCellPhoneNumber(String cellNumber) {

        return cellNumber != null
                && cellNumber.matches("\\+27[6-8][0-9]{8}");
    }

    // SIGNUP USER
    public static String signupUser(RegUser user) {

        if (!checkUserName(user.userName)) {
            return "Username is not correctly formatted; "
                    + "please ensure that your username contains "
                    + "an underscore and is no more than five "
                    + "characters in length.";
        }

        if (!checkPasswordComplexity(user.passWord)) {
            return "Password is not correctly formatted; "
                    + "please ensure that the password contains "
                    + "at least eight characters, a capital letter, "
                    + "a number, and a special character.";
        }

        if (!checkCellPhoneNumber(user.cellNumber)) {
            return "Cell number is incorrectly formatted or does "
                    + "not contain an international code; "
                    + "please correct the number and try again.";
        }

        return "Signup was successful!";
    }

    // SIGNIN USER
    public static boolean signinUser(
            RegUser user,
            Scanner input) {

        System.out.println("\nSIGNIN STEP");

        while (true) {

            System.out.print("Enter your username: ");
            if (!input.hasNextLine()) {
                return false;
            }
            String enteredUserName = input.nextLine();

            System.out.print("Enter your password: ");
            if (!input.hasNextLine()) {
                return false;
            }
            String enteredPassWord = input.nextLine();

            if (enteredUserName.equals(user.userName)
                    && enteredPassWord.equals(user.passWord)) {

                System.out.println(
                        "\n" + returnSigninStatus(user, true));

                return true;

            } else {

                System.out.println(
                        "\n" + returnSigninStatus(user, false));

                System.out.println("Please try again.\n");
            }
        }
    }

    // RETURN SIGNIN STATUS
    public static String returnSigninStatus(
            RegUser user,
            boolean signinSuccessful) {

        if (signinSuccessful) {
            return "Welcome "
                    + user.name
                    + ", "
                    + user.lastName
                    + " it is great to see you again.";
        }

        return "Username or password incorrect, please try again.";
    }
}
