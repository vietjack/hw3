ConfigActivity.java
Activity class that is invoked from the GameActivity to configure the images.  It's a ListActivity which has a custom Adapter implementation to gather the image data from the SD card and display it in a list.

GameActivity.java
Activity where the main gameplay is.  Handles displaying the cell images as well as taking care of the game timer.

GamePlayManager.java
Handles all the gameplay logic.

GameGrid.java
Underlying data structure that the GamePlayManager uses to control the game.