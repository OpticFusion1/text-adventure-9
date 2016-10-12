package util;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Location.Direction.NORTH;
import static util.Location.Direction.SOUTH;
import static util.Location.Direction.WEST;
import static util.Location.Direction.EAST;

public final class Processor {

  private final GameState GS;

  public Processor() {
    GS = new GameState(Location.VILLAGE, Inventory.of(Item.KNIFE));
  }

  public String processCommand(final String command) {

    if (GS.location().isNamed("Eerie Mountain")) {
      Matcher matcher = KILL.matcher(command);
      return matcher.matches() ? "You have slain the dragon and beat the game! Congratulations!" :
          "That's not going to kill the dragon! This is not the time to be doing anything else!";
    }
    Matcher matcher = MOVE.matcher(command);
    if (matcher.matches()) {
      return travel(matcher.group("direction"));
    }
    matcher = QUIT.matcher(command);
    if (matcher.matches()) {
      return quit(command);
    }
    matcher = TALK.matcher(command);
    if (matcher.matches()) {
      return talkTo(matcher.group("person"));
    }
    matcher = INFO.matcher(command);
    if (matcher.matches()) {
      return info(command);
    }
    matcher = BAG.matcher(command);
    if (matcher.matches()) {
      return "Contents of bag: " + GS.bag().toString();
    }
    matcher = TOSS.matcher(command);
    if (matcher.matches()) {
      return toss(matcher.group("command"), matcher.group("item"));
    }
    matcher = CLIMB.matcher(command);
    if (matcher.matches()) {
      return climb(command);
    }
    matcher = USE.matcher(command);
    if (matcher.matches()) {
      return use(matcher.group("command"), matcher.group("item"));
    }
    matcher = GET_FISH.matcher(command);
    if (matcher.matches()) {
      return getFish(matcher.group("number"));
    }
    matcher = GET.matcher(command);
    if (matcher.matches()) {
      return get(matcher.group("command"), matcher.group("item"));
    }
    matcher = EXAMINE.matcher(command);
    if (matcher.matches()) {
      return examine(matcher.group("item"));
    }
    matcher = SPECIAL.matcher(command);
    if (matcher.matches()) {
      return special(command);
    }
    return "“Sorry, I don't know how to do that.”";
  }

  private String talkTo(final String personName) {
    try {
      final NPC person = NPC.from(personName, GS.location());
      return person.talkToMessage(GS);
    }
    catch (final IllegalArgumentException e) {
      final List<Interactable> people = NPC.peopleAt(GS.location());
      if (people.size() == 0){
        return "There's no one here to talk to.";
      }
      else if (personName != null) {
        return "That person's not here! You can talk to " +
            Interactable.interactablesToString(people) + ".";
      }
      else {
        return "Who do you want to talk to? You can talk to " +
            Interactable.interactablesToString(people) + ".";
      }
    }
  }

  private String info(final String command) {
    if (command.equals("look") || command.equals("info")) {
      return GS.location().info();
    }
    else if (command.equals("check surroundings") || command.equals("surroundings")) {

      if (GS.location() == Location.CAVE) {
        return "There's nothing but cave walls around here and the entrance to the west.";
      }
      final String northLoc = NORTH.of(GS.location()) != Location.INVALID_LOCATION ?
          NORTH.of(GS.location()).canonicalName() : "Eerie Mountain";
      final String southLoc = SOUTH.of(GS.location()) != Location.INVALID_LOCATION ?
          SOUTH.of(GS.location()).canonicalName() : "the enemy country of Ikunaq";
      final String westLoc = WEST.of(GS.location()) != Location.INVALID_LOCATION ?
          WEST.of(GS.location()).canonicalName() : "creepy dense forest";
      final String eastLoc = EAST.of(GS.location()) != Location.INVALID_LOCATION ?
          EAST.of(GS.location()).canonicalName() : "a huge, uncrossable river";

      return "To the north lies " + northLoc + ". To the south lies " + southLoc +
          ". To the west lies " + westLoc + ". To the east lies " + eastLoc + ".";
    }
    else {
      return "You are currently at " + GS.location().canonicalName() + ".";
    }
  }

  private String toss(final String command, final String itemName) {
    if (itemName == null) {
      return "What would you like to " + command + "? You currently have " + GS.bag().toString() + ".";
    }
    try{
      final Item item = Item.from(itemName);
      GS.bag().remove(item);
      return "You tossed one " + item.canonicalName() + ".";
    }
    catch (final IllegalArgumentException e) {
      return "You don't have that!";
    }
  }

