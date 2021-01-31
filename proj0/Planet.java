public class Planet
{

	private static final double G = 6.67 * 1e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img)
	{
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p)
	{
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p)
	{
		double dx = xxPos - p.xxPos;
		double dy = yyPos - p.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Planet p)
	{
		double r = calcDistance(p);
		double force = G * mass * p.mass / (r * r);
		return force;
	}

	public double calcForceExertedByX(Planet p)
	{
		double force = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dx = p.xxPos - xxPos;
		double force_x = force * dx / r;
		return force_x;
	}

	public double calcForceExertedByY(Planet p)
	{
		double force = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dy = p.yyPos - yyPos;
		double force_y = force * dy / r;
		return force_y;
	}

	public double calcNetForceExertedByX(Planet[] all)
	{
		double net_f = 0;
		for (Planet p: all)
		{
			if (p.equals(this)) continue;
			net_f += calcForceExertedByX(p);
		}
		return net_f;
	}

	public double calcNetForceExertedByY(Planet[] all)
	{
		double net_f = 0;
		for (Planet p: all)
		{
			if (p.equals(this)) continue;
			net_f += calcForceExertedByY(p);
		}
		return net_f;
	}

	public void update(double dt, double f_x, double f_y)
	{
		double a_x = f_x / mass;
		double a_y = f_y / mass;
		xxVel += dt * a_x;
		yyVel += dt * a_y;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw()
	{
		StdDraw.picture(xxPos, yyPos, "./images/" + imgFileName);
	}
}