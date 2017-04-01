package Horse; 
import java.io.File;
import java.util.*;

public class Horse {
	
		
	
	public void horseRead() {	
		
		
	}
	
	public void horseWrite() {
		
	}
	
	public static void horseListen(String path, int sec) {		
		int[] voice; 
		try{
			//Call the Horse to Read
			File file = new File(path);
			WavFile wavFile = WavFile.openWavFile(file);
			
			
			int numChannels; 
			int numFrames; 
			int sampleRate;
			int bufferSize; 
			int[] buffer; 
			
			
			wavFile.display();
			numFrames = (int) wavFile.getNumFrames(); 
			numChannels = wavFile.getNumChannels();
			sampleRate = (int) wavFile.getNumFrames(); 
			bufferSize = numFrames * numChannels;
			
			buffer = new int[bufferSize];	// Create a buffer of bufferSizr Frames
	
			int framesRead;
			int min;
			int max;
	
			do
			{
				framesRead = wavFile.readFrames(buffer, bufferSize);
	
				for (int s=0 ; s<bufferSize; s++)
				{
					System.out.println( buffer[s]);
				}
			}
			while (framesRead != 0);			
			/** Display Information **/ 
			System.out.println("\nnumFrames: " + numFrames + "\n"
						+ "bufferSize: " + bufferSize + "\n"
						+ "sampleRate: " + sampleRate); 
			
			
			// Close the wavFile
				wavFile.close();
		} catch (Exception e) { System.out.println(e);}		
		
	}
			
	public static File horseSpeak(int[] voice) {
		File file = null; 		
		try
	      {
	         int sampleRate = 44100;    // Samples per second
	         double duration = 5.0;     // Seconds

	         // Calculate the number of frames required for specified duration
	         long numFrames = (long)(duration * sampleRate);

	         // Create a wav file with the name specified as the first argument
	         WavFile wavFile = WavFile.newWavFile(new File(args[0]), 2, numFrames, 16, sampleRate);

	         // Create a buffer of 100 frames
	         double[][] buffer = new double[2][100];

	         // Initialise a local frame counter
	         long frameCounter = 0;

	         // Loop until all frames written
	         while (frameCounter < numFrames)
	         {
	            // Determine how many frames to write, up to a maximum of the buffer size
	            long remaining = wavFile.getFramesRemaining();
	            int toWrite = (remaining > 100) ? 100 : (int) remaining;

	            // Fill the buffer, one tone per channel
	            for (int s=0 ; s<toWrite ; s++, frameCounter++)
	            {
	               buffer[0][s] = Math.sin(2.0 * Math.PI * 400 * frameCounter / sampleRate);
	               buffer[1][s] = Math.sin(2.0 * Math.PI * 500 * frameCounter / sampleRate);
	            }

	            // Write the buffer
	            wavFile.writeFrames(buffer, toWrite);
	         }

	         // Close the wavFile
	         wavFile.close();
	      }
	      catch (Exception e)
	      {
	         System.err.println(e);
	      }
		
		
		
		
		return file; 
	}
}
