package com.securelog.app.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

public class IntegrityModel {

    private File selectedFile;
    private String currentSha256;

    private final File baselineFile = new File("baseline.properties");

    public void setSelectedFile(File file) {
        this.selectedFile = file;
        this.currentSha256 = null;
    }

    public Optional<File> getSelectedFile() {
        return Optional.ofNullable(selectedFile);
    }

    public void setCurrentSha256(String sha256) {
        this.currentSha256 = sha256;
    }

    public Optional<String> getCurrentSha256() {
        return Optional.ofNullable(currentSha256);
    }

    // baseline: key = absolute path, value = sha256
    public void saveBaseline(Path path, String sha256) throws IOException {
        Properties props = loadProps();
        props.setProperty(path.toString(), sha256);
        storeProps(props);
    }

    public Optional<String> readBaseline(Path path) throws IOException {
        Properties props = loadProps();
        return Optional.ofNullable(props.getProperty(path.toString()));
    }

    private Properties loadProps() throws IOException {
        Properties props = new Properties();
        if (baselineFile.exists()) {
            try (InputStream in = new FileInputStream(baselineFile)) {
                props.load(in);
            }
        }
        return props;
    }

    private void storeProps(Properties props) throws IOException {
        try (OutputStream out = new FileOutputStream(baselineFile)) {
            props.store(out, "SecureLog Guardian - Baseline SHA-256");
        }
    }
}
