Betamax Demo (in Java)
==

This project (currently) demonstrates a potential bug in the betamax library.


If you have a Java project that calls a SOAP service and has more than one
@Betamax annotated tests in the same class, the second request will timeout.

Example (from src/test/java/com/mbpfefferle/betamax/demo/WhoisTest.java)


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

In the above example, testWhoIsOSU is proxied correctly, and the tape is
written.  However, testWhoIsOhio times-out.  No tape is written.

Use `mvn clean test` to reproduce the issue.

