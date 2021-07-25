# M4 Testing Brief
DoorTest contains 2 JUnits test to ensure that a door can be locked and unlocked correctly
This is important to ensure that the door state can be modified in the room file to correctly allow or disallow player entry and exit

EnemyTest contains 2 seperate JUnits Tests to test the Enemy class used in the Dungeon game
The first JUnits test ensures that enemies can sucessfully take damage and have their HP lowered. This test allows for the room class to delete
enemies once their hp sucessfully reaches 0 or lower
The second JUnits test tests the damage dealt by enemies ensuring that damage is correctly returned based on value instantiated by the constructor
for calculating player damage

PlayerTest initializes a player object to test the Player class which is a core class for the dungeon game.
The PlayerTest checks to ensure that on construction, player hp, current hp, and weapons are clearly and accurately instantiated and
that player coordinates can be accurately controlled within a room's 2D array of RoomTiles, accounting for updates to player object since M3.

Room test for M4 contains 3 seperate Junits tests testing new changes made to the room class
The first test tests monster creation to ensure the correct amount of enemies are created on room instantiation for the starting ending and random rooms
The second test checks to see that a Room sucessfully removes monsters from the existing list of monsters when their HP points reach 0
The third test ensures that doors are correctly locked on room instantiation.

###### @Test testDoorLock() - Verifies room doors are correctly locked upon generation of door entity  
###### @Test testDoorUnlock() - Verifies room doors can be correctly unlocked

###### @Test testTakeDamage() - Verifies monsters appropriately take damage given damage modifiers and health attributes for monster variants  
###### @Test testDealDamage() - Verifies monsters deal damage correctly based on variant and coordinate placement (accounting for range)

###### @Test returnData3() - Test written to ensure player creation on hard difficulty with mage returns correct attributes and functions correctly in damage and movement.   

###### @Test testMonsterCreation() verifies monster spawning in rooms is correct based on room variant and arguments
###### @Test testMonsterDefeat() verifies removal of dead monsters from room works correctly
###### @Test testDoorUnlock() verifies that room exits (door entities) are locked upon generation of room
