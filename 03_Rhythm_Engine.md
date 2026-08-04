03_Rhythm_Engine.md

Rhythm Loop App

Rhythm Engine Specification

Version: 1.0

---

1. Purpose

The Rhythm Engine is responsible for all rhythm-related playback inside the application.

It manages:

- Rhythm selection
- Gapless looping
- Tempo (BPM)
- Preview
- Play
- Pause
- Stop
- Volume

The Rhythm Engine is completely independent from the Harmony Engine.

---

2. Responsibilities

The Rhythm Engine controls:

- Loading rhythm files
- Decoding audio
- Continuous loop playback
- BPM adjustment
- Playback state
- User interaction
- Communication with the Audio Engine

---

3. Supported Audio

Version 1

Recommended format:

MP3

Future versions may support:

- WAV
- FLAC
- OGG

---

4. Loop Playback

Every rhythm must loop seamlessly.

Requirements:

No silence.

No click.

No gap.

The loop should sound continuous.

---

5. Rhythm Categories

Supported categories:

Persian

Turkish

Azeri

Kurdish

Arabic

European

My Own

Favorites

Each category loads independently.

---

6. Rhythm Selection

Selecting a rhythm performs:

Stop previous rhythm.

Load new rhythm.

Display current rhythm.

Prepare playback.

Playback does not begin automatically unless the user presses Play.

---

7. Preview

Preview is independent.

Characteristics:

Plays selected rhythm only.

Does not replace active rhythm.

Does not modify Favorites.

Ends automatically.

---

8. Playback States

Rhythm may exist in one of four states.

Stopped

Ready

Playing

Paused

Only one state can exist at a time.

---

9. Play

Play begins playback from:

Beginning

or

Paused position

depending on current state.

---

10. Pause

Pause freezes playback.

Position is preserved.

Play resumes from paused location.

---

11. Stop

Stop performs:

Playback ends.

Loop stops.

Playback position resets.

State becomes Stopped.

---

12. BPM System

Each rhythm stores:

Default BPM

Current BPM

Default BPM is defined by developer.

Current BPM is editable by user.

---

13. BPM Controls

Available controls:

+10

-10

+1

-1

BPM Slider

Slider allows continuous adjustment.

---

14. BPM Rules

Changing BPM affects:

Playback speed.

Loop timing.

Rhythm only.

Harmony remains unchanged.

---

15. Volume

Rhythm has independent volume.

Changing Rhythm Volume never changes Harmony Volume.

---

16. Active Rhythm

Application always displays:

Category

Rhythm Number

Current BPM

Playback State

---

17. Favorites Interaction

Saving Favorite stores:

Category

Rhythm

Current BPM

Volume

If Harmony is active, it is stored separately.

---

18. Category Switching

User may switch categories at any time.

Current playback continues until:

Stop

or

New Rhythm selection.

Simply entering another category does not stop playback.

---

19. Returning to Main Screen

Back button:

Returns user to Main Screen.

Playback continues.

Stop is never automatic.

---

20. Error Handling

Possible errors:

Missing file

Corrupted file

Unsupported format

Engine should:

Display message.

Remain stable.

Never crash.

---

21. Performance Requirements

Startup:

Fast.

CPU usage:

Low.

Memory:

Minimal.

Loop playback:

Continuous.

Suitable for long rehearsals.

---

22. User Experience

Musician should always know:

Which rhythm is selected.

Whether it is playing.

Current BPM.

Current category.

---

23. Future Expansion

Future versions may include:

Time signature metadata.

Rhythm names.

Search.

Filtering.

Waveform display.

Advanced tempo algorithms.

JSON metadata.

---

24. Design Principles

Rhythm Engine should remain:

Independent.

Simple.

Reliable.

Reusable.

Stable.

---

25. Final Principle

The Rhythm Engine is the heart of the application.

Its primary objective is:

«Provide stable, seamless, low-latency rhythm playback with intuitive tempo control for musicians.»

Every future improvement must preserve playback reliability above all else.

---

End of Document
