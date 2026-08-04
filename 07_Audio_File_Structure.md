07_Audio_File_Structure.md
Rhythm Loop App
Audio File Structure Specification
Version: 1.0

1. Purpose
This document defines how audio files are organized, named, stored, and loaded inside the Rhythm Loop App.
The goal is to create a simple system for Version 1 while allowing future expansion without changing the core architecture.

2. Version 1 Strategy
Version 1 uses a bundled audio library.
All built-in rhythm and harmony files are included inside the application package (APK).
Advantages:
Fast loading
Offline operation
Reliable playback
No server dependency
Simple deployment

3. Asset Structure
Recommended structure:
assets/

├── rhythms/
│
│   ├── Persian/
│   │
│   │   ├── P1.mp3
│   │   ├── P2.mp3
│   │   ├── P3.mp3
│   │
│   ├── Turkish/
│   │
│   │   ├── T1.mp3
│   │   ├── T2.mp3
│
│   ├── Azeri/
│   │
│   ├── Kurdish/
│   │
│   ├── Arabic/
│   │
│   └── European/
│
├── harmony/
│
│   ├── H1.mp3
│   ├── H2.mp3
│   ├── H3.mp3
│
└── myown/

4. Rhythm File Naming
Version 1 uses simple numbering.
Examples:
P1.mp3

P2.mp3

P3.mp3
The letter represents category.
The number represents rhythm index.

5. Category Naming
Folder names represent categories.
Example:
Persian

Turkish

Azeri

Arabic
The folder name becomes the category identifier.

6. Harmony File Naming
Version 1:
Simple numbering.
Example:
H1.mp3

H2.mp3

H3.mp3
Future versions may include:
H1_SharedMinor.mp3

H2_Shor.mp3

7. Audio Requirements
All rhythm files should be prepared before adding to the application.
Requirements:
Clean audio
Correct looping points
No silence at loop boundaries
No unwanted clicks
Consistent volume level

8. Rhythm Loop Preparation
Each rhythm file should already contain:
Complete loop section.
The application does not need to calculate loops in Version 1.
Benefits:
Better performance
Simpler code
More reliable playback

9. BPM Metadata
Version 1:
BPM may be stored separately or inside application configuration.
Example:
P1.mp3

Default BPM: 90

10. Future JSON Structure
Future versions can introduce metadata.
Example:
{
  "id": "P1",
  "category": "Persian",
  "name": "6/8 Slow",
  "bpm": 90,
  "file": "P1.mp3"
}

11. Future Full Library Example
{
 "rhythms":[
   {
    "id":"P1",
    "category":"Persian",
    "name":"6/8 Slow",
    "bpm":90,
    "file":"Persian/P1.mp3"
   }
 ]
}

12. My Own Storage
User-created files are not stored in the APK.
They are stored separately.
Example:
Internal Storage/

MyOwn/

Own1.mp3

Own2.mp3

13. File Protection
Built-in assets:
Cannot be deleted.
Cannot be modified by user.
User-created assets:
Can be:
Played
Favorited
Deleted

14. Asset Loading
Application startup:
Load categories.
Load available files.
Display library.
Audio files should be loaded only when needed.

15. Performance Rules
Do not preload all audio files.
Recommended:
Load selected file only.
Keep memory usage low.

16. Adding New Content
Developer workflow:
Add new folder:
Example:
rhythms/

NewCategory/
Add files:
N1.mp3

N2.mp3
Update configuration if required.
Rebuild APK.

17. Content Management Philosophy
The developer should never need to manually modify application code for every new rhythm.
The structure should support:
Adding folders
Adding files
Updating metadata

18. Version 2 Content System
Future system:
Audio files + JSON metadata.
Advantages:
Full names
Descriptions
BPM
Time signatures
Search
Filtering
Categories

19. Backup and Migration
Future versions should allow:
Exporting:
My Own files
Favorites
User settings
Importing:
New rhythm packs
Harmony packs

20. Final Principle
The audio structure should balance:
Version 1 simplicity
with
Future scalability.
The system must allow the application to start simple while supporting a professional audio library in future versions.

End of Document
