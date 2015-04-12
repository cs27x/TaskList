# TaskList
[![Build Status](https://travis-ci.org/cs27x/TaskList.svg?branch=master)](https://travis-ci.org/cs27x/TaskList)
###Mission Statement:
Task List provides a comprehensive task reminder system by combining concepts from traditional to-do lists and reminder systems with an algorithmical and location-based smart planning system, taking the errand out of maintaining a to-do list.

###Project Summary:
Task List aims to provide an extremely useful and easy to use to-do list which will help sort the tasks and provide an easily maintainable group of tasks. Ease of use will be achieved through a UI that tries to minimize the number of actions required from a user to update, edit, delay, or complete a given task. Through push notifications and a page in the app dedicated to task modifications, the app will minimize the number of actions required to both reach the desired task and edit it. Tasks will be sorted in a number of views such as simple list, calendar, or category form. Different categories will be built around a number criteria such as the nature of the task in terms of its goal (health/fitness, chores/errands, acamedic, work related, etc.) and in terms of its timing (fixed deadline, delayable, daily task, one-time task, etc.). Users will also be able to modify/create certain fields and categories.

Tha other half of the app is centered around a smart sorting system using the Simplex method. By using the categories of the task combined with its importance, the app will sort the tasks and send suggestions about which task merits attention. The app will keep track of productivity and efficieny by prompting user for estimated time required and actual time spent. Furthermore, the app will use things like time of day to further enhance the suggestion system. The app has the potential to combine location criteria such as distance to a task to add to the suggestion engine as well.

Task List has other potential improvements such as multiple device modification and syncing, a web interface, and a social component. These may represent future enhancements or later build cycle goals.

###Build Cycle Pieces:
Data Models: underlying classes and structure for the tasks, the metrics, groups of tasks, and categories included in the sorting and analysis.

Planning algorithm: algorithm to sort, priortize, and suggest tasks based on the metrics and data from the tasks.

UI - Android: Calendar views (day, week, month), List Views and groupings, Task view, edit task view, task updater page, and push notifications, and home page.

Metric analysis: learning piece of the app which takes historical data about task completion and helps the planning algorithm.

Potential:
Server/Backed: Server side of the tasks to provide common location for tasks and provide syncing across devices and web platform. Also usable for email notifications.

UI - Web: UI for a web page representation of the app.

Social: Ability to push and share tasks with other users. Also, ability to share metrics and completion data amongst users

###Prototype:
[Hand-drawn screens](https://drive.google.com/a/vanderbilt.edu/file/d/0B7nuCalowrzTYndSXzhxRmhGR1k/view?usp=sharing)

###Instructions:
(To be added)
