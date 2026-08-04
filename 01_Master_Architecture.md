01_Master_Architecture.md

Rhythm Loop App

Master Architecture

Version: 1.0

---

1. System Overview

Rhythm Loop App is designed as a modular audio application optimized for live musical performance.

The system is composed of several independent modules that communicate through clearly defined interfaces.

Each module has a single responsibility, making future development and maintenance straightforward.

---

2. Architectural Goals

The architecture is designed to satisfy the following goals:

- Fast startup
- Stable playback
- Modular codebase
- Independent audio layers
- Simple navigation
- Easy future expansion
- Minimal CPU usage
- Low memory footprint
- Offline operation

---

3. High-Level Architecture

+-----------------------------+
|          UI Layer           |
+-----------------------------+
            |
            |
+-----------------------------+
|     Application Controller  |
+-----------------------------+
      |      |      |
      |      |      |
 Rhythm  Harmony  Favorites
 Engine   Engine    Manager
      |
      |
 Audio Playback Engine
      |
      |
 Asset Manager
      |
      |
 Internal Storage

---

4. Main Modules

The application consists of the following modules:

UI Layer

Responsible for:

- User interaction
- Buttons
- Sliders
- Lists
- Navigation
- Playback status

No audio processing occurs here.

---

Application Controller

Acts as the central coordinator.

Responsibilities:

- Receives UI events
- Dispatches commands
- Prevents conflicting actions
- Coordinates Rhythm and Harmony

---

Rhythm Engine

Responsible for:

- Rhythm playback
- BPM modification
- Loop playback
- Rhythm volume
- Preview

The Rhythm Engine never controls Harmony.

---

Harmony Engine

Responsible for:

- Harmony playback
- Harmony preview
- Harmony volume

Harmony intentionally ignores BPM changes.

---

Audio Playback Engine

Responsible for:

- Audio decoding
- Looping
- Gapless playback
- Mixing Rhythm and Harmony
- Synchronization

This module should be isolated from UI logic.

---

Favorites Manager

Stores:

- Rhythm
- Harmony
- BPM
- Volume

Allows instant restoration of saved presets.

---

My Own Manager

Handles:

- Imported files
- Loop creation
- Start point
- End point
- Fine tuning
- Saving
- Deleting user-created loops

---

Asset Manager

Responsible for loading audio assets.

Version 1:

Assets are bundled inside APK.

Version 2:

Supports metadata through JSON.

---

5. Navigation Architecture

Main Screen

↓

Rhythm Category

↓

Rhythm Selection

↓

Playback

Back always returns to Main Screen.

No deep navigation hierarchy.

---

6. Audio Architecture

Two completely independent playback channels exist.

Channel A

Rhythm

Supports:

- Play
- Pause
- Stop
- Volume
- BPM

Channel B

Harmony

Supports:

- Play
- Pause
- Stop
- Volume

No BPM processing.

---

7. Playback Rules

Rhythm:

- Loop forever
- Gapless playback
- Tempo adjustable

Harmony:

- Plays independently
- Continues until changed
- Manual Play only

Preview:

Always independent.

Preview never interrupts active playback.

---

8. BPM Architecture

Each rhythm stores:

Default BPM

Current BPM

Current BPM is editable.

Harmony ignores BPM completely.

---

9. Storage Architecture

Application Assets

assets/

rhythms/

Persian/

Turkish/

Azeri/

Arabic/

European/

Harmony/

User Files

Internal Storage

MyOwn/

Favorites/

Settings/

Built-in assets cannot be deleted.

User-created assets can.

---

10. Favorite Architecture

Favorite object contains:

- Rhythm Category
- Rhythm File
- Harmony File (optional)
- BPM
- Rhythm Volume
- Harmony Volume

Loading a Favorite restores exactly the saved configuration.

---

11. My Own Architecture

Workflow:

Import File

↓

Preview

↓

Adjust Start

↓

Adjust End

↓

Fine Tune

↓

Save

↓

Available inside My Own library

---

12. User Interface Architecture

Landscape only.

Main Screen

↓

Category

↓

Rhythm List

↓

Playback

Harmony selection always remains quickly accessible.

---

13. Error Handling

Application should gracefully handle:

Missing file

Corrupted fi
le

Unsupported file

Playback interruption

Invalid Favorite

No crashes.

Always return control to the user.

---

14. Future Architecture

Version 2 may introduce:

JSON metadata

Named rhythms

Cloud synchronization

Additional rhythm packs

Waveform visualization

Search

MIDI support

External controllers

The Version 1 architecture should already allow these additions without major redesign.

---

15. Design Principles

Every module should remain:

Independent

Reusable

Replaceable

Testable

Simple

The application should favor reliability over unnecessary complexity.

---

16. Final Principle

The architecture exists for one purpose:

«Allow musicians to start playing within seconds while guaranteeing stable and uninterrupted rhythm playback.»

Every future modification should preserve this principle.

---

End of Document
