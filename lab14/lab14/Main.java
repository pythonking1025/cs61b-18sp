package lab14;

import lab14lib.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		/** Your code here. */
		Generator generator = new SineWaveGenerator(200);
		GeneratorPlayer gp = new GeneratorPlayer(generator);
		GeneratorDrawer gd = new GeneratorDrawer(generator);
		GeneratorAudioVisualizer gav = new GeneratorAudioVisualizer(generator);

		Generator g1 = new SineWaveGenerator(200);
		Generator g2 = new SineWaveGenerator(201);

		ArrayList<Generator> generators = new ArrayList<Generator>();
		generators.add(g1);
		generators.add(g2);
		MultiGenerator mg = new MultiGenerator(generators);

		gav = new GeneratorAudioVisualizer(mg);

		Generator generator1 = new SawToothGenerator(512);
		gav = new GeneratorAudioVisualizer(generator1);

		Generator generator2 = new AcceleratingSawToothGenerator(200, 1.1);
		gav = new GeneratorAudioVisualizer(generator2);

		Generator generator3 = new StrangeBitwiseGenerator(1024);
		gav = new GeneratorAudioVisualizer(generator3);
		gav.drawAndPlay(128000, 1000000);
	}
} 