import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class SILab2Test {
    //Every Statement
    @Test
    public void test1() {
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, ""));
        assertEquals(ex.getMessage(),"allItems list can't be null!");
    }
    @Test
    public void test2() {
        Item item1 = new Item(null, 1,300,1);
        List<Item> items = List.of(item1);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, ""));
        assertEquals(ex.getMessage(),"Invalid item!");
    }
    @Test
    public void test3() {
        Item item1 = new Item("name", 1,300,1);
        List<Item> items = List.of(item1);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, null));
        assertEquals(ex.getMessage(),"Invalid card number!");
    }
    @Test
    public void test4() {
        Item item1 = new Item("name", 1,300,0);
        List<Item> items = List.of(item1);
        RuntimeException ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items, "a000000000000000"));
        assertEquals(ex.getMessage(),"Invalid character in card number!");
    }
    @Test
    public void test5() {
        Item item1 = new Item("name", 1,300,0);
        List<Item> items = List.of(item1);
        double expected = 300;//no discount 300 * 1
        double actual = SILab2.checkCart(items,"0000000000000000");
        assertEquals(expected,actual);
    }

    //Multiple Condition if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10)
    static final String VALID_CARD = "0000000000000000";
    @Test
    public void test1_FFF() {
        Item item1 = new Item("name", 1,300,0);
        List<Item> items = List.of(item1);
        double expected = 300;
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test2_FFT() {
        Item item1 = new Item("name", 11,300,0);
        List<Item> items = List.of(item1);
        double expected = 3270; // -30 + (300*11)
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test3_FTF() {
        Item item1 = new Item("name", 1,300,1);
        List<Item> items = List.of(item1);
        double expected = -30; // -30 + 0 (discount = 1)
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test4_FTT() {
        Item item1 = new Item("name", 11,300,1);
        List<Item> items = List.of(item1);
        double expected = -30; // -30 +0
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test5_TFF() {
        Item item1 = new Item("name", 1,301,0);
        List<Item> items = List.of(item1);
        double expected = 271; // -30 +301
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test6_TFT() {
        Item item1 = new Item("name", 11,301,0);
        List<Item> items = List.of(item1);
        double expected = 3281; // (301 * 11) -30
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test7_TTF() {
        Item item1 = new Item("name", 1,301,1);
        List<Item> items = List.of(item1);
        double expected = -30;// -30 + 0
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
    @Test
    public void test8_TTT() {
        Item item1 = new Item("name", 11,301,1);
        List<Item> items = List.of(item1);
        double expected = -30; // -30 + 0
        double actual = SILab2.checkCart(items,VALID_CARD);
        assertEquals(expected,actual);
    }
}
