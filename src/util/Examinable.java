package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum Examinable implements Interactable {

  DESERT_ROCK ("rock", Location.DESERT1, Collections.singletonList("boulder")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return nameUsed.equalsIgnoreCase("boulder") ? "Itʼs not just a boulder! Itʼs a rock!" :
      "The pioneers used to ride these babies for miles!";
    }
  },
  SIGN ("sign", Location.DESERT6, Collections.emptyList()) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "It reads: “Beware! The town to the east…” The rest of it is illegible.";
    }
  },
  BANANA_TREE ("tree", Location.NORTHERN_FOREST, Collections.emptyList()) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "The trees here are quite abundant in bananas if you want to pick them.";
    }
  },
  MONUMENT ("monument", Location.MONUMENT, Collections.singletonList("large monument")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return GS.gotBoots() ? "This thing must be like 20 kilugs tall!" :
          "Uh oh! Looks like someone left their boots here.";
    }
  },
  MOVING_TREE ("tree", Location.CENTRAL_FOREST, Collections.singletonList("tree")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return GS.savedCat() ? "“Phew. Itʼs just the wind. I thought it was an Ent for a second.”" :
          "Oh! Itʼs a cat shaking up the branches! " +
              "You should take him with you and help him find his way home.";
    }
  },
  BEACH ("beach", Location.BEACH_NORTH, Collections.singletonList("sand")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return GS.retrievedRing() ? "“Itʼs just the reflection of the sun in the sand. " +
          "And here I thought itʼd be something valuable.”" :
          "Itʼs a wedding ring! You should take it and figure out what itʼs worth!";
    }
  },
  CACTUS ("cactus", Location.DESERT5, Collections.emptyList()) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "Try using your knife on it to get some water if you start running low.";
    }
  },
  SCROLL ("scroll", Location.EASTERN_ROAD, Collections.emptyList()) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "“This is written in Ikunaqian! Umm… This is dangerous stuff… " +
          "Iʼll just leave it where I found it.”";
    }
  },
  SHACK ("shack", Location.ABANDONED_TOWN,
      Arrays.asList("old shack", "wooden shack", "old wooden shack")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "A bunch of skeletons… Maybe you should get out of here.";
    }
  },
  CASTLE ("castle", Location.QUEENS_CITY, Collections.singletonList("palace")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "The queenʼs castle is huge! The guards are giving suspecting glances though… " +
          "Donʼt stare too long.";
    }
  },
  CAVE_BOULDER ("boulder", Location.CAVE, Collections.singletonList("rock")) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return !GS.gotArmor() ? "Itʼs a huge boulder! Thereʼs some armor hidden underneath!" :
          "Itʼs a huge boulder! You already grabbed the armor that used to be here.";
    }
  },
  CREEK ("creek", Location.CREEK, Collections.emptyList()) {
    @Override
    String examineMessage(final GameState GS, final String nameUsed) {
      return "This creek is all dried up; it doesnʼt look like it has any fish…";
    }
  };

  private final String name;
  private final Location location;
  private final List<String> aliases;

  Examinable(final String name, final Location location, final List<String> aliases) {
    this.name = name;
    this.location = location;
    this.aliases = aliases;
  }

  @Override
  public String canonicalName() {
    return name;
  }

  Location location() {
    return location;
  }

  abstract String examineMessage(final GameState GS, final String nameUsed);

  private boolean hasName(final String name) {
    return name.equalsIgnoreCase(this.name) || aliases.contains(name);
  }

  static Examinable from(final String name, final Location location) {
    for (final Examinable examinable : Examinable.values()) {
      if (examinable.hasName(name) && examinable.location == location) {
        return examinable;
      }
    }
    throw new IllegalArgumentException("This is not a valid examinable.");
  }

  static List<Interactable> examinablesAt(final Location location) {
    final List<Interactable> examinables = new ArrayList<>();
    for (final Examinable examinable : Examinable.values()) {
      if (examinable.location() == location) {
        examinables.add(examinable);
      }
    }
    return examinables;
  }
}
