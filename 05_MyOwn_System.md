MyOwn_System.md
Rhythm Loop App
My Own System Specification
Version: 1.0

1. Purpose
The My Own module allows users to import their own rhythm audio files, define loop points, fine-tune the loop, and save them as reusable rhythm assets inside the application.
The goal is to let musicians build a personal rhythm library without modifying the original built-in content.

2. Design Philosophy
The My Own system should be:
Simple
Fast
Non-destructive
Reliable
Easy to understand
The original imported file should never be modified during editing.
All editing happens before the user presses Save.

3. Main Workflow
Open File
      ↓
Preview
      ↓
Adjust Loop Start
      ↓
Adjust Loop End
      ↓
Fine Tune
      ↓
Preview Again
      ↓
Save
      ↓
Available in My Own Library

4. Import File
User selects an audio file from the device.
Recommended formats:
MP3
Future versions:
WAV
FLAC
OGG
If the format is unsupported, an error message should be displayed.

5. Preview
The user can preview the imported file at any time.
Preview never affects the current rhythm or harmony playback.

6. Loop Start
User defines:
Loop Start Position
using:
Slider
The selected point represents the beginning of the loop.

7. Loop End
User defines:
Loop End Position
using:
Slider
The selected point represents the end of the loop.

8. Fine Tune
Fine tuning allows precise adjustment.
Separate controls exist for:
Loop Start
Loop End
Each supports:
+100 ms
-100 ms
+10 ms
-10 ms
Both loop points are adjusted independently.

9. Non-Destructive Editing
Until Save is pressed:
Nothing is permanently changed.
User may:
Move Start
Move End
Preview
Repeat
Unlimited times.

10. Save
After pressing Save:
The application creates a new loop file.
The original imported file remains untouched.
The new loop becomes part of the application's My Own library.

11. File Naming
Version 1
Automatic naming:
Own1
Own2
Own3
...
No manual naming is required.
Future versions may support custom names.

12. Storage
Saved loops are stored inside the application's private storage.
Example:
Internal Storage

MyOwn/

Own1.mp3

Own2.mp3

Own3.mp3
Users should not need to manage these files manually.

13. My Own Library
Each saved loop appears as a card.
Each card contains:
Play
Favorite
Delete
Version 1 displays:
Own1
Own2
...
Future versions may display custom names.

14. Play
Play starts playback of the selected custom loop.
The loop behaves exactly like a built-in rhythm.
It supports:
Play
Pause
Stop
BPM adjustment
Volume adjustment

15. Favorite
A My Own rhythm may be saved as a Favorite.
Favorite stores:
Loop
BPM
Rhythm Volume
Harmony (if selected)
Harmony Volume

16. Delete
Delete removes only the selected My Own loop.
Built-in rhythm files can never be deleted.
A confirmation dialog should be displayed before deletion.

17. Editing Existing Loops
Version 1
Editing an already-saved loop is not supported.
To change a loop:
Re-import the source file
Create a new loop
Save as a new item
Future versions may allow editing.

18. Interaction with Rhythm Engine
After saving:
A My Own loop behaves exactly like a normal rhythm.
It appears in the My Own category.
All Rhythm Engine functions apply.

19. Interaction with Harmony
My Own loops work seamlessly with the Harmony Engine.
Harmony remains completely independent.
Changing BPM affects only the My Own rhythm.
Harmony playback is unchanged.

20. Error Handling
Possible errors:
File not found
Unsupported format
Corrupted audio
Save failed
Storage full
The application should display an appropriate message and remain stable.

21. Performance Requirements
Import should be fast.
Preview should start quickly.
Saving should not block the interface.
Playback must remain gapless after saving.

22. Future Expansion
Future versions may include:
Waveform visualization
Zoomable timeline
Automatic loop detection
Trim and fade controls
Rename loops
Duplicate loops
Export loops
Cloud synchronization
[8/3/26 4:08 PM] سرزمینم ایران!!: 23. Design Principles
The My Own system should always remain:
Easy for beginners
Powerful enough for musicians
Non-destructive
Reliable
Consistent with the rest of the application

24. Final Principle
The purpose of the My Own system is:
Allow musicians to quickly build a personal rhythm library from their own audio files while keeping the editing process simple, safe, and fully integrated with the application's playback engine.
End of Document
