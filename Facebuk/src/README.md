#CS210X Project #1 Rationale

##Entity

Entity was created as a base class to extract redundant fields
and to give everything a common base class. For example, Person and
Pet can now be treated the same (and they are in a few cases).
On a larger scale, Entity could be used to expand upon the project
to add multiple different other Entity-type objects.

##Friendable

The Friendable class was created to accomodate classes that can have
friends -- specifically, Person and Pet. It removes redundacy from
the code and helps restrict what can and cannot be a friend (so
kickboxing bags *cannot* be friends with someone).
