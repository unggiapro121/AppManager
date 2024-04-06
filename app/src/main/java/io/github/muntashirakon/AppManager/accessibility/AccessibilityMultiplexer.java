// SPDX-License-Identifier: GPL-3.0-or-later

package io.github.muntashirakon.AppManager.accessibility;

import android.os.Bundle;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AccessibilityMultiplexer {
    private static final int M_INSTALL = 1;
    private static final int M_UNINSTALL = 1 << 1;
    private static final int M_CLEAR_CACHE = 1 << 2;
    private static final int M_CLEAR_DATA = 1 << 3;
    private static final int M_FORCE_STOP = 1 << 4;
    private static final int M_NAVIGATE_TO_STORAGE_AND_CACHE = 1 << 5;
    private static final int M_LEADING_ACTIVITY_TRACKER = 1 << 6;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(flag = true, value = {
            M_INSTALL,
            M_UNINSTALL,
            M_CLEAR_CACHE,
            M_CLEAR_DATA,
            M_FORCE_STOP,
            M_NAVIGATE_TO_STORAGE_AND_CACHE,
            M_LEADING_ACTIVITY_TRACKER,
    })
    private @interface Flags {
    }

    private static final AccessibilityMultiplexer sInstance = new AccessibilityMultiplexer();

    public static AccessibilityMultiplexer getInstance() {
        return sInstance;
    }

    @Flags
    private int mFlags = 0;
    private final Bundle mArgs = new Bundle();

    public boolean isInstallEnabled() {
        return (mFlags & M_INSTALL) != 0;
    }

    public boolean isUninstallEnabled() {
        return (mFlags & M_UNINSTALL) != 0;
    }

    public boolean isClearCacheEnabled() {
        return (mFlags & M_CLEAR_CACHE) != 0;
    }

    public boolean isClearDataEnabled() {
        return (mFlags & M_CLEAR_DATA) != 0;
    }

    public boolean isForceStopEnabled() {
        return (mFlags & M_FORCE_STOP) != 0;
    }

    public boolean isNavigateToStorageAndCache() {
        return (mFlags & M_NAVIGATE_TO_STORAGE_AND_CACHE) != 0;
    }
    public boolean isLeadingActivityTracker() {
        return (mFlags & M_LEADING_ACTIVITY_TRACKER) != 0;
    }

    public void clearFlags() {
        mFlags = 0;
    }

    public void enableInstall(boolean enable) {
        addOrRemoveFlag(M_INSTALL, enable);
    }

    public void enableUninstall(boolean enable) {
        addOrRemoveFlag(M_UNINSTALL, enable);
    }

    public void enableClearCache(boolean enable) {
        addOrRemoveFlag(M_CLEAR_CACHE, enable);
    }

    public void enableClearData(boolean enable) {
        addOrRemoveFlag(M_CLEAR_DATA, enable);
    }

    public void enableForceStop(boolean enable) {
        addOrRemoveFlag(M_FORCE_STOP, enable);
    }

    public void enableNavigateToStorageAndCache(boolean enable) {
        addOrRemoveFlag(M_NAVIGATE_TO_STORAGE_AND_CACHE, enable);
    }

    public void enableLeadingActivityTracker(boolean enable) {
        addOrRemoveFlag(M_LEADING_ACTIVITY_TRACKER, enable);
    }

    @Nullable
    public String getTitleText() {
        return mArgs.getString("title");
    }

    public void setTitleText(String title) {
        mArgs.putString("title", title);
    }

    private void addOrRemoveFlag(@Flags int flag, boolean add) {
        if (add) addFlag(flag);
        else removeFlag(flag);
    }

    private void addFlag(@Flags int flag) {
        mFlags |= flag;
    }

    private void removeFlag(@Flags int flag) {
        mFlags &= ~flag;
    }
}
