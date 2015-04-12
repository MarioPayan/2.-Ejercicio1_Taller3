import java.util.*;
import java.lang.*;
import java.io.*;


class Logica
{
	private int radio1=0;
	private int x1=0;
	private int y1=0;
	
	private int radio2=0;
	private int x2=0;
	private int y2=0;
	
	private int[][] point;
	
	private ArrayList results = new ArrayList();
	
	Logica(int x1, int y1, int r1, int x2, int y2, int r2, int[][] point){
		this.x1=x1;
		this.y1=y1;
		this.radio1=r1;
		
		this.x2=x2;
		this.y2=y2;
		this.radio2=r2;
		
		this.point= new int[point.length][2];
		
		for(int i=0;i<point.length;i++){
			this.point[i][0]=point[i][0];
			this.point[i][1]=point[i][1];
		}
		
		run();
		resultExport();
	}
	
	public void run(){
		for(int i=0;i<point.length;i++){
			results.add(step(point[i][0],point[i][1]) + "\n");
		}
	}
	
	public String step(int x, int y){
		String result= "("+ x +"," + y + ") -> ";
		boolean en1 = false;
		boolean en2 = false;
		
		if(Math.sqrt(Math.pow(x-x1,2) + Math.pow(y-y1,2)) <= radio1){
			en1 = true;
		}
		
		if(Math.sqrt(Math.pow(x-x2,2) + Math.pow(y-y2,2)) <= radio2){
			en2 = true;
		}
		
		if(en1&&en2){result+="dentro de ambas circunferencias";}
		else if(en1){result+="dentro de la circunferencia 1";}
		else if(en2){result+="dentro de la circunferencia 2";}
		else{result+="por fuera de ambas circunferencias";}
		return result;
	}
	
	public ArrayList resultExport(){
		return results;
	}
	/* PRUEBA
	public static void main (String[] args) throws java.lang.Exception
	{
		int[][] asd = new int[2][2];
		asd[0][0]= 4; asd[0][1]= 0;
		asd[1][0]= 1; asd[1][1]= 6;
		
		Logica ob = new Logica(0,0,5,0,0,3,asd);
	}
	*/
}
