
package ZwothePoe1;

import java.util.Scanner;

public class RegUser {

    // Variables
    String name;
    String lastName;
    String userName;
    String passWord;
    String cellNumber;

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        RegUser user = new RegUser();

        // NAME
        System.out.print("Enter your name: ");
        user.name = input.nextLine().trim();

        while (user.name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter your name: ");
            user.name = input.nextLine().trim();
        }

        // LAST NAME
        System.out.print("Enter your last name: ");
        user.lastName = input.nextLine().trim();

        while (user.lastName.isEmpty()) {
            System.out.print(
                    "Last name cannot be empty. Enter your last name: ");
            user.lastName = input.nextLine().trim();
        }

        // USERNAME
        while (true) {

            System.out.print("Enter your username: ");
            user.userName = input.nextLine();

            if (LogUser.checkUserName(user.userName)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println(
                        "Username is not correctly formatted; "
                        + "please ensure that your username contains "
                        + "an underscore and is no more than five "
                        + "characters in length.");
            }
        }

        // PASSWORD
        while (true) {

            System.out.print("Enter your password: ");
            user.passWord = input.nextLine();

            if (LogUser.checkPasswordComplexity(user.passWord)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println(
                        "Password is not correctly formatted; "
                        + "please ensure that the password contains "
                        + "at least eight characters, a capital letter, "
                        + "a number, and a special character.");
            }
        }

        // CELLPHONE NUMBER
        while (true) {

            System.out.print("Enter your cellphone number: ");
            user.cellNumber = input.nextLine().trim();

            if (LogUser.checkCellPhoneNumber(user.cellNumber)) {
                System.out.println("Cell number successfully captured.");
                break;
            } else {
                System.out.println(
                        "Cell number is incorrectly formatted or does "
                        + "not contain an international code; "
                        + "please correct the number and try again.");
            }
        }

        // SIGNUP
        System.out.println("\n" + LogUser.signupUser(user));

        // SIGNIN
        boolean signedIn = LogUser.signinUser(user, input);

        // START QUICKCHAT AFTER SUCCESSFUL SIGNIN
        if (signedIn) {
            Message.startQuickChat(input, user.cellNumber);
        }

        input.close();
    }
}
