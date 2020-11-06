# Memory Plugin for Minecraft
You have a boring serverlobby or just want to play memory in minecraft?
Try our version of memory in minecraft, 
because our plugin brings variety in your serverlobby.

## Features
* unique minigame
* awesome gui with beautiful animations
* easy to configure
* works out of the box
* varying themes

## Setup
* Put the [`memory.jar`](https://github.com/WeLoveOpenSourcePlugins/memory/releases/latest) in your plugin folder
* Start the server and wait until the message file got created
* Customize the messages (messages.yml)
* Start your server again and connect

## How to play
Right-click the customized block (default: noteblock) to join the queue or use /memory. If a second player joins the queue, the game will start.
Leave the game by closing the inventory.

## Configuration
##### config.yml

```yaml
join_per_block: false
join_block: 'NOTE_BLOCK'
```

##### messages.yml

```yaml
tag: '&8[&eMemory&8]'
game:
  against: '%tag% &7You are playing against &e%player%&7.'
  first_turn: '%tag% &e%player% &7starts. (Theme: %theme%))'
  turn: '%tag% &6%player% goes on.'
  not_your_turn: '%tag% &cIt''s not your turn.'
  pair_found: '%tag% &e%player% found a pair..'
  no_pair_found: '%tag% &7%player% didn''t find a pair.'
  again: '%tag% &6%player% can take another card.'
  again_player: '%tag% &6You can take another card.'
queue:
  leave: '%tag% &cYou left the queue.'
  added: '%tag% &aYou joined the queue. &7Waiting for player..'
result:
  draw: '%tag% &eDraw! - Nobody won.'
  win: '%tag% &e%player% has won the game. (%score% pairs)'
```

## Pictures
[![YouTube Gameplay](https://img.youtube.com/vi/-aDJW23rj88/0.jpg)](http://www.youtube.com/watch?v=-aDJW23rj88)
![Queue](https://image.prntscr.com/image/NN0lUvmUTcyjvZx91S-WYw.png)
![GUI](https://image.prntscr.com/image/EUy4z29UT0GKME8PcWXpYQ.png)
-----------------------------

## Problems during installation?
Just write a PM to [@Paul2708](https://www.spigotmc.org/members/paul2708.44827/) on Spigot or contact me via Twitter ([@theplayerpaul](https://twitter.com/theplayerpaul)) or Discord (Paul2708#1098).
Thank you for your attention.

## Contribute
If you find any bugs or want to contribute, just open an issue! We would appreciate it.

> WeLoveOpenSourcePlugins :heart: