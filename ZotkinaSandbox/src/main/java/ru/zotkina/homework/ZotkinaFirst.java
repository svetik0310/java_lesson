package ru.zotkina.homework;

public class ZotkinaFirst{
	public static void main (String[] args)
	{
		System.out.println("Hello, world! Java forever!");
		Point firstpoint= new Point(1.0,1.0);
		Point secondpoint= new Point(2.0,2.0);
		printresult(firstpoint.distance(secondpoint));
		printresult(distance(secondpoint,firstpoint));
	}

	public static void printresult(double res)
	{
		System.out.println("Расстояние между двумя случайными точками равно =" + res);
	}

	public static double distance(Point p1,Point p2)
	{
		return Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p2.y-p1.y),2));
	}
}