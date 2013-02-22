package com.mbpfefferle.betamax.demo;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import co.freeside.betamax.Betamax;
import co.freeside.betamax.Recorder;

import net.webservicex.Whois;
import net.webservicex.WhoisSoap;

import org.junit.*;

public class WhoisTest {

    @Rule public Recorder recorder = new Recorder();

    @Test
    public void testSanity() {
        assertThat(true, is(true));
    }

    @Test
    @Betamax(tape="who-is-osu")
    public void testWhoIsOSU() {
        Whois service = new Whois();
        WhoisSoap target = service.getWhoisSoap();
        assertThat(target.getWhoIS("osu.edu"), containsString("The Ohio State University"));
    }

    @Test
    @Betamax(tape="who-is-ohio")
    public void testWhoIsOhio() {
        Whois service = new Whois();
        WhoisSoap target = service.getWhoisSoap();
        assertThat(target.getWhoIS("ohio.edu"), containsString("Ohio University"));
    }

}

