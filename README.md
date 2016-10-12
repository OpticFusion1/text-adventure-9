# Usage:
To play the game download the JAR file from `releases` and run
```
java -jar text-adventure.jar
```
to bring up the game text. This is the recommended method.

Alternatively, you can download the source code to compile and run it yourself
from within the `src` directory using
```
javac -encoding utf8 main/TextAdventure.java
java main/TextAdventure
```
or run from within an IDE.

# Info
This text adventure game is mostly a game of performing quests and completing tasks for people
(like rescuing a cat for a small girl).

## Main commands:

* `talk to <person>` / `speak with <person>`
* `go <direction>` (`north` or `n`, `south` or `s`, etc.)
* `look` / `info` (returns location description/message)
* `where` / `Where am I?` (returns name of location)
* `check surroundings` (returns names of locations north, south, west, and east of current location)
* `examine <object>`
* `get <item>` / `take <item>` (at River Hut you can also type `take <number> fish` to take more than one fish at a time)
* `drop <item>` / `toss <item>`
* `use <item>`
* `check bag` / `check inventory` (returns a list of items in the bag and how many of each)
* `quit` / `rage quit`
* `kill dragon` / `kill with sword` / `use sword` (only works in Eerie Mountain to kill the dragon)

There are a couple of others that have the same functionality as those above and can be used
such as `head <direction>` = `go <direction>`, `eat <food_item>` = `use <food_item>`,
`drink water` = `use water`, (The commands `drink` and `eat` are **not** equivalent to `use`,
but proper subsets of it that work only with specific items.) There are also other hidden commands not listed here.

## Obtainable items:

* knife
* rope
* banana
* fish
* soup
* eggs
* flowers
* magic necklace
* cat
* water
* ring
* boots
* armor
* sword
* sand

Some of these are taken in certain locations. Others are given to you by talking to people.

## Examinable objects:

* rock (in Desert)
* sign
* tree (has bananas)
* tree (has a cat)
* monument
* cactus
* scroll
* castle
* boulder (in Cave)
* creek
* beach

## Characters one can talk to:
* mom
* man (Creek)
* man (River hut)
* boy
* small girl
* blacksmith
* grocer
* artisan
* old man
* woman (Western Road)
* woman (Open Field)
* Darren
* Bobbi
* Dave

# SPOILERS:

The fastest way to beat the game without any sidequests is to take lots of fish from the fisherman
at the River Hut then bring some to his brother at Creek then take his boots at Large Monument.
With the boots you can wade across the river to enter Cave and take the armor under the boulder
in there. Then you can get the ring at the Royal Beach just west of the cave and give it to Dave at
Southern Road. Then return to your village and talk to mom who will give you flowers. Then talk to
Dave again and he will take your flowers and tell you you can have his sword. Talk to the blacksmith
in Queenʼs City and he will now give you the sword. With the armor and sword, you can now enter
the eerie mountain and use your sword to kill the dragon.
