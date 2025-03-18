public class GuitarHero {
	public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[37];
      
        for (int i = 0; i < 37; i++) {
            double frequency = 440* Math.pow(1.05956, i-24);
            strings[i] = new GuitarString(frequency);
        }
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                
                int index = keyboard.indexOf(key);
                
                if (index >= 0) {
                    strings[index].pluck();
                }
            }

            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
            } StdAudio.play(sample);


            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}