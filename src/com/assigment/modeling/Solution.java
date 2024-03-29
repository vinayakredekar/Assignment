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

class Crawl implements Moveable{
	public String move() {
		return "I am Crawling";
	}
}

class Jump implements Moveable{
	public String move() {
		return "I am Jumping";
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

class FrogSingingBehaviour implements SingingBehaviour{

	@Override
	public String sing() {
		return "Grup,Grup";
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


/*
 * Modelling a Fish. Section B
 * Abstarction for Aquatic animal who doesn't walk but swim 
 */
class AquaticAnimal extends Animal implements Swimmable{
	EatingBehaviour eatingBehaviour;
	@Override
	public String swim() {	
		return "I am swimming";
	}
	
	public String eat() {
		return eatingBehaviour.eat();
	}
}

/*
 * Fish Model
 */
class Fish extends AquaticAnimal{
	
	 String features() {
		 return "default features";
	 }
}

class CarnivoreFish implements EatingBehaviour{

	@Override
	public String eat() {
		return "Eat other fish";	
	}
}

class HerbivoreFish implements EatingBehaviour{

	@Override
	public String eat() {
		return "Eat aquatic plants";	
	}
}


interface Joker{
	String joke();
}

class Shark extends Fish{
   
   Shark(){
	   eatingBehaviour = new CarnivoreFish();
   }

   @Override
   String features() {
	  return "Large and Grey";
   }
}


class ClownFish extends Fish implements Joker{

	ClownFish(){
		eatingBehaviour = new HerbivoreFish();
	}
	
	@Override
	public String joke() {
		return "Joking";
		
	}

	@Override
	String features() {
		return "Small and Orange";
	}
	
}
/*
 * Modelling a Dolphin
 */
class AquaticMammal extends AquaticAnimal {
	
	String breathAir() {
		return "I am breathing air";
	}
}

class Dolphin extends AquaticMammal {

	Dolphin(){
		eatingBehaviour = new CarnivoreFish();	
	}
}


interface Metamorphosable {
	void metamorphose();
}


class ButterFly extends Terrestrial implements Metamorphosable{
	FlyBehaviour flyingBehaviour = new Flightless();
	Moveable moveable = new Crawl();
	ButterFly(int age){
		if(age > 14) metamorphose();
	}
	
	String fly() {
		return flyingBehaviour.fly();
	}

	String move() {
		return moveable.move();
	}
	
	@Override
	public void metamorphose() {
		flyingBehaviour = new Flying();
		moveable = new Walk();
	}
	
}


class Amphibian extends AquaticAnimal implements SingingBehaviour{
	Moveable moveable;
	SingingBehaviour singable;
	
	String move() {
		return moveable.move();
	}
	@Override
	public String sing() {
		return singable.sing();
	}
}

	

class Frog extends Amphibian{
	
	Frog(){
		singable = new FrogSingingBehaviour();
		moveable = new Jump();
	}
	
	public String move() {
		return moveable.move();
	}
	
	public String sing() {
		return singable.sing();
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
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Section B : Fish Modelling");
		System.out.println("***************************");
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Shark features");
		System.out.println("***************************");
	   
	    Fish shark = new Shark();
	    System.out.println(shark.eat());
	    System.out.println(shark.features());
	   
	    System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("ClownFish features");
		System.out.println("***************************");
	   
		ClownFish clownFish = new ClownFish();
	    System.out.println( clownFish.eat());
	    System.out.println(clownFish.features());
	    System.out.println(clownFish.joke());
	    
	    
	    System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Dophnin beahviour");
		System.out.println("***************************");
	   //Dolpin 
		Dolphin dolpin = new Dolphin();
		System.out.println(dolpin.breathAir());
		System.out.println(dolpin.swim());
		System.out.println(dolpin.eat());
		
		
		System.out.println();
		System.out.println();
		System.out.println("***************************");
		System.out.println("Butterfly  Metamorphosis");
		System.out.println("***************************");
	   
	   //ButterFly
	   ButterFly caterpiller = new ButterFly(5);
	   System.out.println();
		System.out.println();
	   System.out.println("***************************");
	   System.out.println("Catepillar  behavior");
	   System.out.println("***************************");
	   System.out.println(caterpiller.fly());
	   System.out.println(caterpiller.move());
		
	   System.out.println();
	   System.out.println();
	   System.out.println("***************************");
	   System.out.println("ButterFly  behavior");
	   System.out.println("***************************");
	   System.out.println();
	   System.out.println();
	   ButterFly butterFly = new ButterFly(17);
	   System.out.println(butterFly.fly());
	   System.out.println(butterFly.move());
	   
	   //Modelling a frog
	   System.out.println();
	   System.out.println();
	   System.out.println("***************************");
	   System.out.println("Frog  behavior");
	   System.out.println("***************************");
	   Frog frog = new Frog();
	   System.out.println(frog.move());
	   System.out.println(frog.sing());
	   
	   
	   //Section E
	   
	   Animal[] animals = new Animal[]{
			   new Bird(), //Walk, Sing
			   new Duck(), //Fly, Walk, Sing, Swim
			   new Chicken(), //Walk, Sing
			   new Rooster(), //Walk, Sing
			   new Parrot(), //Fly, Sing
			   new Fish(), //Swim
			   new Shark(), //Swim
			   new ClownFish(),//Swim
			   new Dolphin(), //Swim
			   new Frog(),//Sing //Swim
			   new Dog(), //Walk
			   new ButterFly(17), //Fly
			   new Cat() //Walk
			   };
	   
	   int flyCount = 0;
	   int walkCount = 0;
	   int singCount  = 0;
	   int swimCount = 0;
	   for(Animal animal : animals) {
		   if((animal instanceof Bird) &&(((Bird)animal).flyBehaviour instanceof Flying)) flyCount++;
		   if(animal instanceof Walking) walkCount++;
		   if((animal instanceof SingingBehaviour) || (animal instanceof Singable)) singCount++;
		   if(animal instanceof Swimmable) swimCount++;
	   }
	   
	   System.out.println();
	   System.out.println();
	   System.out.println("***************************");
	   System.out.println("Counting Animals");
	   System.out.println("***************************");
	   System.out.println("Fly count"+flyCount);
	   System.out.println("Walk count"+walkCount);
	   System.out.println("Sing count"+singCount);
	   System.out.println("Swim count"+swimCount);
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
	Fish shark;
	Animal sharkAnimal;
	Fish clownFish;
	AquaticAnimal dolphin;
	ButterFly caterpiller;
	ButterFly butterFly;
	
	
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
		shark = new Shark();
		sharkAnimal = new Shark();
		clownFish = new ClownFish();
		dolphin = new Dolphin();
		caterpiller = new ButterFly(6); //6 days old
		butterFly = new ButterFly(18); //Adult butterfly
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
	
	@Test
	public void test_Shark_Can_Not_Sing_OR_Walk() {
		assertFalse(shark instanceof Singable);
		assertFalse(sharkAnimal instanceof Walking);
	}
	
	@Test
	public void test_Shark_Can_Not_Swim() {
		assertTrue(sharkAnimal instanceof Swimmable);
	}	
	
	@Test
	public void test_Shark_is_a_Joker() {
		assertFalse(shark instanceof Joker);
	}
	
	@Test
	public void test_Clown_Fish_is_Not_A_Carnivore() {
		assertFalse(clownFish.eatingBehaviour instanceof CarnivoreFish);
	}
	
	
	@Test
	public void test_Dolphin_Is_Not_A_Fish() {
		assertFalse(dolphin instanceof Fish);
	}
	
	@Test
	public void test_Dolphin_Can_Breath_Air() {
		assertTrue(dolphin instanceof AquaticMammal);
	}
	
	@Test
	public void test_catepillar_fly_And_Walk() {
		assertFalse(caterpiller.flyingBehaviour instanceof Flying);
		assertFalse(caterpiller.moveable instanceof Walk);
	}
	
	@Test
	public void test_ButterFly_Fly_And_Walk() {
		assertTrue(butterFly.flyingBehaviour instanceof Flying);
		assertTrue(butterFly.moveable instanceof Walk);
	}
}

