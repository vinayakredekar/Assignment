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


interface Singable extends Mimicable{
	String sing();
	
}

interface NonSingable extends Mimicable{
	String makeSound();
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

class Terrestrial extends Animal {
	
	String move() {
		return "I am moving";
	}
	
}

class Walking extends Terrestrial {

	Moveable moveable = new Walk();//default behaviour terrestrial animals
	
	String move() {
		return moveable.move();
	}
}


class Bird extends Walking implements Singable  {  // Since all birds can walk but not fly. Flying behaviour will vary 
	FlyBehaviour flyBehaviour = new Flying(); // default behaviour and override in case bird doesn't fly in the subclass
	public String sing() {
		return "I am singing";
	}
	
	public String mimic() {
		return sing();
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

/*
 * Section 3.1, 3.b,3.c 
 */
class Rooster extends DomesticBird  {

	public String sing() {
		return "Cock-a-doodle-doo";
	}
}

interface  SingingBehaviour{
	String sing();
}

class RoosterSingingBehaviour implements SingingBehaviour{

	@Override
	public String sing() {
		return "Cock-a-doodle-doo";
	}
}

/*
 * Eating Behaviour
 */
interface EatingBehaviour {
	String eat();
}

class DomesticEatingBehaviour implements EatingBehaviour{

	@Override
	public String eat() {
		return "I eat only grains!";
	}
}

class RoosterB{ //Without inheritance, using composition
	FlyBehaviour flyBehaviour;
	EatingBehaviour eatingBehaviour;
	SingingBehaviour singingBehaviour;
	
	RoosterB(){
		flyBehaviour = new Flightless();
		eatingBehaviour = new DomesticEatingBehaviour();
		singingBehaviour = new RoosterSingingBehaviour();
	}
		
	String eat() {
		return eatingBehaviour.eat();
	}
	
	String sing() {
		return singingBehaviour.sing();
	}
	
	String fly() {
		return flyBehaviour.fly();
	}
	
	String walk() {
		return "I am walking";
	}
}

/*
 * Section A 4.a,4.b,4.c,4.d
 */
interface Mimicable {
	String mimic();
}



class Parrot extends Bird  {
    
	Mimicable mimic;
	
	Parrot(){
		
	}
	
	Parrot(Mimicable mimic){
		this.mimic = mimic;
	}
	
	public String sing() {
		return "screech";
	}
	
	public String mimic() {
		return (mimic !=null)?mimic.mimic():sing();
	}
	
}


class Dog extends Walking implements NonSingable{
	public String makeSound() {
		return "Woof, woof";
	}

	@Override
	public String mimic() {
		return makeSound();
	}
}

class Phone implements NonSingable{

	@Override
	public String mimic() {
		return makeSound();
	}

	@Override
	public String makeSound() {
		return "Tring, Tring";
	}
	
}

class Cat extends Walking implements NonSingable{
	public String makeSound() {
		return "Meow";
	}
	
	@Override
	public String mimic() {
		return makeSound();
	}
}

public class Solution {
	public static void main(String[] args) {
		System.out.println("***************************");
		System.out.println("Basic Bird Behaviour ");
		System.out.println("***************************");
		
		Bird bird = new Bird();
		System.out.println(bird.eat());
		System.out.println(bird.move());
		System.out.println(bird.sing());
		System.out.println(bird.fly());
		System.out.println(bird.mimic());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Chicken Behaviour ");
		System.out.println("***************************");
		
		Bird chicken = new Chicken();
		System.out.println(chicken.eat());
		System.out.println(chicken.move());
		System.out.println(chicken.sing());
		System.out.println(chicken.fly());
		System.out.println(chicken.mimic());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Duck Behaviour ");
		System.out.println("***************************");
		
		Duck duck = new Duck();
		System.out.println(duck.eat());
		System.out.println(duck.move());
		System.out.println(duck.sing());
		System.out.println(duck.fly());
		System.out.println(duck.swim());
		System.out.println(duck.mimic());
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Rooster Behaviour with inheritence");
		
		Bird rooster = new Rooster();
		System.out.println(rooster.eat());
		System.out.println(rooster.move());
		System.out.println(rooster.sing());
		System.out.println(rooster.fly());
		System.out.println(rooster.mimic());
		
		System.out.println();
		System.out.println();
		System.out.println("*************************************");
		System.out.println("Rooster Behaviour without inheritence");
		System.out.println("*************************************");
		
		RoosterB roosterB = new  RoosterB();
		System.out.println(roosterB.eat());
		System.out.println(roosterB.walk());
		System.out.println(roosterB.sing());
		System.out.println(roosterB.fly());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Mimicing Parrot");
		System.out.println("***************************");
		Bird originalParrot = new Parrot();
		Bird mimicDog = new Parrot(new Dog());
		Bird mimicCat = new Parrot(new Cat());
		Bird mimicRooster = new Parrot(new Rooster());
		Bird mimicPhone = new Parrot(new Rooster());
		
		System.out.println("No mimic :"+originalParrot.mimic());
		
		System.out.println("Mimicing Dog :"+mimicDog.mimic());

		System.out.println("Mimicing Cat :"+mimicCat.mimic());

		System.out.println("Mimicing Rooster :"+mimicRooster.mimic());
		
		System.out.println("Mimicing Phone :"+mimicPhone.mimic());
	}
	
	
	Bird bird;
	Bird chicken;
	Duck duck;
	Bird rooster;
	RoosterB roosterB;
	Bird parrotMimicRooster;
	Bird parrotMimicDog;
	Bird parrotMimicCat;
	Bird parrotMimicPhone;
	Dog dog;
	Cat cat;
	Phone phone;
	Bird originalParrot;
	
	
	@Before
	public void setUp() {
		bird = new Bird();
		chicken = new Chicken();
		duck = new Duck();
		rooster = new Rooster();
		roosterB = new RoosterB();
		dog = new Dog();
		cat = new Cat();
		phone = new Phone();
		parrotMimicRooster = new Parrot(new Rooster());
		parrotMimicDog = new Parrot(new Dog());
		parrotMimicCat = new Parrot(new Cat());
		parrotMimicPhone = new Parrot(new Phone());
		originalParrot = new Parrot();
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
	
	//3.a
	@Test
	public void test_Rooster_with_inheritence_can_Cock_a_doodle_doo() {
		assertEquals("Cock-a-doodle-doo", rooster.sing());
	}
	
	//3.b
	@Test
	public void test_Chicken_And_Rooster_Belong_To_Same_Family() {
		assertTrue((chicken instanceof DomesticBird) && (rooster instanceof DomesticBird)); 
	}
	
	//3.c
	@Test
	public void test_Rooster_with_compisition_can_Cock_a_doodle_doo() {
		assertEquals("Cock-a-doodle-doo", roosterB.sing());
	}

	//Section A sub Sec 4
	@Test
	public void test_Parrot_that_mimimcs() {
		assertEquals(rooster.sing(), parrotMimicRooster.mimic());
		assertEquals(dog.makeSound(), parrotMimicDog.mimic() );
		assertEquals(cat.makeSound(), parrotMimicCat.mimic() );
		assertEquals(phone.makeSound(), parrotMimicPhone.mimic() );
	}
	
	
	@Test
	public void test_Parrot_with_No_Mimic() {
		assertEquals(originalParrot.sing(), originalParrot.mimic());
		
	}
}

