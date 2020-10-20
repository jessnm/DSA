import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

//import java.rmi.server.ExportException;


public class TestQueueClass {
    Queue<String> q;

    @Before
    public void setup() throws Exception{
        this.q = new MyQueue<String>(5);
        this.q.push("1");
        this.q.push("2");
        this.q.push("3");
    }

    @After
    public void tearDown(){
        this.q = null;
    }

    /*@Test
    public void testPush1() throws Exception{
        Assert.assertEquals(3,this.q.size());
        this.q.push("4");
        Assert.assertEquals(4,this.q.size());
    }

    @Test
    public void testPush2() throws Exception{
        Assert.assertEquals(3,this.q.size());
        this.q.push("4");
        this.q.push("5");
        Assert.assertEquals(5,this.q.size());
    }

    @Test(expected = FullQueueException.class)
    public void TestPushFull() throws Exception{
        Assert.assertEquals(3,this.q.size());
        this.q.push("4");
        this.q.push("5");
        this.q.push("6");
        Assert.assertEquals(5,this.q.size());
    }*/

    //TEST POP

    @Test

    public void TestPop1() throws Exception{
        Assert.assertEquals(3,this.q.size());
        this.q.pop();
        Assert.assertEquals(2,this.q.size());
    }

    @Test(expected = EmptyQueueException.class)
    public void TestPopEmpty() throws Exception{
        Assert.assertEquals(3,this.q.size());
        this.q.pop();
        this.q.pop();
        this.q.pop();
        this.q.pop();
        Assert.assertEquals(5,this.q.size());
    }
}
