package com.xu.musicplayer.lyric;

import com.xu.musicplayer.player.XMusic;
import com.xu.musicplayer.system.Constant;

/**
 * Java MusicPlayer 歌词线程
 *
 * @Author: hyacinth
 * @ClassName: LyricyThread
 * @Description: TODO
 * @Date: 2019年12月26日 下午8:00:13
 * @Copyright: hyacinth
 */
public class LyricyThread extends Thread {

    private LyricyNotify notify;
    private long time = 0;
    private int index = 0;

    private String format = "";
    private boolean add = true;

    public LyricyThread(LyricyNotify notify) {
        this.notify = notify;
    }

    @Override
    public void run() {
        int length = Constant.PLAYING_SONG_LYRIC.size();
        while (XMusic.isPlaying() && index <= length) {
            for (int i = 0, len = Constant.PLAYING_SONG_LYRIC.size(); i < len; i++) {
                String secounds = Constant.PLAYING_SONG_LYRIC.get(i).split(Constant.MUSIC_PLAYER_SYSTEM_SPLIT)[0];
                if (secounds.startsWith("0")) {
                    secounds = secounds.substring(0, secounds.lastIndexOf("."));
                    if (secounds.equalsIgnoreCase(format_time(time))) {
                        index = i;
                        add = false;
                    } else {
                        if (add) {
                            index++;
                        }
                    }
                }
            }
            this.notify.lyric(index, time);
            time++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 时间转换
     *
     * @param time
     * @return
     */
    private String format_time(long time) {
        long merchant = time / 60;
        long remainder = time % 60;
        if (time < 10) {
            format = "00:0" + time;
        } else if (time < 60) {
            format = "00:" + time;
        } else {
            if (merchant < 10 && remainder < 10) {
                format = "0" + merchant + ":0" + remainder;
            } else //noinspection ConstantConditions
                if (merchant < 10 && remainder < 60) {
                    format = "0" + merchant + ":" + remainder;
                } else //noinspection ConstantConditions
                    if (merchant >= 10 && remainder < 10) {
                        format = merchant + ":0" + remainder;
                    } else //noinspection ConstantConditions,ConstantConditions
                        if (merchant >= 10 && remainder < 60) {
                            format = merchant + ":0" + remainder;
                        }
        }
        return format;
    }

}
