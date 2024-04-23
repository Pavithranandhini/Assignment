package org.example.fileReaderSetup;

import java.util.*;

public class FilePropertyManager {


    private static final String API_PROPERTY_FILEPATH = "endpoints.properties"; ;
    private static FilePropertyManager instance = null;
        private static final Object lock = new Object();
        private final Map<String, String> configValues = new HashMap<String, String>();

        private FilePropertyManager() {
        }

        // Creating singleton instance
        public static FilePropertyManager getInstance() {

            if (instance == null) {
                synchronized (lock) {
                    instance = new FilePropertyManager();
                    instance.loadData();
                }
            }

            return instance;
        }

        // Get all configuration data and assign to related fields
        private void loadData() {
            // Declaring properties object
            Properties prop = new Properties();
            // Read Configuration file
            try {
                List<String> list = new ArrayList<>();
                list.add(API_PROPERTY_FILEPATH);

                for (String str : list) {
                    prop.load(this.getClass().getClassLoader().getResourceAsStream(str));
                    for (Object key : prop.keySet()) {
                        configValues.put(key.toString(), prop.getProperty(key.toString()));
                    }
                }
            } catch (Exception ie) {
                ie.printStackTrace();
            }
        }

        String GetValueGivenKey(String key) {
            try {
                return configValues.get(key);
            } catch (Exception e) {
                return null;
            }
        }

        void setValue(String key, String value) {
            try {
                configValues.putIfAbsent(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        void replaceValue(String key, String value) {
            try {
                configValues.replace(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

