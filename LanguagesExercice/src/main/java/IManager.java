package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class IManager {

    private static IManager manager;

    private List<String> resources;
    private List<String> languages;

    //HashMap<String, String> dataES;
    HashMap<String, ResourceBundle> data;


    private IManager() throws IOException {//Constructor privat
        resources = getResourceFiles("main/resources");
        languages = getLanguagesList(resources);

        this.data = new HashMap<String, ResourceBundle>();

        for (String language : languages) {
            this.data.put(language, ResourceBundle.getBundle(language));
        }
    }

    public static IManager getInstance() throws IOException {//Unic metode de entrada
        if (manager == null) manager = new IManager();
        return manager;
    }

    public String getText(String language, String key) {
        String result = null;
        result = this.data.get(language).getString(key);
        return result;
    }

    private List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        InputStream in = getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String resource;

        while ((resource = br.readLine()) != null) {
            filenames.add(resource);
        }
        return filenames;
    }

    private InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);

        return in == null ? getClass().getResourceAsStream(resource) : in;
    }

    private ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private String getFilename(String input) {
        String fileName = input;
        int pos = fileName.indexOf(".properties");
        if (pos > 0 && pos < (fileName.length() - 1)) { // If '.' is not the first or last character.
            fileName = fileName.substring(0, pos);
            return fileName;
        } else {
            return null;
        }
    }

    public List<String> getResources() {
        return resources;
    }

    public List<String> getLanguagesList(List<String> resources) {
        List<String> languages = new ArrayList<>();

        for (String resource:resources) {
            languages.add(getFilename(resource));
        }

        return languages;
    }

    public List<String> getLanguages() {

        return languages;
    }

    public static void main(String[] args) throws IOException {
        IManager manager = new IManager();

        for (String language : manager.getLanguages()) {
            System.out.println(language + ": " + IManager.getInstance().getText(language, "l1"));
        }

        System.out.println("----------");

        for (String language : manager.getLanguages()) {
            System.out.println(language + ": " + IManager.getInstance().getText(language, "l2"));
        }
    }
}
