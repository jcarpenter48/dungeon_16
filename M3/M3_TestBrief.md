# M3 Testing Brief
PlayerTest initializes a player object to test the Player class which is a core class for the dungeon game.
The PlayerTest checks to ensure that on construction, player hp, current hp, and weapons are clearly and accurately instantiated and
that player coordinates can be acccurately controlled within a room's 2D array of RoomTiles.  
###### @Test setCoordinates() - Test written to ensure player coordinate alteration methods responsible for placing character in world function correctly. Key to ensuring correct player movement.  
###### @Test returnData2() - Test written to new player creation, coordinate alteration, damage attribute, and movement coordinate change are correct. This verifies methods do not conflict or break when used in sequence.  

RoomTest instantiates a room object and tests to see if doors are generated on room tile coordinates correctly when surrounding rooms are instantiated.
RoomTest checks to ensure that the player can traverse through rooms when each room is called by the game controller.  
###### @Test returnData() - Verifies room entity tiles for doors are correctly spawned for new rooms (i.e. a room with only a left and down door only has left and down door tiles)  
###### @Test attach4way() - Verifies room variant designation is correct for a room with 4 connections (udlr, has up down left and right exits)  
###### @Test attach3way() - Verifies room variant designation is correct for a room with 3 connections (udl, has up down and left exits)  
###### @Test attach2way() - Verifies room variant designation is correct for a room with 2 connections (ul, has up and left exits)
###### @Test exitRoomTestGen() - Verifies that an exit room can be created and designated correctly (no exits from exit room except to outside of dungeon, triggering victory screen)  
###### @Test startRoomTestGen() - Verifies that the starting room can be created and designated correctly (4 exits, custom room variant)  
