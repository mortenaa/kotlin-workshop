# How to use marp
https://marp.app/

## Marp CLI
https://github.com/marp-team/marp-cli

Install with `npm install -g @marp-team/marp-cli`.

Converts to HTML by default: `marp slides.md`.

Convert to pdf: `marp --pdf slides.md` or `marp slides.md -o slides.pdf`

Convert to powerpoint: `marp --pptx slides.md` or `marp slides.md -o slides.pptx`

Watch mode will re-render output on change in markdown file: `marp -w slides.md`

## Marp Visual Studio Code
* Install `Marp for VS Code` from Marketplace.
* add ```
---
marp: true
---
```
to the top of the file.

Restart VS Code if you don't see the preview option in upper right corner.
