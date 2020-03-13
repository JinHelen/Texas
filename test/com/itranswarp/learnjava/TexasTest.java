package com.itranswarp.learnjava;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TexasTest {

	@Test
	void test() {
		 	assertEquals("White wins",Texas.Compare("2H3D5S9CKD","2C3H4S8CAH"));
	        assertEquals("Black wins",Texas.Compare("2H4S4C2D4H","2S8SASQS3S"));
	        assertEquals("Black wins",Texas.Compare("2H3D5S9CKD","2C3H4S8CKH"));
	        assertEquals("Black wins",Texas.Compare("9DTHJCQSKH","4H5D6S7C8D"));
	        assertEquals("Black wins",Texas.Compare("7H7D7S9CKD","4D5H4C2S4H"));
	        assertEquals("Tie",Texas.Compare("2H3D5S9CKD","2D3H5C9SKH"));
	        assertEquals("White wins",Texas.Compare("2H3H5H9HKD","2H3H4H5H6H"));
	        assertEquals("White wins",Texas.Compare("4H4D5S5C6D","2D2H6C6S8H"));
	}

}
