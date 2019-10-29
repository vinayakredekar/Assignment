package com.assigment.modeling;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;



class Animal {
	String eat() {
		return  "I am eating";	
	}
}
/*
 * Since  animal can walk or crawl or swim
 */
interface Moveable{
	String move();
}
class Walk implements Moveable{
	public String move() {
		return "I am walking";
	}
}

interface FlyBehaviour {
	String fly();
}

class Flying implements FlyBehaviour{

	@Override
	public String fly() {
		return "I am flying";	
	}
}

class Flightless implements FlyBehaviour {

	@Override
	public String fly() {
		return "I can not fly";
	}
}


class Walking extends Animal {

	Moveable moveable = new Walk();//default behaviour terrestrial animals
	String walk() {
		return moveable.move();
	}
}


class Bird extends Walking {  // Since all birds can walk but not fly. Flying behaviour will vary 
	FlyBehaviour flyBehaviour = new Flying(); // default behaviour and override in case bird doesn't fly in the subclass
	String sing() {
		return "I am singing";
	}
	
	String fly() {
		return flyBehaviour.fly();
	}
	
}



public class Solution {
	public static void main(String[] args) {
		System.out.println("***************************");
		System.out.println("Basic Bird Behaviour ");
		
		Bird bird = new Bird();
		System.out.println(bird.eat());
		System.out.println(bird.walk());
		System.out.println(bird.sing());
		System.out.println(bird.fly());
	}
	
	
	Bird bird;
	
	@Before
	public void setUp() {
		bird = new Bird();
	}
	
	@Test
	public void test_Bird_default_sing() {
		assertEquals("I am singing", bird.sing());
	}
}

