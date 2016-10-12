package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum NPC implements Interactable {

  MOM ("Mom", Location.VILLAGE, Collections.singletonList("mother")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.gotArmor()) {
        GS.bag().add(Item.FLOWERS);
        return "Mom: “I doubt it will be of any use to you but I picked these flowers for you.”";
      }
      else if (GS.talkedToMom()) {
        return "Mom: “Hope youʼre having a great adventure!”";
      }
      else {
        GS.bag().add(Item.NECKLACE);
        GS.bag().add(Item.SOUP);
        GS.bag().add(Item.SOUP);
        GS.completedTalkedToMom();
        return "Mom: “I will miss you you on your long journey. Take these items with you!”";
      }
    }
  },
  CREEK_MAN ("Man", Location.CREEK, Collections.singletonList("young man")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (!GS.bag().contains(Item.FISH)) {
        return "Man: “Zzzzzzz!”";
      }
      else if (!GS.fedBro()) {
        GS.bag().remove(Item.FISH);
        GS.completedFedBro();
        return "Man: “Is that fish I smell! From my brother? I should apologize… But thanks. " +
            "Itʼs not much, but if you find my boots, youʼre welcome to have them if youʼd like!”";
      }
      else {
        int i = GS.bag().getCount(Item.FISH);
        while (i != 0) {
          GS.bag().remove(Item.FISH);
          GS.bag().add(Item.WATER);
          i--;
        }
        return "Man: “More fish! Thank you! I donʼt really have much, but here have some water.”";
      }
    }
  },
  FISHERMAN ("Fisherman", Location.RIVER_HUT, Arrays.asList("man", "fisher")) {
    @Override
    String talkToMessage(final GameState GS) {
      return "Fisherman: “My brother and I got into a fight. " +
          "Heʼs run off to who knows where. Heʼs so stubborn sometimes. " +
          "Take as much fish as youʼd like, but please take some to him if you can.”";
    }
  },
  BOY ("Boy", Location.SOUTHERN_FOREST, Arrays.asList("small boy", "young boy", "little boy")) {
    @Override
    String talkToMessage(final GameState GS) {
      GS.completedFoundBoy();
      return "Boy: “I love coming here to play with my animal friends! " +
          "I wonder if dad gets worried though…”";
    }
  },
  GIRL ("Girl", Location.WESTERN_ROAD, Arrays.asList("small girl", "young girl", "little girl")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.bag().contains(Item.CAT)) {
        GS.bag().remove(Item.CAT);
        GS.completedReturnedCat();
        return "Small girl: “Thank you so much for finding my kitty! Youʼre a nice man!”";
      }
      else if (GS.returnedCat()) {
        return "Small girl: “I gived kitty a leash so he can never leave me again!”";
      }
      else {
        return "Small girl: “My kitty is losted! I hope I find him soon!”";
      }
    }
  },
  OLD_MAN ("Old Man", Location.WESTERN_ROAD, Collections.singletonList("man")) {
    @Override
    String talkToMessage(final GameState GS) {
      // Latin for “If only I were younger and stronger! For back then I could kill
      // the evil dragon, but alas!, to grow old is to grow useless.”
      return "Old Man: “Utinam jūnior et fortior essem! Nam tunc dracōnem malum " +
          "interficere poteram, sed, heu!, senēscere est inūtilis fierī.”";
    }
  },
  ROAD_WOMAN ("Woman", Location.WESTERN_ROAD, Collections.singletonList("young woman")) {
    @Override
    String talkToMessage(final GameState GS) {
      return "Woman: “My grandpa is not from around here. He immigrated here when his " +
          "home country was at war. I wish I could understand what he was saying though.”";
    }
  },
  BLACKSMITH ("Blacksmith", Location.QUEENS_CITY, Collections.singletonList("smith")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.helpedDave() && !GS.gotSword()) {
        GS.bag().add(Item.SWORD);
        GS.completedGotSword();
        return "Blacksmith: “So Dave said heʼd give you his sword, eh? " +
            "Well heʼs already paid so I guess I donʼt care who it goes to.”";
      }
      else if (!GS.helpedDave()) {
        return "Blacksmith: “Iʼm holding a sword for Dave. Hopefully he comes to pick it up soon.”";
      }
      else {
        return "Blacksmith: “Be careful with that sword, kiddo!”";
      }
    }
  },
  GROCER ("Grocer", Location.QUEENS_CITY, Collections.emptyList()) {
    @Override
    String talkToMessage(final GameState GS) {
      return "Grocer: “Oh! Youʼre the child of the miller from the village! " +
          "I wish I had food to sell you but Iʼve gone bankrupt…”";
    }
  },
  ARTISAN ("Artisan", Location.QUEENS_CITY, Collections.emptyList()) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.bag().getCount(Item.BANANA) >= 10) {
        for (int i = 0; i < 10; i++) {
          GS.bag().remove(Item.BANANA);
        }
        GS.bag().add(Item.NECKLACE);
        return "Artisan: “Bananas! For me! Thank you so much! " +
            "Hereʼs something I made! I hope itʼs useful to you.”";
      }
      if (GS.bag().getCount(Item.FISH) >= 10) {
        for (int i = 0; i < 10; i++) {
          GS.bag().remove(Item.FISH);
        }
        GS.bag().add(Item.NECKLACE);
        return "Artisan: “Fish! For me! Thank you so much! " +
            "Hereʼs something I made! I hope itʼs useful to you.”";
      }
      else {
        return "Artisan: “The grocerʼs foods were the only ones in town I could afford. " +
            "Ever since he went bankrupt Iʼve been unable to feed my family of 10.”";
      }
    }
  },
  GUARD ("Guard", Location.QUEENS_CITY, Collections.emptyList()) {
    @Override
    String talkToMessage(final GameState GS) {
      return "Guard: “No oneʼs allowed in. The queen is busy with meetings discussing what " +
          "should be done about the dragon north of here. Her job is so stressful.”";
    }
  },
  FIELD_WOMAN ("Woman", Location.OPEN_FIELD, Arrays.asList("young woman", "painter", "artist")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.bag().contains(Item.NECKLACE)) {
        GS.bag().remove(Item.NECKLACE);
        return "Woman: “Is that necklace for me! Youʼre such a thoughtful young man!”";
      }
      else if (GS.bag().contains(Item.RING)) {
        return "Woman: “Umm… I donʼt know you well enough to marry you Iʼm sorry. " +
            "If you find any other jewelry though, come talk to me! ;)”";
      }
      else {
        return "Woman: “I like to find the beauty in things. " +
            "Paintings arenʼt enough though… I could use some new jewelry!”";
      }
    }
  },
  DARREN ("Darren", Location.SMALL_FARM, Collections.singletonList("farmer")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.foundBoy()) {
        GS.bag().add(Item.EGGS);
        return "Darren: “My sonʼs in the forest playing you say? " +
            "Well Iʼm just relieved to know where he is. " +
            "Thank you for keeping an eye on him. Here have some eggs.”";
      }
      else {
        return "Darren: “My boyʼs always disappearing on me. I hope heʼs okay.”";
      }
    }
  },
  BOBBI ("Bobbi", Location.SOUTHERN_ROAD, Collections.singletonList("woman")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.helpedDave()) {
        return "Bobbi: “I canʼt believe Dave is letting you have our sword…”";
      }
      else {
        GS.bag().add(Item.ROPE);
        return "Bobbi: “Dave and I are buying a sword from the blacksmith in Queenʼs City. " +
            "Weʼre planning on killing the dragon that has been stealing our livestock. " +
            "If youʼd like to help, gather some supplies. Hereʼs some rope to start out.”";
      }
    }
  },
  DAVE ("Dave", Location.SOUTHERN_ROAD, Collections.singletonList("man")) {
    @Override
    String talkToMessage(final GameState GS) {
      if (GS.helpedDave()) {
        return "Dave: “Thank you so much for all of your help!”";
      }
      else if (!GS.returnedRing()) {
          if (!GS.bag().contains(Item.RING)) {
            return "Dave: “I lost my wedding ring and I have no gift for her for our " +
                "anniversary! My wife is gonna be so mad…”";
          }
          else {
            GS.bag().remove(Item.RING);
            GS.completedReturnedRing();
            return "Dave: “You found my wedding ring! Thank you so much. " +
                "Now if only I could find some sort of gift for my wife for our anniversary…”";
          }
      }
      else {
        if (GS.bag().contains(Item.FLOWERS)) {
          GS.bag().remove(Item.FLOWERS);
          GS.completedHelpedDave();
          return "Dave: “Youʼre going to let me have these flowers for my anniversary gift! " +
              "I am truly indebted to you! I know! Why donʼt I let you have my sword! " +
              "Just talk to the blacksmith in Queenʼs City. Heʼs holding it for me.”";
        }
        else {
          return "Dave: “Thanks for your help with the ring! I need to go find an " +
              "anniversary present to give my wife now.”";
        }
      }
    }
  },
  GHOST ("Ghost", Location.ABANDONED_TOWN, Collections.singletonList("skeleton")) {
    @Override
    String talkToMessage(final GameState GS) {
      return "A ghost appeared from the skeletal remains. " +
          "Ghost: “Leeeeave! Leeeeave!” You should go! Stat!";
    }
  };

  private final String name;
  private final Location location;
  private final List<String> aliases;

  NPC(final String name, final Location location, final List<String> aliases) {
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

  abstract String talkToMessage(final GameState GS);

  private boolean hasName(final String name) {
    return name.equalsIgnoreCase(this.name) || aliases.contains(name);
  }

  static NPC from(final String name, final Location location) {
    for (final NPC npc : NPC.values()) {
      if (npc.hasName(name) && npc.location() == location) {
        return npc;
      }
    }
    throw new IllegalArgumentException("This is not a valid NPC.");
  }

  static List<Interactable> peopleAt(final Location location) {
    final List<Interactable> people = new ArrayList<>();
    for (final NPC npc : NPC.values()) {
      if (npc.location().equals(location)) {
        people.add(npc);
      }
    }
    return people;
  }

}
