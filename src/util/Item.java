package util;

import java.util.HashMap;
import java.util.Map;

enum Item implements Interactable {

  KNIFE ("knife") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  ROPE ("rope") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  BANANA ("banana") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.NORTHERN_FOREST) {
        GS.bag().add(Item.BANANA);
        return "You grabbed a banana! Ah, the good old days.";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  FISH ("fish") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.RIVER_HUT) {
        GS.bag().add(Item.FISH);
        return "You grabbed a fish! Better bring it to the fisherman's brother for him.";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  SOUP ("soup") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  EGGS ("eggs") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  FLOWERS ("flowers") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  NECKLACE ("magic necklace") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  CAT ("cat") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.CENTRAL_FOREST && !GS.savedCat()) {
        GS.bag().add(Item.CAT);
        GS.completedSavedCat();
        return "“I should find out whose cat this is.”";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  WATER ("water") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().getX() == 5) {
        return "The river looks far too polluted to get any potable water from…";
      }
      else if (GS.location() == Location.CREEK) {
        return "This creek is too dried up…";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  RING ("ring") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.BEACH_NORTH && !GS.retrievedRing()) {
        GS.bag().add(Item.RING);
        GS.completedRetrievedRing();
        return "You grabbed the wedding ring! “I wonder whose it is… Oh well!”";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  BOOTS ("boots") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.MONUMENT && !GS.gotBoots()) {
        if (GS.fedBro()) {
          GS.bag().add(Item.BOOTS);
          GS.completedGotBoots();
          return "These boots can withstand any weather!";
        }
        else {
          return "You can't just take someone's boots without permission!";
        }
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  ARMOR ("armor") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.CAVE) {
        if (GS.gotArmor()) {
          return "You already got the armor!";
        }
        else {
          GS.bag().add(Item.ARMOR);
          GS.completedGotArmor();
          return "You found some armor!";
        }
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  SWORD ("sword") {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  SAND ("sand") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().isNamed("Desert") || GS.location().isNamed("Royal Beach")) {
        GS.bag().add(Item.SAND);
        return "This is probably useless. But I'll take some sand.";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  SCROLL ("scroll") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.EASTERN_ROAD) {
        return "It's best to just leave that vile piece of paper here. " +
            "You don't want to get caught with that on you.";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  },
  SCORPION ("scorpion") {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        return "That's dangerous!";
      }
      throw new IllegalArgumentException("This item isn't here.");
    }
  };

  private final String name;

  Item(final String name) {
    this.name = name;
  }

  @Override
  public String canonicalName() {
    return name;
  }

  abstract String obtainMessage(final GameState GS);

  static Item from(final String string) {
    final String name = aliases().containsKey(string) ? aliases().get(string) : string;
    for (final Item item : Item.values()) {
      if (item.canonicalName().equals(name)) {
        return item;
      }
    }
    throw new IllegalArgumentException("This is not a valid item.");
  }

  boolean isEdible() {
    return (this == BANANA || this == FISH || this == SOUP || this == EGGS || this == CAT);
  }

  boolean isPotable() {
    return (this == WATER || this == SOUP);
  }

  boolean canCut() {
    return (this == KNIFE || this == SWORD);
  }

  private static Map<String, String> aliases() {
    final Map<String, String> aliases = new HashMap<>();
    aliases.put("necklace", "magic necklace");
    aliases.put("magical necklace", "magic necklace");
    aliases.put("wedding ring", "ring");
    aliases.put("kitty", "cat");
    aliases.put("kitten", "cat");
    return aliases;
  }
}
