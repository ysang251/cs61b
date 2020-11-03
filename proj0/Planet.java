import java.lang.Math;

public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final double GRAVITY=6.67*Math.pow(10,-11);
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;	
	}
	
	public double calcDistance(Planet p){
		return Math.sqrt(Math.pow(this.xxPos-p.xxPos,2)+Math.pow(this.yyPos-p.yyPos,2));
	}
	
	public double calcForceExertedBy(Planet p){
		double distance = this.calcDistance(p);
		double force = GRAVITY*this.mass*p.mass/Math.pow(distance,2);
		//double forcex = force *(this.xxPos-p.xxPos)/distance
		//double forcey = force *(this.yyPos-p.yyPos)/distance
		return force;
	}

	public double calcForceExertedByX(Planet p){
		return this.calcForceExertedBy(p) *(p.xxPos-this.xxPos)/this.calcDistance(p);
	}	
	
	public double calcForceExertedByY(Planet p){
		return this.calcForceExertedBy(p) *(p.yyPos-this.yyPos)/this.calcDistance(p);
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets){
		
		double sumforceX=0;
		for(int i=0; i<allPlanets.length; i+=1){
			if (allPlanets[i] == this){continue;}
			sumforceX += this.calcForceExertedByX(allPlanets[i]);
		}
		return sumforceX;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets){
		
		double sumforceY=0;
		for(int i=0; i<allPlanets.length; i+=1){
			if (allPlanets[i] == this){continue;}
			sumforceY += this.calcForceExertedByY(allPlanets[i]);
		}
		return sumforceY;
	}
		
	public void update(double dt, double fX, double fY){
		double accelerationX=fX/this.mass;
		double accelerationY=fY/this.mass;
		this.xxVel = this.xxVel + dt*accelerationX;
		this.yyVel = this.yyVel + dt*accelerationY;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos = this.yyPos + dt*this.yyVel;
	}
	
	
	public void draw(){
		String imageToDraw = "./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, imageToDraw);	
	}
	
}