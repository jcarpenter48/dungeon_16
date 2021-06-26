# M2 Testing Brief
- Test cases for Milestone 2 were chosen and written primarily for correct screen transitions/responses and 
the Player class. 
- Screen transitions and responses were tested due to the visual/tactile importance to the user of correct 
display and feedback from user input.
- Player class test cases were written because it will be one of the most crucial components of the project 
going forward, so it is important to have tests written in advance.
- Test cases verifying that the correct art resource was loaded were not written and left to manual QA, 
as automation of this type of verification via JUnits is not ideal. JUnits could verify that the filename
and directory were correct, but could not determine the subjective correctness of an art element.
- Test cases ranged from basic (e.g. what scene are we on, did this button do as intended) to edge cases, 
with a focus on the latter to try and ascertain hidden problems. For instance, the config screen tests
ran through a variety of odd inputs in the Player name field to make sure strange, invalid data is not
passed in and the user is prevented from starting the game. 
