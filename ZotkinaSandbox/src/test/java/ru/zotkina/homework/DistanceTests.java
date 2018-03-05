package ru.zotkina.homework;

import org.testng.Assert;
import org.testng.annotations.Test;


public class DistanceTests {
    @Test
    public void testdistance_1()
    {
        Point p1=new Point(0,3);
        Point p2=new Point(0,1);
        Assert.assertEquals(p1.distance(p2),2.0);
    }

    @Test
    public void testdistance_2()
    {
        Point p1=new Point(1,3);
        Point p2=new Point(2,1);
        Assert.assertEquals(p1.distance(p2),2.23606797749979);
    }

    @Test
    public void testdistance_3()
    {
        Point p1=new Point(6.00,3.00000000000000);
        Point p2=new Point(18,8.00);
        Assert.assertEquals(p1.distance(p2),13.0);
    }
}
