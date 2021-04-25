package com.mady.utils;

import javax.media.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MusicPlayer {

    // to store current position
    private Long currentFrame;
    private Clip clip;

    // current status of clip
    private String status;

    private AudioInputStream audioInputStream;
    private String root = System.getProperty("user.dir");
    private String filePath = root + "\\RogueLikeMADY\\src\\main\\resources\\The Legend Of Zelda - The Minish Cap - Minish Village Arrangement (cut version).wav";

    // constructor to initialize streams and clip
    public MusicPlayer() {
        // create AudioInputStream object
        try {
            filePath = Paths.get(filePath).toString();
            audioInputStream =
                    AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

            // create clip reference
            clip = AudioSystem.getClip();

            // open audioInputStream to the clip
            clip.open(audioInputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ignored) {
            try {
//                InputStream is = ClassLoader.getSystemResourceAsStream(
//                        "/The Legend Of Zelda - The Minish Cap - Minish Village Arrangement (cut version).wav");
//                audioInputStream =
//                        AudioSystem.getAudioInputStream(is);

                URL url = getClass().getResource("/The Legend Of Zelda - The Minish Cap - Minish Village Arrangement (cut version).wav");
//                URI uri = new URI("jar:file:" + root + "/RogueLikeMADY-1.0-SNAPSHOT.jar!/The Legend Of Zelda - The Minish Cap - Minish Village Arrangement (cut version).wav");
                Path p = Paths.get(new URI(url.toURI().toString()));
                File f = new File(p.toUri());
                System.out.println(f.getAbsoluteFile());
                audioInputStream =
                        AudioSystem.getAudioInputStream(f.getAbsoluteFile());
//                System.out.println(url);
//                MediaLocator loc = new MediaLocator(url);
//                Player player = Manager.createPlayer(loc);
//                player.start();


                // create clip reference
//                clip = AudioSystem.getClip();
//
//                // open audioInputStream to the clip
//                clip.open(audioInputStream);
//
//                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (IOException | UnsupportedAudioFileException | URISyntaxException ign) {
                ign.printStackTrace();
            }
        }
        System.exit(0);
    }


    public Long getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(Long currentFrame) {
        this.currentFrame = currentFrame;
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(Clip clip) {
        this.clip = clip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public void setAudioInputStream(AudioInputStream audioInputStream) {
        this.audioInputStream = audioInputStream;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private void gotoChoice(int c)
            throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        switch (c) {
            case 1:
                pause();
                break;
            case 2:
                resumeAudio();
                break;
            case 3:
                restart();
                break;
            case 4:
                stop();
                break;
            case 5:
                System.out.println("Enter time (" + 0 +
                        ", " + clip.getMicrosecondLength() + ")");
                Scanner sc = new Scanner(System.in);
                long c1 = sc.nextLong();
                jump(c1);
                break;

        }

    }

    // Method to play the audio
    public void play() {
        //start the clip
        clip.start();

        status = "play";
    }

    // Method to pause the audio
    public void pause() {
        if (status.equals("paused")) {
            System.out.println("audio is already paused");
            return;
        }
        this.currentFrame =
                this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    // Method to resume the audio
    public void resumeAudio() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        if (status.equals("play")) {
            System.out.println("Audio is already " +
                    "being played");
            return;
        }
        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    // Method to restart the audio
    public void restart() throws IOException, LineUnavailableException,
            UnsupportedAudioFileException {
        clip.stop();
        clip.close();
        resetAudioStream();
        currentFrame = 0L;
        clip.setMicrosecondPosition(0);
        this.play();
    }

    // Method to stop the audio
    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    // Method to jump over a specific part
    public void jump(long c) throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        if (c > 0 && c < clip.getMicrosecondLength()) {
            clip.stop();
            clip.close();
            resetAudioStream();
            currentFrame = c;
            clip.setMicrosecondPosition(c);
            this.play();
        }
    }

    // Method to reset audio stream
    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
            LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(
                new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}