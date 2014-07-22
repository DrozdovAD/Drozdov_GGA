package tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Figure {

	static int[][] figureI = {
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
			{0,1,0,0},
		};
	static int[][] figureS = {
			{0,2,0,0},
			{0,2,2,0},
			{0,0,2,0},
			{0,0,0,0},
		};
	static int[][] figureL = {
			{0,3,3,0},
			{0,3,0,0},
			{0,3,0,0},
			{0,0,0,0},
		};
	static int[][] figureT = {
			{0,4,0,0},
			{0,4,4,0},
			{0,4,0,0},
			{0,0,0,0},
		};
	static int[][] figureQ = {
			{0,0,0,0},
			{0,5,5,0},
			{0,5,5,0},
			{0,0,0,0},
		};

	static List<int[][]> allFigures;
	
	static {
		allFigures = new ArrayList<int[][]>();
		allFigures.add(figureI);
		allFigures.add(rotateLeft(figureI));
		
		allFigures.add(figureS);
		int[][] flippedS = flip(figureS);
		allFigures.add(flippedS);
		allFigures.add(rotateLeft(figureS));
		allFigures.add(rotateLeft(flippedS));
		
		allFigures.add(figureL);

		allFigures.add(figureT);
		
		allFigures.add(figureQ);
	}
	
	int[][] data = new int[4][4];

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
		int[][] result = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				
					result[i][j]=data[i][data[0].length-1-j];
				
			}
		}
		return result;
	}
	static int[][] rotateLeft(int[][] data){
		int[][] result = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				result[i][j]=data[j][data[0].length-1-i];
			}
		}
		return result;
	}
	static int[][] rotateRight(int[][] data){
		int[][] result = {{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0},{0, 0, 0, 0}};
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
