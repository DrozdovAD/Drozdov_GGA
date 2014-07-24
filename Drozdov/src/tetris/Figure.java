package tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {

	static int[][] figureI = {
			{0,0,1,0,0},
			{0,0,1,0,0},
			{0,0,1,0,0},
			{0,0,1,0,0},
			{0,0,1,0,0},
		};
	static int[][] figureF = {
		{0,0,0,0,0},
		{0,0,1,1,0},
		{0,1,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
		};
	static int[][] figureL = {
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,1,1,0},
		{0,0,0,0,0},
		};
	static int[][] figureT = {
		{0,0,0,0,0},
		{0,1,1,1,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
		};
	static int[][] figureN = {
		{0,1,0,0,0},
		{0,1,0,0,0},
		{0,1,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
		};
	static int[][] figureP = {
		{0,1,1,0,0},
		{0,1,1,0,0},
		{0,1,0,0,0},
		{0,0,0,0,0},
		{0,0,0,0,0},
	};
	static int[][] figureU = {
		{0,0,0,0,0},
		{0,1,0,1,0},
		{0,1,1,1,0},
		{0,0,0,0,0},
		{0,0,0,0,0},
	};
	static int[][] figureV = {
		{0,0,0,0,0},
		{0,1,0,0,0},
		{0,1,0,0,0},
		{0,1,1,1,0},
		{0,0,0,0,0},
	};
	static int[][] figureW = {
		{0,0,0,0,0},
		{0,1,0,0,0},
		{0,1,1,0,0},
		{0,0,1,1,0},
		{0,0,0,0,0},
	};
	static int[][] figureX = {
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,1,1,1,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
	};
	static int[][] figureY = {
		{0,0,1,0,0},
		{0,1,1,0,0},
		{0,0,1,0,0},
		{0,0,1,0,0},
		{0,0,0,0,0},
	};
	static int[][] figureZ = {
		{0,0,0,0,0},
		{0,1,1,0,0},
		{0,0,1,0,0},
		{0,0,1,1,0},
		{0,0,0,0,0},
	};

	static List<int[][]> allFigures;
	
	static {
		allFigures = new ArrayList<int[][]>();
		

		add(figureI);
		add(figureX);
		add(figureL);
		add(figureT);
		add(figureN);
		add(figureU);
		add(figureV);
		add(figureW);
		add(figureY);
		add(figureZ);
		
		
	}

	private static void add(int[][] figure) {
		int[][] flip;
		allFigures.add(figure);
		flip = flip(figure);
		allFigures.add(flip);
		allFigures.add(rotateLeft(figure));
		allFigures.add(rotateLeft(flip));
		allFigures.add(rotateRight(figure));
		allFigures.add(rotateRight(flip));
		allFigures.add(rotateRight(rotateRight(figure)));
		allFigures.add(rotateRight(rotateRight(flip)));
	}
	
	int[][] data = new int[5][5];

	private Figure() {
	}
	
	private static Random random = new Random();
	
	static Figure generateRandom() {
		int figureIndex = random.nextInt(allFigures.size());
		
		Figure figure = new Figure();
		figure.data = allFigures.get(figureIndex);
		int color=1+random.nextInt(10);
		for (int i = 0; i < figure.data.length; i++) {
			for (int j = 0; j < figure.data[i].length; j++) {
				if(figure.data[i][j]>0) {
					figure.data[i][j]=color;
				}
			}
		}
		
		return figure;
	}
	
	static int[][] flip(int[][] data){
		int[][] result = {{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0}};
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				
					result[i][j]=data[i][data[0].length-1-j];
				
			}
		}
		return result;
	}
	static int[][] rotateLeft(int[][] data){
		int[][] result = {{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0}};
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				result[i][j]=data[j][data[0].length-1-i];
			}
		}
		return result;
	}
	static int[][] rotateRight(int[][] data){
		int[][] result = {{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0},{0, 0, 0, 0,0}};
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				result[i][j]=data[data[0].length-1-j][i];
			}
		}
		return result;
	}

	public int[][] getData() {
		return data;
	}
	
}
