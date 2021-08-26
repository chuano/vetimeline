package com.vetimeline.api.domain.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdDocumentTest {

    @Test
    public void ShouldRemoveAllSpaces() {
        IdDocument document = new IdDocument("4 4 4 4 4 4 4 4 4");
        assertEquals("444444444", document.getValue());
    }

    @Test
    public void ShouldFormatValidDNI() {
        IdDocument document = new IdDocument("12345678A");
        assertEquals("12345678-A", document.getValue());
    }

    @Test
    public void ShouldFormatValidDNIWhitDash() {
        IdDocument document = new IdDocument("12345678-A");
        assertEquals("12345678-A", document.getValue());
    }

    @Test
    public void ShouldFormatValidDNIWhitSpace() {
        IdDocument document = new IdDocument("12345678 A");
        assertEquals("12345678-A", document.getValue());
    }

    @Test
    public void ShouldFormatValidCIF() {
        IdDocument document = new IdDocument("A12345678");
        assertEquals("A-12345678", document.getValue());
    }

    @Test
    public void ShouldFormatValidCIFWithDash() {
        IdDocument document = new IdDocument("A-12345678");
        assertEquals("A-12345678", document.getValue());
    }

    @Test
    public void ShouldFormatValidCIFWithSpace() {
        IdDocument document = new IdDocument("A 12345678");
        assertEquals("A-12345678", document.getValue());
    }

    @Test
    public void ShouldFormatValidNIE() {
        IdDocument document = new IdDocument("A1234567B");
        assertEquals("A-1234567-B", document.getValue());
    }

    @Test
    public void ShouldFormatValidNIEWithDash() {
        IdDocument document = new IdDocument("A-1234567-B");
        assertEquals("A-1234567-B", document.getValue());
    }

    @Test
    public void ShouldFormatValidNIEWithSpace() {
        IdDocument document = new IdDocument("A 1234567 B");
        assertEquals("A-1234567-B", document.getValue());
    }

    @Test
    public void ShouldNotFormatUnknownDocument() {
        IdDocument document = new IdDocument("123456789");
        assertEquals("123456789", document.getValue());
    }

    @Test
    public void ShouldNotFormatUnknownDocumentWithDash() {
        IdDocument document = new IdDocument("1-2-3-4-5-6-7-8-9");
        assertEquals("123456789", document.getValue());
    }

    @Test
    public void ShouldNotFormatUnknownDocumentWithSpace() {
        IdDocument document = new IdDocument("1 2 3 4 5 6 7 8 9");
        assertEquals("123456789", document.getValue());
    }
}