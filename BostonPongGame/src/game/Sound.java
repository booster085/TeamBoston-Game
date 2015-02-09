package game;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final AudioClip HIT = Applet.newAudioClip(Sound.class.getResource("sound/hit.wav"));	
	public static final AudioClip MUSIC = Applet.newAudioClip(Sound.class.getResource("sound/music.wav"));
	public static final AudioClip POINT = Applet.newAudioClip(Sound.class.getResource("sound/point.wav"));
	
}
