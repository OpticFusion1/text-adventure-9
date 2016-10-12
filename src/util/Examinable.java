package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum Examinable implements Interactable {

  DESERT_ROCK ("rock", Location.DESERT1) {
    @Override
    String examineMessage(GameState GS) {
      return "The pioneers used to ride these babies for miles!";
    }
  },
  DESERT_BOULDER ("boulder", Location.DESERT1) {
    @Override
    String examineMessage(GameState GS) {
      return "It's not just a boulder! It's a rock!";
    }
  },
  SIGN ("sign", Location.DESERT6) {
    @Override
    String examineMessage(GameState GS) {
      return "It reads: “Beware! The town to the east…” The rest of it is illegible.";
    }
  },
  BANANA_TREE ("tree", Location.NORTHERN_FOREST) {
    @Override
    String examineMessage(GameState GS) {
      return "The trees here are quite abundant in bananas if you want to pick them.";
    }
  },
  MONUMENT ("monument", Location.MONUMENT) {
    @Override
    String examineMessage(GameState GS) {
      return GS.gotBoots() ? "This thing must be like 20 kilugs tall!" :
          "Uh oh! Looks like someone left their boots here.";
    }
  },
  MOVING_TREE ("tree", Location.CENTRAL_FOREST) {
    @Override
    String examineMessage(GameState GS) {
      return GS.savedCat() ? "“Phew. It's just the wind. I thought it was an Ent for a second.”" :
          "Oh! It's just a cat shaking up the branches! You should take him with you and help him find his way home.";
    }
  },
  BEACH ("beach", Location.BEACH_NORTH) {
    @Override
    String examineMessage(GameState GS) {
      return GS.retrievedRing() ? "“It's just the reflection of the sun in the sand. " +
          "And here I thought it'd be something valuable.”" :
          "It's a wedding ring! You should take it and figure out what it's worth!";
    }
  },
  CACTUS ("cactus", Location.DESERT5) {
    @Override
    String examineMessage(GameState GS) {
      return "Try using your knife on it to get some water if you start running low.";
    }
  },
  SCROLL ("scroll", Location.EASTERN_ROAD) {
    @Override
    String examineMessage(GameState GS) {
      return "“This is written in Ikunaqian! Umm… This is dangerous stuff… I'll just leave it where I found it.”";
    }
  },
  SHACK ("shack", Location.ABANDONED_TOWN) {
    @Override
    String examineMessage(GameState GS) {
      return "A bunch of skeletons… Maybe you should get out of here.";
    }
  },
  CASTLE ("castle", Location.QUEENS_CITY) {
    @Override
    String examineMessage(GameState GS) {
      return "The queen's castle is huge! The guards are giving suspecting glances though… Don't stare too long.";
    }
  },
  CAVE_ROCK ("rock", Location.CAVE) {
    @Override
    String examineMessage(GameState GS) {
      return !GS.gotArmor() ? "It's a huge boulder! There's some armor hidden underneath!" :
          "It's a huge boulder! You already grabbed the armor that used to be here.";
    }
  },
  CAVE_BOULDER ("boulder", Location.CAVE) {
    @Override
    String examineMessage(GameState GS) {
      return !GS.gotArmor() ? "It's a huge boulder! There's some armor hidden underneath!" :
          "It's a huge boulder! You already grabbed the armor that used to be here.";
    }
  },
  CREEK ("creek", Location.CREEK) {
    @Override
    String examineMessage(GameState GS) {
      return "This creek is all dried up; it doesn't look like it has any fish…";
    }
  };

  private final String name;
  private final Location location;

  Examinable(final String name, final Location location) {
    this.name = name;
    this.location = location;
  }

  @Override
  public String canonicalName() {
    return name;
  }

  Location location() {
    return location;
  }

  abstract String examineMessage(final GameState GS);

  static Examinable from(final String string, final Location location) {
    final String name = aliases().containsKey(string) ? aliases().get(string) : string;
    for (final Examinable examinable : Examinable.values()) {
      if (examinable.location().equals(location) && examinable.canonicalName().equalsIgnoreCase(name)) {
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

  private static Map<String, String> aliases() {
    final Map<String, String> aliases = new HashMap<>();
    aliases.put("trees", "tree");
    aliases.put("large monument", "monument");
    aliases.put("sand", "beach");
    aliases.put("old shack", "shack");
    aliases.put("wooden shack", "shack");
    aliases.put("old wooden shack", "shack");
    aliases.put("palace", "castle");
    return aliases;
  }
}