  private String climb(final String command) {
    if (GS.location().isNamed("Cliff Side")) {
      if (command.equals("climb up")) {
        return "Uh… You mean climb down, silly.";
      }
      else if (GS.bag().contains(Item.ROPE)) {
        if (GS.location().getX() == 4) {
          GS.updateLocation(EAST.of(GS.location()));
        }
        else if (GS.location().getX() == 5) {
          GS.updateLocation(SOUTH.of(GS.location()));
        }
        return "You climb down the side of the cliff. It's kind of scary, but fun!";
      }
      else {
        return "You can't just climb down the cliff with no rope! That's dangerous!";
      }
    }
    else if (GS.location() == Location.BEACH_NORTH || GS.location() == Location.RIVER_HUT) {
      if (command.equals("climb down")) {
        return "Uh… You mean climb up, silly.";
      }
      else {
        GS.updateLocation(WEST.of(GS.location()));
        return "You scaled up the cliff side!";
      }
    }
    else {
      return "There's nothing to climb here!";
    }
  }

  private String travel(final String directionString) {
    if (directionString == null) {
      return "What direction do you want to go? North, south, east, or west?";
    }
    final Location.Direction direction  = Location.Direction.from(directionString);
    if (direction == Location.Direction.INVALID_DIRECTION) {
      return "That's not a direction!";
    }
    final Location targetLoc = direction.of(GS.location());
    return targetLoc == Location.INVALID_LOCATION ? cannotGo(direction) : targetLoc.travelMessage(GS);
  }

  private String quit(final String command) {
    if (command.equals("die") || command.equals("kill self")) {
      return "You died!";
    }
    else if (command.equals("jump") || command.equals("jump off")) {
      return GS.location().isNamed("Cliff Side") ?
          "You jumped off the cliff and died." : "There's nothing for you to jump off of.";
    }
    else {
      return "Bye!";
    }
  }

  private String get(final String command, final String itemName) {
    final List<String> fruit = Arrays.asList(
        "banana", "bananas", "apple", "apples", "orange", "oranges", "peach", "peaches");
    if (itemName == null) {
      return "What item would you like to " + command + "?";
    }
    else if ((command.equals("pick") || command.equals("pluck")) && !fruit.contains(itemName)) {
      return "Umm… That's not a fruit. You can't pick that.";
    }
    else if (GS.bag().isFull()) {
      return "There's no more room in your bag for anything else! It already has 50 items. " +
          "Maybe you should toss some things to make room.";
    }
    else if (GS.location() == Location.NORTHERN_FOREST && itemName.equals("bananas")) {
      GS.bag().add(Item.BANANA); GS.bag().add(Item.BANANA); GS.bag().add(Item.BANANA);
      return "You grabbed three bananas! Leave some for the monkeys to eat.";
    }
    else {
      try {
        final Item item = Item.from(itemName);
        return item.obtainMessage(GS);
      }
      catch (final IllegalArgumentException e) {
        return "That item is not here!";
      }
    }
  }

  private String getFish(final String number) {
    if (GS.location() == Location.RIVER_HUT) {
      final int numFish = Integer.parseInt(number);
      for (int i = 0; i < numFish; i++) {
        if (!GS.bag().isFull()) {
          GS.bag().add(Item.FISH);
        }
        else {
          return i == 0 ? "There's no more room in your bag for anything else! " +
              "It already has 50 items. Maybe you should toss some things to make room." :
              "Uh oh! Your bag filled up. You were only able to grab " + i + " fish.";
        }
      }
      return "You grabbed " + numFish + " fish! Better bring some to the fisherman's brother for him.";
    }
    else {
      return "“Sorry, I don't know how to do that.”";
    }
  }

  private String examine(final String itemName) {
    try {
      final Examinable examinable = Examinable.from(itemName, GS.location());
      return examinable.examineMessage(GS);
    }
    catch (final IllegalArgumentException e) {
      final List<Interactable> examinables = Examinable.examinablesAt(GS.location());
      if (examinables.size() == 0){
        return "There's nothing here for you to examine.";
      }
      else if (itemName != null) {
        return "That's not here! You can examine " +
            Interactable.interactablesToString(examinables) + ".";
      }
      else {
        return "What do you want to examine? You can examine " +
            Interactable.interactablesToString(examinables) + ".";
      }
    }
  }

  private String special(final String command) {
    if ((command.equals("cut") ||command.equals("cut cactus")) &&
        (GS.location().getX() == 3 && GS.location().getY() == 5)) {
      GS.bag().add(Item.WATER);
      return "You got water from the cactus!";
    }
    else if (command.equals("scream") || command.equals("shout")) {
      return "“Aaaarrrrgggghhhh!!!”";
    }
    else if (command.equals("throw eggs") && GS.bag().contains(Item.EGGS)
        && GS.location() == Location.QUEENS_CITY) {
      GS.bag().remove(Item.EGGS);
      return "Guard: “Hey! Don't egg the queen's castle!!!”";
    }
    else if (command.equals("climb tree") && GS.location().isNamed("Forest")) {
      return "And just what would that accomplish? Stop being such a kid.";
    }
    else if ((command.equals("climb monument") || command.equals("climb large monument"))
        && GS.location() == Location.MONUMENT) {
      return "Blasphemy! This is a sacred monument!";
    }
    else {
      return "“Sorry, I don't know how to do that.”";
    }
  }

