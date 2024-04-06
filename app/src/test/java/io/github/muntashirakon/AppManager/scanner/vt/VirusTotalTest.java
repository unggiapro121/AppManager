// SPDX-License-Identifier: GPL-3.0-or-later

package io.github.muntashirakon.AppManager.scanner.vt;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import io.github.muntashirakon.AppManager.backup.convert.OABConverter;

public class VirusTotalTest {
    private static final String API_KEY = null;
    private final ClassLoader classLoader = getClass().getClassLoader();
    private VirusTotal vt;

    @Before
    public void setUp() throws Exception {
        if (API_KEY == null) return;
        vt = new VirusTotal(API_KEY);
    }

    @Test
    public void scanThrowsNothing() throws IOException {
        if (vt == null) return;
        assert classLoader != null;
        File baseApk = new File(classLoader.getResource(OABConverter.PATH_SUFFIX).getFile(), "dnsfilter.android/base.apk");
        try (FileInputStream fis = new FileInputStream(baseApk)) {
            VtFileScanMeta vtFileScanMeta = vt.scan("dnsfilter.android", fis);
            System.out.println(vtFileScanMeta);
        }
    }

    @Test
    public void fetchReportsThrowsNothing() throws IOException {
        if (vt == null) return;
        List<VtFileReport> reports = vt.fetchReports(new String[]{"029e2ed8dea7db94a293bdb7c0d197059f85d4dc51b6ff56548b29b65afe13c5", "a5146a143c7bbd6a0b8384a1aa233243b72cca94cbec62aa3d70a82f5b262550"});
        // Throws nothing
        System.out.println(reports);
    }
}
