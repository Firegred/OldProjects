package me.Firegred.NoteBlock;

import org.bukkit.Sound;

public class SoundID {

	public static float FF0 = 0.500F;
	public static float G0 = 0.530F;
	public static float GG0 = 0.560F;
	public static float A0 = 0.590F;
	public static float AA0 = 0.630F;
	public static float B0 = 0.670F;
	public static float C0 = 0.710F;
	public static float CC0 = 0.750F;
	public static float D0 = 0.790F;
	public static float DD0 = 0.840F;
	public static float E0 = 0.890F;
	public static float F0 = 0.940F;
	public static float FF1 = 1.000F;
	public static float G1 = 1.060F;
	public static float GG1 = 1.120F;
	public static float A1 = 1.190F;
	public static float AA1 = 1.260F;
	public static float B1 = 1.330F;
	public static float C1 = 1.410F;
	public static float CC1 = 1.500F;
	public static float D1 = 1.590F;
	public static float DD1 = 1.680F;
	public static float E1 = 1.780F;
	public static float F1 = 1.890F;
	public static float FF2 = 2.000F;
	public static float[] oct0 = {FF0,G0, GG0, A0, AA0, B0, C0, CC0, D0, DD0, E0,F0};
	public static float[] oct1 = {FF1,G1, GG1, A1, AA1, B1, C1, CC1, D1, DD1, E1,F1};
	public static float[] sharps = {FF0, GG0, AA0, CC0, DD0, FF1, GG1, AA1, CC1, DD1, FF2};
	public static float[] naturals = {G0, A0, B0, C0, D0, E0, F0, G1, A1, B1, C1, D1, E1, F1};
	public static float oct2 = FF2;
	
	public static String NotessharpString[] = {"Note: F# 0","Note: G# 0","Note: A# 0","Note: C# 0","Note: D# 0","Note: F# 1","Note: G# 1","Note: A# 1","Note: C# 1","Note: D# 1","Note: F# 2"};
	public static String NotesnormalString[] = {"Note: G 0","Note: A 0","Note: B 0","Note: C 0",
		"Note: D 0","Note: E 0","Note: F 0","Note: G 1",
		"Note: A 1","Note: B 1","Note: C 1","Note: D 1",
		"Note: E 1","Note: F 1"};

	
	public static float Notessharp[] = {FF0, GG0,AA0, 0, CC0,DD0 , 0, FF1,GG1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,AA1,0,CC1,DD1,0,FF2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
	
	public static float NotesNormal[] = { 
		 0,0,0,0,0,0,0,0,0,0,
		G0,
		 
		 A0,
		 
		 B0 ,
		 C0,
		
		 D0,
		 
		 E0 ,
		 F0,
		 
		 G1 ,
		 
		 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		 A1 ,
		 
		 
		 B1,
		 C1,
		 
		 D1,
		 
		 E1,
		 F1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
		 };
	
	public static String[] Stringoctave0 = {"Note{F#}","Note{G}","Note{G#}","Note{A}","Note{A#}",
		"Note{B}","Note{C}","Note{C#}","Note{D}","Note{D#}","Note{E}", "Note{F}"
		};
	public static String[] Stringoctave1 = {"Note{F#}","Note{G}","Note{G#}","Note{A}","Note{A#}",
		"Note{B}","Note{C}","Note{C#}","Note{D}","Note{D#}","Note{E}", "Note{F}"};
	public static String Stringoctave2 = "Note{F#}";
	
	public static float calculateNote(String note, int octave) {
		if(octave == 0) {
			for(int x = 0; x < Stringoctave0.length; x++) {
				if(note.equals(Stringoctave0[x])) {
					return oct0[x];
				}
			}
		}
		else if(octave == 1) {
			for(int x = 0; x < Stringoctave1.length; x++) {
				if(note.equals(Stringoctave1[x])) {
					return oct1[x];
				}
			}
		}
		else if(octave ==2) {
			return oct2;
		}
		return 0.5f;
		
	}
	
	
	public static float getSharp(int i) {
		return Notessharp[i];
	}
	public static float getNormal(int i) {
		return NotesNormal[i];
	}
	public static String getSharpNote(int i) {
		return NotessharpString[i];
	}
	public static String getNormalNote(int i) {
		return NotesnormalString[i];
	}
	public static Sound ID(int i) {
		//return sounds[i];
		return Sound.values()[i];
	}
	public static int soundLength() {
		//return sounds.length;
		return Sound.values().length;
	}
}
