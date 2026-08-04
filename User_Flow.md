User_Flow.md
Rhythm Loop App
User Flow Specification
Version: 1.0

1. Purpose
This document describes the complete user journey inside the Rhythm Loop App.
The goal is to define how users interact with the application and ensure that every action is simple, predictable, and suitable for musicians.

2. Main User Goal
The main user goal is:
Select a rhythm, optionally add harmony, adjust tempo, and start playing with minimum effort.
The application should minimize unnecessary steps.

3. Application Entry Flow
Open App

↓

Main Screen

↓

Select Rhythm Category

↓

Select Rhythm

↓

Adjust BPM (Optional)

↓

Select Harmony (Optional)

↓

Press Play

4. Main Screen Flow
When the application starts:
User sees:
Rhythm categories
Harmony section
Now Playing section
Playback controls
No audio starts automatically.

5. Selecting a Rhythm Category
User selects:
Example:
Persian
Flow:
Main Screen

↓

Persian Category

↓

Rhythm List
The application displays available rhythms.

6. Selecting a Rhythm
User selects a rhythm.
Example:
P3
System behavior:
Previous selected rhythm is replaced.
New rhythm is loaded.
Current selection is highlighted.
Playback does not automatically start.

7. Adjusting BPM
User may change tempo.
Available controls:
+10
-10
+1
-1
BPM Slider
Flow:
Select Rhythm

↓

Adjust BPM

↓

Preview or Play
BPM affects only Rhythm.

8. Selecting Harmony
Harmony selection is optional.
Flow:
Harmony Section

↓

Select Harmony

↓

Harmony Highlighted

↓

Press Play
Harmony does not automatically start.

9. Starting Playback
When user presses Play:
System starts:
Rhythm
and if selected:
Harmony
Both operate independently.

10. During Playback
User can:
Change BPM
Change Rhythm Volume
Change Harmony Volume
Pause
Stop
Change Harmony
The application continues operating smoothly.

11. Pause Flow
When user presses Pause:
Current position is saved.
State changes:
Playing
↓
Paused
Pressing Play resumes from current position.

12. Stop Flow
When user presses Stop:
Playback ends.
Position resets.
State:
Stopped

13. Back Navigation
If user presses Back:
Current Screen

↓

Previous Screen
Playback continues.
The application never stops audio automatically during navigation.

14. Favorites Flow
Creating Favorite
User:
Select Rhythm

↓

Adjust BPM

↓

Select Harmony (Optional)

↓

Set Volumes

↓

Press Heart Icon

↓

Favorite Saved

15. Loading Favorite
User:
Favorites Screen

↓

Select Favorite

↓

Configuration Loaded

↓

Press Play
Loading a Favorite does not automatically start sound.

16. Deleting Favorite
User:
Favorites Screen

↓

Select Delete Icon

↓

Confirm

↓

Favorite Removed
Original audio files remain unchanged.

17. My Own Flow
Creating Personal Loop
User:
My Own

↓

Import Audio File

↓

Preview

↓

Set Start Point

↓

Set End Point

↓

Fine Tune

↓

Save

18. My Own Playback
Saved file appears:
My Own Library

↓

Select File

↓

Play
A My Own rhythm behaves like a normal rhythm.

19. My Own Favorite
User can:
My Own File

↓

Heart Icon

↓

Saved as Favorite
Saved Favorite includes:
My Own file
BPM
Volume
Optional Harmony

20. My Own Delete Flow
Only user-created files can be deleted.
Flow:
My Own

↓

Delete Icon

↓

Confirmation

↓

File Removed
Built-in rhythms cannot be deleted.

21. Preview Flow
Preview is available for:
Rhythm
Harmony
Behavior:
Select Item

↓

Preview

↓

Temporary Playback

↓

Return
Preview does not modify active settings.

22. Error Flow
If an error occurs:
Examples:
Missing file
Invalid audio
Storage problem
System:
Show Message

↓

Keep User State

↓

Allow Recovery
No application crash.

23. Live Performance Flow
Recommended live usage:
Open App

↓

Select Favorite

↓

Press Play

↓

Perform
The workflow should require minimal attention.

24. User Experience Principles
Every action should be:
Clear
Fast
Predictable
Reversible
The user should always understand:
What is selected
What is playing
What will happen next
[8/3/26 5:42 PM] سرزمینم ایران!!: 25. Final User Flow Principle
The ideal experience is:
A musician opens the application and starts playing within seconds, without needing technical knowledge.
The application should disappear behind the music and become a reliable performance tool.

End of Document
