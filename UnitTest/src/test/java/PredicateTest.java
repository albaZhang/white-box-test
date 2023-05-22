import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {

  private BankAccount bankAccount;
  Logger logger = Logger.getLogger(UnitTest.class);
  private boolean isAllPass;

  @Before
  public void initial() {
    isAllPass = true;
    bankAccount = new BankAccount(100);
  }

  @Test
  public void testDeposit() {

    try {
      bankAccount.deposit(50);
      assertEquals(150, bankAccount.getBalance(), 0);

      bankAccount.deposit(-100);
      assertEquals(150, bankAccount.getBalance(), 0);

      bankAccount.lockAccount();
      bankAccount.deposit(50);
      assertEquals(150, bankAccount.getBalance(), 0);

    } catch (AssertionError e) {
      isAllPass = false;
      logger.error(e.getMessage());
    }
  }

  @Test
  public void testWithdraw() {
    try {
      bankAccount.withdraw(50);
      assertEquals(50, bankAccount.getBalance(), 0);
      assertFalse(bankAccount.isOverdrawn());

      bankAccount.withdraw(-50);
      assertEquals(50, bankAccount.getBalance(), 0);
      assertFalse(bankAccount.isOverdrawn());

      bankAccount.withdraw(500);
      assertEquals(50, bankAccount.getBalance(), 0);
      assertTrue(bankAccount.isOverdrawn());

      bankAccount.lockAccount();
      bankAccount.withdraw(50);
      assertEquals(50, bankAccount.getBalance(), 0);

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
