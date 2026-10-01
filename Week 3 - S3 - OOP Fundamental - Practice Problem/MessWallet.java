import java.util.Scanner;

public class MessWallet {

    private double balance;

    /**
     * Creates a wallet with an opening balance. A negative opening balance is
     * corrected to 0 and reported, because the balance can never go negative.
     *
     * @param openingBalance initial balance
     */
    public MessWallet(double openingBalance) {
        if (openingBalance < 0) {
            System.out.println("Warning: opening balance cannot be negative, starting at 0.0");
            this.balance = 0.0;
        } else {
            this.balance = openingBalance;
        }
    }

    /**
     * Adds money to the wallet.
     *
     * @param amount amount to add
     */
    public void topUp(double amount) {
        if (amount <= 0) {
            System.out.println("Top-up rejected: amount must be greater than 0");
            return;
        }

        balance += amount;
        System.out.println("Balance after top-up: " + balance);
    }

    /**
     * Removes money from the wallet, rejecting any amount larger than the
     * current balance so the balance never goes negative.
     *
     * @param amount amount to deduct
     */
    public void deduct(double amount) {
        if (amount <= 0) {
            System.out.println("Deduct rejected: amount must be greater than 0");
            return;
        }

        if (amount > balance) {
            System.out.println("Deduct rejected: insufficient balance");
            return;
        }

        balance -= amount;
        System.out.println("Balance after deduct: " + balance);
    }

    /**
     * Read-only access to the balance. There is deliberately no setter, so the
     * balance can never be overwritten from outside the class.
     *
     * @return current balance
     */
    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter opening balance: ");
        double openingBalance = scanner.nextDouble();

        MessWallet wallet = new MessWallet(openingBalance);

        System.out.print("Enter top-up amount: ");
        wallet.topUp(scanner.nextDouble());

        System.out.print("Enter amount to deduct: ");
        wallet.deduct(scanner.nextDouble());

        System.out.println("Final balance: " + wallet.getBalance());

        scanner.close();
    }
}