/*
 * Minecraft / Telegram Integrator
 * By Kyngo (github.com/kyngo)
 * Copyright (c) 2018
 */

package net.kyngo.mctgintegrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 *
 * @author kyngo
 */
public class TelegramWrapper {
    private String configFile = "./plugins/mc-tg-integrator.yml";
    private String tgToken = null;
    private String tgGid = null;
    
    public TelegramWrapper() {
        try {
            File file = new File(configFile);
 
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null) {
                String[] line = st.split(": ");
                if (line.length == 2) {
                    switch (line[0]) {
                        case "token":
                            tgToken = line[1].replace("\"", "");
                            break;
                        case "group":
                            tgGid = line[1];
                            break;
                        default:
                            System.out.println("Unrecognized value: " + line[0]);
                            break;
                    }
                } else {
                    throw new IllegalArgumentException("Config file seems to be corrupted...");
                }
            }
            if (tgToken == null && tgGid == null) {
                throw new IllegalArgumentException("Not all configurations on the file were loaded! File must contain \"token\" and \"id\" parameters.");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Configuration file was not found!"
                    + "It must be named \"mc-tg-integrator.yml\" and has to be placed inside the \"plugins\" directory.\nThis file must contain two lines, e.g.:\n\n"
                    + "token: \"abcdef123456:tokentokentoken789\"\ngroup: -1234567890\n\n");
        } catch (Exception e) {
            System.out.println("An error occured when trying to process this message! More details are shown below.");
            System.out.println(e.getMessage());
        }
    }
    
    private void StreamToAPI(String payload) {
        try {
            URL url = new URL(String.format("https://api.telegram.org/bot%s/%s", tgToken, payload));
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            in.close();
        } catch (Exception e) {
            System.out.println("An error occured when trying to process this message! More details are shown below.");
            System.out.println(e.getMessage());
        }
    }
    
    public void Send(String payload) {
        this.StreamToAPI(
                String.format("sendMessage?chat_id=%s&text=%s", this.tgGid, payload)
        );
        
    }
}
