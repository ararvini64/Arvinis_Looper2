Favorites_System.md
Rhythm Loop App
Favorites System Specification
Version: 1.0

1. Purpose
The Favorites System allows users to save frequently used rhythm configurations and quickly recall them during practice or live performance.
A Favorite is not just a saved file.
It is a complete playback setup.

2. Design Philosophy
The Favorites System should provide:
Fast access
Simple operation
Reliable recall
No repeated setup
A musician should be able to save a preferred combination once and use it again immediately.

3. What a Favorite Represents
A Favorite represents a saved musical configuration.
It may contain:
Rhythm
Harmony (optional)
BPM setting
Rhythm volume
Harmony volume

4. Favorite Types
There are two possible Favorite configurations.

Type A: Rhythm Only
Example:
Persian
P3
BPM: 90
Rhythm Volume: 80%
When loaded:
Rhythm is loaded
BPM is restored
Rhythm volume is restored
No Harmony is activated

Type B: Rhythm + Harmony
Example:
Turkish
T5

Harmony:
H2

BPM:
110

Volumes:
Rhythm 75%
Harmony 50%
When loaded:
Rhythm is loaded
Harmony is loaded
BPM is restored
Both volumes are restored

5. Creating a Favorite
User workflow:
Select Rhythm
      ↓
Adjust BPM
      ↓
Select Harmony (optional)
      ↓
Adjust Volumes
      ↓
Press Favorite
      ↓
Configuration Saved

6. Favorite Button
The Favorite icon is represented by:
Heart icon
The icon should be visually clear.
When active:
The heart shows that the current configuration is saved.

7. Favorite Data Structure
Version 1:
A Favorite contains:
Favorite

ID

Rhythm Category

Rhythm File

Harmony File (optional)

BPM

Rhythm Volume

Harmony Volume

8. Loading a Favorite
When user selects a Favorite:
The application restores all saved settings.
Process:
Load Favorite

↓

Load Rhythm

↓

Restore BPM

↓

Restore Rhythm Volume

↓

Load Harmony (if exists)

↓

Restore Harmony Volume

↓

Ready for Play

9. Auto Play Rule
Version 1:
Loading a Favorite does not automatically start playback.
The user presses Play.
Reason:
Avoid unexpected sound during live situations.

10. Favorite Playback
After loading:
The Favorite behaves exactly like a normal rhythm selection.
User can:
Play
Pause
Stop
Change BPM
Change Volume

11. Editing a Favorite
Version 1:
When user changes a loaded Favorite:
The original Favorite remains unchanged.
If the user wants the new configuration saved:
A new Favorite can be created.
Future versions may include:
"Update Favorite"

12. Deleting a Favorite
Users can delete saved Favorites.
Deleting a Favorite:
Removes only the saved configuration.
It does not delete:
Rhythm files
Harmony files
My Own files

13. Built-in Content Protection
Built-in rhythms and harmonies are permanent.
Users cannot delete them.
Favorites only store references to these files.

14. My Own Integration
My Own loops can also be added to Favorites.
Example:
My Own

Own3

BPM:
95

Harmony:
H4
The Favorite system treats My Own loops like normal rhythms.

15. Visual Design
Favorite list uses the same card design as other lists.
Each Favorite card displays:
Name / ID
Rhythm
Harmony (if available)
Play button
Delete button

16. Favorite Sorting
Version 1:
Favorites are displayed by creation order.
Future versions may support:
Alphabetical sorting
Most used
Custom ordering

17. Error Handling
Possible errors:
Missing rhythm file
Missing harmony file
Deleted My Own file
Corrupted Favorite data
Behavior:
Show warning
Keep application stable
Allow user correction

18. Storage
Favorites are stored separately from audio files.
Example:
Internal Storage

Favorites/

favorite_001.json

favorite_002.json
Future versions may use a complete JSON database.

19. Future Expansion
Future versions may include:
Favorite naming
Favorite folders
User tags
Search
Backup
Cloud synchronization
Sharing Favorites

20. Design Principles
Favorites must remain:
Simple
Fast
Reliable
Predictable
The user should always understand what will happen when a Favorite is loaded.
[8/3/26 4:41 PM] سرزمینم ایران!!: 21. Final Principle
The purpose of the Favorites System is:
Allow musicians to instantly recall their preferred rhythm setup without repeating manual adjustments.
A Favorite is a saved performance configuration, not just a bookmark.

End of Document
