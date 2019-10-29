package com.assigment.modeling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/*
 * Basic Animal function
 */
class Animal {
	String eat() {
		return  "I am eating";	
	}
}
/*
 * Since  terrestrial animal can walk or crawl
 */
interface Moveable{
	String move();
}

class Walk implements Moveable{
	public String move() {
		return "I am walking";
	}
}


interface Singable {
	String sing();
	
}

/*
 * Section 1.a & 1.b
 */

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


class Bird extends Walking implements Singable {  // Since all birds can walk but not fly. Flying behaviour will vary 
	FlyBehaviour flyBehaviour = new Flying(); // default behaviour and override in case bird doesn't fly in the subclass
	public String sing() {
		return "I am singing";
	}
	
	String fly() {
		return flyBehaviour.fly();
	}
	
}
/*
 * Section 2.a, 2.b , 2.c, 2.d
 * Chicken inherits Domestic behavior, primarily terrestrial birds with no flying ability
 * Duck inherits from abstract type Aquatic bird, adding the ability to swim in addition to common bird behaviour
 * 
 */
class DomesticBird extends Bird {
	
	DomesticBird(){
		flyBehaviour = new Flightless();
	}
	
	String eat(){
		return "I eat only grains!";
	}
}

class Chicken extends DomesticBird  {
	
	public String sing() {
		return "Cluck,cluck";
	}
}

interface Swimmable{
	String swim();
}

class AquaticBird extends Bird implements Swimmable{

	@Override
	public String swim() {
		return "I am swimming";
	}
	
}

class Duck extends AquaticBird {
	
	public String sing() {
		return "Quack, quack";
	}
}

public class Solution {
	public static void main(String[] args) {
		System.out.println("***************************");
		System.out.println("Basic Bird Behaviour ");
		System.out.println("***************************");
		
		Bird bird = new Bird();
		System.out.println(bird.eat());
		System.out.println(bird.walk());
		System.out.println(bird.sing());
		System.out.println(bird.fly());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Chicken Behaviour ");
		System.out.println("***************************");
		
		Bird chicken = new Chicken();
		System.out.println(chicken.eat());
		System.out.println(chicken.walk());
		System.out.println(chicken.sing());
		System.out.println(chicken.fly());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Duck Behaviour ");
		System.out.println("***************************");
		
		Duck duck = new Duck();
		System.out.println(duck.eat());
		System.out.println(duck.walk());
		System.out.println(duck.sing());
		System.out.println(duck.fly());
		System.out.println(duck.swim());
	}
	
	
	Bird bird;
	Bird chicken;
	Duck duck;
	
	
	@Before
	public void setUp() {
		bird = new Bird();
		chicken = new Chicken();
		duck = new Duck();
	}
	
	@Test
	public void test_Bird_default_sing() {
		assertEquals("I am singing", bird.sing());
	}
	
	@Test
	public void test_Chicken_can_not_swim() {
		assertFalse(chicken instanceof AquaticBird);
	}
	
	@Test
	public void test_Duck_can_swim() {
		assertTrue(duck instanceof AquaticBird);
		assertEquals("I am swimming", duck.swim());
	}
	
	@Test
	public void test_Duck_can_Quack() {
		assertEquals("Quack, quack", duck.sing());
	}
	
	@Test
	public void test_Chicken_can_Cluck() {
		assertEquals("Cluck,cluck", chicken.sing());
	}
	
}

