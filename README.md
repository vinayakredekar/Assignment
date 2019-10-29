# Assignment
## Section A : Modeling Animal
### Sub Section 1
Optimization in the Animal Class 
* Not all animals can walk, making Animal class as base for representation of any animal.
* Since eat() is the basic function any animal can perform, all animal type will inherit this function
* Segregated the variable behavior like movement, flying etc into different move and fly strategy
* Optimizing the code to return value for unit test

### Sub Section 2
* Chicken is assumed Domestic, eating patterns vary. 
* Duck is assumed  assumed to be wild in this example. To segregate domestic and non-domestic.

### Sub Section 3
* Rooster belong to the same family as Chicken, both are Domestic
* Rooster can be implemented with Composition and Inheritence. A maintainable Rooster with same behavior as inherited can achieved with composition. 

## Sub Section 4
* Parrot can mimic any object that is mimicable, living or non-living. Thus improving the parrots ability to mimic any sound.

#Section B
## Modeling a Fish
* Fish can swim but cannot walk or fly. Each Fish has peculiar features to display.
* Abstraction of animal that can swim is maintainable.
* Dolphin is not a Fish, however, it can swim. Can be easily extended from Abstract AquaticAnimal
* Further Abstarction can be created for for AquaticMammals. Apprently, Dolphin is aquatic mammal. This could be useful if we want to extend for similar types e.g Whale
