package de.hotmail.gurkilein.bankcraft.banking;

import org.bukkit.entity.Player;

public class ExperienceBukkitHandler {
	
	
	public static void removeExperienceFromPocket(Player player, int amount) {
		
		int currentExp = getTotalExperience(player);
		
		player.setLevel(0);
		player.setExp(0);
		player.setTotalExperience(0);
		
		if (currentExp-amount>=0)
		player.giveExp(currentExp-amount);
		
		
	}
	
	public static void addExperienceToPocket(Player player, int amount) {
		player.giveExp(amount);
	}
	
	
	
	
	//Used to calculate the REAL total experience of a player
	public static int getTotalExperience(Player player) {
		int currentLevel = player.getLevel();
		float experienceTowardsNextLevel = player.getExp();
		int experienceFromLevels = 0;
		int experienceNeededFromCurrentLevelToNext = 0;
		
		
		//calculate experience from levels see http://www.minecraftwiki.net/wiki/Experience
		if (currentLevel <= 15) {
			experienceFromLevels = (2*currentLevel)+7;
		} else
		if (currentLevel <= 30) {
			experienceFromLevels = (5*currentLevel)-38;
		} else {
			experienceFromLevels = (9*currentLevel)-158;
		}
			
		
		//calculate experience needed to level up
		if (currentLevel <= 14) {
			experienceNeededFromCurrentLevelToNext = (currentLevel*currentLevel)+(6*currentLevel);
		} else
		if (currentLevel <= 29) {
			experienceNeededFromCurrentLevelToNext = (2.5*(currentLevel*currentLevel))-((40.5*currentLevel)+360);
		} else {
			experienceNeededFromCurrentLevelToNext = (4.5*(currentLevel*currentLevel))-((162.5*currentLevel)+2220);
		}
			
		//calculate total xp	
		return experienceFromLevels+(int)(experienceTowardsNextLevel*experienceNeededFromCurrentLevelToNext);
	}
}
