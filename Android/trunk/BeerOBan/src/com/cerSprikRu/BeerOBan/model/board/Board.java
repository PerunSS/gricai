package com.cerSprikRu.BeerOBan.model.board;

import java.util.Scanner;

import android.content.res.Resources;

import com.cerSprikRu.BeerOBan.BeerOBan;
import com.cerSprikRu.BeerOBan.model.enums.Direction;
import com.cerSprikRu.BeerOBan.model.objects.Beer;
import com.cerSprikRu.BeerOBan.model.objects.BeerCrate;
import com.cerSprikRu.BeerOBan.model.objects.GameObject;
import com.cerSprikRu.BeerOBan.model.objects.Player;

public class Board {
	private Tile[][] tiles;
	private static Board instance = new Board();
	private String difficulty;
	// private Context context;
	private Resources res;

	private Board() {
	}

	public static Board getInstance() {
		return instance;
	}

	public void loadLevel(int level) {
		try {
			// res = context.getResources();
			Scanner sc = new Scanner(res.openRawResource(BeerOBan
					.getLvlResource("lvl" + level)));
			difficulty = sc.nextLine();
			String dimension[] = sc.nextLine().split(";");
			tiles = new Tile[Integer.parseInt(dimension[0])][Integer
					.parseInt(dimension[1])];
			int row = 0;
			while (sc.hasNext()) {
				String rowElements[] = sc.nextLine().split(";");
				int column = 0;
				for (String element : rowElements) {
					element = element.trim();
					tiles[row][column] = new Tile(row, column);
					// prazna celija
					if (element.equals("0"))
						;
					// igrac sa energijom - npr p20 - player, 20 energy
					else if (element.startsWith("p")) {
						Player player = new Player(Integer.parseInt(element
								.substring(1)), res);
						tiles[row][column].setGameObject(player);
					}
					// pivo sa energijom - npr b10 - pivo, 10 energy
					else if (element.startsWith("b")) {
						Beer beer = new Beer(Integer.parseInt(element
								.substring(1)), res);
						tiles[row][column].setGameObject(beer);
					}
					// sanduk piva s tezinom - npr c2 - sanduk tezine 2
					else if (element.startsWith("c")) {
						BeerCrate crate = new BeerCrate(
								Integer.parseInt(element.substring(1)), res);
						tiles[row][column].setGameObject(crate);
					}
					// prepreka
					else if (element.startsWith("x")) {
						tiles[row][column].setObstacle(true);
					}
					// cilj
					else if (element.startsWith("d")) {
						tiles[row][column].setDestination(true);
					}
					column++;
				}
				row++;
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean move(Direction direction) {
		Player player = getPlayer();
		Tile playerPosition = getTile(player.getPosition().getX(), player
				.getPosition().getY());
		Tile targetTile = null;
		Tile behindTargetTile = null;
		int newValue;
		switch (direction) {
		case NORTH:
			newValue = playerPosition.getY() - 1;
			if (newValue < 0)
				return false;
			targetTile = getTile(playerPosition.getX(), newValue);
			behindTargetTile = getTile(playerPosition.getX(), newValue - 1);
			break;
		case SOUTH:
			newValue = playerPosition.getY() + 1;
			if (newValue >= tiles.length)
				return false;
			targetTile = getTile(playerPosition.getX(), newValue);
			behindTargetTile = getTile(playerPosition.getX(), newValue + 1);
			break;
		case WEST:
			newValue = playerPosition.getX() - 1;
			if (newValue < 0)
				return false;
			targetTile = getTile(newValue, playerPosition.getY());
			behindTargetTile = getTile(newValue - 1, playerPosition.getY());
			break;
		case EAST:
			newValue = playerPosition.getX() + 1;
			if (newValue >= tiles[0].length)
				return false;
			targetTile = getTile(newValue, playerPosition.getY());
			behindTargetTile = getTile(newValue + 1, playerPosition.getY());
			break;
		}
		System.out.println("player position: ["+playerPosition.getY()+","+playerPosition.getX()+"]");
		if(targetTile!=null)
			System.out.println("target tile: ["+targetTile.getY()+","+targetTile.getX()+"]");
		if(behindTargetTile!=null)
			System.out.println("behind target tile: ["+behindTargetTile.getY()+","+behindTargetTile.getX()+"]");
		if (targetTile != null) {
			GameObject object = targetTile.getObject();
			// ukoliko je prepreka ne moze da se krece dalje, vraca false
			if (targetTile.isObstacle())
				return false;
			// ukoliko postoji nesto na zeljenom polju
			if (object != null) {
				// na zeljenom polju je pivo
				if (object instanceof Beer) {
					Beer beer = (Beer) object;
					if (player.canMove(null)) {
						player.increseEnergy(beer.getAmount());
						targetTile.setGameObject(player);
						playerPosition.setGameObject(null);
						return true;
					}
					return false;
				}
				// ukoliko postoji celija iza objekta
				if (behindTargetTile != null) {
					GameObject behindObject = behindTargetTile.getObject();
					// nalazi se objekat iza objekta koji zelimo da pomerimo,
					// vraca se false
					if (behindObject != null)
						return false;
					BeerCrate crate = (BeerCrate) object;
					if (player.canMove(crate)) {
						behindTargetTile.setGameObject(object);
						targetTile.setGameObject(player);
						playerPosition.setGameObject(null);
						
						
					System.out.println("after crate move");
					System.out.println("player position: ["+playerPosition.getY()+","+playerPosition.getX()+"]: "+playerPosition.getObject() );
					if(targetTile!=null)
						System.out.println("target tile: ["+targetTile.getY()+","+targetTile.getX()+"]: "+targetTile.getObject());
					if(behindTargetTile!=null)
						System.out.println("behind target tile: ["+behindTargetTile.getY()+","+behindTargetTile.getX()+"]: "+behindTargetTile.getObject());
					printTiles();
					return true;
					}
					return false;

				}
				// pokusavamo da pomerimo objekat a iza njega nema celije
				else
					return false;
			} else {
				if (player.canMove(null)) {
					System.out.println("after player move");
					targetTile.setGameObject(player);
					playerPosition.setGameObject(null);
					System.out.println("player position: ["+playerPosition.getY()+","+playerPosition.getX()+"]: "+playerPosition.getObject());
					if(targetTile!=null)
						System.out.println("target tile: ["+targetTile.getY()+","+targetTile.getX()+"]: "+targetTile.getObject());
					printTiles();
					return true;
				}
				return false;
			}
		}
		return false;
	}

	private Player getPlayer() {
		for (Tile[] row : tiles)
			for (Tile cell : row) {
				if (cell.getObject() != null)
					if (cell.getObject() instanceof Player){
						System.out.println(cell.getX()+","+cell.getY()+": "+cell.getObject());
						return (Player) cell.getObject();
					}
			}
		return null;
	}

	private Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || y >= tiles.length || x >= tiles[y].length)
			return null;
		return tiles[y][x];
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setResources(Resources res) {
		this.res = res;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public int getColumns() {
		if (tiles != null)
			if (tiles[0] != null)
				return tiles[0].length;
		return -1;
	}

	public void printTiles() {
		for (Tile[] row : tiles) {
			for (Tile cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public int getRows() {
		if (tiles != null)
			return tiles.length;
		return -1;
	}

}
