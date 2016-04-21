package com.dungeongame;

/**
 * 
 * Specification in https://leetcode.com/problems/dungeon-game/
 * 
 * @author Yun Lin
 *
 */
public class DungeonGame {
	
	class State{
		Integer currentHealth;
		Integer minHealth;
		@Override
		public String toString() {
			return "State [currentHealth=" + currentHealth + ", minHealth=" + minHealth + "]";
		}
	}
	
	/**
	 * The method implements a dynamic programming algorithm. We maintain another
	 * 2-dimensional array values of State type, which record that, if the knight 
	 * go to the position of (i, j), how much health he would have left then, and the
	 * minimum health he would gone through if he go through the corresponding path.
	 * 
	 * 
	 * @param dungeon
	 * @return
	 */
	public int calculateMinimumHP(int[][] dungeon) {
		int width = dungeon.length;
		int length = dungeon[0].length;
		State[][] values = new State[width][length];
		
		for(int i=0; i<width; i++){
			for(int j=0; j<length; j++){
				Integer deltaRightValue = (i>0) ? dungeon[i-1][j] : null;
				Integer deltaDownValue = (j>0) ? dungeon[i][j-1] : null;
				
				int delta = dungeon[i][j];

				State state = new State();
				if(deltaDownValue == null && deltaRightValue == null){
					state.currentHealth = dungeon[i][j];
					state.minHealth = dungeon[i][j];
				}
				else if(deltaDownValue == null && deltaRightValue != null){
					state.currentHealth = values[i-1][j].currentHealth + delta;
					
					state.minHealth = (values[i-1][j].minHealth < state.currentHealth) ? values[i-1][j].minHealth : state.currentHealth;
				}
				else if(deltaDownValue != null && deltaRightValue == null){
					state.currentHealth = values[i][j-1].currentHealth + delta;
					state.minHealth = (values[i][j-1].minHealth < state.currentHealth) ? values[i][j-1].minHealth : state.currentHealth;
				}
				else{
					int downCurrentBlood = values[i][j-1].currentHealth + delta;
					int downMinBood = (values[i][j-1].minHealth < downCurrentBlood) ? values[i][j-1].minHealth : downCurrentBlood;
					
					int rightCurrentBlood = values[i-1][j].currentHealth + delta;
					int rightMinBlood = (values[i-1][j].minHealth < rightCurrentBlood) ? values[i-1][j].minHealth : rightCurrentBlood;
					
					state.currentHealth = (downMinBood > rightMinBlood) ? downCurrentBlood : rightCurrentBlood;
					state.minHealth = (downMinBood > rightMinBlood) ? downMinBood : rightMinBlood;
				}
				
				values[i][j] = state;
			}
		}
		
		int minBlood = values[width-1][length-1].minHealth;
		
		return (minBlood > 0) ? 1 : 1 - minBlood;
	}

	/**
	 * expected result is 3 instead of 5.
	 * @param args
	 */
	public static void main(String[] args) {
		DungeonGame game = new DungeonGame();
		int[][] dungeon = new int[][]{
			{1, -3, 3},
			{0, -2, 0},
			{-3,-3,-3}
		};

		int blood = game.calculateMinimumHP(dungeon);
		System.out.println(blood);
	}

}
