# GunGame

A Minecraft GunGame plugin for Spigot 1.18.2+, featuring level-based kits, a configurable shop, and player statistics tracking.

## Features

- **Level Progression:** Players advance through levels by getting kills, unlocking new kits and weapons.
- **Configurable Kits:** Define kits for each level in `levels.json`.
- **Shop System:** Players can buy effects and items using in-game currency (Vault required).
- **Statistics:** Tracks kills, deaths, and highest level for each player (SQLite backend).
- **Multiworld Support:** Restrict GunGame to specific worlds via config.
- **Admin Commands:** Manage player stats and reset data.
- **Sounds & Messages:** Customizable via `config.json` and `lang.json`.

## Requirements

- Minecraft 1.18.2+ Paper server
- [Vault](https://dev.bukkit.org/projects/vault)
- [Essentials](https://essentialsx.net/)
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/)

## Installation

1. Place the plugin JAR in your server's `plugins` folder.
2. Start the server to generate default config files.
3. Edit `config.json`, `levels.json`, and `lang.json` in the plugin's data folder to customize.
4. Reload or restart the server.

## Configuration

- **`config.json`**: General settings, shop items, sounds, and worlds.
- **`levels.json`**: Define kits for each level (armor, weapons, enchants).
- **`lang.json`**: Customize all plugin messages.

## Commands

- `/gungame <reset|stats> <player> <set|add> <number> <kills|deaths|levels>`  
  *Requires `gungame.admin` permission.*

## Data

- Player stats are stored in `stats.db` (SQLite) in the plugin folder.

## License

MIT License (see `LICENSE` file).

---

**Author:** M3z0id