  private String cannotGo(final Location.Direction direction) {
    if (GS.location() == Location.CAVE) {
      return "There's nothing but cave walls beyond here. I should exit to the west, where I came from.";
    }
    else if (direction == WEST) {
      return GS.location().isNamed("Forest") ?
          "You shouldn't go any deeper into the forest. It's starting to get dense and creepy." :
          "The forest west of here looks too dense and creepy to enter. It's best to avoid it.";
    }
    else if (direction == EAST) {
      return "The river to the east is too wide to cross. Maybe it gets narrower elsewhere.";
    }
    else if (direction == SOUTH) {
      return "South of the border lies the enemy country Ikunaq. It's too risky to go into their territory.";
    }
    else {
      return Location.INVALID_LOCATION.info();
    }
  }

  private String use(final String command, final String itemName) {
    if (itemName == null) {
      return "What item would you like to " + command + "? You currently have " + GS.bag().toString() + ".";
    }
    try {
      if (command.equals("eat")) {
        return eat(Item.from(itemName));
      }
      else if (command.equals("drink")) {
        return drink(Item.from(itemName));
      }
      else {
        return use(Item.from(itemName));
      }
    }
    catch (final IllegalArgumentException e) {
      return "You don't have that!";
    }
  }

  private String use(final Item item) {
    if (item.isEdible()) {
      return eat(item);
    }
    else if (item.isPotable()) {
      return drink(item);
    }
    else if (!GS.bag().contains(item)) {
        return "You don't have that!";
    }
    else if (item == Item.ROPE && GS.location().isNamed("Cliff Side") ||
        GS.location() == Location.BEACH_NORTH || GS.location() == Location.RIVER_HUT) {
      return climb("climb");
    }
    else if (item.canCut() && GS.location().getX() == 3 && GS.location().getY() == 5) {
      GS.bag().add(Item.WATER);
      return "You got water from the cactus!";
    }
    else if (item == Item.NECKLACE) {
      GS.updateLocation(Location.VILLAGE);
      GS.bag().remove(Item.NECKLACE);
      return "Whoa! You were teleported back home! But the necklace crumbled to dust in the process…";
    }
    else {
      return "You can't use that here!";
    }
  }

  private String drink(final Item item) {
    if (item.isPotable()) {
      GS.bag().remove(item);
      return (item == Item.WATER && GS.location().isNamed("Desert")) ?
          "It's the quenchiest!" : "You drank some " + item.canonicalName() + ".";
    }
    else {
      return "You can't drink that!";
    }
  }

  private String eat(final Item item) {
    if (item.isEdible()) {
      GS.bag().remove(item);
      return item == Item.EGGS ? "You ate the eggs." : "You ate a " + item.canonicalName() + ".";
    }
    else {
      return "You can't eat that!";
    }
  }

  private final Pattern INFO = Pattern.compile("(?:look|info|where( am i)?\\??|(check )?surroundings)");
  private final Pattern BAG = Pattern.compile("(?:check )?(?:bag|inventory)");
  private final Pattern EXAMINE = Pattern.compile("(?:check|examine|inspect|read)(?: (?<item>.+))?");
  private final Pattern TALK = Pattern.compile("(?:talk|speak)(?: (?:to|with) (?<person>.+))?");
  private final Pattern MOVE = Pattern.compile("(?:go|head|run|walk)(?: (?<direction>.+))?");
  private final Pattern CLIMB = Pattern.compile("(?:climb(?: up| down| cliff)?)");
  private final Pattern USE = Pattern.compile("(?<command>use|eat|drink)(?: (?<item>.+))?");
  private final Pattern TOSS = Pattern.compile("(?<command>toss|drop|trash|remove)(?: (?<item>.+))?");
  private final Pattern GET = Pattern.compile("(?<command>get|take|grab|pick|pluck)(?: (?<item>.+))?");
  private final Pattern GET_FISH = Pattern.compile("(?:get|take|grab) (?<number>\\d+) fish");
  private final Pattern QUIT = Pattern.compile("(?:quit|rage quit|exit|give up|die|kill self|jump off|jump)");
  private final Pattern KILL = Pattern.compile("(?:kill(?: dragon)?(?: with sword)?|use sword)");
  private final Pattern SPECIAL = Pattern.compile(
      "(?:cut(?: cactus)?|scream|shout|throw eggs|climb tree|climb(?: large)? monument)");
}
