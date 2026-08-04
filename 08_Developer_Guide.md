Developer_Guide.md
Rhythm Loop App
Developer Guide
Version: 1.0

1. Purpose
This document provides implementation guidance for developers building the Rhythm Loop App.
The objective is to create a stable, simple, and professional audio application for musicians.
The developer should prioritize:
Audio reliability
Low latency
Simple architecture
Clean code
Future scalability

2. Development Philosophy
This application is not a general music player.
It is a musical performance tool.
The developer must always consider:
Fast interaction
Predictable behavior
Stable playback
Minimal user distraction

3. Recommended Architecture
The application should follow a modular architecture.
Recommended layers:
UI Layer

↓

Application Controller

↓

Audio Controllers

↓

Audio Engine

↓

Storage / Assets

4. Module Separation
The following modules should remain independent:
Rhythm Module
Handles:
Rhythm selection
BPM
Loop playback
Rhythm volume

Harmony Module
Handles:
Harmony selection
Playback
Harmony volume
Harmony must never control Rhythm.

Favorites Module
Handles:
Save
Load
Delete
Restore settings

My Own Module
Handles:
Import
Loop editing
Saving
Deleting user files

5. Audio Implementation Rules
Audio playback is the most important part of the application.
Requirements:
Smooth playback
No gaps
No clicks
Stable looping
Low latency

6. Rhythm Playback Rules
Rhythm playback must support:
Play
Pause
Stop
Loop
BPM change
The engine must maintain playback stability while changing BPM.

7. Harmony Playback Rules
Harmony playback must support:
Play
Pause
Stop
Volume
Harmony does not support BPM changes.

8. State Management
Each audio engine should have a clear state.
Example:
STOPPED

READY

PLAYING

PAUSED
Avoid hidden states.
The UI must always reflect the real audio state.

9. UI Communication
The UI should never directly control audio files.
Correct flow:
User Action

↓

UI

↓

Controller

↓

Audio Engine

↓

Playback

10. File Management
Built-in assets:
Read-only.
User files:
Writable.
Never allow user actions to modify application assets.

11. My Own Implementation
The developer should avoid destructive editing.
The original imported file remains unchanged.
A new processed loop is created after Save.

12. Favorites Implementation
Favorites should store references and settings.
Example:
{
 "rhythm":"Persian/P1.mp3",
 "harmony":"H2.mp3",
 "bpm":95,
 "rhythmVolume":80,
 "harmonyVolume":50
}

13. Error Handling
The application should gracefully handle:
Missing audio files
Invalid data
Storage errors
Unsupported formats
Never crash during normal usage.

14. Performance Requirements
The application should:
Start quickly.
Use minimal memory.
Avoid unnecessary background processes.
Avoid loading unused audio files.

15. Testing Requirements
Before release, test:
Rhythm
Play
Pause
Stop
Loop
BPM changes

Harmony
Independent playback
Volume
Switching

Favorites
Save
Load
Delete

My Own
Import
Edit
Save
Delete

16. Live Performance Testing
Important scenarios:
Long playback sessions
Repeated Play/Pause
Rapid rhythm changes
Switching Harmony while playing
Changing BPM during playback

17. Version 1 Restrictions
Do not add unnecessary complexity.
Avoid:
Cloud systems
Accounts
Online libraries
Advanced editing
Complex settings
Focus on a reliable first release.

18. Future Compatibility
The developer should keep space for:
JSON metadata
New categories
Additional audio packs
Cloud backup
MIDI integration

19. Code Quality Rules
Code should be:
Modular
Documented
Readable
Testable
Avoid:
Duplicate logic
Hard-coded behavior
Tight coupling

20. Recommended Development Order
Implementation order:
Audio Engine
Rhythm Engine
Harmony Engine
Main UI
Favorites
My Own
Final UI polish

21. Release Checklist
Before release:
All built-in rhythms tested
All Harmony tracks tested
BPM works correctly
Favorites restore correctly
My Own works correctly
No crashes
Landscape layout verified
[8/3/26 4:47 PM] سرزمینم ایران!!: 22. Final Developer Principle
The developer should remember:
The application is an instrument. Stability and simplicity are more important than adding features.
Every technical decision should support a musician who needs reliable rhythm playback at any moment.

End of Document
