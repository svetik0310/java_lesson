package ru.zotkina.soap;

import com.buzzbuzhome.BBHLocation;
import com.buzzbuzhome.GeoIP;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GeoIpServiceTests {
    @Test
    public void testMyIp()
    {
        BBHLocation geo=new GeoIP().getGeoIPSoap12().getUserLocation("192.168.0.101");
        assertEquals(geo.getCountryCode(),"US");
    }
}
