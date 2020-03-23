/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author osma
 */
public class KassapaateTest {

    public KassapaateTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(400);

    }

    @After
    public void tearDown() {
    }

    //
    @Test
    public void rahaAlussa() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());

    }

    @Test
    public void ostaKateisella() {
        kassa.syoMaukkaasti(400);
        kassa.syoEdullisesti(240);
        assertEquals(100640, kassa.kassassaRahaa());
        assertEquals(2, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());

    }

    @Test
    public void ostaKateisellaLiianVahanRahaa() {
        kassa.syoMaukkaasti(399);
        kassa.syoEdullisesti(239);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());

    }

    @Test
    public void ostaKortillaMaukkaasti() {
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kortti.saldo());
    }

    @Test
    public void ostaKortillaEdullisesti() {
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty() + kassa.maukkaitaLounaitaMyyty());
        assertEquals(160, kortti.saldo());

    }

    @Test
    public void lataaKortille() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
        assertEquals(900, kortti.saldo());
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(100500, kassa.kassassaRahaa());
        assertEquals(900, kortti.saldo());

    }
}
