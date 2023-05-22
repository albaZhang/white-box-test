public class BankAccount {

  private double balance;
  private boolean isLocked;
  private boolean isOverdrawn;

  public BankAccount(double initialBalance) {
    this.balance = initialBalance;
    this.isLocked = false;
    this.isOverdrawn = false;
  }

  public void deposit(double amount) {
    if (!isLocked) {
      if (amount > 0) {
        balance += amount;
      }
    }
  }

  public void withdraw(double amount) {
    if (!isLocked) {
      if (amount > 0) {
        if (balance >= amount) {
          balance -= amount;
        } else {
          isOverdrawn = true;
        }
      }
    }
  }

  public void lockAccount() {
    isLocked = true;
  }

  public void unlockAccount() {
    isLocked = false;
  }

  public boolean isOverdrawn() {
    return isOverdrawn;
  }

  public double getBalance() {
    return balance;
  }

  public boolean isLocked() {
    return isLocked;
  }
}
