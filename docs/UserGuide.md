---
layout: page
title: User Guide
---

Harmonia is a **desktop app for managing tasks related to your academic life, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Harmonia can get your task management done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------
## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a task: add](#adding-a-task-add)
  - [Listing all tasks: list](#listing-all-tasks-list)
  - [Deleting a task: delete](#deleting-a-task-delete)
  - [Locating a task: find](#locating-a-task-find)
  - [Marking as complete: mark](#marking-as-complete--mark)
  - [Marking as complete: unmark](#marking-as-incomplete--unmark)
  - [Editing a task: edit](#editing-a-task--edit)
  - [Exiting the program: exit](#exiting-the-program--exit)
  - [Saving the data](#saving-the-data)
- [Command Summary](#command-summary)


## Quick start

1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `Harmonia.jar` from [here](https://github.com/AY2122S2-CS2103T-T09-1/tp.git)
3. Copy the file to the folder you want to use as the home folder for your Harmonia.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.
   ![Ui](images/Ui.png)
5. Type the command in the command box and press Enter to execute it. (E.g type `list` to list down all the tasks)

--------------------------------------------------------------------------------------------------------------------
## Features

### Adding a task: `add`

Adds a task into the Harmonia.

Format:  `add d/DESCRIPTION`
Example: `add d/CS2103T tp meeting`

### Listing all tasks: `list`

Shows a list of all the existing tasks in the task list.

Format: `list`

### Deleting a task: `delete`

Deletes a specified task from Harmonia.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX` from the task list.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3

Example: `delete 3` deletes the 3rd task in the task list

### Locating a task: `find`

Finds a task which description or tag contains the given keywords.

Format:
- `find KEYWORD [MORE KEYWORDS]`
- `find t/TAG`

Example:
- `find book`
- `find book read`
- `find t/CS2103T`


### Marking as complete : `mark`

Marks specified task from the existing task list from as complete.

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as complete.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3,


Example: `list` followed by `mark 2` marks the 2nd task in the task list as complete.

### Marking as incomplete : `unmark`

Marks specified task from the existing task list from as incomplete.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as incomplete.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3,


Example: `list` followed by `unmark 3` marks the 3rd task in the task list as incomplete.

### 3.8 Editing a task : `edit`

Edits an existing task in the task list.

Format: `edit INDEX [n/NAME] [d/DESCRIPTION] [dl/DEADLINE] [p/PRIORITY] [t/TAG]...`
- `DEADLINE` should be in the format `YYYY-MM-DD`.
- `PRIORITY` should be one of `low`, `medium`, `high`.
- `TAG` should not contain any space.

Example:
- `edit 1 d/Prepare for CS2103T tutorial`
- `edit 2 n/CS2103T meeting t/CS2103T`

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
You can key `list` to see the sequence of tasks in the list to check the index of the task you wish to edit.
</div>

### 3.9 [Coming soon] Sorting tasks : `sort`

Sorts the tasks by the specified sort key and sort order and list them out.

Format: `sort by/SORT_KEY in/SORT_ORDER`
- `SORT_KEY` is the property of the task used for sorting. Currently, supported sort keys are:
  - `deadline` - sort by the due date of the task
  - `priority` - sort by the priority of the task
  - `name` - sort by the name of the task
- `SORT_ORDER` is the order in which the tasks are listed out.
  - `ascending`
  - `descending`
  
Example:
- `sort by/deadline in/ascending`: lists the tasks with the earliest deadline first
- `sort by/priority in/descending`: lists the tasks with the highest priority first

<div markdown="span" class="alert alert-primary">:bulb: **Note:**
You can use the following abbreviations to sort tasks faster.
- `asc` in place of `ascending`
- `desc` in place of `descending`
</div>

### 3.10 Viewing help: `help`

Shows the link to the user guide.

Format: `help`

### 3.11 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 3.12 Saving the data

Harmonia's data is saved in the hard disk automatically after any command changes the data. There is no need to save manually.

### 3.13 Editing the data file

Harmonia's data is saved as JSON file ([Location of Harmonia.jar]/data/harmonia.json). Advanced users are encouraged to update the data by editing JSON file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Harmonia will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------
## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME d/DESCRIPTION dl/DEADLINE p/PRIORITY [t/TAG]…` <br> e.g., `add n/CS2103T tp meeting d/read the weekly tasks before the meeting dl/2022-03-27 p/medium t/CS2103T t/meeting`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [d/DESCRIPTION] [dl/DEADLINE] [p/PRIORITY] [t/TAG]..`<br> e.g.,`edit 2 n/CS2101 meeting t/CS2101`
**Mark**| `mark INDEX [INDEX]...` <br> e.g., `mark 3` <br> e.g., `mark 1 2 3`
**Unmark**| `unmark INDEX [INDEX]...` <br> e.g., `unmark 3` <br> e.g., `unmark 1 2 3`
**List**| `list` <br> `list t/`
**Sort**| `sort by/SORT_KEY in/SORT_ORDER` <br> e.g., `sort by/deadline in/descending` <br> e.g., `sort by/priority in/asc`
**Find** | `find [n/NAME_KEYWORD]... [t/TAG_KEYWORD]... [start/START_DATE] [end/END_DATE]` <br> e.g., `find n/book n/read` <br> e.g., `find t/test t/CS2103T` <br> e.g., `find start/2022-03-14 end/2022-03-18` <br> e.g., `find t/CS2103T` <br> e.g., `find n/book t/test start/2022-03-15`
**Help** | `help`
**Exit** | `exit`
