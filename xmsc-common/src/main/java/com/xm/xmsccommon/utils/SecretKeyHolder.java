package com.xm.xmsccommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class SecretKeyHolder {
    private static final Logger log = LoggerFactory.getLogger(SecretKeyHolder.class);

    SecretKeyHolder() {
    }

    static native String getSecretKey();

    private static synchronized void loadSoLib() throws IOException {
        String libFullName = "libSecurity-DataSource-SecretKey.so";
        String absoluteLibPath = System.getProperty("security.datasource.so.path", "/opt/platform") + File.separator + libFullName;
        BufferedInputStream reader = null;
        FileOutputStream writer = null;
        File extractedLibFile = File.createTempFile("libtest", ".so");

        try {
            reader = new BufferedInputStream(new FileInputStream(new File(absoluteLibPath)));
            writer = new FileOutputStream(extractedLibFile);

            for(byte[] buffer = new byte[1024]; reader.read(buffer) > 0; buffer = new byte[1024]) {
                writer.write(buffer);
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }

            if (writer != null) {
                writer.close();
            }

        }

        System.load(extractedLibFile.toString());
        if (extractedLibFile.exists()) {
            extractedLibFile.delete();
        }

    }

    static {
        try {
            loadSoLib();
        } catch (IOException var1) {
            log.error(var1.getMessage(), var1);
            System.exit(1);
        }

    }
}
