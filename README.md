crowd-pulse-data-java
=====================

Java data access layer for Crowd Pulse.

---------------------

The available plugins are:

* `message-fetch` to load messages stored on a database
* `message-rx-fetch` to load messages stored on a database in a reactive way (experimental)
* `message-filter` to filter messages in a pipeline
* `message-persist` to save messages in a database
* `profile-fetch` to load profiles stored on a database
* `profile-rx-fetch` to load profiles stored on a database in a reactive way (experimental)
* `profile-persist` to save profiles in a database
* `streamer` to make the elements flow untouched in the stream
* `personaldata-fetch` to load personal data stored on a database 
* `personaldata-persist` to save personal data in a database 

The included packages are:

* `entity`: POJO classes of Crowd Pulse entities
* `repository`: repositories for object persistence on MongoDB
* `plugin`: shared `IPlugin` implementations to fetch, filter and persist `Message`s and `Profile`s
