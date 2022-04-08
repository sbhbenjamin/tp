---
layout: page
title: Thikhina's Project Portfolio Page
---

### Project: Harmonia

**Overview**

Harmonia is a command-line-based personal task manager, which helps university students to manage their academic tasks. It is written in Java and has a GUI implemented with JavaFX.

Given below are my contributions to the project.

**Evolve Storage to TaskList:** [#108](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/108)
- Add `TaskListStorage` to implement storage functionality
- Add `JsonSerializableTaskList` to support JSON storage of `TaskList`
- Add `JsonTaskListStorage` to access data stored JSON file
- Add `JsonAdaptedTask` to store Task in `Jackson` friendly format
- Update `Storage` to implement API for Storage component
- Update `StorageManager` to manage `TaskList` data in local storage
- Update `Deadline` to support its storage
- Update `CompletionStatus` to support its storage

**Add Mark Command:** [#129](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/129)
- Add `MarkCommand` to mark a specified task as complete
- Add `MarkCommandParser` to parse user input along with `mark` command word
- Update `HarmoniaParser` to parse `mark` command word
- Update `Model`, `ModelManager`, `TaskList` to support marking of a task
- Update `UniqueTaskList` with method to check if exactly the same task exists

**Add Unmark Command:** [#133](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/133)
- Add `UnmarkCommand` to mark a specified task as incomplete
- Add `UnmarkCommandParser` to parse user input along with `unmark` command word
- Update `HarmoniaParser` to parse `unmark` command word
- Update `Model`, `ModelManager`, `TaskList` and `UniqueTaskList` to abstract out the logic need for both `MarkCommand` and `UnmarkCommand`

**Test MarkCommand and MarkCommandParser:** [#142](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/142)
- Add `MarkCommandTest`
- Add `MarkCommandParserTest`
- Update `HarmoniaParserTest`

**Test Storage:** [#144](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/144)
- Add `JsonAdaptedTask`
- Add `AddSerializableTaskListTest`
- Update `StorageManagerTest`
- Add and update JSON files required for testing

**Test JsonUserPrefsStorage and JsonTaskListStorage:** [#149](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/149)
- Add `JsonAdaptedTask`
- Add `AddSerializableTaskListTest`
- Update `StorageManagerTest`
- Add and update JSON files required for testing

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=thikhinab&breakdown=true)

**Documentation**
- User Guide
  - Added `Mark`, `Unmark`, `Edit`, `Exit`, `Saving Data`, `Command Summary` and made changes to `Notes about Command Format` [#64](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/64)

- Developer Guide
  - Added section on `Mark/Unmark functionality` [#167](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/167)

**Team**
- PRs reviewed: [#108](https://github.com/AY2122S2-CS2103T-T09-1/tp/pull/108)
- Provided suggestions on implementations to other team members

**Community**
- Contributed to forum discussions: [#47](https://github.com/nus-cs2103-AY2122S2/forum/issues/47)
- Requested help which led to useful information in the forum: ([#56](https://github.com/nus-cs2103-AY2122S2/forum/issues/56), [#57](https://github.com/nus-cs2103-AY2122S2/forum/issues/57)
- Reported bugs for other teams in the class ([14 bugs identified in the PE-D](https://github.com/thikhinab/ped/issues))
