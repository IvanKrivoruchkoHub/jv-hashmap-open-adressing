import org.example.myhashmap.MyHashMap;
import org.example.myhashmap.MyMap;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void putAndGetOk() {
        MyMap myHashMap = new MyHashMap();
        for (int i = -50; i < 50; i++) {
            myHashMap.put(i, i * 2);
        }
        for (int i = -50; i < 50; i++) {
            long actualValue = myHashMap.get(i);
            Assert.assertEquals(String.format("Test failed! Expected %d, but was %d", i * 2, actualValue),
                    i * 2, actualValue);
        }
    }

    @Test
    public void sizeOk() {
        MyMap myHashMap = new MyHashMap();
        myHashMap.put(1, 3);
        myHashMap.put(0, 5);
        myHashMap.put(-5, 1);
        Assert.assertEquals("Test failed! The size isn't correct. Expected 0 but was "
                + myHashMap.size(), 3, myHashMap.size());
    }

    @Test
    public void putTheSameElement() {
        MyMap myHashMap = new MyHashMap();
        myHashMap.put(1, 6);
        myHashMap.put(0, 10);
        myHashMap.put(-5, 2);

        myHashMap.put(1, 3);
        myHashMap.put(0, 5);
        myHashMap.put(-5, 1);

        long firstActualValue = myHashMap.get(1);
        long secondActualValue = myHashMap.get(0);
        long thirdActualValue = myHashMap.get(-5);

        Assert.assertEquals("Test failed! Expected 3, but was "
                + firstActualValue, 3, firstActualValue);
        Assert.assertEquals("Test failed! Expected 5, but was "
                + secondActualValue, 5, secondActualValue);
        Assert.assertEquals("Test failed! Expected 1, but was "
                + thirdActualValue, 1, thirdActualValue);
    }

    @Test
    public void getByNonExistedKey() {
        MyMap myHashMap = new MyHashMap();
        Assert.assertEquals("Test failed! If key doesn't exist, we should return 0.",
                0, myHashMap.get(8));
    }

    @Test
    public void getSizeOfEmptyHashMap() {
        MyMap myHashMap = new MyHashMap();
        Assert.assertEquals("Test failed! The size isn't correct. Expected 0 but was "
                + myHashMap.size(), 0, myHashMap.size());
    }
}
