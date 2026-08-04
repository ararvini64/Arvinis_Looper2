AI_Master_Prompt.md
Rhythm Loop App
AI Master Development Prompt
Version: 1.0

1. Role
You are a senior mobile application architect and audio software engineer.
Your task is to design and implement the Rhythm Loop App according to the specifications provided in this documentation.
Do not simplify the requirements without approval.
Do not add unnecessary features.
The primary objective is:
Build a stable, fast, musician-focused rhythm application.

2. Project Description
Rhythm Loop App is a professional audio application for musicians.
The application provides:
Rhythm playback
Independent harmony playback
BPM control
Favorites
User-created loops (My Own)
Offline operation
The application should behave like a musical instrument, not a media player.

3. Core Rules
Follow these rules at all times:
Rhythm and Harmony are independent systems.
BPM affects Rhythm only.
Harmony never follows BPM changes.
No automatic playback on startup.
No automatic Harmony playback after selection.
User must always initiate playback.
Built-in assets cannot be deleted.
User-created files can be deleted.
Simplicity is more important than feature quantity.

4. Required Architecture
Implement the following modules:
UI Layer

Application Controller

Rhythm Engine

Harmony Engine

Audio Engine

Favorites Manager

My Own Manager

Asset Manager

Storage Manager
Each module must have clear responsibilities.
Avoid tightly coupled code.

5. Audio Requirements
The application must provide:
Smooth playback
Gapless looping
Low latency
Stable long sessions
Independent audio channels
The system must support:
Rhythm Channel
Harmony Channel
Both channels can operate simultaneously.

6. Rhythm Engine Requirements
Implement:
Rhythm selection
Play
Pause
Stop
Loop playback
BPM adjustment
Volume control
BPM controls:
+10
-10
+1
-1
Slider

7. Harmony Engine Requirements
Implement:
Harmony selection
Preview
Play
Pause
Stop
Volume control
Do not implement BPM changes for Harmony.
Harmony must remain independent.

8. User Interface Requirements
The application must use:
Landscape orientation.
The interface must have:
Large buttons
Clear status indicators
Minimal navigation
Fast access
The user must always see:
Current Rhythm
Current Harmony
BPM
Playback status

9. Main Screen Requirements
Main screen contains:
Categories:
Persian
Turkish
Azeri
Kurdish
Arabic
European
My Own
Favorites
The user should reach a playable rhythm quickly.

10. Favorites Requirements
A Favorite stores:
Rhythm

Harmony (optional)

BPM

Rhythm Volume

Harmony Volume
Loading a Favorite restores the complete configuration.
Do not automatically start playback.

11. My Own Requirements
Users can:
Import audio files
Set loop start
Set loop end
Fine tune timing
Save custom loops
Fine tuning:
+100ms
-100ms
+10ms
-10ms
Editing must be non-destructive.

12. File Structure
Version 1:
Use bundled assets.
Example:
assets/

rhythms/

Persian/

P1.mp3

Turkish/

T1.mp3


harmony/

H1.mp3

13. Storage Rules
Built-in:
Read-only.
User content:
Writable.
Store:
Favorites
My Own files
Settings
separately.

14. Development Priorities
Implement in this order:
Audio Engine
Rhythm Engine
Harmony Engine
Main UI
Favorites
My Own
Final polish

15. Do Not Add Without Approval
Do not add:
User accounts
Cloud features
Social features
Online streaming
Complex editing
Unnecessary animations
These may be considered for future versions.

16. Testing Requirements
Before delivery test:
Audio
Long playback
Loop stability
BPM changes
Simultaneous Rhythm + Harmony
UI
Landscape layouts
Navigation
Touch accuracy
Data
Favorites
My Own files
Error handling

17. Code Quality Requirements
The code must be:
Clean
Modular
Documented
Maintainable
Avoid:
Hard-coded values
Duplicate logic
Unnecessary dependencies

18. Future Compatibility
The architecture should allow:
JSON metadata
New rhythm packs
Harmony libraries
Cloud backup
MIDI support
without rewriting the core system.
[8/3/26 4:50 PM] سرزمینم ایران!!: 19. Final Instruction
Build the application according to the documented specifications.
When a design decision is unclear:
Choose the option that improves:
Simplicity
Reliability
Speed
Musician usability
Do not optimize for unnecessary complexity.

Final Project Statement
Create a professional rhythm companion application where musicians can instantly select, control, and perform with rhythms and optional harmony backgrounds.
The final product must feel reliable enough to use during real musical practice and performance.

End of Document
