public class ProxyBankAccount {

  private BankAccount bankAccount;
  private double lastBalance;

  public ProxyBankAccount (BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    this.lastBalance = bankAccount.getBalance();
  }

  public void deposit(double amount) {
    bankAccount.deposit(amount);
    logDeposit(amount);

  }
  public void withdraw(double amount) {
    bankAccount.withdraw(amount);
  }

  public void logDeposit(double amount) {

  }
}
