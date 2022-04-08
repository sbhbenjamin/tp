---
layout: page
title: Nicolle's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=nicollegann&breakdown=true)


* **Enhancements implemented**:

  * Evolved AddressBook into Harmonia 
    * Modified `EditCommand` and `EditCommandParser` by replacing the fields in AB3 (`phone`, `email`, `address`) with fields used in Harmonia (`description`, `deadline`) (PR: [#125](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/125)).
    * Modified `FindCommand` and `ClearCommand` to suit Harmonia (PR: [#121](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/121)).
    * Replaced sample data for AB3 with sample data for Harmonia.
    * Modified test utilities (PR: [#130](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/130)).
    * Modified tests for `edit`, `find` and `clear` commands (PR: [#137](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/137)).
    * Refactored test for `LogicManager`

  * Enhancement to the `find` feature (PR: [#156](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/156))
    * Added support to search by tags.

  * Enhancement to the `help` feature (PR: [#188](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/188))
    * Added the option of opening the user guide using the user's default browser.
    * Justification: This enhancement provides convenience for the user as they can choose to view the user guide by clicking the button instead of copying and pasting the url into another browser window.
    * Credits: Code to open url using default browser adapted from [here](https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java) 
    
  * Enhancement to the `list` feature (PR: [#196](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/196))
    * Added support to list all existing tags in the task list.
    * Justification: This enhancement complements other features such as the `add`, `edit` and `find` features. Users can quickly view what tags are used in the task list, before proceeding to use them in the other commands.

  * Conducted Testing (PR: [#274](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/274))
    * Wrote additional tests for `TagList` and the `list` command.
    * Updated existing tests for the `add` and `edit` commands.


* **Project management**:
  * Managed release [`v1.2`](https://github.com/AY2122S2-CS2103T-T09-1/tp/releases/tag/v1.2) 
  * Helped to wrap up and close milestone `v1.3`


* **Documentation**:
  * User Guide (PRs: [#186](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/186), [#200](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/200), [#276](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/276))
    * Added documentation for sections Getting Started and Command format.
    * Added documentation for the `clear` feature.
    * Updated documentation to add a warning note for the `edit` feature.
    * Updated documentation for the `list t/` feature.
    * Conducted cosmetic tweaks to existing documentation to improve consistency in terms of formatting and language used.
 
  * Developer Guide
    * Added documentation for use cases (PR: [#61](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/61)).
    * Added implementation details for search by tags feature (PR: [#163](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/163)).


* **Team-based tasks**:
  * Updated the README (PR: [#59](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/59)). 
  * PRs reviewed (with non-trivial review comments): [#142](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/142), [#191](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/191)


* **Community**:
  * Reported non-trivial bugs and suggestions for other teams: [#340](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/340), [#343](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/343), [#344](https://github.com/AY2122S2-CS2103-F11-2/tp/issues/344)
