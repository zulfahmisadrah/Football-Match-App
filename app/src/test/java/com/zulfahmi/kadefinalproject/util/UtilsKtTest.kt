package com.zulfahmi.kadefinalproject.util

import org.junit.Test

import org.junit.Assert.*

class UtilsKtTest {

    @Test
    fun formatDate() {
        val dateString = "25/11/18"
        assertEquals("Sun, 25 Nov 2018",dateString.formatDate())
    }

    @Test
    fun parse() {
        val str = "Daniel Carrico; Simon Kjaer; Sergi Gomez; "
        val strParsed = "Daniel Carrico,Simon Kjaer,Sergi Gomez,"
        assertEquals(strParsed,str.parse(replacement = ","))
    }

    @Test
    fun parseGoals() {
        val str = "30':Andre Silva; 30':Andre Silva; 30':Andre Silva; "
        val strParsed = "30':Andre Silva,30':Andre Silva,30':Andre Silva,"
        assertEquals(strParsed,str.parse(replacement = ","))
    }
}