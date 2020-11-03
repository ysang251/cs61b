public class NBody{
	
	//private static In in = new In("./data/planets.txt");
	//private static int numberofplanets = in.readInt();
	
	public static double readRadius(String path){
		In in = new In(path);
		int numberofplanets = in.readInt();
		double radius = Double.parseDouble(in.readString());
		return radius;
	}
	

	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int numberofplanets = in.readInt();
		double radius = Double.parseDouble(in.readString());
		Planet[] planets = new Planet[numberofplanets];
		for(int i=0; i<numberofplanets; i+=1){
			double xP= Double.parseDouble(in.readString());
			double yP= Double.parseDouble(in.readString());
			double xV= Double.parseDouble(in.readString());
			double yV= Double.parseDouble(in.readString());
			double mass = Double.parseDouble(in.readString());
			String image = in.readString();
			planets[i]= new Planet(xP,yP,xV,yV,mass,image);
		} 
		return planets;
		
	}
	
	
	public static void main(String[] args){
		double endtime = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		
		String imageToDraw = "./images/starfield.jpg";
		StdDraw.setScale(-radius, radius);
		
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);
		
		for (int i=0; i<planets.length; i+=1){
			planets[i].draw();	
		}
		
		/* Shows the drawing to the screen, and waits 10 milliseconds. */
		StdDraw.show();
		StdDraw.pause(10);
		
		
		StdDraw.enableDoubleBuffering();
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];		
		for (double time=0; time<=endtime; time=time+dt){

			for (int i=0; i<planets.length; i+=1){
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
			}
			StdDraw.clear();
			StdDraw.picture(0,0,imageToDraw);
			for (int i=0; i<planets.length; i+=1){
			 	planets[i].update(dt,xForces[i],yForces[i]);
				planets[i].draw();	
			}
			StdDraw.show();
			StdDraw.pause(10);
			
			
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