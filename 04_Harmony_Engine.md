Harmony_Engine.md
Rhythm Loop App
Harmony Engine Specification
Version: 1.0

1. Purpose
The Harmony Engine is responsible for all harmonic background playback inside the application.
Harmony is designed to accompany the rhythm without interfering with it.
Unlike the Rhythm Engine, the Harmony Engine never controls tempo.
Its responsibility is only harmonic playback.

2. Objectives
The Harmony Engine must provide:
Stable playback
Independent control
Independent volume
Preview
Manual playback
Easy replacement
Low CPU usage
Harmony should always remain optional.
The application must also work perfectly without Harmony.

3. Philosophy
Harmony is treated as a completely separate audio layer.
Rhythm and Harmony should never depend on each other.
Stopping one must not stop the other.
Pausing one must not pause the other.
Changing one must not modify the other.

4. Responsibilities
Harmony Engine controls:
Harmony selection
Loading files
Preview
Play
Pause
Stop
Volume
Nothing else.

5. Supported Audio
Version 1
Recommended format:
MP3
Future versions may support:
WAV
FLAC
OGG

6. Harmony Library
Harmony files are stored separately from rhythms.
Example:
assets/

Harmony/

H1.mp3

H2.mp3

H3.mp3
Future versions may organize harmonies by:
Scale
Mode
Genre
Instrument

7. Harmony Selection
Selecting a Harmony performs:
Stop current Harmony
Load new Harmony
Display new Harmony
Prepare playback
Playback does not start automatically.
The user must press Play.

8. Preview
Preview is completely independent.
Characteristics:
Plays only selected Harmony.
Does not replace current playback.
Ends automatically.
Does not change Favorite settings.

9. Playback States
Harmony exists in one of four states.
Stopped
Ready
Playing
Paused
Only one state is active.

10. Play
Play starts:
Beginning
or
Paused position
depending on current state.

11. Pause
Pause freezes playback.
Current position is preserved.
Play resumes from paused position.

12. Stop
Stop:
Ends playback.
Resets playback position.
Returns state to Stopped.

13. Tempo Rules
Harmony never changes tempo.
Harmony ignores:
BPM
Tempo Slider
+10
-10
+1
-1
Changing rhythm speed must never affect Harmony playback.

14. Volume
Harmony has its own volume slider.
Changing Harmony volume never changes Rhythm volume.
Both sliders operate independently.

15. Background Behavior
Harmony is designed as a background musical layer.
It should:
Remain smooth.
Avoid sudden interruptions.
Support long playback sessions.

16. Interaction with Rhythm
Possible situations:
Rhythm Playing
Harmony Playing
↓
Both continue.

Rhythm Stopped
Harmony Playing
↓
Harmony continues.

Harmony Stopped
Rhythm Playing
↓
Rhythm continues.

Both engines are completely independent.

17. Returning to Main Screen
When Back is pressed:
Harmony continues.
Playback is not interrupted.
Unless user explicitly presses Stop.

18. Replacing Harmony
Selecting another Harmony:
Stops previous Harmony.
Loads new Harmony.
Waits for Play.
Never starts automatically.

19. Favorites
Favorite stores:
Selected Harmony
Harmony Volume
If Favorite contains no Harmony:
No Harmony should be loaded.

20. Application Startup
When application opens:
Harmony is not automatically loaded.
Nothing plays automatically.
User always initiates playback.

21. Error Handling
Possible errors:
Missing file
Corrupted file
Unsupported format
Behavior:
Display error.
Remain stable.
Never crash.

22. Performance Requirements
Harmony playback should consume minimal CPU.
Memory usage should remain low.
Playback must remain stable during long sessions.

23. User Experience
User should always know:
Current Harmony
Playback State
Volume
Whether Harmony is active
Clear visual highlighting should indicate active Harmony.

24. Future Expansion
Future versions may include:
Multiple Harmony banks
Categories
Search
Metadata
Instrument information
Scale information
Chord information
JSON support
Cloud libraries

25. Design Principles
Harmony Engine must remain:
Simple
Independent
Reliable
Stable
Expandable
[8/3/26 4:05 PM] سرزمینم ایران!!: 26. Final Principle
Harmony is an enhancement—not the center of the application.
Its purpose is:
Provide an optional, stable and independent harmonic background that musicians can freely combine with any rhythm without affecting rhythm playback or tempo.
Every future enhancement should preserve this independence.

End of Document
