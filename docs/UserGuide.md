--- layout: page    
title: User Guide
---   
AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents    
  {:toc}

--------------------------------------------------------------------------------------------------------------------   
## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>    
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>    
   Some example commands you can try:

    * **`list`** : Lists all contacts.

    * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.
    * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

    * **`clear`** : Deletes all contacts.

    * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------   
## Features

<div markdown="block" class="alert alert-info">    

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>    
  e.g. in `add d/DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `add d/CS2103T meeting`.

* Items in square brackets are optional.<br>    
  e.g `d/DESCRIPTION [t/TAG]` can be used as `d/Meeting t/CS2103T` or as `d/Meeting`.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>    
  e.g. if you specify `t/CS2103T t/Assignment`, only `t/CS2103T` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `list` and `exit`) will be ignored. <br>    
  e.g. if the command specifies `list 123`, it will be interpreted as `list`.

</div>    

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

### Editing a task : `edit`

Edits the specified task in the task list.

Format: `edit INDEX [d/DESCRIPTION] [t/TAG]`
- Edits the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the task will be removed i.e adding of tags is not cumulative.
- You can remove all the task’s tags by typing `t/` without specifying any tags after it.

Example: `edit 2 d/Prepare for CS2103T Tutorial` Edits the description of the 2nd task to be `Prepare for CS2103T Tutorial`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Harmonia's data is saved in the hard disk automatically after any command changes the data. There is no need to save manually.
    
--------------------------------------------------------------------------------------------------------------------   
## Command summary

Action | Format, Examples
--------|------------------ 
**Add** | `add d/DESCRIPTION` <br> e.g., `add d/read book`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [d/DESCRIPTION] [t/TAG]`<br> e.g.,`edit 2 n/CS2101 meeting t/CS2101`
**Mark**| `mark INDEX` <br> e.g., `mark 3`
**Unmark**| `unmark INDEX` <br> e.g., `unmark 3`
**List**| `list`
**Find** | `find KEYWORD [MORE_KEYWORDS]` <br> `find t/[TAG]` <br> e.g., `find book` <br> e.g., `find book read` <br> e.g., `find t/CS2103T`
**Exit** | `exit`
