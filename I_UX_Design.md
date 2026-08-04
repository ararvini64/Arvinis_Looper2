I_UX_Design.md

Rhythm Loop AppU

UI / UX Design Specification

Version: 1.0

---

1. Design Philosophy

The application is designed for musicians who need to access rhythms as quickly as possible.

The interface should feel like a musical instrument rather than a traditional media player.

Design priorities:

- Simple
- Fast
- Clean
- Large touch targets
- Minimal navigation
- Landscape optimized

---

2. Screen Orientation

Only Landscape mode is supported.

Portrait mode is disabled.

All UI elements must fit inside the visible screen.

No controls should appear outside the display.

---

3. Main Screen

The Main Screen is the application's home page.

It contains four sections:

Header

Displays:

- Application Logo
- Application Name

---

Rhythm Categories

Large buttons:

- Persian
- Turkish
- Azeri
- Kurdish
- Arabic
- European
- My Own
- Favorites

Each category uses its own color.

Buttons should be large enough for live performance.

---

Harmony Section

Displays available Harmony tracks.

Each Harmony shows:

- Name
- Preview button
- Select button

Selected Harmony is highlighted.

Harmony does not start automatically.

User must press Play.

---

Now Playing Panel

Always visible.

Shows:

- Rhythm Category
- Rhythm Number / Name
- Harmony Name
- Current BPM
- Playback Status

---

4. Rhythm Category Screen

When a category is selected:

Example:

Persian

The screen displays:

Top Toolbar

↓

Tempo Controls

↓

Volume

↓

Rhythm List

↓

Bottom Controls

---

5. Top Toolbar

Contains:

Back button

Category Name

Current BPM

Favorite Button

Back returns to Main Screen.

Playback continues unless the user presses Stop.

---

6. Tempo Controls

Controls include:

+10

-10

+1

-1

BPM Slider

Changing BPM affects only Rhythm.

Harmony remains unchanged.

---

7. Volume Controls

Independent sliders:

Rhythm Volume

Harmony Volume

Changing one never changes the other.

---

8. Rhythm List

Each rhythm appears inside a rounded card.

Example:

P1

P2

P3

...

Version 1 displays only numbers.

Version 2 may display:

P1 - 6/8 Slow

P2 - Bandari

...

---

Each card supports:

Preview

Play

Favorite

Active rhythm is highlighted.

---

9. Preview Behavior

Preview plays only the selected file.

Preview never replaces current playback.

Preview is independent.

---

10. Playback Controls

Rhythm

Play

Pause

Stop

Harmony

Play

Pause

Stop

Both systems are completely independent.

---

11. Back Button

If user presses Back:

Application returns to Main Screen.

Playback continues.

Unless user pressed Stop beforehand.

---

12. Main Screen During Playback

Main Screen still shows:

Current Rhythm

Current Harmony

Play State

User can:

Pause

Stop

Select another Category

Select another Harmony

---

13. Harmony Selection

Selecting a new Harmony:

Stops previous Harmony.

Loads new Harmony.

Does not start playback automatically.

User presses Play.

---

14. Favorites Screen

Displays:

Favorite cards.

Each card contains:

Rhythm

Harmony

Saved BPM

Play

Delete

Delete removes only Favorite.

Never removes original assets.

---

15. My Own Screen

Displays:

User-created loops.

Each item includes:

Play

Favorite

Delete

Built-in rhythms never appear here.

---

16. My Own Editor

Workflow:

Open File

↓

Preview

↓

Start Slider

↓

End Slider

↓

Fine Tune

↓

Save

↓

Appears in My Own list

---

17. Fine Tune Controls

Independent controls.

Start:

+100ms

-100ms

+10ms

-10ms

End:

+100ms

-100ms

+10ms

-10ms

User may repeat adjustments until satisfied.

---

18. Save Behavior

Saving creates:

Own1

Own2

Own3

...

Stored inside application storage.

---

19. Delete Behavior

Delete available only for:

My Own

Favorites

Delete is represented by an icon.

Not by text.

Built-in application assets cannot be deleted.

---

20. Colors

Each rhythm category has its own identity color.

Example:

Persian

Deep Blue

Turkish

Orange

Arabic

Green

Favorites

Red

My Own

Purple

Colors help quick recognition.

---

21. Typography

Large readable font.

Suitable for stage lighting.

No
[8/3/26 3:43 PM] سرزمینم ایران!!: tiny labels.

Minimal text.

---

22. Touch Targets

Buttons must remain large.

Spacing should prevent accidental touches.

Designed for musicians using one hand.

---

23. Scrolling

Rhythm lists scroll vertically.

Categories with few rhythms do not scroll.

Application automatically adapts.

---

24. Visual Feedback

Selected Rhythm:

Highlighted.

Selected Harmony:

Highlighted.

Playing:

Clearly indicated.

Paused:

Clearly indicated.

Stopped:

Clearly indicated.

User should never wonder what is currently active.

---

25. UX Principles

Every screen should answer immediately:

Where am I?

What is playing?

How do I change it?

How do I go back?

---

26. Final Design Goal

The interface should require almost no learning.

A musician should feel comfortable using it within minutes.

The application should remain clean, elegant and reliable during live performance.

---

End of Document
