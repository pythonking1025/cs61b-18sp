public class NBody
{
	private static String bg_img = "./images/starfield.jpg";
	private static final String bg_music = "./audio/2001.mid";
	public static double readRadius(String path)
	{
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path)
	{
		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i = 0; i < num; i ++)
		{
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}

	public static void main(String[] args) 
	{
		double t = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		
		StdAudio.play(bg_music);

		double time = 0.0;
		int n = planets.length;

		double[] xForces = new double[n];
		double[] yForces = new double[n];

		while (time < t)
		{
			for (int i = 0; i < n; i ++)
			{
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, bg_img);

			for (Planet p : planets)
			{
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			t += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  		  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  		  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
} 