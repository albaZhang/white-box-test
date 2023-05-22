import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElementPathTest {

  private BankAccount bankAccount;
  Logger logger = Logger.getLogger(UnitTest.class);
  private boolean isAllPass;

  @Before
  public void initial() {
    isAllPass = true;
    bankAccount = new BankAccount(1000);
  }

  @Test
  public void testDeposit() {

    try {
      bankAccount.deposit(100);
      assertEquals(1100, bankAccount.getBalance(), 0);

      bankAccount = new BankAccount(-100);
      bankAccount.deposit(500);
      assertEquals(400, bankAccount.getBalance(), 0);

      bankAccount.isLocked();
      bankAccount.deposit(100);
      assertEquals(500, bankAccount.getBalance(), 0);

    } catch (AssertionError e) {
      isAllPass = false;
      logger.error(e.getMessage());
    }
  }

  @Test
  public void testWithdraw() {
    try {
      bankAccount.withdraw(500);
      assertEquals(500, bankAccount.getBalance(), 0);
      assertFalse(bankAccount.isOverdrawn());

      bankAccount = new BankAccount(-100);
      bankAccount.withdraw(500);
      assertEquals(-100, bankAccount.getBalance(), 0);
      assertTrue(bankAccount.isOverdrawn());

      bankAccount.withdraw(500);
      assertEquals(-100, bankAccount.getBalance(), 0);
      assertTrue(bankAccount.isOverdrawn());


      bankAccount = new BankAccount(1000);
      bankAccount.withdraw(1500);
      assertEquals(1000, bankAccount.getBalance(), 0);
      assertTrue(bankAccount.isOverdrawn());

      bankAccount.isLocked();
      bankAccount.withdraw(500);
      assertEquals(500, bankAccount.getBalance(), 0);

    } catch (AssertionError e) {
      isAllPass = false;
      logger.error(e.getMessage());
    }
  }


  @Test
  public void testLockAccount() {
    try {
      bankAccount.lockAccount();
      assertTrue(bankAccount.isLocked());

    } catch (AssertionError e) {
      isAllPass = false;
      logger.error(e.getMessage());
    }
  }

  @After
  public void ultimate() {
    if (isAllPass)
      logger.info("所有测试用例均成功通过");
    else
      logger.error("有未通过测试的用例");
  }
}
