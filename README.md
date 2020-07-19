# ItemActionsLib
A framework library for Item Actions.

Authors: Katsumag
Contributor: Conclure

ItemActionsLib is a simple to use library for adding actions to items, as the name suggests.

[![Build Status](https://ci.katsumag.me/buildStatus/icon?job=Build+IAL)](https://ci.katsumag.me/job/Build%20IAL/)
# Setup

You will need to shade and relocate ItemActionsLib to use it at runtime.

**Gradle**
```gradle

plugins {
    id 'com.github.johnrengelman.shadow' version '6.0.0' //Or newer version
}

repositories {
    maven { url "https://repo.katsumag.me/repository/maven-releases/" }
}

dependencies {
    implementation "me.katsumag:item-actions-lib:[VERSION]"
}

shadowJar {
    relocate 'me.katsumag.itemactionslib', '[YOUR PACKAGE].itemactionslib'
}
```

An example of `[YOUR PACKAGE]` could be `me.conclure.plugin`. If you want to use latest version
of ItemActionsLib, use version `1.0`. It should be replace `[VERSION]`.

# Usage

At first you'll have to create an instance of the class ItemActionManager. This should be done through 
invoking the static method `ItemActionManager.create(JavaPlugin)`, as shown below. Notice that if you
try to invoke `ItemActionManager.create(JavaPlugin)` more than once will throw an exception.

```java
class Main extends JavaPlugin {
   @Override
   public void onEnable() {
      ItemActionManager itemActionManager = ItemActionManager.create(this);
   }
}
```

Now when you have an instance of the manager class you may call `ItemActionManager#newItem(ItemStack)`
or `ItemActionManager#newItem(Material)`. This will create a new ActionItem instance for you. To add
an Action to this ActionItem you may invoke `ActionItem#addAction(ActionType, Action)`. The method
takes an ActionType and and Action. The Action parameter represents a consumer and a lambda expression
or a method reference may be used. All current possible ActionTypes can be found 
[here](https://github.com/katsumag/ItemActionsLib/blob/master/src/main/java/me/katsumag/itemactionslib/ActionType.java).
An example is also shown below.

```java
ActionItem item = itemActionManager.newItem(Material.DIRT);
item.addAction(ActionType.CLICK, action -> {
   //TODO your code
});
```

If you wish to remove all actions for a specific ActionType, you may invoke `ActionItem#removeActions(ActionType)`.

```java
item.removeActions(ActionType.CLICK);
```

You can clear all actions from a specific item by invoking `ActionItem#clearActions()`.

```java
item.clearActions();
```

FYI: All ActionItems and their Actions are being tracked by a UUID which is stored in every ActionItem instance.

# Contribute

If you want to contribute you may open a pull request 
[here](https://github.com/katsumag/ItemActionsLib/compare),
as we accept most of them.

# Support

If you have any questions or are in need of support, you may join HelpChat discord server
[here](https://helpch.at/discord).
