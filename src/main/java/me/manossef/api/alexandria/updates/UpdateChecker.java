package me.manossef.api.alexandria.updates;

import me.manossef.api.alexandria.TypeChecks;

/**
 * A blueprint for a class that checks a resource for updates.
 */
public interface UpdateChecker {
    /**
     * Checks the resource for updates.
     */
    void checkForUpdates();

    static boolean isStable(String version) {
        String[] parts = version.split("\\.");
        if(parts.length != 3)
            return false;
        for(String part : parts) {
            if(!TypeChecks.isInt(part))
                return false;
        }
        return true;
    }

    static int[] getReleaseVersionNumber(String version) {
        if(!isStable(version))
            throw new IllegalArgumentException("Cannot get major, minor and patch versions from a development version string");
        String[] parts = version.split("\\.");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
    }

    static int compareVersions(String version1, String version2) {
        if(!isStable(version1) || !isStable(version2))
            throw new IllegalArgumentException("Versions must be stable versions");
        int[] semver1 = getReleaseVersionNumber(version1);
        int[] semver2 = getReleaseVersionNumber(version2);
        int compareMajor = Integer.compare(semver1[0], semver2[0]);
        if(compareMajor != 0) return compareMajor;
        int compareMinor = Integer.compare(semver1[1], semver2[1]);
        if(compareMinor != 0) return compareMajor;
        return Integer.compare(semver1[2], semver2[2]);
    }
}