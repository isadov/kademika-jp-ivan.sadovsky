package day10.magazinediscountsystemtests;

import day10.magazinediscountsystem.DiscountSystem;
import mainprojects.magazine.justconstructor.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)

public class NewDiscountSystemTest extends Assert {

    DiscountSystem ds;

    @Before
    public void init() {
        ds = new DiscountSystem();
    }

    @Test
    public void checkDiscountSystemNotNull() {
        assertNotNull("Not Created New ds", ds);
    }

    @Test
    public void checkAddItem() {
        Transaction ts = new Transaction();
        ds.addItem(ts);

        assertEquals(ts, ds.getItemByIndex(0));
    }

    @Test
    public void checkRemoveItem() {
        Transaction ts = new Transaction();

        ds.addItem(ts);
        ds.removeItem();

        assertNull(null, ds.getItemByIndex(0));
    }

    @Test
    public void checkRemoveAllItem() {
        Transaction ts = new Transaction();
        Transaction ts1 = new Transaction();
        Transaction ts2 = new Transaction();

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        ds.removeAllItem();

        assertNotNull(null, ds.getItemByIndex(0));
    }

    @Test
    public void checkGetItemByIndex() {
        assertNull(null, ds.getItemByIndex(0));
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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(75, ds.getSum(), 0.0001);
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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(0, ds.getDiscount());
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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(5, ds.getDiscount());

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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(10, ds.getDiscount());

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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(6, ds.getTotalSum(), 0.0001);

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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(71.25, ds.getTotalSum(), 0.0001);

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

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(9045, ds.getTotalSum(), 0.0001);

    }

    @Test
    public void checkSizeOfDiscount() {
        Transaction ts = new Transaction();
        Transaction ts1 = new Transaction();
        Transaction ts2 = new Transaction();

        ds.addItem(ts);
        ds.addItem(ts1);
        ds.addItem(ts2);

        assertEquals(3, ds.getSizeOfContainer());
    }


}
