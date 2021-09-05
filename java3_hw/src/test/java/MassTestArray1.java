import java3_hw6.MyClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class MassTestArray1 {
    private int[] in;
    private int[] out;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{2, 5, 4, 7, 8}, new int[]{7, 8}},
                {new int[]{2, 5, 4}, new int[]{}},
                {new int[]{2, 5, 4, 7, 8, 4}, new int[]{}},
                {new int[]{2, 5, 4, 7, 4, 8}, new int[]{8}}
        });
    }

    public MassTestArray1(int[] in, int[] out) {
        this.in = in;
        this.out = out;
    }

    @Test
    public void massTestarrayAfter4() {
        Assert.assertArrayEquals(out, MyClass.arrayAfter4(in));
    }
}
