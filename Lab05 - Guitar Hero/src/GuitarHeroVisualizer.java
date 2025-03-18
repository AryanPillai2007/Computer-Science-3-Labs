public class GuitarHeroVisualizer { 
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        final int N = 500;  
        
        double[] sampleBuffer = new double[N];
        int currentIndex = 0;
        int drawCounter = 0;
        final int DRAW_FREQUENCY = 50; 
        
        double CONCERT_A = 440.0;
        GuitarString[] strings = new GuitarString[37];
        for (int i = 0; i < strings.length; i++) {
            double frequency = CONCERT_A * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(frequency);
        }
  
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.setPenRadius(0.01);
        StdDraw.enableDoubleBuffering();
        
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index >= 0) {
                    strings[index].pluck();
                }
            }
            double sample = 0.0;
            for (GuitarString string:strings) {
                sample += string.sample();
            }
            
            sampleBuffer[currentIndex] = sample;
            currentIndex = (currentIndex + 1) % N;
            
            drawCounter++;
            if (drawCounter >= DRAW_FREQUENCY) {
                drawCounter = 0;
                StdDraw.clear();
                
                for (int i = 0; i < N-1; i++) {
                    int i1 = (currentIndex + i) % N;
                    int i2 = (currentIndex + i + 1) % N;
                    StdDraw.line(i, sampleBuffer[i1], i+1, sampleBuffer[i2]);
                }
                
                StdDraw.line(0, 0, N, 0);      
                StdDraw.line(0, -1.0, 0, 1.0);  
                StdDraw.show();
            }

            StdAudio.play(sample);

            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}