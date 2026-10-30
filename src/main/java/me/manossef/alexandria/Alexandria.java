package me.manossef.alexandria;

import me.manossef.api.alexandria.updates.UpdateChecker;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Alexandria extends JavaPlugin {
    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }

    public void checkVersion(String version, Plugin plugin) {
        String currentVersion = this.getDescription().getVersion();
        if(version.equals(currentVersion)) return;
        int[] checked;
        boolean isCheckedBuild = false;
        if(UpdateChecker.isStable(version)) {
            checked = UpdateChecker.getReleaseVersionNumber(version);
            isCheckedBuild = true;
        } else checked = UpdateChecker.getReleaseVersionNumber(version.split("-")[0]);
        int[] current;
        boolean isCurrentBuild = false;
        if(UpdateChecker.isStable(currentVersion)) {
            current = UpdateChecker.getReleaseVersionNumber(currentVersion);
            isCurrentBuild = true;
        } else current = UpdateChecker.getReleaseVersionNumber(currentVersion.split("-")[0]);
        if(checked[0] < 1) {
            plugin.getLogger().severe(plugin.getName() + " depends on Alexandria version " + version + ", which does not exist. This plugin is being disabled.");
            this.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        if(!isCheckedBuild && checked[0] == 1 && checked[1] == 0 && checked[2] == 0) {
            plugin.getLogger().warning(plugin.getName() + " depends on Alexandria version " + version + ", which is a development build for the plugin's first release. Errors may occur and certain features might not work. You have been warned.");
            return;
        }
        if(current[0] < checked[0]) {
            plugin.getLogger().warning(plugin.getName() + " depends on Alexandria version " + version + ", which is newer than the current version, " + currentVersion + ". Some API features might not exist or work as expected.");
            return;
        }
        if(current[0] > checked[0]) {
            plugin.getLogger().warning(plugin.getName() + " depends on Alexandria version " + version + ", which is older than the current version, " + currentVersion + ". Some API features might not work as expected.");
            return;
        }
        if(current[1] < checked[1]) {
            plugin.getLogger().warning(plugin.getName() + " depends on Alexandria version " + version + ", which is newer than the current version, " + currentVersion + ". Some API features might not exist in this version.");
            return;
        }
        if(current[1] == checked[1] && isCurrentBuild && !isCheckedBuild) {
            plugin.getLogger().warning(plugin.getName() + " depends on Alexandria version " + version + ", which is the release target of the current build, " + currentVersion + ". Some API features might not be fully implemented.");
        }
    }
}