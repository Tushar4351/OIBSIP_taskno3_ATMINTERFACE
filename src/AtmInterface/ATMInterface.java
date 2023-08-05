package AtmInterface;

import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ATMInterface {
    String fullName;
    String dateOfBirth;
    String gender;
    String maleOption = "M";
    String maleOption2 = "m";
    String femaleOption = "F";
    String femaleOption2 = "f";
    String userName;
    String accountNumber;
    String password;
    String pin;
    String phoneNumber;
    String transactionHistory = "Your Transaction History: \n";
    Scanner input = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    float balance = 0f;
    int transactions = 0;

    public void viewProfile() {
        System.out.println("Your Profile: \n");
        System.out.println("Full-Name: " + this.fullName);
        System.out.println("Date of Birth: " + this.dateOfBirth);
        System.out.println("Gender: " + this.gender);
        System.out.println("Username: " + this.userName);
        System.out.println("Password: " + this.password);
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("4-Digit-Pin (Don't share with anyone): " + this.pin);
        System.out.println("Phone Number: " + this.phoneNumber);
    }

    public void register() {
        System.out.println("...Registration...");
        System.out.print("Enter Full Name: ");
        this.fullName = input.nextLine();
        System.out.print("Enter Date of Birth (dd/mm/yyyy): ");
        this.dateOfBirth = input.nextLine();
        System.out.print("Enter Gender (M/F): ");
        this.gender = input.nextLine();
        while (!((this.gender).equals(maleOption) || (this.gender).equals(maleOption2) ||
                (this.gender).equals(femaleOption) || (this.gender).equals(femaleOption2))) {
            System.out.print("Invalid...Please enter correct Gender (M/F): ");
            this.gender = input.nextLine();
        }
        if (this.gender.equals(maleOption) || this.gender.equals(maleOption2)) {
            this.gender = "Male";
        } else {
            this.gender = "Female";
        }

        System.out.print("Enter Username: ");
        this.userName = input.nextLine();
        System.out.print("Enter Password: ");
        this.password = input.nextLine();
        System.out.print("Enter Account Number: ");
        this.accountNumber = input.nextLine();
        while ((accountNumber.length()) != 10) {
            System.out.print("!!! Please Enter Valid 10 Digit Account Number: ");
            this.accountNumber = input.nextLine();
        }
        System.out.print("Enter 4-Digit-Pin: ");
        this.pin = input.nextLine();
        while ((pin.length()) != 4) {
            System.out.print("!!! Please Enter Valid 4 Digit Pin: ");
            this.pin = input.nextLine();
        }
        System.out.print("Enter Phone Number: ");
        this.phoneNumber = input.nextLine();
        while ((phoneNumber.length()) != 10) {
            System.out.print("!!! Please Enter Valid 10 Digit Mobile Number: ");
            this.phoneNumber = input.nextLine();
        }
        System.out.println("Your are Successfully Registered...");
        System.out.println("Kindly login to your account to perform any transactions...");
    }

    public boolean login() {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter Account Number : ");
            String acctNumber = input.nextLine();
            if (acctNumber.equals(accountNumber)) {
                System.out.print("Enter 4 Digit Pin : ");
                String pinpass = input.nextLine();
                System.out.print("Confirm Your 4 Digit Pin : ");
                String conpin = input.nextLine();
                if (conpin.equals(pinpass)) {
                    while (!loggedIn) {
                        if (pinpass.equals(pin)) {
                            System.out.println("Logged-in Successfully!!");
                            loggedIn = true;
                        } else {
                            System.out.println("Enter correct pin!!");
                            break;
                        }
                    }
                } else {
                    System.out.println("Please enter the same pin twice...");
                }
            } else {
                System.out.println("Register to login...");
            }
        }
        return loggedIn;
    }

    public void deposit() {
        System.out.print("Enter amount to deposit into your Account: ");
        float depositAmount = input.nextFloat();
        if (depositAmount > 500000f) {
            System.out.println("This ATM is not capable to deposit money beyond 5,00,000/-");
        } else {
            balance += depositAmount;
            transactions++;
            System.out.println("Successfully deposited Rs. " + depositAmount + " into your account at " + dtf.format(now));
            System.out.println("Your Current Balance after this transaction is: " + balance);
            String history = " ";
            history = "\n Rs " + depositAmount + " deposited at " + dtf.format(now) + " .\n";
            transactionHistory = transactionHistory.concat(history);
        }
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw from your account: ");
        float withdrawAmount = input.nextFloat();
        if (withdrawAmount < 500000) {
            if (withdrawAmount > balance) {
                System.out.println("Sorry! Insufficient Balance...");
            } else {
                balance -= withdrawAmount;
                transactions++;
                System.out.println("Successfully withdrawn Rs. " + withdrawAmount + " from your account at " + dtf.format(now));
                System.out.println("Your Current Balance after this transaction is: " + balance);
                String history = " ";
                history = "\n Rs " + withdrawAmount + " withdrawn at " + dtf.format(now) + " .\n";
                transactionHistory = transactionHistory.concat(history);
            }
        } else {
            System.out.println("This ATM cannot dispense Money more than 5,00,000/-");
        }
    }

    public void transfer() {
        System.out.println("Enter the Username to transfer: ");
        String userToTransfer = input.nextLine();
        System.out.print("Enter the amount to transfer to " + userToTransfer + ": ");
        float totalAmount = input.nextFloat();
        if (totalAmount < 500000f) {
            if (totalAmount > balance) {
                System.out.println("Sorry! Amount can't be transferred due to Insufficient Balance.");
                System.out.println("Your Balance was: " + balance);
            } else {
                balance -= totalAmount;
                transactions++;
                System.out.println("Successfully transferred Rs. " + totalAmount + " to " + userToTransfer +
                        " from your account at " + dtf.format(now));
                System.out.println("Your Current Balance after this transaction is: " + balance);
                String history = " ";
                history = "\n Rs " + totalAmount + " was transferred to " + userToTransfer +
                        "'s account from your account at " + dtf.format(now) + " .\n";
                transactionHistory = transactionHistory.concat(history);
            }
        } else {
            System.out.println("Cannot transfer money beyond 5,00,000/-");
        }
    }

    public void transactionHistory() {
        if (transactions == 0) {
            System.out.println("No Transactions");
        } else {
            System.out.println(transactionHistory);
        }
    }

    public void checkBalance() {
        System.out.println("\nBalance amount is: " + balance);
    }

    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------Welcome to ATM-----------");
        System.out.println("\nSelect any one option from the below:");
        System.out.println("1. Register (New Users should Register First)\n2. Exit");
        System.out.print("Your choice: ");
        int choice = input.nextInt();
        if (choice == 1) {
            ATMInterface atm = new ATMInterface();
            atm.register();
            while (choice == 1) {
                System.out.println("\n Select any one option: ");
                System.out.println("1. Login (If already Registered)\n2. Exit");
                System.out.print("Your option: ");
                int option = input.nextInt();
                if (option == 1) {
                    if (atm.login()) {
                        while (true) {
                            System.out.println("\n\nThis ATM is able to perform the following operations: ");
                            System.out.println("1. View Profile\n2. Deposit\n3. Withdraw\n4. Transfer" +
                                    "\n5. Transaction History\n6. Check Balance\n7. Exit");
                            System.out.print("Enter your option: ");
                            int select = input.nextInt();
                            switch (select) {
                                case 1:
                                    atm.viewProfile();
                                    break;
                                case 2:
                                    atm.deposit();
                                    break;
                                case 3:
                                    atm.withdraw();
                                    break;
                                case 4:
                                    atm.transfer();
                                    break;
                                case 5:
                                    atm.transactionHistory();
                                    break;
                                case 6:
                                    atm.checkBalance();
                                    break;
                                case 7:
                                    System.out.println("\nThank you...Visit Again...:)");
                                    System.exit(0);
                                default:
                                    System.out.println("Invalid option!...Enter Valid option...");
                            }
                        }
                    }
                } else {
                    System.out.println("\nThank you...Visit Again...:)");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("\nThank you...Visit Again...:)");
            System.exit(0);
        }
    }
}
