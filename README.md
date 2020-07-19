# ItemActionsLib
Library for Item Actions 

Authors: Katsumag, Conclure

ItemActionsLib is a simple to use library for adding actions to items, as the name suggests.

#Dependency Information

You will need to shade and relocate IAL to be able to use it at runtime.

**Gradle**
```gradle

plugins {
    id 'com.github.johnrengelman.shadow' version '5.2.0'
}

repositories {
    maven { url "https://repo.katsumag.me/repository/maven-releases/" }
}

dependencies {
    implementation "me.katsumag:item-actions-lib:1.0"
}

shadowJar {
    relocate 'me.katsumag.itemactionslib', 'somewhere.in.your.project'
}```

#Example Usage

```java
final ItemActionManager manager = new ItemActionManager(this);

final Item item = manager.newItem(Material.WOODEN_AXE).addAction(ActionType.RIGHT_CLICK, event -> {
   //TODO something here 
});```

ItemActionManager#newItem accepts both a Material and an ItemStack. If you want to see all available ActionTypes, you can see them [here.](https://github.com/katsumag/ItemActionsLib/blob/master/src/main/java/me/katsumag/itemactionslib/ActionType.java)
