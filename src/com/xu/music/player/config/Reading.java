package com.xu.music.player.config;

import com.xu.music.player.system.Constant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;

public class Reading {

    public HashSet<String> read() {
        File file = new File(Constant.MUSIC_PLAYER_SONG_LISTS_FULL_PATH);
        if (file.exists() && file.isFile()) {
            HashSet<String> songs = new HashSet<String>();
            Constant.MUSIC_PLAYER_SONGS_LIST.clear();
            InputStreamReader FReader = null;
            BufferedReader BReader = null;
            String song;
            try (FileInputStream stream = new FileInputStream(file)) {
                FReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
                BReader = new BufferedReader(FReader);
                while ((song = BReader.readLine()) != null) {
                    songs.add(song);
                    Constant.MUSIC_PLAYER_SONGS_LIST.add(song);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (FReader != null) {
                        FReader.close();
                    }
                    if (BReader != null) {
                        BReader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return songs;
        } else {
            return null;
        }

    }

    public boolean empty(String k) {
        return k == null || k.length() <= 0;
    }

}
