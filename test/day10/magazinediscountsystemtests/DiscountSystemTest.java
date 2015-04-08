package day10.magazinediscountsystemtests;

import day10.magazinediscountsystem.DiscountSystem;
import mainprojects.magazine.justconstructor.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)

// написать сортировку ДЗ. Не делать
// Вывести иерархию... ДЗ. Не делать НО если интересно...


public class DiscountSystemTest {

    DiscountSystem discountSystem;

    @Before
    public void init() {
        discountSystem = new DiscountSystem();
    }

    @Test
    public void checkDiscountSystemNotNull() {
        assertNotNull("Not Created New ds", discountSystem);
    }

    @Test
    public void checkAddItem() {
        Transaction ts = new Transaction();
        discountSystem.addItem(ts);

        assertEquals(ts, discountSystem.getItemByIndex(0));
    }

    @Test
    public void checkRemoveItem() {
        Transaction ts = new Transaction();

        discountSystem.addItem(ts);
        discountSystem.removeItem();

        assertNull(discountSystem.getItemByIndex(0));
    }

    @Test
    public void checkRemoveAllItem() {
        Transaction ts = new Transaction();
        Transaction ts1 = new Transaction();
        Transaction ts2 = new Transaction();

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        discountSystem.removeAllItem();

        System.out.println("Test !" + discountSystem.getItemByIndex(0));

        assertNull("Should Be Null", discountSystem.getItemByIndex(0));
    }

    @Test
    public void checkGetItemByIndex() {

        assertNull(discountSystem.getItemByIndex(0));
    }

    @Test
    public void checkSum() {
        Transaction ts = new Transaction();
        ts.setQuantity(5);
        ts.setPrice(5);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(5);
        ts1.setPrice(5);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(5);
        ts2.setPrice(5);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(75, discountSystem.getSum(), 0);//
    }

    @Test
    public void checkDiscountWhen0() {
        Transaction ts = new Transaction();
        ts.setQuantity(1);
        ts.setPrice(2);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(1);
        ts1.setPrice(2);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(1);
        ts2.setPrice(2);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(0, discountSystem.getDiscount());
    }

    @Test
    public void checkDiscountWhen5() {
        Transaction ts = new Transaction();
        ts.setQuantity(5);
        ts.setPrice(5);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(5);
        ts1.setPrice(5);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(5);
        ts2.setPrice(5);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(5, discountSystem.getDiscount());

    }

    @Test
    public void checkDiscountWhen10() {
        Transaction ts = new Transaction();
        ts.setQuantity(5);
        ts.setPrice(5);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(5);
        ts1.setPrice(5);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(100);
        ts2.setPrice(100);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(10, discountSystem.getDiscount());

    }

    @Test
    public void checkTotalSumWhenDiscount0() {
        Transaction ts = new Transaction();
        ts.setQuantity(2);
        ts.setPrice(1);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(2);
        ts1.setPrice(1);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(2);
        ts2.setPrice(1);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(6, discountSystem.getTotalSum(), 0.0001);

    }

    @Test
    public void checkTotalSumWhenDiscount5() {
        Transaction ts = new Transaction();
        ts.setQuantity(5);
        ts.setPrice(5);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(5);
        ts1.setPrice(5);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(5);
        ts2.setPrice(5);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(71.25, discountSystem.getTotalSum(), 0.0001);

    }

    @Test
    public void checkTotalSumWhenDiscount10() {
        Transaction ts = new Transaction();
        ts.setQuantity(5);
        ts.setPrice(5);

        Transaction ts1 = new Transaction();
        ts1.setQuantity(5);
        ts1.setPrice(5);

        Transaction ts2 = new Transaction();
        ts2.setQuantity(100);
        ts2.setPrice(100);

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(9045, discountSystem.getTotalSum(), 0.0001);

    }

    @Test
    public void checkSizeOfDiscount() {
        Transaction ts = new Transaction();
        Transaction ts1 = new Transaction();
        Transaction ts2 = new Transaction();

        discountSystem.addItem(ts);
        discountSystem.addItem(ts1);
        discountSystem.addItem(ts2);

        assertEquals(3, discountSystem.getSizeOfContainer());
    }


}
