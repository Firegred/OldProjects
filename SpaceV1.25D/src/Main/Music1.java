package Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class Music1 {
    private boolean play;
	private Sequencer myseq;
	private File myMidiFile;
	private Sequence supersequence;
	private boolean muted;
	public Music1() {
		myseq = null;
		play = true;
		muted = false;
		 try {
		        // From file
			  
			 myseq = MidiSystem.getSequencer(); 
			 myseq.open();
			 
			 myMidiFile = new File("./src/sounds/Bad Apple!!.mid");
			 URL url = getClass().getResource("/sounds/Bad Apple!!.mid");
			 InputStream m = url.openStream();
			 supersequence = MidiSystem.getSequence(m);
			 myseq.setSequence(supersequence);
			 
			 
			
          
		    } catch (MalformedURLException e) {
		    } catch (IOException e) {
		    } catch (MidiUnavailableException e) {
		    } catch (InvalidMidiDataException e) {
		    	System.out.println(e);
			}
		 run();
	}
	public boolean isMuted() {
		return muted;
	}
	public void setMuted(boolean b) {
		muted = b;
	}
	public boolean isPlaying() {
		return play;
	}
	public void setPlaying(boolean b) {
		play = b;
	}
	public Sequencer getSequencer() {
		return myseq;
	}
	public void stop() {
		myseq.stop();
	}
	public void pause() {
        for(int i = 0; i < 16; i++) {
		myseq.setTrackMute(i, true);
        }
	}
	public void unpause() {
		for(int i = 0; i < 16; i++) {
		myseq.setTrackMute(i, false);
		}
	}
	public void run() {
		myseq.setLoopCount(1000);
		myseq.start(); 
	}
}
