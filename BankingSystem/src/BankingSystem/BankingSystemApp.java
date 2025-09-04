package BankingSystem;

import java.util.*;

public class BankingSystemApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankingService service = new BankingServiceImpl();

        while (true) {
            System.out.println("Banking System");
            System.out.println("1. Add Customer");
            System.out.println("2. Add Account");
            System.out.println("3. Add Beneficiary");
            System.out.println("4. Add Transaction");
            System.out.println("5. Find Customer by Id");
            System.out.println("6. List all Accounts of a Customer");
            System.out.println("7. List all Transactions of an Account");
            System.out.println("8. List all Beneficiaries of a Customer");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Customer Id: ");
                    int cid = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String cname = sc.nextLine();
                    System.out.print("Address: "); String addr = sc.nextLine();
                    System.out.print("Contact: "); String contact = sc.nextLine();
                    service.addCustomer(new Customer(cid, cname, addr, contact));
                    System.out.println("Your Detail has been Added");
                    break;

                case 2:
                    System.out.print("Account Id: ");
                    int aid = sc.nextInt();
                    System.out.print("Customer Id: ");
                    int custId = sc.nextInt(); sc.nextLine();
                    System.out.print("Type (Saving/Current): ");
                    String type = sc.nextLine();
                    System.out.print("Balance: ");
                    double bal = sc.nextDouble();
                    service.addAccount(new Account(aid, custId, type, bal));
                    System.out.println("your balance is (Initial Payment):"+bal);
                    break;

                case 3:
                    System.out.print("Beneficiary Id: ");
                    int bid = sc.nextInt();
                    System.out.print("Customer Id: ");
                    int bcid = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: "); String bname = sc.nextLine();
                    System.out.print("Account Number: "); String accNo = sc.nextLine();
                    System.out.print("Bank Details: "); String bank = sc.nextLine();
                    service.addBeneficiary(new Beneficiary(bid, bcid, bname, accNo, bank));
                    break;

                case 4:
                    System.out.print("Transaction Id: ");
                    int tid = sc.nextInt();
                    System.out.print("Account Id: ");
                    int tacc = sc.nextInt(); sc.nextLine();
                    System.out.print("Type (Deposit/Withdrawal): ");
                    String ttype = sc.nextLine();
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    service.addTransaction(new Transaction(tid, tacc, ttype, amt));
                    System.out.println("Transaction of "+amt+" has been "+ttype);
                    break;

                case 5:
                    System.out.print("Enter Customer Id: ");
                    int findId = sc.nextInt();
                    System.out.println(service.findCustomerById(findId));
                    break;

                case 6:
                    System.out.print("Enter Customer Id: ");
                    int custAccId = sc.nextInt();
                    System.out.println("Accounts for Customer ID: " + custAccId);
                    for (Account a : service.getAccountsByCustomerId(custAccId)) {
                        System.out.println(a);
                    }
                    break;

                case 7:
                    System.out.print("Enter Account Id: ");
                    int accId = sc.nextInt();
                    System.out.println("Transactions for Account ID: " + accId);
                    for (Transaction t : service.getTransactionsByAccountId(accId)) {
                        System.out.println(t);
                    }
                    break;

                case 8:
                    System.out.print("Enter Customer Id: ");
                    int custBenId = sc.nextInt();
                    System.out.println("Beneficiaries for Customer ID: " + custBenId);
                    for (Beneficiary b : service.getBeneficiariesByCustomerId(custBenId)) {
                        System.out.println(b);
                    }
                    break;

                case 9:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
