import java3_hw6.MyClass;
import org.junit.Assert;
import org.junit.Test;

public class TestArray {
    @Test
    public void test1arrayAfter4() {
        int[] in =  new int[]{1, 3, 4, 5, 5, 6, 8};
        int[] out =  new int[]{5, 5, 6, 8};
        Assert.assertArrayEquals(out, MyClass.arrayAfter4(in));
    }

    @Test(expected = RuntimeException.class)
    public void test2arrayAfter4() {
        int[] in =  new int[]{1, 3, 5, 5, 6, 8};
        MyClass.arrayAfter4(in);
    }

    @Test
    public void test1arrayContainsOnly1and4(){
        int[] in = new int[]{1, 3, 4, 5};
        Assert.assertFalse(MyClass.arrayContainsOnly1and4(in));
    }

    @Test
    public void test2arrayContainsOnly1and4(){
        int[] in = new int[]{1, 4, 1};
        Assert.assertTrue(MyClass.arrayContainsOnly1and4(in));
    }

    @Test
    public void testAdd(){
        Assert.assertEquals(5, MyClass.add(2,3));
    }
}
