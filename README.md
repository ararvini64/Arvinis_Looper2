README.md
Rhythm Loop App
Professional Rhythm & Harmony Companion Application for Musicians.
Version: 1.0

1. Project Overview
Rhythm Loop App is a mobile application designed for musicians who need fast, reliable, and high-quality rhythm accompaniment during practice, teaching, and performance.
The application provides:
Professional rhythm playback
Independent harmony playback
BPM control
Personal rhythm creation
Favorite configurations
The main goal is simplicity, speed, and reliability.

2. Main Concept
The application works like a musical instrument.
A musician should be able to:
Open the application.
Select a rhythm.
Adjust BPM if needed.
Add optional harmony.
Start playing.
The entire workflow should require minimal interaction.

3. Core Features
Rhythm Engine
Provides:
Rhythm categories
Rhythm selection
Loop playback
BPM control
Rhythm volume

Harmony Engine
Provides:
Optional harmonic background
Independent playback
Independent volume
Harmony does not follow rhythm tempo.

Favorites
Allows users to save:
Rhythm
Harmony
BPM
Volume settings
for instant recall.

My Own
Allows users to:
Import personal audio files
Create custom loops
Fine tune loop points
Save personal rhythms

4. Supported Categories
Version 1 categories:
Persian
Turkish
Azeri
Kurdish
Arabic
European
My Own
Favorites

5. Version 1 Philosophy
Version 1 focuses on:
Stability
Offline operation
Fast performance
Simple user experience
Features intentionally excluded:
User accounts
Cloud services
Online libraries
Complex editing systems

6. Architecture
The project uses a modular architecture:
UI Layer

↓

Application Controller

↓

Rhythm Engine

Harmony Engine

Favorites Manager

My Own Manager

↓

Audio Engine

↓

Storage / Assets

7. Documentation Structure
Project documentation:
00_Project_Overview.md

01_Master_Architecture.md

02_UI_UX_Design.md

03_Rhythm_Engine.md

04_Harmony_Engine.md

05_MyOwn_System.md

06_Favorites_System.md

07_Audio_File_Structure.md

08_Developer_Guide.md

09_AI_Master_Prompt.md

CHANGELOG.md

README.md
Additional design documentation:
10_User_Flow.md

11_Functional_Requirements.md

8. Development Rules
All development decisions should follow these principles:
Simplicity
Do not add unnecessary complexity.

Reliability
Audio playback must always be stable.

Independence
Rhythm and Harmony must remain separate systems.

Performance
The application should work smoothly on normal devices.

9. Important Technical Rules
BPM
BPM affects only Rhythm.
Harmony ignores BPM.

Playback
No automatic playback on startup.
No automatic Harmony playback after selection.
User always starts playback.

Storage
Built-in files:
Read-only.
User files:
Editable and removable.

10. Recommended Development Order
Implementation should follow:
Audio Engine
Rhythm Engine
Harmony Engine
User Interface
Favorites
My Own
Testing and polishing

11. Future Expansion
The architecture should support future features:
JSON metadata
Named rhythms
Search
Cloud backup
Rhythm packs
MIDI support
Advanced editing
without rebuilding the core application.

12. AI Development Usage
When providing this project to an AI coding assistant:
Provide all Markdown files together.
The AI should read:
README.md
Project Overview
Architecture
Feature specifications
Developer Guide
before generating code.

13. Final Project Statement
Rhythm Loop App is designed to be a dependable musical companion.
The application succeeds when a musician can confidently use it during practice or performance without distraction.
The most important priorities are:
Stable audio
Simple operation
Fast access
Professional reliability

End of Document
